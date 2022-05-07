package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.scene.layout.GridPane;

/**
 * This class is a GridPane that displays the cell states
 * 
 * @author Thang Nguyen
 *
 */
public class CellGridView extends GridPane {
	
	private static String STYLE_CLASS = "cell-grid-view";
	private CellView[][] cellViews;

	/**
	 * Create a two-dimensional array of CellViews and add them to the GridPane at
	 * the positions with the same row and column indices. Also, add the style class
	 * given as a constant.
	 * 
	 * @param numRows
	 * @param numCols
	 * @param cellLength
	 */
	public CellGridView(int numRows, int numCols, int cellLength) {
		initCells(numRows, numCols, cellLength);
		this.getStyleClass().add(STYLE_CLASS);
	}

	/**
	 * Initialize the cells of this view.Clear the children of this view.
	 * Initialize the cellViews array using the given dimensions.Suggestion: take
	 * a look at the addRow) method of the GridPane class. You may find it easier to
	 * work with. This is just a suggestion though, as you can do something similar
	 * to yourProject 4 code instead to accomplish the same result.
	 * 
	 * @param numRows
	 * @param numCols
	 * @param cellLength
	 */
	public void initCells(int numRows, int numCols, int cellLength) {
		// Clear children 
		getChildren().clear();
		
		cellViews = new CellView[numRows][numCols];
		
//		for (int i = 0; i < numRows; ++i) {
//			for (int j = 0; j < numCols; ++j) {
//				getCellView(i, j).getChildren().clear();
//			}
//		}
		
		
		
		for (int row = 0; row < numRows; ++row) {
			for (int col = 0; col < numCols; ++col) {
				
				cellViews[row][col] = new CellView(cellLength);
				add(cellViews[row][col], col, row); // add(Node child, int columnIndex, int rowIndex)
			}
		}
	}

	/**
	 * Get the CellView using the given indices.
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @return
	 */
	public CellView getCellView(int rowIdx, int colIdx) {
		return cellViews[rowIdx][colIdx];
	}

	/**
	 * Update the state of the CellView with the given indices.
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @param state
	 */
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellViews[rowIdx][colIdx].setState(state);
	}
}
