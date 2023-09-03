package com.logical.gameoflife.util;

import com.logical.gameoflife.models.Grid;

public class OutputPrinter {
	  /**
	   *
	   * @param grid
	   * - Print the state of the cells in the grid
	   */
	  public static void printGrid(Grid grid){
			System.out.println("\nGeneration = "+grid.getGeneration()+"\n");
			boolean[][] cells = grid.getCells();
			for(int row = 0; row< grid.getRowLength(); row++){
				  for(int column = 0; column<grid.getColumnLength(); column++){
						int state = cells[row][column] ? 1 : 0;
						System.out.print(state+" ");
				  }
				  System.out.println();
			}
	  }
}
