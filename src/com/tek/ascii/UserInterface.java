package com.tek.ascii;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.tek.ascii.lib.AsciiLib;
import javax.swing.SwingConstants;

public class UserInterface {

	private JFrame frame;
	public File file;

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 223, 266);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JButton chooseimage = new JButton("Choose Image");
		chooseimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "png", "jpg", "jpeg");
				
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				fileChooser.setFileFilter(filter);
				
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
				}
			}
		});
		chooseimage.setBounds(15, 52, 179, 29);
		frame.getContentPane().add(chooseimage);
		
		JSpinner spinnerx = new JSpinner();
		spinnerx.setBounds(93, 95, 101, 26);
		frame.getContentPane().add(spinnerx);
		
		JSpinner spinnery = new JSpinner();
		spinnery.setBounds(93, 133, 101, 26);
		frame.getContentPane().add(spinnery);
		
		JLabel lblX = new JLabel("Art Width");
		lblX.setBounds(15, 98, 179, 20);
		frame.getContentPane().add(lblX);
		
		JLabel lblArtHeight = new JLabel("Art Height");
		lblArtHeight.setBounds(15, 136, 179, 20);
		frame.getContentPane().add(lblArtHeight);
		
		JButton btnNewButton = new JButton("Convert !");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    int valueX = (int) spinnerx.getValue();
				int valueY = (int) spinnery.getValue();
				
				try {
					BufferedImage image = ImageIO.read(file);
					
					String output = AsciiLib.toAscii(image, valueX, valueY);
					
					if(output != null) {
						String fileName = stripExtension(file.getAbsolutePath());
						
						File outputFile = new File(fileName + ".txt");
						
						if(outputFile.exists()) outputFile.delete();
						outputFile.createNewFile();
						
						PrintWriter fileWriter = new PrintWriter(outputFile);
						
						fileWriter.write(output);
						
						fileWriter.close();
						
						JOptionPane.showMessageDialog(null, "Saved ASCII art as " + fileName + ".txt");
					}else {
						throw(new NullPointerException());
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "An error occured, double check your entered values.");
				}
			}
		});
		btnNewButton.setBounds(15, 172, 179, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblImageAscii = new JLabel("Image 2 ASCII");
		lblImageAscii.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageAscii.setBounds(15, 16, 179, 20);
		frame.getContentPane().add(lblImageAscii);
		
		frame.setVisible(true);
	}
	
	static String stripExtension (String str) {
        // Handle null case specially.

        if (str == null) return null;

        // Get position of last '.'.

        int pos = str.lastIndexOf(".");

        // If there wasn't any '.' just return the string as is.

        if (pos == -1) return str;

        // Otherwise return the string, up to the dot.

        return str.substring(0, pos);
    }
}
