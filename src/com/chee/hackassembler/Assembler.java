package com.chee.hackassembler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Assembler {

	private Parser firstParser;
	private Parser secondParser;
	private byte[] commandOutput;
	private Path filePath;
	private BufferedWriter writer;
	private SymbolTable predefSymbols;
	private SymbolTable labels;
	private SymbolTable variables;
	private int rom;
	private int ram;
	
	public Assembler(Path path) {
		firstParser = new Parser(path);
		secondParser = new Parser(path);
		filePath = path;
		String filePathName = filePath.toString();
		String hackFile = filePathName.replace("asm", "hack");
		filePath = Paths.get(hackFile);
		try {
			writer = Files.newBufferedWriter(filePath,
					StandardOpenOption.CREATE, StandardOpenOption.WRITE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch(IOException e) {
			e.printStackTrace();
		}
		commandOutput = new byte[16];
		predefSymbols = new SymbolTable();
		labels = new SymbolTable();
		variables = new SymbolTable();
		rom = 0;
		/*
		 * Ram address allocation starts from 16th address in hack
		 * First 15 addresses are predefined symbols.
		 */
		ram = 16;
	}
	
	public void assemble() {
		firstPass();
		secondPass();
	}
	
	private void firstPass() {
		generatePredefSymbolTable();
		while(firstParser.hasMoreCommands()) {
			firstParser.advance();
			if(firstParser.commandType() == "ACOMMAND" 
					|| firstParser.commandType() == "CCOMMAND") {
				rom++;
			} else if(firstParser.commandType() == "LCOMMAND") {
				String symbol = firstParser.symbol();
				labels.addEntry(symbol, rom);
			} else {
				continue;
			}
		}
	}
	
	private void generatePredefSymbolTable() {
		predefSymbols.addEntry("SP", 0);
		predefSymbols.addEntry("LCL", 1);
		predefSymbols.addEntry("ARG", 2);
		predefSymbols.addEntry("THIS", 3);
		predefSymbols.addEntry("THAT", 4);
		predefSymbols.addEntry("SCREEN", 16384);
		predefSymbols.addEntry("KBD", 24576);
		for(int i = 0; i < 16; i++) {
			String label = "R";
			label += Integer.toString(i);
			predefSymbols.addEntry(label, i);
		}
	}
	
	private void secondPass() {
		while(secondParser.hasMoreCommands()) {
			secondParser.advance();
			if(secondParser.commandType() == "ACOMMAND") {
				commandOutput = assembleACommand(secondParser.currentCommand);
			} else if(secondParser.commandType() == "CCOMMAND") {
				commandOutput = assembleCCommand(secondParser.currentCommand);
			} else {
				continue;
			}
			writeToFile();
		}
		try {
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeToFile() {
		try {
			for(int i = commandOutput.length - 1; i >= 0; i--) {
				String num = Byte.toString(commandOutput[i]);
				writer.write(num);
			}
			writer.write("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private byte[] assembleACommand(String command) {
		byte[] result = new byte[16];
		String symbol = secondParser.symbol();
		int number;
		try {
			number = Integer.parseInt(symbol);	
		} catch(NumberFormatException nfe) {
			if(predefSymbols.contains(symbol)) {
				number = predefSymbols.getAddress(symbol);
			} else if(labels.contains(symbol)) {
				number = labels.getAddress(symbol);
			} else if(variables.contains(symbol)) {
				number = variables.getAddress(symbol);
			} else {
				number = ram;
				variables.addEntry(symbol, ram);
				ram++;
			}
		}
		String numberString = Integer.toBinaryString(number);
		char[] binaryNumber = numberString.toCharArray();
		int in = 0;
		for(int i = binaryNumber.length - 1; i >= 0; i--) {
			byte num = Byte.parseByte(String.valueOf(binaryNumber[i]));
			result[in] = num;
			in++;
		}
		for(int i = in; i < 16; i++) {
			result[i] = 0;
		}
		return result;
	}
	
	private byte[] assembleCCommand(String command) {
		byte[] jump = Code.jump(secondParser.jump());
		byte[] dest = Code.dest(secondParser.dest());
		byte[] comp = Code.comp(secondParser.comp());
		byte[] result = new byte[16];
		for(int i = 0; i < jump.length; i++) {
			result[i] = jump[i];
		}
		for(int i = 3; i < dest.length + 3; i++) {
			result[i] = dest[i - 3];
		}
		for(int i = 6; i < comp.length + 6; i++) {
			result[i] = comp[i - 6];
		}
		for(int i = 13; i < result.length; i++) {
			result[i] = 1;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Path path = Paths.get("W:\\Chee Peng\\Desktop\\Comp Sci\\NAND2Tetris\\Hardware Simulator\\nand2tetris\\projects\\06\\rect\\Rect.asm");
		Assembler assembler = new Assembler(path);
		assembler.assemble();
		System.out.println("Assembly Completed");
	}
}
