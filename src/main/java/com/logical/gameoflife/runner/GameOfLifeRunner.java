package com.logical.gameoflife.runner;

import com.logical.gameoflife.algo.GameOfLife;
import com.logical.gameoflife.models.Grid;
import com.logical.gameoflife.util.OutputPrinter;

import java.util.Scanner;

public class GameOfLifeRunner {
	  /**
	   * This is the main runner class for running GameOfLife Algo
	   * - It checks the state of each cell and applies the rules
	   * - Based on the rules new generation of cells are created
	   *
	   * Performance
	   * - All the cells needs to be checked
	   * - In each generation additional space of (m * n) is required - m rows and n columns
	   * - 1 million generations took 15 secs to run
	   */
	  public static void main(String[] args){
			GameOfLife gameOfLife = new GameOfLife();

			// take number of generations as input from console
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the number of generations:");
			int generations = scanner.nextInt();

			if(generations <= 0){
				  System.out.println("Number of generations must be greater than 0");
				  System.exit(-1);
			}


			// print initial state
			Grid grid = gameOfLife.getGrid();
			System.out.println("Initial state:");
			OutputPrinter.printGrid(grid); // initial state

			// create generations
			for(int generation = 1;generation <= generations;generation++){
				  gameOfLife.createNextGeneration();
				  OutputPrinter.printGrid(grid);
			}
	  }
}
