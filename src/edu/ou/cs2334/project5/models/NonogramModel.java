package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonogramModel {

	private static final String DELIMITER = " ";
	private static final int IDX_NUM_ROWS = 0;
	private static final int IDX_NUM_COLS = 1;

	private int[][] rowClues;
	private int[][] colClues;
	private CellState[][] cellStates;

	public NonogramModel(int[][] rowClues, int[][] colClues) {
		// Implement deepCopy.
		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}

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

	public NonogramModel(String filename) throws IOException {
		// new NonogramModel => this
		this(new File(filename));
	}

	public void resetCells() {
		for (int rowIdx = 0; rowIdx < getNumRows(); ++rowIdx) {
			for (int colIdx = 0; colIdx < getNumCols(); ++colIdx) {
				setCellState(rowIdx, colIdx, CellState.EMPTY);
			}
		}
	}

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

	// TODO: Add more TODOs

	/* Helper methods */

	public int getNumRows() {
		int copy = this.rowClues.length;
		return copy;
	}

	public int getNumCols() {
		int copy = this.colClues.length;
		return copy;
	}

	public int[] getRowClue(int rowIdx) {
		return Arrays.copyOf(rowClues[rowIdx], rowClues[rowIdx].length);
	}

	public int[] getColClue(int colIdx) {
		return Arrays.copyOf(colClues[colIdx], colClues[colIdx].length);
	}

	/**
	 * Return the state of the cell with the given row and column indices
	 * 
	 * @param rowIdx row index
	 * @param colIdx column index
	 * @return CellState
	 */
	public CellState getCellState(int rowIdx, int colIdx) {
		CellState copy = cellStates[rowIdx][colIdx];
		return copy;
	}

	public boolean getCellStateAsBoolean(int rowIdx, int colIdx) {
		CellState copy = getCellState(rowIdx, colIdx);
		return CellState.toBoolean(copy);
	}

	/* Setters */

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

	public boolean isSolved() {
		for (int i = 0; i < getNumRows(); ++i) {
			if (isRowSolved(i) == false) {
				return false;
			}

		}
		for(int j = 0; j < getNumCols(); ++j) {
			if (isColSolved(j) == false) {
				return false;
			}
		}
		return true;
	}

	public boolean isColSolved(int colIdx) {
		return Arrays.equals(projectCellStatesCol(colIdx), getColClue(colIdx));
	}

	public boolean isRowSolved(int rowIdx) {
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
