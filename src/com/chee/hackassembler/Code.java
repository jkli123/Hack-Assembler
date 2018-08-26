package com.chee.hackassembler;

public class Code {

	public static byte[] dest(String mnemonic) {
		byte[] result = new byte[3];
		if(mnemonic == null) {
			return result;
		}
		switch(mnemonic) {
		case("M"):
			result[0] = 1;
			result[1] = 0;
			result[2] = 0;
			break;
		case("D"):
			result[0] = 0;
			result[1] = 1;
			result[2] = 0;
			break;
		case("MD"):
			result[0] = 1;
			result[1] = 1;
			result[2] = 0;
			break;
		case("A"):
			result[0] = 0;
			result[1] = 0;
			result[2] = 1;
			break;
		case("AM"):
			result[0] = 1;
			result[1] = 0;
			result[2] = 1;
			break;
		case("AD"):
			result[0] = 0;
			result[1] = 1;
			result[2] = 1;
			break;
		case("AND"):
			result[0] = 1;
			result[1] = 1;
			result[2] = 1;
			break;
		default:
			result[0] = 0;
			result[1] = 0;
			result[2] = 0;
			break;
		}
		return result;
	}
	
	public static byte[] comp(String mnemonic) {
		byte[] result = new byte[7];
		if(mnemonic.contains("M")) {
			result[6] = 1;
		} else {
			result[6] = 0;
		}
		String converted = mnemonic.replace('M', 'A');
		switch(converted) {
			case("0"):
				result[0] = 0;
				result[1] = 1;
				result[2] = 0;
				result[3] = 1;
				result[4] = 0;
				result[5] = 1;
				break;
			case("1"):
				result[0] = 1;
				result[1] = 1;
				result[2] = 1;
				result[3] = 1;
				result[4] = 1;
				result[5] = 1;
				break;
			case("-1"):
				result[0] = 0;
				result[1] = 1;
				result[2] = 0;
				result[3] = 1;
				result[4] = 1;
				result[5] = 1;
				break;
			case("D"):
				result[0] = 0;
				result[1] = 0;
				result[2] = 1;
				result[3] = 1;
				result[4] = 0;
				result[5] = 0;
				break;
			case("A"):
				result[0] = 0;
				result[1] = 0;
				result[2] = 0;
				result[3] = 0;
				result[4] = 1;
				result[5] = 1;
				break;
			case("!D"):
				result[0] = 1;
				result[1] = 0;
				result[2] = 1;
				result[3] = 1;
				result[4] = 0;
				result[5] = 0;
				break;
			case("!A"):
				result[0] = 1;
				result[1] = 0;
				result[2] = 0;
				result[3] = 0;
				result[4] = 1;
				result[5] = 1;
				break;
			case("-D"):
				result[0] = 1;
				result[1] = 1;
				result[2] = 1;
				result[3] = 1;
				result[4] = 0;
				result[5] = 0;
				break;
			case("-A"):
				result[0] = 1;
				result[1] = 1;
				result[2] = 0;
				result[3] = 0;
				result[4] = 1;
				result[5] = 1;
				break;
			case("D+1"):
				result[0] = 1;
				result[1] = 1;
				result[2] = 1;
				result[3] = 1;
				result[4] = 1;
				result[5] = 0;
				break;
			case("A+1"):
				result[0] = 1;
				result[1] = 1;
				result[2] = 1;
				result[3] = 0;
				result[4] = 1;
				result[5] = 1;
				break;
			case("D-1"):
				result[0] = 0;
				result[1] = 1;
				result[2] = 1;
				result[3] = 1;
				result[4] = 0;
				result[5] = 0;
				break;
			case("A-1"):
				result[0] = 0;
				result[1] = 1;
				result[2] = 0;
				result[3] = 0;
				result[4] = 1;
				result[5] = 1;
				break;
			case("D+A"):
				result[0] = 0;
				result[1] = 1;
				result[2] = 0;
				result[3] = 0;
				result[4] = 0;
				result[5] = 0;
				break;
			case("D-A"):
				result[0] = 1;
				result[1] = 1;
				result[2] = 0;
				result[3] = 0;
				result[4] = 1;
				result[5] = 0;
				break;
			case("A-D"):
				result[0] = 1;
				result[1] = 1;
				result[2] = 1;
				result[3] = 0;
				result[4] = 0;
				result[5] = 0;
				break;
			case("D&A"):
				result[0] = 0;
				result[1] = 0;
				result[2] = 0;
				result[3] = 0;
				result[4] = 0;
				result[5] = 0;
				break;
			case("D|A"):
				result[0] = 1;
				result[1] = 0;
				result[2] = 1;
				result[3] = 0;
				result[4] = 1;
				result[5] = 0;
				break;
		}
		return result;
	}
	
	public static byte[] jump(String mnemonic) {
		byte[] result = new byte[3];
		if(mnemonic == null) {
			return result;
		}
		switch(mnemonic) {
			case("JGT"):
				result[0] = 1;
				result[1] = 0;
				result[2] = 0;
				break;
			case("JEQ"):
				result[0] = 0;
				result[1] = 1;
				result[2] = 0;
				break;
			case("JGE"):
				result[0] = 1;
				result[1] = 1;
				result[2] = 0;
				break;
			case("JLT"):
				result[0] = 0;
				result[1] = 0;
				result[2] = 1;
				break;
			case("JNE"):
				result[0] = 1;
				result[1] = 0;
				result[2] = 1;
				break;
			case("JLE"):
				result[0] = 0;
				result[1] = 1;
				result[2] = 1;
				break;
			case("JMP"):
				result[0] = 1;
				result[1] = 1;
				result[2] = 1;
				break;
			default:
				result[0] = 0;
				result[1] = 0;
				result[2] = 0;
				break;
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		
	}
}
