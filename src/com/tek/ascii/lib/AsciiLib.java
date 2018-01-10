package com.tek.ascii.lib;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * AsciiLib.class by Link
 * Utility class to perform a bunch
 * of different ascii/image operations
 */

public class AsciiLib {
	
	public static String toAscii(BufferedImage image, int outX, int outY) {
		if(image == null || outX == 0 || outY == 0 || outX < 0 || outY < 0) return null;
		
		int inX = image.getWidth();
		int inY = image.getHeight();
		
		if(outX > inX || outY > inY) return null;
		
		int raX = inX / outX;
		int raY = inY / outY;
		
		StringBuilder builder = new StringBuilder();
		
		for(int y = 0; y < inY; y += raY) {
			for(int x = 0; x < inX; x += raX) {
				int areaBrightness = 0;
				int i = 0;
				
				for(int px = x - raX; px < x; px++) {
					for(int py = y - raY; py < y; py++) {
						int clr   = image.getRGB(x,y); 
						int red   = (clr & 0x00ff0000) >> 16;
						int green = (clr & 0x0000ff00) >> 8;
						int blue  = (clr & 0x000000ff);
						
						Color color = new Color(red, green, blue);
						
						areaBrightness += brightness(color);
						
						i++;
					}
				}
				
				areaBrightness /= i;
				
				char processed = ArtChar.charFrom(areaBrightness);
				
				builder.append(processed);
			}
			
			builder.append(System.lineSeparator());
		}
		
		return builder.toString().substring(0, builder.toString().length() - System.lineSeparator().length());
	}
	
	public enum ArtChar{
		a(0, 40, '#'),
		b(41, 80, 'O'),
		c(81, 120, 'C'),
		d(121, 160, '/'),
		e(161, 200, '~'),
		f(201, 240, ','),
		g(241, 255, '.');
		
		public int from, to;
		public char ch;
		
		ArtChar(int from, int to, char ch){
			this.from = from;
			this.to = to;
			this.ch = ch;
		}
		
		public static char charFrom(int brightness) {
			for(ArtChar ac : ArtChar.values()) {
				if(brightness >= ac.from && brightness <= ac.to) return ac.ch;
			}
			
			return ' ';
		}
	}
	
	public static int brightness(Color c)
	{
	   return (int)Math.sqrt(
	      c.getRed() * c.getRed() * .241 + 
	      c.getGreen() * c.getGreen() * .691 + 
	      c.getBlue() * c.getBlue() * .068);
	}
	
}
