package scrabblePackage;

public class ScrabbleGame {
	int letterPoints[] = new int[26];
	Rack rack;
	Square board[][] = new Square[15][15];
	
	
	void initRow7() {
		board[7][3].multiplier = 2;
		board[7][11].multiplier = 2;
	}
	
	void initLetterPoints() {
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
