package com.gsugambit.minecraft.rcon.utils;

import java.util.ArrayList;
import java.util.List;

public final class ByteArrayUtils {
	
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	public static byte[] combineByteArray(byte[] ... arrays) {
		List<Byte> byteList = new ArrayList<>();
		
		for(byte[] array : arrays) {
			for(int i = 0; i < array.length; i++) {
				byteList.add(array[i]);
			}
		}
		
		byte[] result = new byte[byteList.size()];
		for(int i = 0; i < result.length; i++) {
			result[i] = byteList.get(i);
		}
		
		return result;
	}
	
	public static byte[] convertListToArray(List<Byte> byteList) {
		byte[] result = new byte[byteList.size()];
		
		for(int i = 0; i < result.length; i++) {
			result[i] = byteList.get(i);
		}
		
		return result;
	}
	
	public static char[] convertByteArrayToCharArray(byte[] array) {
		char[] result = new char[array.length];
		
		for(int i = 0; i < result.length; i++) {
			result[i] = (char)array[i];
		}
		
		return result;
	}
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static String bytesToHex(char[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
