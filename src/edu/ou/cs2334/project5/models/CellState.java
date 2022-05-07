package edu.ou.cs2334.project5.models;

/**
 * This enum is used to represent the state of the NonogramModel grid.
 * 
 * @author Thang Nguyen
 *
 */
public enum CellState {
	/** 
	 * Empty enum
	 */
	EMPTY,/** Filled enum */FILLED, /** Marked enum*/MARKED;
	/** 
	 * return a cell state in boolean form
	 * @param state cell state
	 * @return boolean a cell state in boolean form
	 */
	public static boolean toBoolean(CellState state) {
		return state.equals(FILLED) ? true : false; // either .equals() or ==
	}
}
