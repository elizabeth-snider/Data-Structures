import java.util.Arrays;

//MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
*
*  On my honor, <NAME>, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: eys275
*  email address: elizabethsnider2011@gmail.com
*  Unique section number: 50860
*  Number of slip days I am using: 0
*/

/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 */
public class MathMatrix {
    
    // instance variable
	private int[][] cells;
	
    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {
    	
    	//checks precondition
    	if(mat == null || mat.length <= 0 || mat[0].length <= 0 || !rectangularMatrix(mat)) {
    		throw new IllegalArgumentException("Violation of precondition: int[][] mat");
    	}
    	
    	//creates MathMatrix with cells equal to values in mat
    	int rows = mat.length;
    	int cols = mat[0].length;
        cells = new int[rows][cols];
        
        for(int rowLength = 0; rowLength < rows; rowLength++) {
        	for(int colLength = 0; colLength < cols; colLength++) {
        		cells[rowLength][colLength] = mat[rowLength][colLength];
        	}
        }
    }


    /**
     * create a MathMatrix of the specified size with all cells set to the intialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns. 
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal 
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {
        
    	//checks preconditions
    	if(numRows <= 0 || numCols <= 0) {
    		throw new IllegalArgumentException("Violation of precondition: numRows and numCols "
    				+ "are greater than 0.");
    	}
    	
    	//creates MathMatrix with all cells set to initialVal
    	cells = new int[numRows][numCols];
    	
    	for(int rowLength = 0; rowLength < numRows; rowLength++) {
        	for(int colLength = 0; colLength < numCols; colLength++) {
        		cells[rowLength][colLength] = initialVal;
        	}
        }
    	
    }

    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
    public int getNumRows() {
    	return cells.length;
    }


    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
    public int getNumColumns(){
        return cells[0].length;
    }


    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < getNumRows(), col  0 <= col < getNumColumns()
     * @param  row  0 <= row < getNumRows()
     * @param  col  0 <= col < getNumColumns()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
    	
    	//checks preconditions
    	if(row < 0 || row >= getNumRows() || col < 0 || col >= getNumColumns()) {
    		throw new IllegalArgumentException("Violation of precondition: row and col "
    				+ "are not within bounds.");
    	}
    	
        return cells[row][col];
    }


    /**
     * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
     * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned Matrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix add(MathMatrix rightHandSide){
    	
    	//checks preconditions
    	if(rightHandSide.getNumRows() != getNumRows() || rightHandSide.getNumColumns() 
    			!= getNumColumns()) {
    		throw new IllegalArgumentException("Violation of precondition: rigthHandSide "
    				+ "does not equal the correct lengths.");
    	}
    	
    	//adds the current MathMatrix and rightHandSide
    	MathMatrix result = new MathMatrix(cells.length, cells[0].length, 0);
    	MathMatrix cellsMatrix = new MathMatrix(cells);
    	
    	for(int row = 0; row < cells.length; row++) {
    		for(int col = 0; col < cells[0].length; col++) {
    			 result.change(row, col, cellsMatrix.getVal(row, col) + rightHandSide.getVal(row, col));
    		}
    	}
    	
        return result;
    }
    
    //replaces current index with passed value
    //@param row, column, and value to be replaced
    public void change(int row, int col, int value) {
    	cells[row][col] = value;
    }


    /**
     * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide from this MathMatrix.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned MathMatrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix subtract(MathMatrix rightHandSide){
    	
    	//checks preconditions
    	if(rightHandSide.getNumRows() != getNumRows() || rightHandSide.getNumColumns() 
    			!= getNumColumns()) {
    		throw new IllegalArgumentException("Violation of precondition: rigthHandSide "
    				+ "does not equal the correct lengths.");
    	}
    	
    	//subtracts current MathMatrix and rightHandSide
    	MathMatrix result = new MathMatrix(cells.length, cells[0].length, 0);
    	MathMatrix cellsMatrix = new MathMatrix(cells);
    	
    	for(int row = 0; row < cells.length; row++) {
    		for(int col = 0; col < cells[0].length; col++) {
    			 result.change(row, col, cellsMatrix.getVal(row, col) - rightHandSide.getVal(row, col));
    		}
    	}
    	
        return result;
    }


    /**
     * implements matrix multiplication, (this MathMatrix) * rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumColumns()
     * <br>post: This method should not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying this MathMatrix and rightHandSide.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned MathMatrix is equal to the number of columns in rightHandSide.
     */
    public MathMatrix multiply(MathMatrix rightHandSide){
    	
    	//checks preconditions
    	if(rightHandSide.getNumRows() != getNumColumns()) {
    		throw new IllegalArgumentException("Violation of precondition: rightHandSide number of "
    				+ "rows does not equal MathMatrix number of columns");
    	}
    	
    	//multiplies current MathMatrix and rightHandSide
    	MathMatrix result = new MathMatrix(cells.length, rightHandSide.getNumColumns(), 0);
    	MathMatrix cellsMatrix = new MathMatrix(cells);
    	
    	for(int row = 0; row < cells.length; row++) {
    		for(int col = 0; col < rightHandSide.getNumColumns(); col++) {
    			 for(int length = 0; length < cells[0].length; length++) {
    				 result.change(row, col, result.getVal(row, col) + 
    						 cellsMatrix.getVal(row,length) * rightHandSide.getVal(length, col));
    			 }
    		}
    	}
    	
        return result;
    } 


