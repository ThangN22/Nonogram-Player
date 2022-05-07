package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * This class is a BorderPane that displays the row clues in the left position,
 * the column clues in the top position, and the cells in the middle position.
 * 
 * @author Thang Nguyen
 *
 */
public class NonogramView extends BorderPane {
	private static String STYLE_CLASS = "nonogram-view";
	private static String SOLVED_STYLE_CLASS = "nonogram-view-solved";
	private LeftCluesView leftCluesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox bottomHBox;
	private Button loadBtn;
	private Button resetBtn;

	public NonogramView() {
		getStyleClass().add(STYLE_CLASS);
	}

	public void initialize(int[][] rowClues, int[][] colClues, int cellLength) {
		cellGridView = new CellGridView(rowClues.length, colClues.length, cellLength);
		int colCluesHeight = MaxLengthWidth(colClues);// using helper method
		topCluesView = new TopCluesView(colClues, cellLength, colCluesHeight);
		int rowCluesWidth = MaxLengthWidth(rowClues);// using helper method
		leftCluesView = new LeftCluesView(rowClues, cellLength, rowCluesWidth); // Aligning elements in borderpane, Java FX
		

		setLeft(leftCluesView);
		setTop(topCluesView);
		BorderPane.setAlignment(topCluesView, Pos.CENTER_RIGHT); // THIS!
		setCenter(cellGridView);
		initBottomHBox();
		setBottom(bottomHBox);
	}

	public void initBottomHBox() {
		bottomHBox = new HBox();
		bottomHBox.setAlignment(Pos.CENTER);
		loadBtn = new Button("load");
		resetBtn = new Button("reset");
		bottomHBox.getChildren().add(getLoadButton());
		bottomHBox.getChildren().add(getResetButton());

	}

	// Helper method body found on stack overflow:
	private int MaxLengthWidth(int[][] clues) {
		// https://stackoverflow.com/questions/34760736/get-max-length-of-row-and-column-in-java-two-dimensional-array
		int max = 0;
		for (int i = 0; i < clues.length; ++i) {
			max = Math.max(max, clues[i].length); // set largest length , [][this one], to max.
		}
		return max;
	}

	// Perhaps there needs to be two helper methods
//	private int MaxLengthHeight(int[][] clues) {
//		// https://stackoverflow.com/questions/34760736/get-max-length-of-row-and-column-in-java-two-dimensional-array
//		int max = 0;
//		for (int i = 0; i < clues.length; ++i) { 
//			max = Math.max(max, clues[i].length); // set largest length , [][this one], to max.
//		}
//		return max;
//	}

	public CellView getCellView(int rowIdx, int colIdx) {
		return cellGridView.getCellView(rowIdx, colIdx);
	}

	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellGridView.setCellState(rowIdx, colIdx, state);
	}

	public void setRowClueState(int rowIdx, boolean solved) {
		// Call left clues view
		leftCluesView.setState(rowIdx, solved);

	}

	public void setColClueState(int colIdx, boolean solved) {
		// Call top clues view
		topCluesView.setState(colIdx, solved);
	}

	public void setPuzzleState(boolean solved) {
		if (solved == true) {
			getStyleClass().add(SOLVED_STYLE_CLASS);
		}
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
		Alert thang = new Alert(AlertType.INFORMATION); // My name, Thang, in Vietnamese means
																			// victory. Set the alert type to
																			// information, specifically.
		// Set header, content, and title
		thang.setHeaderText("Puzzle Solved");
		thang.setContentText("Congratrulations!");
		thang.setTitle("You win!");
		// Show 
		thang.show();
		
		
	}
}
