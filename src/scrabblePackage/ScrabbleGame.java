package scrabblePackage;
import java.util.*;

public class ScrabbleGame {
	static ArrayList<Word> wordList = new ArrayList<Word>();
	int letterPoints[] = new int[26];
	Rack rack = new Rack();
	Square board[][] = new Square[15][15];
	
	ScrabbleGame() {
		initBoard();
		initRow7();
		initLetterPoints();
	}
	
	
	void initBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = new Square();
				board[row][col].row = row; board[row][col].col = col;
			}
		}
	}
	
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
	
	/**
	 * Returns the starting square to achieve highest PV for the given word
	 * while satisfying the center square requirement
	 * @param wrd
	 * @return legal starting Square with highest PV for the word
	 */
	Solution highestPVStartSquare(Word wrd) {
		Solution solution = new Solution();
		solution.word = wrd;
		solution.startingSquare.row = 7;
		solution.startingSquare.col = 7;
		
		int pointValue = 0;
		int highestPV = 0;
		
		for (int i = 0; i < wrd.theWord.length(); i++) {
			for (int j = 0; j < wrd.theWord.length(); j++) {
				pointValue += wrd.computePV(wrd.theWord.charAt(j),  board[7][7 - i + j].multiplier);
				if (pointValue > highestPV) {
					highestPV = pointValue;
					solution.startingSquare.col = 7 - i;
				}
			}
			pointValue = 0;
		}
		solution.pointValue = highestPV;
		return solution;
	}
	
	Solution solveFirstMove(ArrayList<Word> legalWrds) {
		Solution bestFirstMove = new Solution();
		
		for (int i = 0; i < legalWrds.size(); i++) {
			if (bestFirstMove.pointValue < this.highestPVStartSquare(legalWrds.get(i)).pointValue) {
				bestFirstMove = this.highestPVStartSquare(legalWrds.get(i));
			}
		}
		return bestFirstMove;
	}
}
