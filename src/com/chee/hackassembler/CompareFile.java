package com.chee.hackassembler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CompareFile {

	public static void main(String[] args) {
		Path file1 = Paths.get("W:\\Chee Peng\\Desktop\\Comp Sci\\NAND2Tetris\\Hardware Simulator\\nand2tetris\\projects\\06\\pong\\PongLCorrect.hack");
		Path file2 = Paths.get("W:\\Chee Peng\\Desktop\\Comp Sci\\NAND2Tetris\\Hardware Simulator\\nand2tetris\\projects\\06\\pong\\PongL.hack");
		try {
			BufferedReader reader1 = Files.newBufferedReader(file1);
			BufferedReader reader2 = Files.newBufferedReader(file2);
			int lineNum = 1;
			while(true) {
				if(reader1.readLine().equals(reader2.readLine())) {
					System.out.println("Line " + lineNum + ": Correct");
					lineNum++;
				} else {
					System.out.println("Line " + lineNum + ": Wrong");
					lineNum++;
					break;
				}	
			}
			System.out.println("Comparison Completed");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
