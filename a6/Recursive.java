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

//imports

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Various recursive methods to be implemented. Given shell file for CS314
 * assignment.
 */
public class Recursive {

	public static final int DOUBLE = 2;
	public static final int THREES = 3;
	public static final String NEGATIVE = "-";
	public static final int[] x = { -1, 0, 1, 0 };
	public static final int[] y = { 0, 1, 0, -1 };

	/**
	 * Problem 1: convert a base 10 int to binary recursively. <br>
	 * pre: n != Integer.MIN_VALUE<br>
	 * <br>
	 * post: Returns a String that represents N in binary. All chars in returned
	 * String are '1's or '0's. Most significant digit is at position 0
	 * 
	 * @param n the base 10 int to convert to base 2
	 * @return a String that is a binary representation of the parameter n
	 */
	public static String getBinary(int n) {
		if (n == Integer.MIN_VALUE) {
			throw new IllegalArgumentException(
					"Failed precondition: " + "getBinary. n cannot equal " 
			+ "Integer.MIN_VALUE. n: " + n);
		}

		StringBuilder result = new StringBuilder();

		// adds a negative sign if n is negative
		if (n < 0) {
			result.append(NEGATIVE);
			n = Math.abs(n);
		}

		// base case: returns n if n is 0 or 1
		// otherwise recursively adds 1 or 2 to the String based on if n has a
		// remainder when divided by 2
		if (n < DOUBLE) {
			return result.append(n).toString();
		} else {
			return result.append(getBinary(n / DOUBLE))
					.append(n % DOUBLE).toString();
		}

	}

	/**
	 * Problem 2: reverse a String recursively.<br>
	 * pre: stringToRev != null<br>
	 * post: returns a String that is the reverse of stringToRev
	 * 
	 * @param stringToRev the String to reverse.
	 * @return a String with the characters in stringToRev in reverse order.
	 */
	public static String revString(String stringToRev) {
		if (stringToRev == null) {
			throw new IllegalArgumentException("Failed precondition: "
		+ "revString. parameter may not be null.");
		}

		// base case: returns the String if it is one character long or empty
		if (stringToRev.length() <= 1) {
			return stringToRev;
		}

		// recursively rearranges the String until the last char becomes the first
		return revString(stringToRev.substring(1)) + stringToRev.charAt(0);
	}

	/**
	 * Problem 3: Returns the number of elements in data that are followed directly
	 * by value that is double that element. pre: data != null post: return the
	 * number of elements in data that are followed immediately by double the value
	 * 
	 * @param data The array to search.
	 * @return The number of elements in data that are followed immediately by a
	 *         value that is double the element.
	 */
	public static int nextIsDouble(int[] data) {
		if (data == null) {
			throw new IllegalArgumentException("Failed precondition: " 
		+ "revString. parameter may not be null.");
		}

		int count = 0;
		int index = 0;

		// base case: if array is empty, return 0
		if (data.length == 0) {
			return 0;
		} else {
			// call on helper method that indexes through array
			// returns count of integers that are followed by their doubled value
			count = checkDouble(data, index, count);
		}

		return count;
	}

	// helper method that recursively checks through the array of data
	// by incrementing the index
	// @param passes array of data, index, and count
	public static int checkDouble(int[] data, int index, int count) {
		if (index + 1 != data.length) {
			if (data[index + 1] == data[index] * DOUBLE) {
				count++;
				return checkDouble(data, index + 1, count);
			} else {
				return checkDouble(data, index + 1, count);
			}
		}

		return count;
	}

	// CS314 students, add your nextIsDouble helper method here

	/**
	 * Problem 4: Find all combinations of mnemonics for the given number.<br>
	 * pre: number != null, number.length() > 0, all characters in number are
	 * digits<br>
	 * post: see tips section of assignment handout
	 * 
	 * @param number The number to find mnemonics for
	 * @return An ArrayList<String> with all possible mnemonics for the given number
	 */
	public static ArrayList<String> listMnemonics(String number) {
		if (number == null || number.length() == 0 || !allDigits(number)) {
			throw new IllegalArgumentException("Failed precondition: " 
		+ "listMnemonics");
		}

		ArrayList<String> result = new ArrayList<>();
		// calls on recursive helper method that passes the result parameter
		// (which contains all possible mnemonics)
		recursiveMnemonics(result, "", number);
		return result;
	}

