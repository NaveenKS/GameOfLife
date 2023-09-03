package com.logical.gameoflife.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Keeps track lives cells along with the contents in the Grid
 */
public class GridOptimized extends Grid{

	  private Set<Position> liveCells;

	  public GridOptimized(){
			super();
			populateLiveCellPositions();
	  }

	  public Set<Position> getLiveCells() {
			return liveCells;
	  }

	  /**
	   * mark the cells to live state - true, in the middle of 25*25 matrix in glider pattern
	   */
	  private void populateLiveCellPositions(){

			int midRow = rowLength/2;
			int midColumn = columnLength/2;

			liveCells = new HashSet<>();

			Position position = new Position(midRow-1,midColumn);
			liveCells.add(position);

			position = new Position(midRow,midColumn+1);
			liveCells.add(position);

			position = new Position(midRow+1,midColumn+1);
			liveCells.add(position);

			position = new Position(midRow+1,midColumn);
			liveCells.add(position);

			position = new Position(midRow+1,midColumn-1);
			liveCells.add(position);
	  }
}
