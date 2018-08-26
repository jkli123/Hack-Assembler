package com.chee.hackassembler;

import java.util.Hashtable;

public class SymbolTable {

	protected Hashtable<String, Integer> table;
	
	public SymbolTable() {
		table = new Hashtable<>();
	}
	
	public void addEntry(String symbol, Integer address) {
		table.put(symbol, address);
	}
	
	public boolean contains(String symbol) {
		return table.containsKey(symbol);
	}
	
	public int getAddress(String symbol) {
		return table.get(symbol);
	}
}
