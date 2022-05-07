package edu.ou.cs2334.project5.models;

/**
 * This enum is used to represent the state of the NonogramModel grid.
 * 
 * @author Thang Nguyen
 *
 */
public enum CellState {
	EMPTY, FILLED, MARKED;

	public static boolean toBoolean(CellState state) {
		return state.equals(FILLED) ? true : false; // either .equals() or ==
	}
}
