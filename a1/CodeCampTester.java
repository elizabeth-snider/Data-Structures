import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// CodeCamp.java - CS314 Assignment 1 - Tester class

/*
 * Student information for assignment: 
 * Name: Elizabeth Snider
 * email address: elizabethsnider2011@gmail.com
 * UTEID: eys275
 * Section 5 digit ID: 50860
 * Grader name: Henry Liu
 * Number of slip days used on this assignment: 0
 */

/*
 * Average number of pairs of people with shared birthdays: 45
 * How many people it takes so there is a 50% chance that at least 2
 * have a shared birthday: 23
 * 
 * Num people: 2, number of experiments with one or more shared birthday: 135, percentage: 0.27
Num people: 3, number of experiments with one or more shared birthday: 407, percentage: 0.81
Num people: 4, number of experiments with one or more shared birthday: 849, percentage: 1.70
Num people: 5, number of experiments with one or more shared birthday: 1349, percentage: 2.70
Num people: 6, number of experiments with one or more shared birthday: 2055, percentage: 4.11
Num people: 7, number of experiments with one or more shared birthday: 2881, percentage: 5.76
Num people: 8, number of experiments with one or more shared birthday: 3699, percentage: 7.40
Num people: 9, number of experiments with one or more shared birthday: 4758, percentage: 9.52
Num people: 10, number of experiments with one or more shared birthday: 5797, percentage: 11.59
Num people: 11, number of experiments with one or more shared birthday: 6877, percentage: 13.75
Num people: 12, number of experiments with one or more shared birthday: 8388, percentage: 16.78
Num people: 13, number of experiments with one or more shared birthday: 9732, percentage: 19.46
Num people: 14, number of experiments with one or more shared birthday: 11147, percentage: 22.29
Num people: 15, number of experiments with one or more shared birthday: 12692, percentage: 25.38
Num people: 16, number of experiments with one or more shared birthday: 14182, percentage: 28.36
Num people: 17, number of experiments with one or more shared birthday: 15765, percentage: 31.53
Num people: 18, number of experiments with one or more shared birthday: 17397, percentage: 34.79
Num people: 19, number of experiments with one or more shared birthday: 19125, percentage: 38.25
Num people: 20, number of experiments with one or more shared birthday: 20736, percentage: 41.47
Num people: 21, number of experiments with one or more shared birthday: 22215, percentage: 44.43
Num people: 22, number of experiments with one or more shared birthday: 23914, percentage: 47.83
Num people: 23, number of experiments with one or more shared birthday: 25244, percentage: 50.49
Num people: 24, number of experiments with one or more shared birthday: 26963, percentage: 53.93
Num people: 25, number of experiments with one or more shared birthday: 28205, percentage: 56.41
Num people: 26, number of experiments with one or more shared birthday: 30192, percentage: 60.38
Num people: 27, number of experiments with one or more shared birthday: 31226, percentage: 62.45
Num people: 28, number of experiments with one or more shared birthday: 32667, percentage: 65.33
Num people: 29, number of experiments with one or more shared birthday: 34129, percentage: 68.26
Num people: 30, number of experiments with one or more shared birthday: 35258, percentage: 70.52
Num people: 31, number of experiments with one or more shared birthday: 36540, percentage: 73.08
Num people: 32, number of experiments with one or more shared birthday: 37677, percentage: 75.35
Num people: 33, number of experiments with one or more shared birthday: 38682, percentage: 77.36
Num people: 34, number of experiments with one or more shared birthday: 39521, percentage: 79.04
Num people: 35, number of experiments with one or more shared birthday: 40714, percentage: 81.43
Num people: 36, number of experiments with one or more shared birthday: 41660, percentage: 83.32
Num people: 37, number of experiments with one or more shared birthday: 42433, percentage: 84.87
Num people: 38, number of experiments with one or more shared birthday: 43278, percentage: 86.56
Num people: 39, number of experiments with one or more shared birthday: 43892, percentage: 87.78
Num people: 40, number of experiments with one or more shared birthday: 44423, percentage: 88.85
Num people: 41, number of experiments with one or more shared birthday: 45083, percentage: 90.17
Num people: 42, number of experiments with one or more shared birthday: 45615, percentage: 91.23
Num people: 43, number of experiments with one or more shared birthday: 46110, percentage: 92.22
Num people: 44, number of experiments with one or more shared birthday: 46779, percentage: 93.56
Num people: 45, number of experiments with one or more shared birthday: 47038, percentage: 94.08
Num people: 46, number of experiments with one or more shared birthday: 47425, percentage: 94.85
Num people: 47, number of experiments with one or more shared birthday: 47752, percentage: 95.50
Num people: 48, number of experiments with one or more shared birthday: 48084, percentage: 96.17
Num people: 49, number of experiments with one or more shared birthday: 48282, percentage: 96.56
Num people: 50, number of experiments with one or more shared birthday: 48481, percentage: 96.96
Num people: 51, number of experiments with one or more shared birthday: 48740, percentage: 97.48
Num people: 52, number of experiments with one or more shared birthday: 48929, percentage: 97.86
Num people: 53, number of experiments with one or more shared birthday: 49076, percentage: 98.15
Num people: 54, number of experiments with one or more shared birthday: 49217, percentage: 98.43
Num people: 55, number of experiments with one or more shared birthday: 49294, percentage: 98.59
Num people: 56, number of experiments with one or more shared birthday: 49447, percentage: 98.89
Num people: 57, number of experiments with one or more shared birthday: 49479, percentage: 98.96
Num people: 58, number of experiments with one or more shared birthday: 49590, percentage: 99.18
Num people: 59, number of experiments with one or more shared birthday: 49660, percentage: 99.32
Num people: 60, number of experiments with one or more shared birthday: 49714, percentage: 99.43
Num people: 61, number of experiments with one or more shared birthday: 49755, percentage: 99.51
Num people: 62, number of experiments with one or more shared birthday: 49802, percentage: 99.60
Num people: 63, number of experiments with one or more shared birthday: 49814, percentage: 99.63
Num people: 64, number of experiments with one or more shared birthday: 49850, percentage: 99.70
Num people: 65, number of experiments with one or more shared birthday: 49876, percentage: 99.75
Num people: 66, number of experiments with one or more shared birthday: 49924, percentage: 99.85
Num people: 67, number of experiments with one or more shared birthday: 49924, percentage: 99.85
Num people: 68, number of experiments with one or more shared birthday: 49941, percentage: 99.88
Num people: 69, number of experiments with one or more shared birthday: 49949, percentage: 99.90
Num people: 70, number of experiments with one or more shared birthday: 49943, percentage: 99.89
Num people: 71, number of experiments with one or more shared birthday: 49977, percentage: 99.95
Num people: 72, number of experiments with one or more shared birthday: 49966, percentage: 99.93
Num people: 73, number of experiments with one or more shared birthday: 49979, percentage: 99.96
Num people: 74, number of experiments with one or more shared birthday: 49978, percentage: 99.96
Num people: 75, number of experiments with one or more shared birthday: 49981, percentage: 99.96
Num people: 76, number of experiments with one or more shared birthday: 49993, percentage: 99.99
Num people: 77, number of experiments with one or more shared birthday: 49987, percentage: 99.97
Num people: 78, number of experiments with one or more shared birthday: 49996, percentage: 99.99
Num people: 79, number of experiments with one or more shared birthday: 49995, percentage: 99.99
Num people: 80, number of experiments with one or more shared birthday: 49996, percentage: 99.99
Num people: 81, number of experiments with one or more shared birthday: 49996, percentage: 99.99
Num people: 82, number of experiments with one or more shared birthday: 49998, percentage: 100.00
Num people: 83, number of experiments with one or more shared birthday: 49999, percentage: 100.00
Num people: 84, number of experiments with one or more shared birthday: 49996, percentage: 99.99
Num people: 85, number of experiments with one or more shared birthday: 49998, percentage: 100.00
Num people: 86, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 87, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 88, number of experiments with one or more shared birthday: 49998, percentage: 100.00
Num people: 89, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 90, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 91, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 92, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 93, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 94, number of experiments with one or more shared birthday: 49999, percentage: 100.00
Num people: 95, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 96, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 97, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 98, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 99, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 100, number of experiments with one or more shared birthday: 50000, percentage: 100.00
 */