	/*
	 * Helper method for listMnemonics mnemonics stores completed mnemonics
	 * mneominicSoFar is a partial (possibly complete) mnemonic digitsLeft are the
	 * digits that have not been used from the original number.
	 */
	private static void recursiveMnemonics(ArrayList<String> mnemonics,
			String mnemonicSoFar, String digitsLeft) {
		// base case: when zero digits are left, add created mnemonics to the ArrayList
		if (digitsLeft.length() == 0) {
			mnemonics.add(mnemonicSoFar);
		} else {
			// get possible letters for each digit
			String letters = digitLetters(digitsLeft.charAt(0));
			// delete digit used from digitsLeft
			digitsLeft = digitsLeft.substring(1, digitsLeft.length());

			// recursively add letters to possible mnemonics
			for (int index = 0; index < letters.length(); index++) {
				mnemonicSoFar += letters.charAt(index);
				recursiveMnemonics(mnemonics, mnemonicSoFar, digitsLeft);
				mnemonicSoFar = mnemonicSoFar.substring(0, 
						mnemonicSoFar.length() - 1);
			}

		}

	}

	// used by method digitLetters
	private static final String[] letters = { "0", "1", "ABC", "DEF", "GHI",
			"JKL", "MNO", "PQRS", "TUV", "WXYZ" };

	/*
	 * helper method for recursiveMnemonics pre: ch is a digit '0' through '9' post:
	 * return the characters associated with this digit on a phone keypad
	 */
	private static String digitLetters(char ch) {
		if (ch < '0' || ch > '9') {
			throw new IllegalArgumentException("parameter " + 
		"ch must be a digit, 0 to 9. Given value = " + ch);
		}
		int index = ch - '0';
		return letters[index];
	}

	/*
	 * helper method for listMnemonics pre: s != null post: return true if every
	 * character in s is a digit ('0' through '9')
	 */
	private static boolean allDigits(String s) {
		if (s == null) {
			throw new IllegalArgumentException("Failed precondition: " 
		+ "allDigits. String s cannot be null.");
		}
		boolean allDigits = true;
		int i = 0;
		while (i < s.length() && allDigits) {
			allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
			i++;
		}
		return allDigits;
	}

