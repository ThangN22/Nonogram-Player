package edu.ou.cs2334.project5.presenters;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.handlers.OpenHandler;
import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.NonogramView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * As in project 4, the NonogramPresenter class represents the brain of our
 * program. The graphical view and model data are connected and synchronized by
 * the presenter
 * 
 * @author Thang Nguyen
 *
 */
public class NonogramPresenter implements Openable {
	private NonogramView view;
	private NonogramModel model;
	private int cellLength;
	private static String DEFAULT_PUZZLE = "puzzles/space-invader.txt";

	/**
	 * Constructor
	 * 
	 * @param cellLength length of cell
	 */
	public NonogramPresenter(int cellLength) throws IOException {
		// Construct a new NonogramModel instance using the DEFAULT_PUZZLE and assign it
		// to the instance variable
		model = new NonogramModel(DEFAULT_PUZZLE);
		// Construct a new View instance and assign it to the instance variable
		view = new NonogramView();
		// Make sure the presenter is initialized (see initPresenter())
		
		// SET CELL LENGTH 
		this.cellLength = cellLength;
		initializePresenter();
	}

	private void initializePresenter() {
		initializeView();
		bindCellViews();
		synchronize();
		configureButtons();
	}

	private void initializeView() { // Maybe have IO exception
		 // Maybe get rid of this?
		view.initialize(model.getRowClues(), model.getColClues(), cellLength); // CLUES???
		
		if (getWindow() != null) {
			getWindow().sizeToScene();
		}
	}

	private void bindCellViews() { // guess
		for (int row = 0; row < model.getNumRows(); ++row) {
			for (int col = 0; col < model.getNumCols(); ++col) {
				int ROW = row; // Make "effectively final" versions of the row and column
				int COL = col;
				view.getCellView(row, col).setOnMouseClicked(new EventHandler<MouseEvent>() { // This method of handling
																								// mouse clicks was
																								// given by Daniel
																								// Yowell & Ewan Green.
					public void handle(MouseEvent event) {

						if (event.getButton() == MouseButton.PRIMARY) { // Left Click
							handleLeftClick(ROW, COL);
						} else if (event.getButton() == MouseButton.SECONDARY) { // Right Click
							handleRightClick(ROW, COL);
						}
					}
				}); // Continue this
			}
		}
	}

	private void handleLeftClick(int rowIdx, int colIdx) {
		CellState state = model.getCellState(rowIdx, colIdx);
		// Clicking EMPTY or MARKED changes it to FILLED
		if (state == CellState.EMPTY || state == CellState.MARKED) {
			updateCellState(rowIdx, colIdx, CellState.FILLED);
		}
		// Clicking FILLED changes it EMPTY
		else if (state == CellState.FILLED) {
			updateCellState(rowIdx, colIdx, CellState.EMPTY);
		}
	}

	private void handleRightClick(int rowIdx, int colIdx) { // guess
		CellState state = model.getCellState(rowIdx, colIdx); // get model's state
		// Clicking EMPTY or FILLED changed it to MARKED
		if (state == CellState.EMPTY || state == CellState.FILLED) {
			updateCellState(rowIdx, colIdx, CellState.MARKED);
		}
		// Clicking MARKED changes it to EMPTY
		else if (state == CellState.MARKED) {
			updateCellState(rowIdx, colIdx, CellState.EMPTY);
		}

	}

	private void updateCellState(int rowIdx, int colIdx, CellState state) {
		CellState gu = model.getCellState(rowIdx, colIdx); // Current
		model.setCellState(rowIdx, colIdx, state);
		
		if (! gu.equals(model.getCellState(rowIdx, colIdx))) { // if are not equal, then changed
			view.setCellState(rowIdx, colIdx, state);
			view.setRowClueState(rowIdx, model.isRowSolved(rowIdx));
			view.setColClueState(colIdx, model.isRowSolved(colIdx));
			
			view.setRowClueState(rowIdx, model.isRowSolved(rowIdx));
			view.setColClueState(colIdx, model.isColSolved(colIdx));
		} 
		
		if (model.isSolved()) {
			processVictory();
		}

	}

	private void synchronize() {
		// Synchronize the cell views with the cell states
		for (int row = 0; row < model.getNumRows(); ++row) {
			for (int col = 0; col < model.getNumCols(); ++col) {
				// Get cell state from model
				CellState state = model.getCellState(row, col);
				// Add that state acquired from model onto view, completing synchronizing of
				// this particular cell
				view.setCellState(row, col, state);
				
				// Synchronize the clue views with row and column states 
				view.setRowClueState(row, model.isRowSolved(row));
				view.setColClueState(col, model.isColSolved(col));
			}
		}
		// Synchronize the puzzle state
		view.setPuzzleState(model.isSolved());
		// Do it. Look at view for this
		
		
		if (model.isSolved()) {
			processVictory();
		}

	}

	private void processVictory() { // guess
		// Remove marks from the cell view
		removeCellViewMarks();
		// Use the view to show a victory alert
		view.showVictoryAlert();
	}

	private void removeCellViewMarks() {
		for (int row = 0; row < model.getNumRows(); ++row) {
			for (int col = 0; col < model.getNumCols(); ++col) {
				CellState state = model.getCellState(row, col);
				if (state == CellState.MARKED) { // Finds marked cells
					view.setCellState(row, col, CellState.EMPTY); // Set them to empty
				}
			}
		}
	}

	private void configureButtons() { // guess
		FileChooser fileChooser1 = new FileChooser();
		fileChooser1.setTitle("Open");
		fileChooser1.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt")); // Learn what extension
																								// filter means
		fileChooser1.setInitialDirectory(new File("."));
		// For the load button, pass in an OpenHandler instance in a similar manner to Project 4.
		view.getLoadButton().setOnAction(new OpenHandler(getWindow(), fileChooser1, this)); // deleted
																							// getMenuItem(view.MENU_ITEM_OPEN)
		// For the reset button, add an ActionEvent EventHandler that calls the resetPuzzle() method.
		view.getResetButton().setOnAction(new EventHandler<ActionEvent>() { // This method of handling the clicking of
																			// the reset button was adapted from
																			// bindCellViews with the help of Abdullah
			public void handle(ActionEvent event) {
				resetPuzzle(); // Helper method
			}
		}); // Continue this

	}

	private void resetPuzzle() {
		model.resetCells();
		synchronize();
	}

	public Pane getPane() {
		return view;
	}

	public Window getWindow() { // Window => Scene => Pane
		try {
		return view.getScene().getWindow();
		}
		catch(NullPointerException e) {
			return null;
		}
	}

	@Override
	public void open(File file) throws IOException {
		// TODO Auto-generated method stub
		model = new NonogramModel(file);
		initializePresenter();
	}

}
