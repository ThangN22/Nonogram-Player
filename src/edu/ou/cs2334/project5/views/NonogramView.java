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

	/**
	 * Constructor to add a style class
	 */
	public NonogramView() {
		getStyleClass().add(STYLE_CLASS);
	}

	/**
	 * Initialize cell grid view, top clues view, and left clues view using the
	 * provided parameters
	 * 
	 * @param rowClues   row clues
	 * @param colClues   column clues
	 * @param cellLength cell length
	 */
	public void initialize(int[][] rowClues, int[][] colClues, int cellLength) {
		cellGridView = new CellGridView(rowClues.length, colClues.length, cellLength);
		int colCluesHeight = MaxLengthWidth(colClues);// using helper method
		topCluesView = new TopCluesView(colClues, cellLength, colCluesHeight);
		int rowCluesWidth = MaxLengthWidth(rowClues);// using helper method
		leftCluesView = new LeftCluesView(rowClues, cellLength, rowCluesWidth); // Aligning elements in borderpane, Java
																				// FX

		setLeft(leftCluesView);
		setTop(topCluesView);
		BorderPane.setAlignment(topCluesView, Pos.CENTER_RIGHT); // THIS!
		setCenter(cellGridView);
		initBottomHBox();
		setBottom(bottomHBox);
	}

	/**
	 * Initialize bottom HBox. Add in load and reset buttons within the HBox
	 */
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
	/**
	 * Return a specified cell view with the given parameters
	 * 
	 * @param rowIdx row index
	 * @param colIdx column index
	 * @return cell view
	 */
	public CellView getCellView(int rowIdx, int colIdx) {
		return cellGridView.getCellView(rowIdx, colIdx);
	}

	/**
	 * set cell states using the given parameters
	 * 
	 * @param rowIdx row index
	 * @param colIdx column index
	 * @param state  cell state
	 */
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellGridView.setCellState(rowIdx, colIdx, state);
	}

	/**
	 * Set row clue of given row index state to the boolean parameter by calling
	 * setState method
	 * 
	 * @param rowIdx row index
	 * @param solved boolean state
	 */
	public void setRowClueState(int rowIdx, boolean solved) {
		// Call left clues view
		leftCluesView.setState(rowIdx, solved);

	}

	/**
	 * Set column clue of given column index to the boolean parameter by calling
	 * setState method
	 * 
	 * @param colIdx column index
	 * @param solved boolean state
	 */
	public void setColClueState(int colIdx, boolean solved) {
		// Call top clues view
		topCluesView.setState(colIdx, solved);
	}

	/**
	 * If parameter is true, add solved style class. If not, remove solved style
	 * class
	 * 
	 * @param solved puzzle solve boolean
	 */
	public void setPuzzleState(boolean solved) {
		if (solved == true) {
			getStyleClass().addAll(SOLVED_STYLE_CLASS);
		} else {
			getStyleClass().removeAll(SOLVED_STYLE_CLASS);

			// getStyleClass().add(STYLE_CLASS);
		}
	}
	/** 
	 * return load button
	 * @return load button
	 */
	public Button getLoadButton() {
		return loadBtn;
	}
	/** 
	 * return reset button
	 * @return reset button
	 */
	public Button getResetButton() {
		return resetBtn;
	}
	/** 
	 * Use the JavaFX Alert class to notify players of a victory.
	 */
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
