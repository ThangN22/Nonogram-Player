package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class encapsulates the state and rules of the game. It stores arrays
 * with the row clues, column clues, and cell states (EMPTY, FILLED, or MARKED).
 * 
 * @author Thang Nguyen
 *
 */
public class NonogramModel {

	private static final String DELIMITER = " ";
	private static final int IDX_NUM_ROWS = 0;
	private static final int IDX_NUM_COLS = 1;

	private int[][] rowClues;
	private int[][] colClues;
	private CellState[][] cellStates;

	/**
	 * Initialize the object using the given arrays of row and column clues. Make
	 * deep copies of the arrays to protect the data. Create a cell grid with the
	 * same number of rows and columns, and initialize the states to EMPTY.
	 * 
	 * @param rowClues row clues
	 * @param colClues column clues
	 */
	public NonogramModel(int[][] rowClues, int[][] colClues) {
		// Implement deepCopy.
		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}

	/**
	 * Initialize the object using the row and column clues in the given file. Set
	 * all of the cell states to EMPTY.
	 * 
	 * @param file file
	 * @throws IOException input output exception
	 */
	public NonogramModel(File file) throws IOException {
		// Number of rows and columns
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String header = reader.readLine();
		String[] fields = header.split(DELIMITER);
		int numRows = Integer.parseInt(fields[IDX_NUM_ROWS]);
		int numCols = Integer.parseInt(fields[IDX_NUM_COLS]);
		// Initialize cellStates.
		cellStates = initCellStates(numRows, numCols);
		// Read in row clues.
		int[][] rowCluesCopy = readClueLines(reader, numRows);
		this.rowClues = deepCopy(rowCluesCopy);
		// Read in column clues.
		int[][] colCluesCopy = readClueLines(reader, numCols);
		this.colClues = deepCopy(colCluesCopy);
		// Close reader
		reader.close();
	}

	/**
	 * Initialize the object using the row and column clues in the text file with
	 * the given name.
	 * 
	 * @param filename name of file
	 * @throws IOException input output exception
	 */
	public NonogramModel(String filename) throws IOException {
		// new NonogramModel => this
		this(new File(filename));
	}

	/**
	 * Change the state of all cells to EMPTY
	 */
	public void resetCells() {
		for (int rowIdx = 0; rowIdx < getNumRows(); ++rowIdx) {
			for (int colIdx = 0; colIdx < getNumCols(); ++colIdx) {
				setCellState(rowIdx, colIdx, CellState.EMPTY);
			}
		}
	}

	/**
	 * Return the nonogram numbers of the given array of cell states.
	 * 
	 * @param cells array list of cell states
	 * @return List of type Integer
	 */
	public static List<Integer> project(boolean[] cells) {

		List<Integer> project = new ArrayList<Integer>();
		// This for loop adds true values
		int temp = 0;
		for (int i = 0; i < cells.length; ++i) {
			if (i == cells.length - 1) {
				if (cells[i] == true) {
					++temp;
					project.add(temp);
				} else if (temp > 0) {
					project.add(temp);
				}

				break;
			}

			if (cells[i] == true) {
				++temp;
			} else if (cells[i] == false) {
				if (temp > 0) {
					project.add(temp);
					temp = 0;
				}
			}
		}
		// If temp is empty, the size is zero, then the ArrayList will be returned as
		// one zero
		if (project.size() == 0 && temp == 0) {
			project.add(0);
		}

		return project;
	}

	/**
	 * Return the projection of the cellStates row with the given index.
	 * 
	 * @param rowIdx row index
	 * @return int[] int array of projections
	 */
	public int[] projectCellStatesRow(int rowIdx) {
		boolean[] project = new boolean[getNumCols()];
		for (int idx = 0; idx < getNumCols(); ++idx) {
			project[idx] = getCellStateAsBoolean(rowIdx, idx);
		}
		List<Integer> intL = project(project);
		int[] intA = new int[intL.size()];
		for (int i = 0; i < intL.size(); ++i) {
			intA[i] = intL.get(i);
		}
		return intA;
	}

	/**
	 * Return the projection of the cellStates column with the given index.
	 * 
	 * @param colIdx column index
	 * @return int[] array of projections
	 */
	public int[] projectCellStatesCol(int colIdx) {
		boolean[] project = new boolean[getNumRows()];
		for (int idx = 0; idx < getNumRows(); ++idx) {
			project[idx] = getCellStateAsBoolean(idx, colIdx);
		}
		List<Integer> intL = project(project);
		int[] intA = new int[intL.size()];
		for (int i = 0; i < intL.size(); ++i) {
			intA[i] = intL.get(i);
		}
		return intA;

	}

	/* Helper methods */
	/**
	 * Return the number of rows in the nonogram.
	 * 
	 * @return number of rows
	 */
	public int getNumRows() {
		int copy = this.rowClues.length;
		return copy;
	}

	/**
	 * Return the number of columns in the nonogram.
	 * 
	 * @return number of columns
	 */
	public int getNumCols() {
		int copy = this.colClues.length;
		return copy;
	}

