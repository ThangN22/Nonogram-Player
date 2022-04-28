package edu.ou.cs2334.project5.presenters;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.NonogramView;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

public class NonogramPresenter implements Openable{
	private NonogramView view; 
	private NonogramModel model; 
	private int cellLength;
	private static String DEFAULT_PUZZLE = "puzzles/space-invader.txt";
	
	public NonogramPresenter(int cellLength) {
		
	}
	
	private void initializePresenter() {
		
	}
	
	private void initializeView() { // guess
		
	}
	
	private void bindCellViews() { // guess
		
	}
	
	private void handleLeftClick(int rowIdx, int colIdx) { // guess
		
	}
	
	private void handleRightClick(int rowIdx, int colIdx) { // guess
		
	}
	
	private void updateCellState(int rowIdx, int colIdx, CellState state) { // guess
		
	}
	
	private void synchronize() { // guess
		
	}
	
	private void processVictory() { // guess
		
	}
	
	private void removeCellViewMarks() { // guess
		
	}
	
	private void configureButtons() { // guess
		
	}
	
	private void resetPuzzle() { // guess
		
	}
	
	public Pane getPane() { // guess
		return null;
	}
	
	public Window getWindow() { // guess
		return null;
	}
	
	
	@Override
	public void open(File file) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
}
