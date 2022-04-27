package edu.ou.cs2334.project5.interfaces;

import java.io.File;
import java.io.IOException;

/**
 * A simple interface used to specify that a class has a special method to
 * handle opening a file. The NonogramMakerPresenter implements these
 * interfaces. This allows the OpenHandler class to handle file opening.
 * 
 * @author Thang Nguyen
 *
 */
public interface Openable {
	/**
	 * Opens a file
	 * @param file file
	 * @throws IOException input output
	 */
	void open(File file) throws IOException; // How to implement the '~' symbol? ps: Good

}