    /**
     * Create and return a new Matrix that is a copy
     * of this matrix, but with all values multiplied by a scale
     * value.
     * <br>pre: none
     * <br>post: returns a new Matrix with all elements in this matrix 
     * multiplied by factor. 
     * In other words after this method has been called 
     * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
     * for all valid r and c.
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     * values in the result multiplied by factor.
     */
    public MathMatrix getScaledMatrix(int factor) {
    	
    	//multiplies each index of the current MathMatrix by the passed factor
    	MathMatrix result = new MathMatrix(cells);
    	
    	for(int row = 0; row < cells.length; row++) {
    		for(int col = 0; col < cells[0].length; col++) {
    				 result.change(row, col, cells[row][col] * factor);
    		}
    	}
    	
        return result;
    }


    /**
     * accessor: get a transpose of this MathMatrix. 
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
    	
    	//creates a transpose of the current MathMatrix
    	MathMatrix result = new MathMatrix(cells[0].length, cells.length, 0);

    	for(int row = 0; row < cells[0].length; row++) {
    		for(int col = 0; col < cells.length; col++) {
    			 result.change(row, col, cells[col][row]);
    		}
    	}
    	
        return result;
    }


    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide){
        /* CS314 Students. The following is standard equals
         * method code. We will learn about in the coming weeks.
         */
        boolean result = false;
        if( rightHandSide != null && this.getClass() == rightHandSide.getClass()){
            // rightHandSide is a non null MathMatrix
            MathMatrix otherMatrix = (MathMatrix) rightHandSide;

           int rows = otherMatrix.getNumRows();
           int cols = otherMatrix.getNumColumns();
           
           if(rows == cells.length && cols == cells[0].length && values(otherMatrix)) {
        	   result = true;
           }
        }
        return result;
    }
    
    //returns true only if every value in the current MathMatrix is equal to the 
    //rightHandSide MathMatrix
    //@param passes rightHandSide as a Matrix
    public boolean values(MathMatrix otherMatrix) {
    	
    	MathMatrix cellsMatrix = new MathMatrix(cells);
    	
    	for(int row = 0; row < cells.length; row++) {
    		for(int col = 0; col < cells[0].length; col++) {
    			if(otherMatrix.getVal(row, col) != cellsMatrix.getVal(row, col)) {
    				return false;
    			}
    		}
    	}
    	
    	return true;
    }


    /**
     * override toString.
     * @return a String with all elements of this MathMatrix. 
     * Each row is on a separate line.
     * Spacing based on longest element in this Matrix.
     */
    public String toString() {
    	int max = 0;
    	int numLength = 0;
    	int num = 0;
    	int spaces = 0;
    	StringBuilder string = new StringBuilder();
    	
    	//finds length of longest element in the current Matrix
    	for(int row = 0; row < cells.length; row++) {
    		for(int col = 0; col < cells[0].length; col++) {
    			int length = ("" + cells[row][col]).length();
    			if(length > max) {
    				max = length;
    			}
    		}
    	}

    	max++;

    	//builds a String based on the max length
    	for(int row = 0; row < cells.length; row++) {
    	   	string.append("|");
    		for(int col = 0; col < cells[0].length; col++) {
    			num = cells[row][col];
    			numLength = ("" + cells[row][col]).length();
    			spaces = max - numLength;
    			for(int space = 0; space < spaces; space++) {
    				string.append(" ");
    			}
    			string.append(num);
    		}
    		string.append("|\n");
    	}
    	
    	return string.toString();
    }



    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main 
     * diagonal must be 0.<br>
     * pre: this is a square matrix. getNumRows() == getNumColumns()  
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise. 
     */
    public boolean isUpperTriangular(){
    	
    	//checks precondition
    	if(getNumRows() != getNumColumns()) {
    		throw new IllegalArgumentException("Violation of precondition: not a square matrix.");
    	}
    	
    	//checks if all elements under the main diagonal are 0
    	for(int row = 0; row < cells.length; row++) {
    		for(int col = 0; col < row; col++) {
    			if(cells[row][col] != 0) {
    				return false;
    			}
    		}
    	}
    	
        return true;
    }

    // method to ensure mat is rectangular
    // pre: mat != null, mat has at least one row
    // return true if all rows in mat have the same
    // number of columns false otherwise.
    public static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("argument mat may not be null and must "
                    + " have at least one row. mat = " + Arrays.toString(mat));
        }
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while (isRectangular && row < mat.length) {
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }

}