package com.logical.gameoflife.algo;

import com.logical.gameoflife.models.GridOptimized;
import com.logical.gameoflife.models.Position;

import java.util.*;

public class GameOfLifeOptimized{

	  private GridOptimized grid;

	  public GameOfLifeOptimized(){
			// initialize the grid
			this.grid = new GridOptimized();
	  }

	  /**
	   Algo:
	   1. Initialize the input 2D array with glider pattern with track of live cells
	   2. create
	   		- liveCellsToBeMarkedAsDead - the cells which needs to be marked as dead
	   		- deadCellsToBeMarkedAsAlive - the cells which needs to be marks as alive
	  		 - neighboringDeadCellsChecked - track on neighboring dead cells which are already checked
	   3. For each live cell
	   		- apply the rules based on the live neighbor count
	   			- Add to the liveCellsToBeMarkedAsDead if it needs to be marked as dead
	  		 - get the dead neighbors around the current live cell
	    		- for each dead neighbor
	  				 - check if it's present in neighboringDeadCellsChecked - this means it's already checked as part of other live cell
	   				 - get the count of live cells around the dead cell and apply rules
	   				 - If it's new state is alive add to deadCellsToBeMarkedAsAlive
	   				 - add the cell to neighboringDeadCellsChecked
	   4. Remove the liveCellsToBeMarkedAsDead from live cells in the grid and mark it dead in cells in the grid
	   5. Add the deadCellsToBeMarkedAsAlive from live cells in the grid and mark it alive in the cells the grid
	   6. Print the Grid
	   */


	  public void createNextGeneration(){
			Set<Position> liveCells = grid.getLiveCells();

			// track of live cells to be marked as dead and dead cells to be marked as alive
			List<Position> liveCellsToBeMarkedAsDead = new ArrayList<>();
			List<Position> deadCellsToBeMarkedAsAlive = new ArrayList<>();

			// keep track of neighboring dead cells that are already checked so that overlap can be repeated checks can be avoided
			Set<Position> neighboringDeadCellsChecked = new HashSet<>();

			for(Position liveCell: liveCells){
				  // apply rules to the current live cell
				  boolean state = applyRulesToTheCurrentCell(liveCell.getRow(),liveCell.getColumn());

				  // if the new state is dead add to liveCellsToBeMarkedAsDead
				  if(!state)
						liveCellsToBeMarkedAsDead.add(new Position(liveCell.getRow(),liveCell.getColumn()));

				  // get all the dead neigbors of the current live cell
				  List<Position> deadNeighbors = getDeadNeighbors(liveCell.getRow(),liveCell.getColumn());
				  deadNeighbors.removeAll(neighboringDeadCellsChecked); // remove the neighbors that are already checked

				  // for each dead neighbor check it has be marked as alive based on the rules
				  deadNeighbors.forEach(dead -> {
						boolean newStateOfTheDeadCell = applyRulesToTheCurrentCell(dead.getRow(),dead.getColumn());

						if(newStateOfTheDeadCell)
							  deadCellsToBeMarkedAsAlive.add(new Position(dead.getRow(),dead.getColumn()));
				  });

				  // add checked dead neighbors to neighboringDeadCellsChecked
				  neighboringDeadCellsChecked.addAll(deadNeighbors);

			}

			// remove dead cells and update the cell state in grid
			liveCells.removeAll(liveCellsToBeMarkedAsDead);
			updateCells(liveCellsToBeMarkedAsDead,false);

			// add live cells and update the cell state in grid
			liveCells.addAll(deadCellsToBeMarkedAsAlive);
			updateCells(deadCellsToBeMarkedAsAlive,true);

			grid.incrementGeneration();
	  }

	  /**
	   *
	   * @param row - row index
	   * @param column - column index
	   * @return - List of Positions which are dead neighbors of the current cell
	   */
	  private List<Position> getDeadNeighbors(int row, int column){
			List<Position> neighbors = new ArrayList<>();
			boolean[][] currentGenCells = grid.getCells();

			int[] rowIndexes = {-1,-1,-1,0,0,1,1,1};
			int[] columnIndexes = {-1,0,1,-1,1,-1,0,1};

			for(int neighbor=0;neighbor<8;neighbor++){
				  int rowIndex = row+rowIndexes[neighbor];
				  int columnIndex = column+columnIndexes[neighbor];

				  if(rowIndex>=0 && rowIndex<grid.getRowLength() && columnIndex>=0 && columnIndex<grid.getColumnLength() && !currentGenCells[rowIndex][columnIndex])
						neighbors.add(new Position(rowIndex,columnIndex));

			}

			return neighbors;
	  }

	  /**
	   * Apply Rules to the current cell and return the new state of the cell
	   * @param row - row index
	   * @param column - column index
	   * @return - new state of the cell - True/False
	   */
	  protected boolean applyRulesToTheCurrentCell(int row, int column){
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


	  /**
	   *
	   * @param cellsToBeUpdated - Position of the cells to be updated
	   * @param state - state - True or False to be updated
	   */
	  private void updateCells(List<Position> cellsToBeUpdated, boolean state){
			boolean[][] cells = grid.getCells();
			cellsToBeUpdated.forEach(cell -> cells[cell.getRow()][cell.getColumn()] = state);
	  }


	  public GridOptimized getGrid() {
			return grid;
	  }
}
