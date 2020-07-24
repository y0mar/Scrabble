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
		File wordFile = new File("C:\\Users\\Omar\\Documents\\GitHub\\Scrabble\\src\\SOWPODS_complete.txt");
		Scanner rackReader = new Scanner(file);
		Scanner wordReader = new Scanner(wordFile);
		
		ArrayList<Word> wordList = new ArrayList<Word>();
		
		Word.initLetterPoints();
		while (wordReader.hasNext()) {
			Word wrd = new Word(wordReader.nextLine());
			wordList.add(wrd);
		}
		
		if(filename == null) {
			System.out.println("You cancelled the choice");
			input.close();
			frame.dispose();
			dialog.dispose();
		} else {
			
			while (rackReader.hasNextLine()) {
				ScrabbleGame game = new ScrabbleGame();
				
				game.rack.loadRack(rackReader.nextLine());
				
				for (int i = 0; i < wordList.size(); i++) {
					String theWrd = wordList.get(i).theWord;
					if (game.rack.checkMatches(wordList.get(i))) {
						game.rack.legalWords.add(wordList.get(i));
					}
				}
				game.rack.legalWords = game.rack.selectionSort(game.rack.legalWords);
				for (int i = 0; i < game.rack.letters.length; i++) {
					System.out.print((char)(65+i) + "=" + game.rack.letters[i] + ", ");
				}
				System.out.println("& " + game.rack.blanks + " blanks\n");
				for (int i = 0; i < game.rack.legalWords.size(); i++) {
					System.out.print(game.rack.legalWords.get(i).theWord + ", ");
					if (i%10 == 0 && i>0) {
						System.out.println();
					}
				}
				System.out.println("\n");
			}
			
			
			frame.dispose();
			dialog.dispose();
		}
		
	}

}
