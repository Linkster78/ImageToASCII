package com.tek.ascii;

import java.io.IOException;

import com.tek.ascii.lib.AsciiLib;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		AsciiLib.loadBrightnessTable();
		
		new UserInterface();
		
	}
	
}
