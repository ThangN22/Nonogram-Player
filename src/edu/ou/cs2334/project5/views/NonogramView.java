package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * This class is a BorderPane that displays the row clues in the left position,
 * the column clues in the top position, and the cells in the middle position.
 * 
 * @author Thang Nguyen
 *
 */
public class NonogramView {
	private static String STYLE_CLASS = "nonogram-view";
	private static String SOLVED_STYLE_CLASS = "nonogram-view-solved";
	private LeftCluesView leftCLuesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox bottomHBox;
	private Button loadBtn;
	private Button resetBtn;

	public NonogramView() {

	}

	public void initialize(int[][] rowClues, int[][] colClues, int cellLength) {

	}

	public void initBottomHbox() {

	}

	public CellView getCellView(int rowIdx, int colIdx) {
		return cellGridView.getCellView(rowIdx, colIdx);
	}

	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellGridView.setCellState(rowIdx, colIdx, state);
	}

	public void setRowClueState(int rowIdx, boolean solved) {
		// Call
	}

	public void setColClueState(int colIdx, boolean solved) {
		// Call
	}

	public void setPuzzleState(boolean solved) {

	}

	public Button getLoadButton() {
		return loadBtn;
	}

	public Button getResetButton() {
		return resetBtn;
	}

	public void showVictoryAlert() {
		// Try reading up on the JavaFX Alert class online to figure out what methods
		// can be used to achieve the Alert (With the text "Puzzle Solved",
		// "Congratulations!", and "You Win!").
	}
}