	/**
	 * Return a deep copy of the row clue
	 * 
	 * @param rowIdx row index
	 * @return int[] int array of row clue
	 */
	public int[] getRowClue(int rowIdx) { // Maybe implement deep copy
		return Arrays.copyOf(rowClues[rowIdx], rowClues[rowIdx].length);
	}

	/**
	 * Return a deep copy of the column clue
	 * 
	 * @param colIdx column index
	 * @return int[] array of column clue
	 */
	public int[] getColClue(int colIdx) { // Maybe implement deep copy
		return Arrays.copyOf(colClues[colIdx], colClues[colIdx].length);
	}
	/** 
	 * get a deep copy of row clues
	 * @return 2d array of row clues
	 */
	public int[][] getRowClues() {
		return deepCopy(rowClues);
	}
	/** 
	 * get a deep copy of column clues
	 * @return 2d array of column clues
	 */
	public int[][] getColClues() { 
		return deepCopy(colClues);
	}
	

	/**
	 * Return the state of the cell with the given row and column indices
	 * 
	 * @param rowIdx row index
	 * @param colIdx column index
	 * @return CellState cell state
	 */
	public CellState getCellState(int rowIdx, int colIdx) {
		CellState copy = cellStates[rowIdx][colIdx];
		return copy;
	}

	/**
	 * Return the boolean state of the cell with the given row and column indices. A
	 * FILLED cell returns true. Otherwise, return false.
	 * 
	 * @param rowIdx row index
	 * @param colIdx column index
	 * @return boolean cell state as boolean
	 */
	public boolean getCellStateAsBoolean(int rowIdx, int colIdx) {
		CellState copy = getCellState(rowIdx, colIdx);
		return CellState.toBoolean(copy);
	}

	/* Setters */
	/**
	 * Set the state of the cell with the given indices. If the enum value is null
	 * or the puzzle is solved, do nothing. Return a boolean value that indices
	 * whether the state of the puzzle changed.
	 * 
	 * @param rowIdx row index
	 * @param colIdx column index
	 * @param state  CellState
	 * @return boolean cell state
	 */
	public boolean setCellState(int rowIdx, int colIdx, CellState state) {
		if (state == null || isSolved() == true) {
			return false;
		}
		if (getCellState(rowIdx, colIdx) == state) {
			return false;
		}
		cellStates[rowIdx][colIdx] = state;
		return true;
	}

	/**
	 * Return true if the puzzle if solved; otherwise, return false.
	 * 
	 * @return boolean for solved
	 */
	public boolean isSolved() {
		for (int i = 0; i < getNumRows(); ++i) {
			if (isRowSolved(i) == false) {
				return false;
			}

		}
		for (int j = 0; j < getNumCols(); ++j) {
			if (isColSolved(j) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return true if the column with the given index is solved. A column is solved
	 * if the projected cellStates column matches the column's clue. Otherwise,
	 * return false.
	 * 
	 * @param colIdx column index
	 * @return boolean for solved
	 */
	public boolean isColSolved(int colIdx) { // One-Liner From Tommy Pham
		return Arrays.equals(projectCellStatesCol(colIdx), getColClue(colIdx));
	}

	/**
	 * Return true if the row with the given index is solved. A row is solved if the
	 * projected cellStates matches the row's clue. Otherwise, return false.
	 * 
	 * @param rowIdx row index
	 * @return boolean for solved
	 */
	public boolean isRowSolved(int rowIdx) { // One-Liner From Tommy Pham
		return Arrays.equals(projectCellStatesRow(rowIdx), getRowClue(rowIdx));
	}

	// This is implemented for you
	private static CellState[][] initCellStates(int numRows, int numCols) {
		// Create a 2D array to store numRows * numCols elements
		CellState[][] cellStates = new CellState[numRows][numCols];

		// Set each element of the array to empty
		for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
			for (int colIdx = 0; colIdx < numCols; ++colIdx) {
				cellStates[rowIdx][colIdx] = CellState.EMPTY;
			}
		}

		// Return the result
		return cellStates;
	}

	// TODO: Implement this method
	private static int[][] deepCopy(int[][] array) {
		// Recieved code structure from
		// https://stackoverflow.com/questions/1564832/how-do-i-do-a-deep-copy-of-a-2d-array-in-java
		return Arrays.stream(array).map(int[]::clone).toArray(int[][]::new);
	}

	// This method is implemented for you. You need to figure out how it is useful.
	private static int[][] readClueLines(BufferedReader reader, int numLines) throws IOException {
		// Create a new 2D array to store the clues
		int[][] clueLines = new int[numLines][];

		// Read in clues line-by-line and add them to the array
		for (int lineNum = 0; lineNum < numLines; ++lineNum) {
			// Read in a line
			String line = reader.readLine();

			// Split the line according to the delimiter character
			String[] tokens = line.split(DELIMITER);

			// Create new int array to store the clues in
			int[] clues = new int[tokens.length];
			for (int idx = 0; idx < tokens.length; ++idx) {
				clues[idx] = Integer.parseInt(tokens[idx]);
			}

			// Store the processed clues in the resulting 2D array
			clueLines[lineNum] = clues;
		}

		// Return the result
		return clueLines;
	}

}
