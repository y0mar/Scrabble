package scrabblePackage;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;

public class driver {
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame();
		FileDialog dialogTestRack = new FileDialog(frame, "Select a test rack file", FileDialog.LOAD);
		FileDialog dialogWordList = new FileDialog(frame, "Select the word list file", FileDialog.LOAD);
		
		dialogTestRack.setDirectory("C://"); dialogWordList.setDirectory("C://");
		dialogTestRack.setFile("*.txt"); dialogWordList.setFile("*.txt");
		
		dialogTestRack.setVisible(true); dialogWordList.setVisible(true);
		
		String filename = dialogTestRack.getFile();
		File file = new File(dialogTestRack.getDirectory() + filename);
		filename = dialogWordList.getFile();
		File wordFile = new File(dialogWordList.getDirectory() + filename);
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
			rackReader.close();
			wordReader.close();
			frame.dispose();
			dialogTestRack.dispose();
			dialogWordList.dispose();
		} else {
			
			while (rackReader.hasNextLine()) {
				Date start = new Date();
				ScrabbleGame game = new ScrabbleGame();
				
				game.rack.loadRack(rackReader.nextLine());
				
				for (int i = 0; i < wordList.size(); i++) {
					if (game.rack.checkMatches(wordList.get(i))) {
						game.rack.legalWords.add(wordList.get(i));
					}
				}
				if (game.rack.legalWords.isEmpty()) {
					System.out.println("Rack: " + game.rack.letterStr + " could not spell any words.\n\n");
					break;
				}
				//game.rack.legalWords = game.rack.selectionSort(game.rack.legalWords); 
				Solution solution = game.solveFirstMove(game.rack.legalWords);
				Date end = new Date();
				long elapsedTime = end.getTime() - start.getTime();
				
				/** PRINTS OUT THE COUNT OF EACH LETTER OF THE ALPHABET EXISTING IN THE RACK AND COUNT OF BLANKS
				for (int i = 0; i < game.rack.letters.length; i++) {
					System.out.print((char)(65+i) + "=" + game.rack.letters[i] + ", ");
				}
				System.out.println("& " + game.rack.blanks + " blanks\n");
				
				**/
				
				/** PRINTS OUT EVERY LEGAL WORD THAT THE GIVEN RACK CAN SPELL
				for (int i = 0; i < game.rack.legalWords.size(); i++) {
					System.out.print(game.rack.legalWords.get(i).theWord + ", ");
					if (i%10 == 0 && i>0) {
						System.out.println();
					}
				}
				System.out.print("\n\n");
				**/
				
				System.out.print("RACK: " + game.rack.letterStr + " yields " + game.rack.legalWords.size() + " legal words." + "\nPLACE WORD ");
				System.out.print(solution.word.theWord + " starting at (" + solution.startingSquare.row + ", " +
							solution.startingSquare.col + ") for " + solution.pointValue + " points.\nElapsed time: " + 
							elapsedTime + " milliseconds.\n\n");
				
			}
			
			rackReader.close();
			wordReader.close();
			frame.dispose();
			dialogTestRack.dispose();
			dialogWordList.dispose();
		}
	}
}
