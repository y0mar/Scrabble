package scrabblePackage;

public class Word {
	static int letterPoints[] = new int[26];
	String theWord;
	int[] letterCounts = new int[26];
	int pointValue;
	
	Word(String wrd) {
		theWord = wrd;
		countLetters();
		computePV();
	}

	
	void countLetters() {
		for (int i = 0; i < theWord.length(); i++) {
			letterCounts[(int)theWord.charAt(i) - 65]++;
		}
	}
	
	void computePV() {
		for (int i = 0; i < 26; i++) {
			pointValue += letterPoints[i] * letterCounts[i];
		}
	}
	
	int computePV(char ch, int squareMultiplier) {
		int points = letterPoints[(int)ch - 65] * squareMultiplier;
		return points;
	}
	
	
	static void initLetterPoints() {
		for (int i = 0; i < letterPoints.length; i++) {
			if (i == 0 || i == 4 || i == 8 || i == 11 || i == 13 || i == 14 || i == 17 || i == 18 || i == 19 || i == 20) {
				letterPoints[i] = 1;
			}
			if (i == 3 || i == 6) {
				letterPoints[i] = 2;
			}
			if (i == 1 || i == 2 || i == 12 || i == 15) {
				letterPoints[i] = 3;
			}
			if (i == 5 || i == 7 || i == 21 || i == 22 || i == 24) {
				letterPoints[i] = 4;
			}
			if (i == 10) {
				letterPoints[i] = 5;
			}
			if (i == 9 || i == 23) {
				letterPoints[i] = 8;
			}
			if (i == 16 || i == 25) {
				letterPoints[i] = 10;
			}
		}
	}
	
}