public class CodeCampTester {

	public static void main(String[] args) {
		final String newline = System.getProperty("line.separator");

		// test 1, hamming distance
		int[] h1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] h2 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int expected = 10;
		int actual = CodeCamp.hammingDistance(h1, h2);
		System.out.println("Test 1 hamming distance: expected value: " + expected + ", actual value: " + actual);
		if (expected == actual) {
			System.out.println(" passed test 1, hamming distance");
		} else {
			System.out.println(" ***** FAILED ***** test 1, hamming distance");
		}

		// test 2, hamming distance
		h1 = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		h2 = new int[] { 1, 3, 2, 4, 5, 6, 8, 7, 9, 10 };
		expected = 4;
		actual = CodeCamp.hammingDistance(h1, h2);
		System.out.println(
				newline + "Test 2 hamming distance: expected value: " + expected + ", actual value: " + actual);
		if (expected == actual) {
			System.out.println(" passed test 2, hamming distance");
		} else {
			System.out.println(" ***** FAILED ***** test 2, hamming distance");
		}

		// test 3, isPermutation
		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] b = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		boolean expectedBool = true;
		boolean actualBool = CodeCamp.isPermutation(a, b);
		System.out.println(
				newline + "Test 3 isPermutation: expected value: " + expectedBool + ", actual value: " + actualBool);
		if (expectedBool == actualBool) {
			System.out.println(" passed test 3, isPermutation");
		} else {
			System.out.println(" ***** FAILED ***** test 3, isPermutation");
		}

		// test 4, is Permutation
		int[] c = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] d = { 55, 0, 0, 0 };
		expectedBool = false;
		actualBool = CodeCamp.isPermutation(c, d);
		System.out.println(
				newline + "Test 4 isPermutation: expected value: " + expectedBool + ", actual value: " + actualBool);
		if (expectedBool == actualBool) {
			System.out.println(" passed test 4, isPermutation");
		} else {
			System.out.println(" ***** FAILED ***** test 4, isPermutation");
		}

		// test 5, mostVowels
		String[] arrayOfStrings = { "aaaaa", "uuuuu", "iiiiii" };
		int expectedResult = 2;
		int actualResult = CodeCamp.mostVowels(arrayOfStrings);
		System.out.println(
				newline + "Test 5 mostVowels: expected result: " + expectedResult + ", actual result: " + actualResult);
		if (actualResult == expectedResult) {
			System.out.println("passed test 5, mostVowels");
		} else {
			System.out.println("***** FAILED ***** test 5, mostVowels");
		}

		// test 6, mostVowels
		arrayOfStrings = new String[] { "aaaaa", "ooooo", "uuuuu" };
		expectedResult = 0;
		actualResult = CodeCamp.mostVowels(arrayOfStrings);
		System.out.println(
				newline + "Test 6 mostVowels: expected result: " + expectedResult + ", actual result: " + actualResult);
		if (actualResult == expectedResult) {
			System.out.println("passed test 6, mostVowels");
		} else {
			System.out.println("***** FAILED ***** test 6, mostVowels");
		}

		// test 7, mostVowels
		arrayOfStrings = new String[] { null, null, "aaaaa" };
		expectedResult = 2;
		actualResult = CodeCamp.mostVowels(arrayOfStrings);
		System.out.println(
				newline + "Test 7 mostVowels: expected result: " + expectedResult + ", actual result: " + actualResult);
		if (actualResult == expectedResult) {
			System.out.println("passed test 7, mostVowels");
		} else {
			System.out.println("***** FAILED ***** test 7, mostVowels");
		}

		// test 8, sharedBirthdays, simple test
		int pairs = CodeCamp.sharedBirthdays(365, 365);
		System.out.println(newline + "Test 8 shared birthdays: expected: 0, actual: " + pairs);
		int expectedShared = 0;
		if (pairs > expectedShared) {
			System.out.println("passed test 8, shared birthdays");
		} else {
			System.out.println("***** FAILED ***** test 8, shared birthdays");
		}

		// test 9, sharedBirthdays, simple test
		pairs = CodeCamp.sharedBirthdays(100, 365);
		System.out.println(newline + "Test 9 shared birthdays: expected: " + "a value of 1 or more, actual: " + pairs);
		if (pairs > 0) {
			System.out.println("passed test 9, shared birthdays");
		} else {
			System.out.println("***** FAILED ***** test 9, shared birthdays");
		}

		// test 10, queensAreASafe
		char[][] board = { { '.', '.', '.' }, { 'q', '.', '.' }, { '.', '.', '.' } };

		expectedBool = true;
		actualBool = CodeCamp.queensAreSafe(board);
		System.out.println(
				newline + "Test 10 queensAreSafe: expected value: " + expectedBool + ", actual value: " + actualBool);
		if (expectedBool == actualBool) {
			System.out.println(" passed test 10, queensAreSafe");
		} else {
			System.out.println(" ***** FAILED ***** test 10, queensAreSafe");
		}

		// test 11, queensAreASafe
		board = new char[][] { { '.', '.', 'q', 'q' }, { '.', 'q', '.', '.' }, { '.', '.', '.', '.' },
				{ 'q', '.', 'q', '.' } };
		expectedBool = false;
		actualBool = CodeCamp.queensAreSafe(board);
		System.out.println(
				newline + "Test 11 queensAreSafe: expected value: " + expectedBool + ", actual value: " + actualBool);
		if (expectedBool == actualBool) {
			System.out.println(" passed test 11, queensAreSafe");
		} else {
			System.out.println(" ***** FAILED ***** test 11, queensAreSafe");
		}

		// test 12, getValueOfMostValuablePlot
		int[][] city = { { 1, 2, -1, -4, -20 }, { -8, -3, 4, 2, 1 }, { 3, 8, 10, 1, 3 }, { -4, -1, 1, 7, -6 }, };

		expected = 29;
		actual = CodeCamp.getValueOfMostValuablePlot(city);
		System.out.println(newline + "Test 12 getValueOfMostValuablePlot: expected value: " + expected
				+ ", actual value: " + actual);
		if (expected == actual) {
			System.out.println(" passed test 12, getValueOfMostValuablePlot");
		} else {
			System.out.println(" ***** FAILED ***** test 12, getValueOfMostValuablePlot");
		}

		// test 13, getValueOfMostValuablePlot
		city[0][0] = 6;
		expected = 29;
		actual = CodeCamp.getValueOfMostValuablePlot(city);
		System.out.println(newline + "Test 13 getValueOfMostValuablePlot: expected value: " + expected
				+ ", actual value: " + actual);
		if (expected == actual) {
			System.out.println(" passed test 13, getValueOfMostValuablePlot");
		} else {
			System.out.println(" ***** FAILED ***** test 13, getValueOfMostValuablePlot");
		}

	} // end of main method

	// pre: list != null
	private static int[] intListToArray(List<Integer> list) {
		if (list == null) {
			throw new IllegalArgumentException("list parameter may not be null.");
		}
		int[] result = new int[list.size()];
		int arrayIndex = 0;
		for (int x : list) {
			result[arrayIndex++] = x;
		}
		return result;
	}

}
