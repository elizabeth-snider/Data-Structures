import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID: eys275
 *  email address: elizabethsnider2011@gmail.com
 *  Grader name: Henry Liu
 *  Number of slip days I am using: 0
 */



/* CS314 Students. Put your experiment results and
 * answers to questions here.
 * 
 * Total and average time to add two matrices based on:
 * 1000 repetitions: 1.90 seconds, 0.0017 seconds
 * 2000 repetitions: 6.02 seconds, 0.0061 seconds
 * 4000 repetitions: 22.84 seconds, 0.0223 seconds
 * 
 * Total and average time to multiply two matrices based on:
 * 200 repetitions: 1.15 seconds, 0.0115 seconds
 * 400 repetitions: 10.45 seconds, 0.1045 seconds
 * 800 repetitions: 156.02 seconds, 1.56 seconds
 * 
 * Questions:
 * 1. I would expect around 90 seconds
 * 2. O(N^2); my data supports this.
 * 3. I would expect around 1600 seconds
 * 4. O(N^3); my data roughly supports this.
 * 5. Around 15000x15000 will cause the program to run out of heap memory.
 * This means my program is allocated around 900 megabytes
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

	//experiment one
	/*public static void experiments() {
		int size = 1000;
		int reps = 1000;
		double total = 0;
		MathMatrix matrixOne = new MathMatrix(size, size, 0);
		MathMatrix matrixTwo = new MathMatrix(size, size, 0);
		Random r = new Random();
		Stopwatch s = new Stopwatch();
		
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				matrixOne.change(row,  col, r.nextInt());
				matrixTwo.change(row,  col, r.nextInt());
			}
		}
		
		for(int length = 0; length < reps; length++) {		
			s.start();	
			matrixOne.add(matrixTwo);
			s.stop();
			total += s.time();
		}
		
		System.out.println(total);
		System.out.println(total/reps);
		
		reps = 100;
		
		for(int length = 0; length < reps; length++) {		
			s.start();	
			matrixOne.multiply(matrixTwo);
			s.stop();
			total += s.time();
		}
		
		System.out.println(total);
		System.out.println(total/reps);
	}*/
	
	
    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int[][] data1 = { {1, 2, 3},
                {4, 5, 6}};
        int[][] data2 = { {7, 8, 9},
                {10, 11, 12}};
        int[][] data3  = {{1, 1, 1}, {1, 1, 1}};
        int[][] e1;

        //test 1, specify size and values constructor
        MathMatrix mat1 = new MathMatrix(5, 5, 0);
        e1 = new int[][] {{0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}};
        printTestResult( get2DArray(mat1), e1, 1, "Constructor with size and initial val specified.");
        
        //test 2, specify size and values constructor
        MathMatrix mat20 = new MathMatrix(2, 2, 2);
        e1 = new int[][] {{2, 2}, {2, 2}};
        printTestResult( get2DArray(mat20), e1, 2, "Constructor with size and initial val specified.");

        //tests 3 and 4, int[][] constructor, deep copy
        mat1 = new MathMatrix( data1 );
        data1[0][0] = 0;
        // alter data1. mat1 should be unchanged if deep copy made
        e1 = new int[][] { {0, 2, 3}, {4, 5, 6} };
        printTestResult( data1, e1, 3, "constructor with one parameter of type int[][]. Testing deep copy made.");
        // data1 altered. mat1 should be unchanged if deep copy made
        e1 = new int[][] { {1, 2, 3}, {4, 5, 6} };
        printTestResult( get2DArray(mat1), e1, 4, "constructor with one parameter of type int[][]. Testing deep copy made.");

        //tests 5 - 7, addition
        data1[0][0] = 1;
        mat1 = new MathMatrix(data1);
        MathMatrix mat2 = new MathMatrix(data2);
        MathMatrix mat3 = mat1.add(mat2);
        e1 = new int[][] { {1, 2, 3}, {4, 5, 6} };
        printTestResult( get2DArray(mat1), e1, 5, "add method. Testing mat1 unchanged.");
        e1 = new int[][] { {7, 8, 9}, {10, 11, 12} };
        printTestResult( get2DArray(mat2), e1, 6, "add method. Testing mat2 unchanged.");
        e1 = new int[][] { {8, 10, 12}, {14, 16, 18} };
        printTestResult( get2DArray(mat3), e1, 7, "add method. Testing mat3 correct result.");

        //test 8, multiplication
        data2 = new int[][] { {1, 2}, {3, 4}, {5, 6} };
        mat2 = new MathMatrix(data2);
        mat1 = new MathMatrix(data1);
        mat3 = mat2.multiply(mat1);
        e1 = new int[][] { {9, 12, 15}, {19, 26, 33}, {29, 40, 51} };
        printTestResult( get2DArray(mat3), e1, 8, "multiply method");
        
        //test 9, multiplication
        data2 = new int[][] { {1, 2}, {3, 4}, {5, 6} };
        mat2 = new MathMatrix(data2);
        mat1 = new MathMatrix(data3);
        mat3 = mat2.multiply(mat1);
        e1 = new int[][] { {3, 3, 3}, {7, 7, 7}, {11, 11, 11} };
        printTestResult( get2DArray(mat3), e1, 9, "multiply method");

        //test 10, toString()
        data1 = new int[][] {{10000, 1000, 100, 10, 1},
            {1, 10, 100, 1000, 10000},
            {-1, 10, -100, 1000, -10000}};
            mat1 = new MathMatrix(data1);
            String expected = "|  10000   1000    100     10      1|\n|      1     10    100   1000  10000|\n|     -1     10"
            		+ "   -100   1000 -10000|\n";
            if (mat1.toString().equals( expected )) {
                System.out.println("passed test 10, oString method.");
            } else {
                System.out.println("failed test 10, toString method.");
            }
            
          //test 11, toString()
            data1 = new int[][] {{0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}};
                mat1 = new MathMatrix(data1);
                String expected2 = "| 0 0 0|\n| 0 0 0|\n| 0 0 0|\n";
                if (mat1.toString().equals( expected2 )) {
                    System.out.println("passed test 11, toString method.");
                } else {
                    System.out.println("failed test 11, toString method.");
                }

            //test 12, upperTriangular
            data1 = new int[][] {{1, 1, 1, 0}, {0, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 0, 1}};
            mat1 = new MathMatrix(data1);
            if (mat1.isUpperTriangular()) {
                System.out.println("Passed test 12, upperTriangular method.");
            } else {
                System.out.println("Failed test 12, upperTriangular method.");
            }

            //test 13, upperTriangular
            data1 = new int[][] {{1, 0, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
            mat1 = new MathMatrix(data1);
            if (!mat1.isUpperTriangular()) {
                System.out.println("Passed test 13, upperTriangular method.");
            } else {
                System.out.println("Failed test 13, upperTriangular method.");   
            }

            //test 14, getVal
            mat1 = new MathMatrix(data1);
            if (mat1.getVal(0,0) == 1) {
                System.out.println("Passed test 13, getVal method.");
            } else {
                System.out.println("Failed test 13, getVal method.");   
            }
            
          //test 15, getVal
            data1 = new int[][] {{1, 2, 3}, {4, 5, 6}};
            mat1 = new MathMatrix(data1);
            if (mat1.getVal(1, 2) == 6) {
                System.out.println("Passed test 13, getVal method.");
            } else {
                System.out.println("Failed test 13, getVal method.");   
            }
            
          //tests 16-18, subtraction
            data1 = new int[][] {{1, 2, 3}, {4, 5, 6}};
            data2 = new int[][] {{7, 8, 9}, {10, 11, 12}};
            mat1 = new MathMatrix(data1);
            MathMatrix mat4 = new MathMatrix(data2);
            MathMatrix mat5 = mat1.subtract(mat4);
            e1 = new int[][] { {1, 2, 3}, {4, 5, 6} };
            printTestResult( get2DArray(mat1), e1, 16, "subtract method. Testing mat1 unchanged.");
            e1 = new int[][] { {7, 8, 9}, {10, 11, 12} };
            printTestResult( get2DArray(mat4), e1, 17, "subtract method. Testing mat2 unchanged.");
            e1 = new int[][] { {-6, -6, -6}, {-6, -6, -6} };
            printTestResult( get2DArray(mat5), e1, 18, "subtract method. Testing mat3 correct result.");
            
            //test 19, getScaledMatrix
            data1 = new int[][] {{1, 2, 3}, {4, 5, 6}};
            mat1 = new MathMatrix(data1);
            MathMatrix mat6 = mat1.getScaledMatrix(2);
            e1 = new int[][] {{2, 4, 6}, {8, 10, 12}};
            printTestResult( get2DArray(mat6), e1, 19, "getScaledMatrix method.");      
            
            //test 20, getScaledMatrix
            data1 = new int[][] {{1, 2, 3}, {4, 5, 6}};
            mat1 = new MathMatrix(data1);
            MathMatrix mat7 = mat1.getScaledMatrix(0);
            e1 = new int[][] {{0, 0, 0}, {0, 0, 0}};
            printTestResult( get2DArray(mat7), e1, 20, "getScaledMatrix method."); 
            
            //test 21, getTranspose
            data1 = new int[][] {{1, 2, 3}, {4, 5, 6}};
            mat1 = new MathMatrix(data1);
            MathMatrix mat8 = mat1.getTranspose();
            e1 = new int[][] {{1, 4}, {2, 5}, {3, 6}};
            printTestResult( get2DArray(mat8), e1, 21, "getTranspose method."); 
            
          //test 22, getTranspose
            data1 = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            mat1 = new MathMatrix(data1);
            MathMatrix mat9 = mat1.getTranspose();
            e1 = new int[][] {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
            printTestResult( get2DArray(mat9), e1, 22, "getTranspose method."); 
            
          //test 23, equals
            data1 = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            MathMatrix e2 = new MathMatrix(data1);
            mat1 = new MathMatrix(data1);
            if(mat1.equals(e2)) {
                System.out.println("Passed test 23, equals method.");
            } else {
                System.out.println("Failed test 23, equals method.");
            }
            
          //test 24, equals
            data1 = new int[][] {{1, 2}, {3, 4}};
            data2 = new int[][] {{1, 2}, {3, 4}};
            mat1 = new MathMatrix(data1);
            MathMatrix e3 = new MathMatrix(data2);
            if(mat1.equals(e3)) {
                System.out.println("Passed test 24, equals method.");
            } else {
                System.out.println("Failed test 24, equals method.");
            }
           
    }

    // method that sums elements of mat, may overflow int!
    // pre: mat != null
    private static int sumVals(MathMatrix mat) {
        if (mat == null) {
            throw new IllegalArgumentException("mat may not be null");
        } 
        int result = 0;
        final int ROWS =  mat.getNumRows();
        final int COLS = mat.getNumColumns();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                result += mat.getVal(r, c); // likely to overflow, but can still do simple check
            }
        }
        return result;
    }

    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    public static MathMatrix createMat(Random randNumGen, int rows,
            int cols, final int LIMIT) {

        if (randNumGen == null) {
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        } else if(rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("rows and columns must be greater than 0. " +
                    "rows: " + rows + ", cols: " + cols);
        }

        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
            }
        }

        return new MathMatrix(temp);
    }

    private static void printTestResult(int[][] data1, int[][] data2, int testNum, String testingWhat) {
        System.out.print("Test number " + testNum + " tests the " + testingWhat +". The test ");
        String result = equals(data1, data2) ? "passed" : "failed";
        System.out.println(result);
    }

    // pre: m != null, m is at least 1 by 1 in size
    // return a 2d array of ints the same size as m and with 
    // the same elements
    private static int[][] get2DArray(MathMatrix m) {
        //check precondition
        if  ((m == null) || (m.getNumRows() == 0) 
                || (m.getNumColumns() == 0)) {
            throw new IllegalArgumentException("Violation of precondition: get2DArray");
        }

        int[][] result = new int[m.getNumRows()][m.getNumColumns()];
        for(int r = 0; r < result.length; r++) {
            for(int c = 0; c < result[0].length; c++) {
                result[r][c] = m.getVal(r,c);
            }
        }
        return result;
    }

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1 matrices
    //      data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    //      the same
    private static boolean equals(int[][] data1, int[][] data2) {
        //check precondition
        if((data1 == null) || (data1.length == 0) 
                || (data1[0].length == 0) || !rectangularMatrix(data1)
                ||  (data2 == null) || (data2.length == 0)
                || (data2[0].length == 0) || !rectangularMatrix(data2)) {
            throw new IllegalArgumentException( "Violation of precondition: equals check on 2d arrays of ints");
        }
        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while (result && row < data1.length) {
            int col = 0;
            while (result && col < data1[0].length) {
                result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }


    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix( int[][] mat ) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null" 
                    + " and must be at least 1 by 1");
        }
        return MathMatrix.rectangularMatrix(mat);
    }
}

