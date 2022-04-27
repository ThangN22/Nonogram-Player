package edu.ou.cs2334.project5.handlers;

import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * This class represents a general handler involving file selection. Its constructor should assign its instance variables
 */
public class AbstractBaseHandler {
	protected Window window;
	protected FileChooser fileChooser;
	/**
	 * This class represents a general handler involving file selection. Its constructor should assign its instance variables
	 * @param window window
	 * @param fileChooser fileChooser
	 */
	protected AbstractBaseHandler(Window window, FileChooser fileChooser) {
		this.window = window;
		this.fileChooser = fileChooser;
	}
}