	/**
	 * Problem 5: Draw a Sierpinski Carpet.
	 * 
	 * @param size  the size in pixels of the window
	 * @param limit the smallest size of a square in the carpet.
	 */
	public static void drawCarpet(int size, int limit) {
		DrawingPanel p = new DrawingPanel(size, size);
		Graphics g = p.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, size, size);
		g.setColor(Color.WHITE);
		drawSquares(g, size, limit, 0, 0);
	}

	/*
	 * Helper method for drawCarpet Draw the individual squares of the carpet.
	 * 
	 * @param g The Graphics object to use to fill rectangles
	 * 
	 * @param size the size of the current square
	 * 
	 * @param limit the smallest allowable size of squares
	 * 
	 * @param x the x coordinate of the upper left corner of the current square
	 * 
	 * @param y the y coordinate of the upper left corner of the current square
	 */
	private static void drawSquares(Graphics g, int size, int limit, 
			double x, double y) {
		// draws squares until they reach the limit size
		if (size > limit) {
			// draws the original square
			g.fillRect((int) x + (size / THREES), (int) y + (size / THREES), 
					size / THREES, size / THREES);

			// nested for loop for x and y and recursively calls the drawSquares method to
			// increment the size of the squares
			for (int i = 0; i < THREES; i++) {
				for (int j = 0; j < THREES; j++) {
					drawSquares(g, size / THREES, limit, 
							x + size / THREES * i, y + size / THREES * j);
				}
			}
		}
	}

	/**
	 * Problem 6: Determine if water at a given point on a map can flow off the map.
	 * <br>
	 * pre: map != null, map.length > 0, map is a rectangular matrix, 0 <= row <
	 * map.length, 0 <= col < map[0].length <br>
	 * post: return true if a drop of water starting at the location specified by
	 * row, column can reach the edge of the map, false otherwise.
	 * 
	 * @param map The elevations of a section of a map.
	 * @param row The starting row of a drop of water.
	 * @param col The starting column of a drop of water.
	 * @return return true if a drop of water starting at the location specified by
	 *         row, column can reach the edge of the map, false otherwise.
	 */
	public static boolean canFlowOffMap(int[][] map, int row, int col) {
		if (map == null || map.length == 0 || !isRectangular(map) 
				|| !inbounds(row, col, map)) {
			throw new IllegalArgumentException("Failed precondition: " 
				+ "canFlowOffMap");
		}

		boolean canFlowOff = false;
		// base case: the recursion runs until you reach the border of the map
		if (row == 0 || col == 0 || row == map.length - 1 || 
				col == map[0].length - 1) {
			canFlowOff = true;
		} else {
			for (int index = 0; index < x.length; index++) {
				// recursive step: calls method with updated index if next tile
				// has a lesser value than the current tile
				if (map[row][col] > map[row + x[index]][col + y[index]]) {
					canFlowOff = canFlowOffMap(map, row + x[index], 
							col + y[index]);
				}
			}
		}

		return canFlowOff;
	}

	/*
	 * helper method for canFlowOfMap - CS314 students you should not have to call
	 * this method, pre: mat != null,
	 */
	private static boolean inbounds(int r, int c, int[][] mat) {
		if (mat == null) {
			throw new IllegalArgumentException("Failed precondition: " 
		+ "inbounds. The 2d array mat may not be null.");
		}
		return r >= 0 && r < mat.length && mat[r] != null 
				&& c >= 0 && c < mat[r].length;
	}

	/*
	 * helper method for canFlowOfMap - CS314 students you should not have to call
	 * this method, pre: mat != null, mat.length > 0 post: return true if mat is
	 * rectangular
	 */
	private static boolean isRectangular(int[][] mat) {
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException("Failed precondition: " 
		+ "inbounds. The 2d array mat may not be null "
					+ "and must have at least 1 row.");
		}
		boolean correct = true;
		final int numCols = mat[0].length;
		int row = 0;
		while (correct && row < mat.length) {
			correct = (mat[row] != null) && (mat[row].length == numCols);
			row++;
		}
		return correct;
	}

	/**
	 * Problem 7: Find the minimum difference possible between teams based on
	 * ability scores. The number of teams may be greater than 2. The goal is to
	 * minimize the difference between the team with the maximum total ability and
	 * the team with the minimum total ability. <br>
	 * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams <br>
	 * post: return the minimum possible difference between the team with the
	 * maximum total ability and the team with the minimum total ability.
	 * 
	 * @param numTeams  the number of teams to form. Every team must have at least
	 *                  one member
	 * @param abilities the ability scores of the people to distribute
	 * @return return the minimum possible difference between the team with the
	 *         maximum total ability and the team with the minimum total ability.
	 *         The return value will be greater than or equal to 0.
	 */
	public static int minDifference(int numTeams, int[] abilities) {
		if (numTeams < 2 || abilities == null || abilities.length < numTeams) {
			throw new IllegalArgumentException("Preconditions violated.");
		}

		int[] numTeammates = new int[numTeams];
		int[] totals = new int[numTeams];
		int index = 0;
		// calls on helper method to assign people to teams
		return assignTeams(abilities, numTeammates, index, totals);
	}

	private static int assignTeams(int[] abilities, int[] numTeammates, 
			int index, int[] totals) {
		int minDiff = Integer.MAX_VALUE;

		// stop here if the index of different possible teammates reaches the end
		if (index == abilities.length) {
			// loop through different teams
			for (int i = 0; i < numTeammates.length; i++) {
				// if a team has 0 players, return minDiff
				if (numTeammates[i] == 0) {
					return minDiff;
				}
			}

			// since all teammates on a team
			// calculate that team combinations minDiff
			minDiff = diff(totals);
			return minDiff;
		}

		// loop throug each team
		for (int i = 0; i < numTeammates.length; i++) {
			// update how many teammates for specific team at that index
			// add the abilities score of that teammate to that team
			numTeammates[i]++;
			totals[i] += abilities[index];
			// recursive call to assign teams again to find new
			// teammate combinations with possible lesser scores
			int newMinDiff = assignTeams(abilities, numTeammates, 
					index + 1, totals);

			// update minDiff is new combinations has team with a
			// lesser minDiff
			if (newMinDiff < minDiff) {
				minDiff = newMinDiff;
			}

			// take out that teammate and take out their respective
			// abilities score
			// essentialy a reset
			numTeammates[i]--;
			totals[i] -= abilities[index];
		}

		return minDiff;
	}

	// method to loop through possible team and find max and minimum
	// abilities totals throughout the set of teams
	// returns the minimum possible difference
	private static int diff(int[] totals) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		// loop through array of totals
		// update max and min while iterating
		for (int index = 0; index < totals.length; index++) {
			if (totals[index] < min) {
				min = totals[index];
			}
			if (totals[index] > max) {
				max = totals[index];
			}
		}

		return max - min;
	}

	/**
	 * Problem 8: Maze solver. <br>
	 * pre: board != null <br>
	 * pre: board is a rectangular matrix <br>
	 * pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*' <br>
	 * pre: there is a single 'S' character present <br>
	 * post: rawMaze is not altered as a result of this method. Return 2 if it is
	 * possible to escape the maze after collecting all the coins. Return 1 if it is
	 * possible to escape the maze but without collecting all the coins. Return 0 if
	 * it is not possible to escape the maze. More details in the assignment
	 * handout.
	 * 
	 * @param rawMaze represents the maze we want to escape. rawMaze is not altered
	 *                as a result of this method.
	 * @return per the post condition
	 */
	public static int canEscapeMaze(char[][] rawMaze) {
		Maze.preconditions(rawMaze);
		Maze.fillMaze(rawMaze);

		// finds the starting position and total number of coins in the maze
		int[] start = Maze.startingPosition(rawMaze);
		int totalCoins = Maze.numCoins(rawMaze);
		int coins = 0;
		int result = 0;

		// runs other methods to solve maze
		result = Maze.move(rawMaze, start, coins, totalCoins, rawMaze);

		return result;
	}

	// nested class for maze
	private static class Maze {

		// constants for char symbols and results
		private static final char START = 'S';
		private static final char EXIT = 'E';
		private static final char COIN = '$';
		private static final char GREEN = 'G';
		private static final char YELLOW = 'Y';
		private static final char BLOCK = '*';
		private static final int PERFECT_EXIT = 2;
		private static final int IMPERFECT_EXIT = 1;
		private static final int CANNOT_EXIT = 0;

		// copy of rawMaze
		private static char[][] maze;

		// creates of copy of rawMaze that can be updated
		// @param passes 2d array of rawMaze
		private static void fillMaze(char[][] rawMaze) {
			maze = new char[rawMaze.length][rawMaze[0].length];

			for (int i = 0; i < rawMaze.length; i++) {
				for (int j = 0; j < rawMaze[0].length; j++) {
					maze[i][j] = rawMaze[i][j];
				}
			}
		}

		// checks for all preconditions
		// @param passes 2d maze array
		private static void preconditions(char[][] maze) {
			// checks if board is null
			if (maze == null) {
				throw new IllegalArgumentException("board cannot be null");
			}

			// checks if maze is rectangular
			boolean correct = true;
			int numCols = maze[0].length;
			int row = 0;
			while (correct && row < maze.length) {
				correct = (maze[row] != null) && (maze[row].length == numCols);
				row++;
			}
			if (!correct) {
				throw new IllegalArgumentException("maze not rectangular");
			}

			// checks if maze contains invalid chars
			int count = 0;
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[0].length; j++) {
					if (maze[i][j] != START && maze[i][j] != EXIT && 
							maze[i][j] != COIN && maze[i][j] != GREEN
							&& maze[i][j] != YELLOW && maze[i][j] != BLOCK) {
						throw new IllegalArgumentException
						("maze contains invalid char(s)");
					}

					if (maze[i][j] == START) {
						count++;
					}
				}
			}

			// checks if maze only has one starting position
			if (count != 1) {
				throw new IllegalArgumentException("maze must have one start");
			}
		}

		// method that moves the current position recursively to find the exit (if
		// possible)
		// also keeps track of coins collected and the best possible path to the exit
		// @param passes the 2d maze array, current position, number of coins collected,
		// total number of coins in the maze, and the raw maze
		private static int move(char[][] maze, int[] current, int coins, 
				int totalCoins, char[][] rawMaze) {
			int result = CANNOT_EXIT;

			// returns 0 if the maze does not contain an exit
			// calls on helper method to find if there's an exit
			if (!Maze.exitExists(rawMaze)) {
				return CANNOT_EXIT;
			}

			// equals result to 2 if exit is found and all coins are collected
			// if not all coins are collected equals result to 1
			if (maze[current[0]][current[1]] == EXIT && coins == totalCoins) {
				result = PERFECT_EXIT;
			} else if (maze[current[0]][current[1]] == 
					EXIT && coins != totalCoins) {
				result = IMPERFECT_EXIT;
			}

			// saves current maze tile and number of coins
			char saveMaze = maze[current[0]][current[1]];
			int saveCoins = coins;

			// goes through maze using the four possible directions
			for (int index = 0; index < x.length; index++) {
				// adds 1 to coin if current position is on coin
				if (maze[current[0]][current[1]] == COIN) {
					coins++;
				}

				// updated position that we are looking at
				int[] next = { current[0] + x[index], current[1] + y[index] };

				// moves position if next tile is not out of bounds or a asterisk
				if (inBounds(maze, next) && maze[next[0]][next[1]] != BLOCK) {
					// updates current tile accordingly
					changeCells(maze, current);

					// recursively calls on move method with updated position
					// saves best result from each path (0, 1, 2)
					int newResult = move(maze, next, coins, 
							totalCoins, rawMaze);
					if (newResult > result) {
						result = newResult;
					}

					// saves time by returning immediately if result is 2
					if (result == PERFECT_EXIT)
						return result;

				}
				// when reaching an exit or dead end, backtracks by returning the tiles and
				// number of coins collected to their previous state and r
				maze[current[0]][current[1]] = saveMaze;
				coins = saveCoins;
			}

			return result;
		}

		// returns boolean for if an exit exists in the maze
		// @param passes the maze 2d array
		private static boolean exitExists(char[][] maze) {
			for (int row = 0; row < maze.length; row++) {
				for (int col = 0; col < maze[0].length; col++) {
					if (maze[row][col] == EXIT) {
						return true;
					}
				}
			}

			return false;
		}

		// changes the current position's cell based on its symbol
		// @param passes the 2d maze array and current position
		private static void changeCells(char[][] maze, int[] current) {
			if (maze[current[0]][current[1]] == START) {
				maze[current[0]][current[1]] = GREEN;
			} else if (maze[current[0]][current[1]] == GREEN) {
				maze[current[0]][current[1]] = YELLOW;
			} else if (maze[current[0]][current[1]] == YELLOW) {
				maze[current[0]][current[1]] = BLOCK;
			} else if (maze[current[0]][current[1]] == COIN) {
				maze[current[0]][current[1]] = YELLOW;
			}
		}

		// checks if current position is in the bounds of the maze
		// @param passes the 2d maze array and current position
		private static boolean inBounds(char[][] maze, int[] current) {
			if (current[0] < maze.length && current[0] >= 0 && current[1]
					< maze[0].length && current[1] >= 0) {
				return true;
			}

			return false;
		}

		// finds the starting position of the maze
		// @param passes the maze 2d array
		private static int[] startingPosition(char[][] maze) {
			int[] start = new int[DOUBLE];

			for (int row = 0; row < maze.length; row++) {
				for (int col = 0; col < maze[0].length; col++) {
					if (maze[row][col] == START) {
						start[0] = row;
						start[1] = col;
						return start;
					}
				}
			}

			return start;
		}

		// finds the total number of coins in the maze
		// @param passes the maze 2d array
		private static int numCoins(char[][] maze) {
			int count = 0;

			for (int row = 0; row < maze.length; row++) {
				for (int col = 0; col < maze[0].length; col++) {
					if (maze[row][col] == COIN) {
						count++;
					}
				}
			}

			return count;
		}
	}
}