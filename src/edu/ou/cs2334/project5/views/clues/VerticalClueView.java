package edu.ou.cs2334.project5.views.clues;
import javafx.geometry.Orientation;
/** 
 * This class represents the numbered clues for a single column. 
 * @author Thang Nguyen
 *
 */
public class VerticalClueView extends AbstractOrientedClueView {

	/**
	 * The style class for this view.
	 */
	private static final String STYLE_CLASS = "vertical-clue-view";

	/**
	 * Constructs a VerticalClueView using the given parameter values.
	 * 
	 * @param colClue an array of column clues
	 * @param cellLength the length of a cell
	 * @param width the maximum number of numbered clues among all columns
	 */
	/** 
	 * Create vertical clue view
	 * @param colClue column clue
	 * @param cellLength cell length
	 * @param height height
	 */
	public VerticalClueView(int[] colClue, int cellLength, int height) {
		super(Orientation.VERTICAL, STYLE_CLASS, colClue, cellLength, height);
		setMaxHeight(height * cellLength);
	}

}
