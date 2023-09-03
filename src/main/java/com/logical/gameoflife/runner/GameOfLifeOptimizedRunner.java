package com.logical.gameoflife.runner;

import com.logical.gameoflife.algo.GameOfLifeOptimized;
import com.logical.gameoflife.models.GridOptimized;
import com.logical.gameoflife.util.OutputPrinter;

import java.util.Scanner;

public class GameOfLifeOptimizedRunner {
	  /**
	   * This is the runner class for optimized solution of Game of Life
	   * - All the cells are not checked for state change
	   * - Live cells are tracked in each generation
	   * - Rules are applied to the live cells and their state is stored
	   * - Only dead neighbors around live cells are checked for state change(neighbors once checked is tracked to avoid repeated checking)
	   *
	   * Performance:
	   * - This is an inplace algorithm - The state is updated in the same grid
	   * - This is optimized in terms of run time since all the cells are not checked for state change
	   * - 1 million generations took 1 sec to run
	   *
	   */
	  public static void main(String[] args){
			GameOfLifeOptimized gameOfLife = new GameOfLifeOptimized();

			// take number of generations as input from console
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the number of generations:");
			int generations = scanner.nextInt();

			if(generations <= 0){
				  System.out.println("Number of generations must be greater than 0");
				  System.exit(-1);
			}

			// print initial state
			GridOptimized grid = gameOfLife.getGrid();
			System.out.println("Initial state:");
			OutputPrinter.printGrid(grid); // initial state

			// create generations
			for(int generation = 1;generation <= generations;generation++){
				  gameOfLife.createNextGeneration();
				  OutputPrinter.printGrid(grid);
			}
	  }
}
