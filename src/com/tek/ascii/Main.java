package com.tek.ascii;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.tek.ascii.lib.AsciiLib;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		String processed = AsciiLib.toAscii(ImageIO.read(new File("C:\\Users\\Julien\\Desktop\\test.png")), 80, 80);
		
		System.out.println(processed);
		
	}
	
}
