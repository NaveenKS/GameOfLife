#Game of Life

**There are two implementations of Game of Life in this project:**

1. **GameOfLife** - This is a simple solution.
    - Class - GameOfLife
    - It checks the state of each cell and applies the rules.
    - Based on the rules new generation of cells are created.

   **Performance:**
   - Time complexity - O(m*n) given m rows and n columns.
   - Space complexity - O(m*n) given m rows and n columns.
   - Sample run - 1 million generations took 15 secs(excluding output print).

2. **GameOfLifeOptimized** - This is an optimized solution in terms of time and space complexity.
    - All the cells are not checked for state change
    - Live cells are tracked in each generation
    - Rules are applied to the live cells and their state is stored
    - Only dead neighbors around live cells are checked for state change(neighbors once checked is tracked to avoid repeated checking)

   **Performance:**
   - Time complexity - O(x) - x is the number of live cells in each generation
   - Space complexity - O(x) - x is the number of live cells in each generation.
   - Sample run - 1 million generations took 1 sec(excluding output print).

Further enhancements:
- Currently, default input(glider pattern) is considered. We can extend this to be user specified.

	   
