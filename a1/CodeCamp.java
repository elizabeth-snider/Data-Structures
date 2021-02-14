//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 * 
 *  replace <NAME> with your name.
 *
 *  On my honor, Elizabeth, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Elizabeth Snider
 *  email address: elizabethsnider2011@gmail.com
 *  UTEID: eys275
 *  Section 5 digit ID: 50860
 *  Grader name: Henry Liu
 *  Number of slip days used on this assignment: 0
 */

import java.util.Random;

public class CodeCamp {

	/**
	 * Determine the Hamming distance between two arrays of ints. Neither the
	 * parameter <tt>aData</tt> or <tt>bData</tt> are altered as a result of this
	 * method.<br>
	 * 
	 * @param aData != null, aData.length == aData.length
	 * @param bData != null
	 * @return the Hamming Distance between the two arrays of ints.
	 */
	public static int hammingDistance(int[] aData, int[] bData) {
		// check preconditions
		if (aData == null || bData == null || aData.length != bData.length) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "hammingDistance. neither parameter may equal null, arrays" + " must be equal length.");
		}

		// loops through both arrays and counts the number of differences
		int count = 0;

		for (int length = 0; length < aData.length; length++) {
			if (aData[length] != bData[length]) {
				count++;
			}
		}

		return count;

	}

	/**
	 * Determine if one array of ints is a permutation of another. Neither the
	 * parameter <tt>aData</tt> or the parameter <tt>bData</tt> are altered as a
	 * result of this method.<br>
	 * 
	 * @param aData != null
	 * @param bData != null
	 * @return <tt>true</tt> if aData is a permutation of bData, <tt>false</tt>
	 *         otherwise
	 * 
	 */
	public static boolean isPermutation(int[] aData, int[] bData) {
		// check preconditions
		if (aData == null || bData == null) {
			throw new IllegalArgumentException(
					"Violation of precondition: " + "isPermutation. neither parameter may equal null.");
		}

		// checks if each integer is present in the other array and compares sum of all
		// the integers
		// permutations with fit both requirements
		int aLength = aData.length;
		int bLength = bData.length;
		int num = 0;

		if (aLength != bLength) {
			return false;
		}

		for (int length = 0; length < aLength; length++) {
			num = aData[length];
			if (contains(aData, bData, aLength, num) & equals(aData, bData, aLength)) {
				return true;
			}
		}

		return false;
	}

	// checks if each integer is present in the permutation
	// @param aData and bData are the two arrays being compared
	// @param aLength is the length of both arrays
	// @param num is the number being checked
	public static boolean contains(int[] aData, int[] bData, int aLength, int num) {
		for (int length = 0; length < aLength; length++) {
			if (num == bData[length]) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	// checks if both arrays have the same sum
	// @param aData and bData are the two arrays being compared
	// @param aLength is the length of both arrays
	public static boolean equals(int[] aData, int[] bData, int aLength) {
		int total = 0;
		int totalTwo = 0;

		for (int oneLength = 0; oneLength < aLength; oneLength++) {
			total += aData[oneLength];
		}

		for (int twoLength = 0; twoLength < aLength; twoLength++) {
			totalTwo += bData[twoLength];
		}

		if (total == totalTwo) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determine the index of the String that has the largest number of vowels.
	 * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 
	 * 'U', and 'u'</tt>. The parameter <tt>arrayOfStrings</tt> is not altered as a
	 * result of this method.
	 * <p>
	 * pre: <tt>arrayOfStrings != null</tt>, <tt>arrayOfStrings.length > 0</tt>,
	 * there is an least 1 non null element in arrayOfStrings.
	 * <p>
	 * post: return the index of the non-null element in arrayOfStrings that has the
	 * largest number of characters that are vowels. If there is a tie return the
	 * index closest to zero. The empty String, "", has zero vowels. It is possible
	 * for the maximum number of vowels to be 0.<br>
	 * 
	 * @param arrayOfStrings the array to check
	 * @return the index of the non-null element in arrayOfStrings that has the
	 *         largest number of vowels.
	 */
	public static int mostVowels(String[] arrayOfStrings) {
		// check preconditions
		if (arrayOfStrings == null || arrayOfStrings.length == 0 || !atLeastOneNonNull(arrayOfStrings)) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "mostVowels. parameter may not equal null and must contain " + "at least one none null value.");
		}

		// counts the number of vowels in each string in the array and chooses the
		// string
		// with the greatest number of vowels
		int most = 0;
		int index = 0;
		int lastIndex = 0;
		String string = "";
		String vowels = "AaEeIiOoUu";

		for (int chosen = 0; chosen < arrayOfStrings.length; chosen++) {
			string = arrayOfStrings[chosen];
			int numVowels = count(arrayOfStrings, string, vowels);

			if (numVowels > most) {
				most = numVowels;
				index = chosen;
				lastIndex = chosen;
			} else if (numVowels == most) {
				index = lastIndex;
			}
		}

		while (arrayOfStrings[index] == null) {
			if (arrayOfStrings[index] == null) {
				index++;
			}
		}

		return index;
	}

	// counts the number of vowels in each string by turning them into char arrays
	// @param arrayOfStrings is the array of strings
	// @param string is the chosen string
	// @param vowels is the string of vowels being used to locate them
	public static int count(String[] arrayOfStrings, String string, String vowels) {
		int vowelCount = 0;

		if (string == "" | string == null) {
			return 0;
		}

		char[] letters = string.toCharArray();
		char[] vowelArray = vowels.toCharArray();

		for (int lengthOne = 0; lengthOne < letters.length; lengthOne++) {

			for (int lengthTwo = 0; lengthTwo < vowelArray.length; lengthTwo++) {

				if (letters[lengthOne] == vowelArray[lengthTwo]) {
					vowelCount++;
				}
			}
		}
		return vowelCount;
	}

	/**
	 * Perform an experiment simulating the birthday problem. Pick random birthdays
	 * for the given number of people. Return the number of pairs of people that
	 * share the same birthday.<br>
	 * 
	 * @param numPeople     The number of people in the experiment. This value must
	 *                      be > 0
	 * @param numDaysInYear The number of days in the year for this experiement.
	 *                      This value must be > 0
	 * @return The number of pairs of people that share a birthday after running the
	 *         simulation.
	 */
	public static int sharedBirthdays(int numPeople, int numDaysInYear) {
		// check preconditions
		if (numPeople <= 0 || numDaysInYear <= 0) {
			throw new IllegalArgumentException(
					"Violation of precondition: " + "sharedBirthdays. both parameters must be greater than 0. "
							+ "numPeople: " + numPeople + ", numDaysInYear: " + numDaysInYear);
		}

		// generates random birthdays per person and counts the number of days shared
		Random r = new Random();
		int max = numDaysInYear;
		int[] birthdays = new int[numDaysInYear];
		int pairs = 0;
		int day = 0;
		int divider = 2;

		for (int person = 0; person < numPeople; person++) {
			day = r.nextInt(max);
			birthdays[day]++;
		}

		for (int days = 0; days < numDaysInYear; days++) {
			pairs += (birthdays[days] * (birthdays[days] - 1)) / divider;
		}

		return pairs;
	}

	/**
	 * Determine if the chess board represented by board is a safe set up.
	 * <p>
	 * pre: board != null, board.length > 0, board is a square matrix. (In other
	 * words all rows in board have board.length columns.), all elements of board ==
	 * 'q' or '.'. 'q's represent queens, '.'s represent open spaces.<br>
	 * <p>
	 * post: return true if the configuration of board is safe, that is no queen can
	 * attack any other queen on the board. false otherwise. the parameter
	 * <tt>board</tt> is not altered as a result of this method.<br>
	 * 
	 * @param board the chessboard
	 * @return true if the configuration of board is safe, that is no queen can
	 *         attack any other queen on the board. false otherwise.
	 */
	public static boolean queensAreSafe(char[][] board) {
		char[] validChars = { 'q', '.' };
		// check preconditions
		if (board == null || board.length == 0 || !isSquare(board) || !onlyContains(board, validChars)) {
			throw new IllegalArgumentException(
					"Violation of precondition: " + "queensAreSafe. The board may not be null, must be square, "
							+ "and may only contain 'q's and '.'s");
		}

		// locates each queen on the board and checks if it is safe in all 8 directions
		// i,j,k,l,m, and n are used as counters
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board.length; column++) {
				if (board[row][column] == 'q') {

					for (int i = column + 1; i < board.length; i++) {
						if (board[row][i] == 'q') {
							return false;
						}
					}
					for (int j = row + 1; j < board.length; j++) {
						if (board[j][column] == 'q') {
							return false;
						}
					}
					for (int k = 1; (k + row) < board.length && (k + column) < board.length; k++) {
						if (board[k + row][k + column] == 'q') {
							return false;
						}
					}
					for (int l = 1; (row - l) > -1 && (column - l) > -1; l++) {
						if (board[row - l][column - l] == 'q') {
							return false;
						}
					}
					for (int m = 1; (row + m) < board.length && (column - m) > -1; m++) {
						if (board[row + m][column - m] == 'q') {
							return false;
						}
					}
					for (int n = 1; (row - n) > -1 && (column + n) < board.length; n++) {
						if (board[row - n][column + n] == 'q') {
							return false;
						}
					}

				}
			}
		}
		return true;
	}

	/**
	 * Given a 2D array of ints return the value of the most valuable contiguous sub
	 * rectangle in the 2D array. The sub rectangle must be at least 1 by 1.
	 * <p>
	 * pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
	 * mat</tt> is a rectangular matrix.
	 * <p>
	 * post: return the value of the most valuable contiguous sub rectangle in
	 * <tt>city</tt>.<br>
	 * 
	 * @param city The 2D array of ints representing the value of each block in a
	 *             portion of a city.
	 * @return return the value of the most valuable contiguous sub rectangle in
	 *         <tt>city</tt>.
	 */
	public static int getValueOfMostValuablePlot(int[][] city) {
		// check preconditions
		if (city == null || city.length == 0 || city[0].length == 0 || !isRectangular(city)) {
			throw new IllegalArgumentException(
					"Violation of precondition: " + "getValueOfMostValuablePlot. The parameter may not be null,"
							+ " must value at least one row and at least" + " one column, and must be rectangular.");
		}

		// creates every possible subplot in a form of a rectangle and chooses the
		// greatest value
		// that can be formed
		int max = city[0][0];
		int length = city.length;
		int current = 0;
		int rowLength = city[0].length;

		for (int top = 0; top < length; top++) {

			for (int bot = (top + 1); bot <= length; bot++) {

				for (int left = 0; left < rowLength; left++) {

					for (int right = (left + 1); right <= rowLength; right++) {

						current = value(top, bot, left, right, city);
						if (current > max) {
							max = current;
						}
					}
				}
			}
		}

		return max;
	}

	// calculates the value of a certain subplot
	// @param top, bot, left, and right are each possible direction of expansion
	// of the rectangular subplot
	// @param city is the 2d array of integers
	public static int value(int top, int bot, int left, int right, int[][] city) {
		int count = 0;

		for (int row = top; row < bot; row++) {

			for (int col = left; col < right; col++) {

				count += city[row][col];
			}
		}

		return count;
	}

	// !!!!! ***** !!!!! ***** !!!!! ****** !!!!! ****** !!!!! ****** !!!!!!
	// CS314 STUDENTS: Put your birthday problem experiment code here:

	// experiment one
	// calculates the average of shared birthdays in 1 million experiments
	// generates random birthdays for each person and averages the total number for
	// each
	// experiment
	public static int experimentOne() {
		int numDaysInYear = 365;
		int numPeople = 182;
		int experiments = 1000000;
		int[] pairArray = new int[experiments];
		int divider = 2;

		for (int num = 0; num < experiments; num++) {
			Random r = new Random();
			int max = numDaysInYear;
			int[] birthdays = new int[numDaysInYear];
			int pairs = 0;
			int day = 0;

			for (int person = 0; person < numPeople; person++) {
				day = r.nextInt(max);
				birthdays[day]++;
			}

			for (int days = 0; days < numDaysInYear; days++) {
				pairs += (birthdays[days] * (birthdays[days] - 1)) / divider;
			}
			pairArray[num] = pairs;
		}

		int total = 0;
		int average = 0;

		for (int length = 0; length < pairArray.length; length++) {
			total += pairArray[length];
			average = total / experiments;
		}

		return average;
	}

	// experiment two
	// generates the number of at least one shared birthdays for 2-100 people over
	// 50,000 experiments
	// uses 365 days
	public static void experimentTwo() {
		int numDaysInYear = 365;
		double percentage = 0;
		int day = 0;
		Random random = new Random();
		boolean found = false;
		int min = 2;
		int max = 100;
		int experiments = 50000;

		for (int person = min; person <= max; person++) {
			double count = 0;

			for (int num = 0; num < experiments; num++) {
				int[] birthdays = new int[numDaysInYear];

				for (int i = 0; i < person; i++) {

					day = random.nextInt(numDaysInYear);
					birthdays[day]++;
				}
				found = false;

				for (int days = 0; days < birthdays.length; days++) {
					if (birthdays[days] >= min && !found) {
						count++;
						found = true;
					}
				}

			}

			percentage = ((count / experiments) * max);
			int countInt = (int) count;
			System.out.printf("Num people: " + person + ", number of "
					+ "experiments with one or more shared birthday: " + countInt + ", percentage: %.2f", percentage);
			System.out.println();
		}
	}

	/*
	 * pre: arrayOfStrings != null post: return true if at least one element in
	 * arrayOfStrings is not null, otherwise return false.
	 */
	private static boolean atLeastOneNonNull(String[] arrayOfStrings) {
		// check precondition
		if (arrayOfStrings == null) {
			throw new IllegalArgumentException(
					"Violation of precondition: " + "atLeastOneNonNull. parameter may not equal null.");
		}
		boolean foundNonNull = false;
		int i = 0;
		while (!foundNonNull && i < arrayOfStrings.length) {
			foundNonNull = arrayOfStrings[i] != null;
			i++;
		}
		return foundNonNull;
	}

	/*
	 * pre: mat != null post: return true if mat is a square matrix, false otherwise
	 */
	private static boolean isSquare(char[][] mat) {
		if (mat == null) {
			throw new IllegalArgumentException("Violation of precondition: " + "isSquare. Parameter may not be null.");
		}
		final int numRows = mat.length;
		int row = 0;
		boolean isSquare = true;
		while (isSquare && row < numRows) {
			isSquare = (mat[row] != null) && (mat[row].length == numRows);
			row++;
		}
		return isSquare;
	}

	/*
	 * pre: mat != null, valid != null post: return true if all elements in mat are
	 * one of the characters in valid
	 */
	private static boolean onlyContains(char[][] mat, char[] valid) {
		// check preconditions
		if (mat == null || valid == null) {
			throw new IllegalArgumentException(
					"Violation of precondition: " + "onlyContains. Parameters may not be null.");
		}
		String validChars = new String(valid);
		int row = 0;
		boolean onlyContainsValidChars = true;
		while (onlyContainsValidChars && row < mat.length) {
			int col = 0;
			while (onlyContainsValidChars && col < mat[row].length) {
				int indexOfChar = validChars.indexOf(mat[row][col]);
				onlyContainsValidChars = indexOfChar != -1;
				col++;
			}
			row++;
		}
		return onlyContainsValidChars;
	}

	/*
	 * pre: mat != null, mat.length > 0 post: return true if mat is rectangular
	 */
	private static boolean isRectangular(int[][] mat) {
		// check preconditions
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "isRectangular. Parameter may not be null and must contain" + " at least one row.");
		}
		boolean correct = mat[0] != null;
		int row = 0;
		while (correct && row < mat.length) {
			correct = (mat[row] != null) && (mat[row].length == mat[0].length);
			row++;
		}
		return correct;
	}

	// make constructor private so no instances of CodeCamp can not be created
	private CodeCamp() {

	}
}