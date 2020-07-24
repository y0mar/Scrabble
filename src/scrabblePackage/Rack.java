package scrabblePackage;
import java.util.*;
import java.io.*;

public class Rack {
	int blanks;
	String letterStr;
	int letters[] = new int[26];
	ArrayList<Word> legalWords = new ArrayList<Word>();
	
	
	void loadRack(String str) {
		letterStr = str;
		for (int i = 0; i < letterStr.length(); i++) {
			if (letterStr.charAt(i) == '_') {
				blanks++;
			} else {
				letters[(int)letterStr.charAt(i) - 65]++;				
			}
		}
	}
	
	/**
	 * returns true if the rack can spell the input word with available letters and blanks
	 * @param wrd
	 * @return
	 */
	boolean checkMatches(Word wrd) {
		int letterCount = wrd.theWord.length();
		
		for (int i = 0; i < 26; i++) {
			if (letters[i] >= wrd.letterCounts[i] && wrd.letterCounts[i] != 0) {
				letterCount--;
			}
		}
		if (letterCount <= blanks) {
			return true;
		}
		return false;
	}
	
	ArrayList<Word> selectionSort(ArrayList<Word> wordList) {
		ArrayList<Word> tempList = new ArrayList<Word>(wordList);
		ArrayList<Word> newList = new ArrayList<Word>();
		
		
		while(!tempList.isEmpty()) {
			int smallest = 0;
			for (int i = 1; i < tempList.size(); i++) {
				if (tempList.get(smallest).pointValue > tempList.get(i).pointValue) {
					smallest = i;
				}
			}
			newList.add(tempList.remove(smallest));
		}
		return newList;
	}
}
