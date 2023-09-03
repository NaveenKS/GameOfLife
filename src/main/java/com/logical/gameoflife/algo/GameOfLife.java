package com.logical.gameoflife.algo;

import com.logical.gameoflife.models.Grid;

/**
	Algo:
 	1. Initialize the input 2D array with glider pattern
	2. Create a new 2D array(boolean)
	3. For each cell in the input 2D array:
		- apply the rules(dead and alive)
		- insert the state of the cell in the new 2D array
	4. Print the output
 */
public class GameOfLife {

	  private Grid grid;

	  public GameOfLife(){
			// initialize grid
			this.grid = new Grid();
	  }

	  /**
	   * - create new generation
	   * 	- For each cell apply rules and mark the state to true or false based on the conditions
	   * 	- increment the generation
	   */
	  public void createNextGeneration(){
			boolean[][] nextGenCells = new boolean[grid.getRowLength()][grid.getColumnLength()];

			// for each cell apply rules and mark the state of the cell in nextGenCells
			for(int row = 0; row<grid.getRowLength(); row++){
				  for(int column = 0; column<grid.getColumnLength(); column++){
						nextGenCells[row][column] = applyRulesToTheCurrentCell(row,column);
				  }
			}

			grid.setCells(nextGenCells);
			grid.incrementGeneration();
	  }

	  /**
	   * Apply Rules to the current cell and return the new state of the cell
	   * @param row - row index
	   * @param column - column index
	   * @return - new state of the cell - True/False
	   */
	  private boolean applyRulesToTheCurrentCell(int row, int column){
			boolean[][] currentGenCells = grid.getCells();

			// get the count of live neighbors of the current cell
			int liveNeighbors = countLiveNeighbors(row,column);

			boolean newCellState;

			/*
			 * Rules:
			 * 1. if a cell is alive and has less than 2 live neighbors or greater than 3 live neighbors it dies
			 * 			- it lives if it has 2 or 3 neighbors
			 * 2. if a cell is dead and has exactly 3 live neighbors it is brought back to live state
			 */
			// if current cell is alive
			if(currentGenCells[row][column])
				  newCellState = (liveNeighbors == 2 || liveNeighbors == 3) ? true:false;
			else
				  newCellState = liveNeighbors == 3 ? true:false;

			return newCellState;
	  }

	  /**
	   *
	   * @param row - row index
	   * @param column - column index
	   * @return - number of live Neighbors
	   */
	  private int countLiveNeighbors(int row, int column){
			boolean[][] currentGenCells = grid.getCells();
			int count = 0;

			// neighbors indices relative to the current cell
			int[] rowIndexes = {-1,-1,-1,0,0,1,1,1};
			int[] columnIndexes = {-1,0,1,-1,1,-1,0,1};

			for(int neighbor=0;neighbor<8;neighbor++){

				  // compute the neighbor indices
				  int rowIndex = row+rowIndexes[neighbor];
				  int columnIndex = column+columnIndexes[neighbor];

				  // check if it's within the boundary and it's a live cell
				  if(rowIndex>=0 && rowIndex<grid.getRowLength() && columnIndex>=0 && columnIndex<grid.getColumnLength() && currentGenCells[rowIndex][columnIndex])
						count++;

			}
			return count;
	  }

	  public Grid getGrid(){
			return this.grid;
	  }

}
