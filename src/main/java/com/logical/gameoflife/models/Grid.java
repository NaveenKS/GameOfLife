package com.logical.gameoflife.models;

public class Grid {
	  public static final int DEFAULT_NO_OF_ROWS = 25;
	  public static final int DEFAULT_NO_OF_COLUMNS = 25;

	  protected int rowLength;
	  protected int columnLength;
	  private boolean[][] cells;
	  private int generation;

	  public Grid(){
			defaultInitializeGrid();
	  }

	  public int getRowLength() {
			return rowLength;
	  }

	  public int getColumnLength() {
			return columnLength;
	  }

	  public int getGeneration() {
			return generation;
	  }

	  public boolean[][] getCells() {
			return cells;
	  }

	  public void setCells(boolean[][] cells) {
			this.cells = cells;
	  }

	  public void incrementGeneration(){
			this.generation+= 1;
	  }

	  /**
	   - Initialize the grid
	   - Mark the live cells in glider pattern at the center on 25*25 2D array
	   */
	  private void defaultInitializeGrid(){
			rowLength = DEFAULT_NO_OF_ROWS;
			columnLength = DEFAULT_NO_OF_COLUMNS;

			// initialize the cells to glider pattern
			initializeAllCellsAsDead();
			markAliveCellsWithGliderPattern();
	  }

	  /**
	   * initialize all the cells in the grid to false - dead state
	   */
	  private void initializeAllCellsAsDead(){
			cells = new boolean[this.rowLength][columnLength];
			for(int row=0;row<rowLength;row++){
				  for(int column = 0;column <columnLength;column++){
						cells[row][column] = false;
				  }
			}
	  }

	  /**
	   * mark the cells to live state - true in the middle of 25*25 matrix in glider pattern
	   */
	  private void markAliveCellsWithGliderPattern(){
			int midRow = rowLength/2;
			int midColumn = columnLength/2;

			cells[midRow-1][midColumn] = true;
			cells[midRow][midColumn+1] = true;
			cells[midRow+1][midColumn+1] = true;
			cells[midRow+1][midColumn] = true;
			cells[midRow+1][midColumn-1] = true;
	  }
}
