package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.scene.layout.GridPane;
/**
 * This class is a GridPane that displays the cell states
 * @author Thang Nguyen
 *
 */
public class CellGridView extends GridPane{
	private static String STYLE_CLASS = "cell-grid-view";
	private CellView[][] cellViews;
	
	public CellGridView(int numRows, int numCols, int cellLength) {
		
	}
	
	public void initCells(int numRows, int numCols, int cellLength) {
		
	}
	
	public CellView getCellView(int rowIdx, int colIdx) {
		CellView copy = new CellView(1); // stub
		return copy; // stub
	}
	
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		
		
	}
}
