package com.chee.hackassembler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Parser {

	private BufferedReader reader;
	protected String currentCommand;
	
	public Parser(Path path) {
		currentCommand = "";
		try {
			reader = Files.newBufferedReader(path);
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isComment(String line) {
		if(line.startsWith("//") || line.startsWith("/*")
				|| line.startsWith("*")) {
			return true;
		}
		return false;
	}
	
	private boolean isWhiteSpace(String line) {
		if(line.equals("")) {
			return true;
		} 
		return false;
	}

	public boolean hasMoreCommands() {
		String nextLine = null;
		try {
			reader.mark(300);
		} catch(IOException e) {
			e.printStackTrace();
		}
		try {
			nextLine = reader.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		if(nextLine == null) {
			return false;
		}
		if(isComment(nextLine) || isWhiteSpace(nextLine)) {
			return hasMoreCommands();
		}
		try {
			reader.reset();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void advance() {
		try {
			currentCommand = reader.readLine();
			removeComments();
			removeSpaces();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void removeComments() {
		if(currentCommand.contains("//")) {
			int index = currentCommand.indexOf("/");
			currentCommand = currentCommand.substring(0, index);
		}
	}
	
	protected void removeSpaces() {
		currentCommand = currentCommand.replace(" ", "");
	}
	
	public String commandType() {
		if(currentCommand.startsWith("@")) {
			return "ACOMMAND";
		} else if(currentCommand.startsWith("(") && currentCommand.endsWith(")")) {
			return "LCOMMAND";
		} else {
			return "CCOMMAND";
		}
	}
	
	public String symbol() {
		if(commandType() == "ACOMMAND") {
			return currentCommand.substring(1);
		} else if(commandType() == "LCOMMAND") {
			return currentCommand.substring(1, currentCommand.length() - 1);
		} else {
			return null;
		}
	}
	
	public String dest() {
		if(commandType() == "CCOMMAND") {
			if(currentCommand.contains(";")) {
				return null;
			} else {
				String[] splitCommand = currentCommand.split("=");
				return splitCommand[0];	
			}
		} else {
			return null;
		}
	}
	
	public String comp() {
		if(commandType() == "CCOMMAND") {
			if(currentCommand.contains(";")) {
				return currentCommand.split(";")[0];
			} else {
				return currentCommand.split("=")[1];
			}
		} else {
			return null;
		}
	}
	
	public String jump() {
		if(commandType() == "CCOMMAND") {
			if(currentCommand.contains("=")) {
				return null;
			} else {
				return currentCommand.split(";")[1];
			}
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		
	}
}
