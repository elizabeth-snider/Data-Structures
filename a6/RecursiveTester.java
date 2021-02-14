
/*  Student information for assignment:
 * 
 *  On our honor, Elizabeth Snider and John Henry Cruz,
 *  this programming assignment is our own work
 *  and we have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1: Elizabeth Snider
 *  UTEID: eys275
 *  email address: elizabethsnider2011@gmail.com
 *  Grader name: Henry Liu
 *  Section number: 50860
 *
 *  Student 2: John Henry Cruz
 *  UTEID: jec4968
 *  email address: johnhenrycruz@utexas.edu
 *
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Tester class for the methods in Recursive.java
 * 
 * @author scottm
 *
 */
public class RecursiveTester {

	// run the tests
	public static void main(String[] args) {
		studentTests();
	}

	private static void runMazeTest(String rawMaze, int rows, int expected, int num) {
		char[][] maze = makeMaze(rawMaze, rows);
		System.out.println("Can escape maze test number " + num);
		printMaze(maze);
		int actual = Recursive.canEscapeMaze(maze);
		System.out.println("Expected result: " + expected);
		System.out.println("Actual result:   " + actual);
		if (expected == actual) {
			System.out.println("passed test " + num);
		} else {
			System.out.println("FAILED TEST " + num);
		}
		System.out.println();
	}

	// print the given maze
	// pre: none
	private static void printMaze(char[][] maze) {
		if (maze == null) {
			System.out.println("NO MAZE GIVEN");
		} else {
			for (char[] row : maze) {
				for (char c : row) {
					System.out.print(c);
					System.out.print(" ");
				}
				System.out.println();
			}
		}
	}

	// generate a char[][] given the raw string
	// pre: rawMaze != null, rawMaze.length() % rows == 0
	private static char[][] makeMaze(String rawMaze, int rows) {
		if (rawMaze == null || rawMaze.length() % rows != 0) {
			throw new IllegalArgumentException(
					"Violation of precondition in makeMaze." + "Either raw data is null or left over values: ");
		}
		int cols = rawMaze.length() / rows;
		char[][] result = new char[rows][cols];
		int rawIndex = 0;
		for (int r = 0; r < result.length; r++) {
			for (int c = 0; c < result[0].length; c++) {
				result[r][c] = rawMaze.charAt(rawIndex);
				rawIndex++;
			}
		}
		return result;
	}

	private static void doOneFlowTest(int[][] world, int r, int c, boolean expected, int testNum) {
		System.out.println("Can Flow Off Map Test Number " + testNum);
		boolean actual = Recursive.canFlowOffMap(world, r, c);
		System.out.println("Start location = " + r + ", " + c);
		System.out.println("Expected result = " + expected + " actual result = " + actual);
		if (expected == actual) {
			System.out.println("passed test " + testNum + " can flow off map.");
		} else {
			System.out.println("FAILED TEST " + testNum + " can flow off map.");
		}
		System.out.println();
	}

	// pre: r != null
	// post: run student test
	private static void studentTests() {
		// CS314 students put your tests here
		// getBinary tests
		System.out.println();
		String actualBinary = "0";
		String expectedBinary = "";
		
		actualBinary = Recursive.getBinary(420);
		expectedBinary = "110100100";
		if (actualBinary.equals(expectedBinary))
			System.out.println("Test 1 passed. get binary");
		else
			System.out.println("Test 1 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);

		actualBinary = Recursive.getBinary(69);
		expectedBinary = "1000101";
		if (actualBinary.equals(expectedBinary))
			System.out.println("Test 2 passed. get binary");
		else
			System.out.println("Test 2 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);
		System.out.println();
		
		// revString tests
		String actualRev = "";
		String expectedRev = "";
		
		actualRev = Recursive.revString("Henry is my favorite TA of all time");
		expectedRev = "emit lla fo AT etirovaf ym si yrneH";
		if (actualRev.equals(expectedRev))
			System.out.println("Test 3 passed. reverse string.");
		else
			System.out.println("Test 3 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);

		actualRev = Recursive.revString("I want to bake Mike Scott a flourless chocolate cake");
		expectedRev = "ekac etalocohc sselruolf a ttocS ekiM ekab ot tnaw I";
		if (actualRev.equals(expectedRev))
			System.out.println("Test 4 passed. reverse string.");
		else
			System.out.println("Test 4 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);
		System.out.println();
		
		// nextIsDouble tests
		int[] numsForDouble = { };
		int actualDouble = 0;
		int expectedDouble = 0;

		numsForDouble = new int[] { 4, 8, 16 };
		actualDouble = Recursive.nextIsDouble(numsForDouble);
		expectedDouble = 2;
		if (actualDouble == expectedDouble)
			System.out.println("Test 5 passed. next is double.");
		else
			System.out.println(
					"Test 5 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);

		numsForDouble = new int[] { 0, 0 };
		actualDouble = Recursive.nextIsDouble(numsForDouble);
		expectedDouble = 1;
		if (actualDouble == expectedDouble)
			System.out.println("Test 6 passed. next is double.");
		else
			System.out.println(
					"Test 6 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);
		System.out.println();
		
		// listMnemonics tests
		ArrayList<String> mnemonics = Recursive.listMnemonics("1001010101010101100");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("1001010101010101100");
		if (mnemonics.equals(expected))
			System.out.println("Test 7 passed. Phone mnemonics.");
		else
			System.out.println("Test 7 failed. Phone mnemonics.");

		mnemonics = Recursive.listMnemonics("33");
		Collections.sort(mnemonics);
		expected.clear();
		expected.add("DD");
		expected.add("DE");
		expected.add("DF");
		expected.add("ED");
		expected.add("EE");
		expected.add("EF");
		expected.add("FE");
		expected.add("FD");
		expected.add("FF");
		Collections.sort(expected);
		if (mnemonics.equals(expected))
			System.out.println("Test 8 passed. Phone mnemonics.");
		else
			System.out.println("Test 8 failed. Phone mnemonics.");
		System.out.println();
		
		// canFlowOffMap tests
		int testNum = 9;
		int [][] world = new int[][] { { 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5 }, { 5, 5, 4, 4, 5, 5 },
			{ 5, 5, 5, 4, 5, 5 }, { 5, 5, 5, 3, 5, 5 }, { 5, 5, 5, 2, 1, 0 }, { 5, 5, 5, 5, 5, 5 } };

		doOneFlowTest(world, 3, 3, false, testNum++);
		doOneFlowTest(world, 4, 3, true, testNum++);
		
		// minDifference tests
		int[] abilities = { 1, 5, 3, 12, 5, 9, 7 };
		if (Recursive.minDifference(3, abilities) == 2)
			System.out.println("Test 11 passed. min difference.");
		else
			System.out.println("Test 11 failed. min difference.");

		if (Recursive.minDifference(5, abilities) == 6)
			System.out.println("Test 12 passed. min difference.");
		else
			System.out.println("Test 12 failed. min difference.");
		System.out.println();
		
		// canEscapeMaze tests
		int mazeTestNum = 13;
		runMazeTest("$GSGY", 1, 0, mazeTestNum++);
		runMazeTest("EGS$Y$", 1, 1, mazeTestNum++);
		
	}

}
