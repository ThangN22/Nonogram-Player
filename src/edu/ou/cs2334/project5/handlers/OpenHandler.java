package edu.ou.cs2334.project5.handlers;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project4.interfaces.Openable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * This class extends the AbstractBaseHandler class, and implements the
 * EventHandler interface and the handle method.
 * 
 * @author Thang Nguyen
 *
 */
public class OpenHandler extends AbstractBaseHandler implements EventHandler<ActionEvent> {

	private Openable opener;

	/**
	 * Assign parameters to instance variables
	 * 
	 * @param window      window
	 * @param fileChooser fileChooser
	 * @param opener      opener
	 */
	public OpenHandler(Window window, FileChooser fileChooser, Openable opener) {
		super(window, fileChooser);
		this.opener = opener;
	}

	/**
	 * Use the FileChooser instance variable to show an open dialog to the user.
	 * Then, if the dialog result (a File) is not null, call the appropriate
	 * Openable method using the specified file.
	 */
	public void handle(ActionEvent event) {

		File dialog = fileChooser.showOpenDialog(window); // "Handles" Exceptions
		if (dialog != null) {
			try {

				opener.open(dialog);
			} catch (IOException e) {
			}
		}

	}
}
