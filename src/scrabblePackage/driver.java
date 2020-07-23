package scrabblePackage;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;

public class driver {
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame();
		FileDialog dialog = new FileDialog(frame, "Select a file", FileDialog.LOAD);
		dialog.setDirectory("C://");
		dialog.setFile("*.txt");
		dialog.setVisible(true);
		Scanner input = new Scanner(System.in);
		
		String filename = dialog.getFile();
		File file = new File(dialog.getDirectory() + filename);
		Scanner fileReader = new Scanner(file);
		
		
		
		
	}

}
