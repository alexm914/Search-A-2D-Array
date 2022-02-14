/*
 * Programming Project 3 (COSC 211)
 * Alex McNulty, Farha Z.
 * Winter 2021
 */

import java.util.ArrayList;
import java.util.Scanner;


/* Farha and I only worked on this project together. 
* We took turns writing out the code as we talked 
* about what the code should do. 
*/
public class RecursionProject3 {

	static ArrayList<String> runs = new ArrayList<String>(); // Create an ArrayList object
	static boolean transposed = false;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// Prompt the user for the dimensions of the array 
		
		System.out.println("What are the dimensions of the array? ");
		int row = input.nextInt();
		int column = input.nextInt();

		char[][] array = new char[row][column];

		populate(array);

		findLongestRun(array, row, column);
	}

	// --------------------------------------------------
	// Method to randomly populate a two-dimensional array with 
	// lowercase letters.
	// -------------------------------------------------
	public static void populate(char[][] array) {
		for (int row = 0; row < array.length; row++) {
			for (int column = 0; column < array[row].length; column++) {
				int num = (int) (Math.random() * 26) + 97;

				array[row][column] = (char) num;
				System.out.print(array[row][column] + " ");
			}
			System.out.println();
		}
	}

	// --------------------------------------------------
	// Method to find longest run 
	// -------------------------------------------------
	public static void findLongestRun(char[][] array, int row, int column) {
		System.out.println("\nHorizontal search: " + search(array, 0, 1));
		char[][] array2 = transposeMatrix(array, row, column);
		transposed = true;
		
		// search transposed matrix 
		System.out.println("\nVertical search: " + search(array2, 0, 1));
	
		// search for LL - UR diagonal runs 
		System.out.println("\nLower Right to Upper Left Diagonal Search: " 
				+ searchDiagonal(1, array, 0, 1, 0, 0));

		// flip array to find LR - UL diagonal runs 
		flipInPlace(array);
		System.out.println("\nLower Left to Upper Right Diagonal Search");
		
		// print flipped array for LL - UR diagonal search
		for (int flipRow = 0; flipRow < array.length; flipRow++) {
			for (int flipColumn = 0; flipColumn < array[flipRow].length; flipColumn++) {
				System.out.print(array[flipRow][flipColumn] + " ");
			}
			System.out.println();
		}
		
		// search the flipped/printed array
		System.out.println(searchDiagonal(1, array, 0, 1, 0, 0));
		
		int longestRun;
		char letter = ' ';
		String letters = "";

		// if there are any runs at all, find longest run
		if (runs.size() > 0) {
			longestRun = Character.getNumericValue(runs.get(0).charAt(0));
			letter = runs.get(0).charAt(2);

			for (int i = 0; i < runs.size(); i++) {
				// if runs is greater than longest run, change letter
				if (Character.getNumericValue(runs.get(i).charAt(0)) > longestRun) {
					longestRun = Character.getNumericValue(runs.get(i).charAt(0));
					letter = runs.get(i).charAt(2);
					letters = "";
					letters += letter + " ";
				}
				// if runs is equal to the longest run, add the letter to our string
				else if (Character.getNumericValue(runs.get(i).charAt(0)) == longestRun) {
					int a = letters.indexOf(runs.get(i).charAt(2));
					if (a == -1) {
						letters += runs.get(i).charAt(2) + " ";
					}
				}
				
			
			}
			// if more than one letter have longest run, print out all letters
			if (letters.length() > 0)
			{
//				if (Character.toString(letter).equals(Character.toString(letters.charAt(0)))) {
					System.out.println("\nLongest run: " + longestRun + " for letters: " + letters);
//				} 
			}
			else
			{
					System.out.println("\nLongest run: " + longestRun + " letter: " + letter);
			}
		}
	}

	// --------------------------------------------------
	// Method to return transposed matrix
	// -------------------------------------------------
	public static char[][] transposeMatrix(char[][] a, int row, int col) {

		char[][] arr = new char[col][row];

		for (int i = 0; i < (a[0].length); i++) {
			for (int j = 0; j < (a.length); j++) {
				arr[i][j] = a[j][i];
			}
		}

		return arr;
	}
	
	// --------------------------------------------------
	// Method to flip matrix
	// -------------------------------------------------
	public static void flipInPlace(char [][] theArray) {
	    for(int i = 0; i < (theArray.length / 2); i++) {
	        char[] temp = theArray[i];
	        theArray[i] = theArray[theArray.length - i - 1];
	        theArray[theArray.length - i - 1] = temp;
	    }
	}

	// --------------------------------------------------
	// recursive method that will search the array horizontally
	// and vertically to determine the length of a run of the same letter
	// -------------------------------------------------
	public static String search(char[][] array, int pickRow, int pickCol) {

		// base case1: letter isn't repeating
		// base case2: the end of a row or column has been reached

		// recursive case: two letters are equal

		char letter = ' ';
		int count = 1;
		int runLength = 1;
		String stats = "";

		char compareLetter = array[0][0];

		for (int row = pickRow; row < array.length; row++) {
			if (row >= 0) {
				compareLetter = array[row][0];
			}
			for (int column = pickCol; column < array[row].length; column++) {
				if (compareLetter == array[row][column]) {
					count++;
					letter = compareLetter;
					if (row == array[row].length - 1 && column == array.length - 1) {
						runLength = count;
						return "\nrow: " + (row + 1) + " col: " + (column) + "\nThe run is " + runLength + " for "
								+ letter;
					}
				} else if (count > 1 && compareLetter != array[row][column]) {
					runs.add(count + ":" + letter);
					runLength = count;
					count = 1;
					if (transposed) {
						stats = "\nrow: " + ((row - (row - (column + 1))) - 1) + " col: " + (row + 1) + "\nThe run is "
								+ runLength + " for " + letter;
					} else {
						if (column == 1) {
							stats = "\nrow: " + (row) + " col: " + (array[row].length) + "\nThe run is " + runLength
									+ " for " + letter;
						} else {
							stats = "\nrow: " + (row + 1) + " col: " + (column) + "\nThe run is " + runLength + " for "
									+ letter;
						}
					}
					compareLetter = array[row][column];
					return search(array, row, column) + stats;
				} else {
					compareLetter = array[row][column];
				}
			}
			if (row >= 0) {
				pickCol = 1;
			}
		}
		return "";
	}

	// --------------------------------------------------
	// recursive method that will search the array diagonally
	// to determine the length of a run of the same letter
	// -------------------------------------------------
	public static String searchDiagonal(int i, char[][] array, int pickRow, int pickCol, int num, int n) {

		// base case1: letter isn't repeating
		// base case2: the end of diagonal line has been reached

		// recursive case: two letters are equal
		
		// variables
		char letter = ' ';
		int count = 1;
		int runLength = 1;
		int row = pickRow;
		int col = pickCol;
		char let = ' ';		
		char compareLetter = array[row][col];
		
		int m = num; 
		int var = n;
		int wRow = 0;
		int wCol = 0;
		
		for (int v = i; v < (array.length + array[0].length) - 2; v++) {
			// if v is greater than the length of the array, size of each row decreases
			if (v >= array[0].length) {
				if (!(array[0].length < array.length && v < array.length))
					m -= 1;
			}
			else {
				// row size to check equals column number
				m = v;
			}
			for (int j = pickRow; j < m; j++) {
				row++;
				col--;
				if (row >= array.length) {
					row = 0;
				}
				
				// set letter to next array value
				letter = array[row][col];
				if (letter == compareLetter) {
					count++;
					let = letter;
					wRow = row + 1;
					wCol = col + 1;
					// if array reaches end, return values
					if (v == (array.length + array[0].length) - 2) {
						return "\nrow: " + wRow + " col: " + wCol
								+ "\nThe run is " + runLength + " for letter: " + let;
					}
				}
				// if run is greater than one, return array search
				else if (count > 1) {
					runs.add(count + ":" + let);
					runLength = count;
					count = 1;
					compareLetter = array[row][col];
					if (m - j == 1 && v < (array[0].length - 1)) {
						return searchDiagonal(v + 1, array, 0, v + 1, m, var) + "\nrow: " + wRow + " col: " + wCol
							+ "\nThe run is " + runLength + " for letter: " + let;
					}
					else {
						return searchDiagonal(v, array, row, col, m, var) + "\nrow: " + wRow + " col: " + wCol
								+ "\nThe run is " + runLength + " for letter: " + let;
					}
				}
				// set compareLetter to current value in array
				else {
					compareLetter = array[row][col];
				}
			}
			// check if we start going down the array vertically
			if (v < array[0].length - 1) {
				compareLetter = array[0][v + 1]; 
				row = 0;
				col = v + 1;
				pickRow = 0;
			}
			else {
				var++;
				compareLetter = array[var][array[0].length - 1];
				row = var;
				col = array[0].length - 1;
				pickRow = 0;
			}
		}
		
		return "";
	}

}