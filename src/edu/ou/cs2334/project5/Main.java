package edu.ou.cs2334.project5;

import edu.ou.cs2334.project5.presenters.NonogramPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class has one instance method that sets the content of the
 * Application window. The start method constructs a NonogramPresenter instance
 * and displays it.
 * 
 * @author Thang Nguyen
 *
 */
public class Main extends Application {
	private int IDX_CELL_SIZE = 0;
	private int DEFAULT_CELL_SIZE = 30;
	/** 
	 * Launch arguments
	 * @param args arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	/** 
	 * Create scene with pane, the pane being of Nonogram Presenter type. Set the scene in primary stage and show.
	 */
	public void start(Stage primaryStage) throws Exception {
		// The graphical components of a JavaFX application are stored in
		// scenes. One such component is a pane. The next two lines create a
		// Scene object that contains an empty Pane object.
		int cellSize = Integer.parseInt(getParameters().getUnnamed().get(IDX_CELL_SIZE)); // get cellSize

		NonogramPresenter present;
		// Make sure cellSize is an appropriate number
		if (cellSize == 0 || getParameters().getUnnamed().isEmpty()) {
			present = new NonogramPresenter(DEFAULT_CELL_SIZE);
		} else {
			present = new NonogramPresenter(cellSize);
		}

		// Create Scene
		Scene scene = new Scene(present.getPane());
		// Add style sheets
		present.getPane().getStylesheets().add("/style.css");

		// The next three lines add the scene to the application stage, set the
		// text in the title bar, and display the window. Note that only one
		// scene can be displayed in a stage at a time.
		primaryStage.setScene(scene);
		primaryStage.setTitle("Nonogram++");
		// Prevent Resizing
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
