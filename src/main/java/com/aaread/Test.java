package com.aaread;

public class Test {
	public static void main(String[] args) {
		
	}
	
	public static byte[] charToByte(char c) {   
        byte[] b = new byte[2];   
        b[0] = (byte) ((c & 0xFF00) >> 8);   
        b[1] = (byte) (c & 0xFF);   
        return b;   
    }  
	
	public static int byteToInt2(byte[] b){
	      return  (((int)b[0]) << 8) + b[1];  
	  }  
}
