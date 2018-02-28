# Search: Maze

The goal of this project was to gain some experience applying the A* search algorithm. It assumes you have already familiarized yourself with Git and Maven.

## Code Implementation

Our task was to implement the constructor of `edu.uab.cis.search.maze.Solver`, filling in the `explored` and `path` fields as appropriate. I implemented this using A* search with an L1 heuristic. Specifically, squares should be explored with the following strategy:

* Squares are ordered for exploration using the score `f(x) = g(x) + h(x)`, with the smallest `f(x)` squares being explored first
* `g(x)` is the length of the path so far, from the start square to the current square, including the steps necessary to avoid obstacles
* `h(x)` is the L1 estimate of the path to the goal, that is, the Manhattan distance to the goal, ignoring potential obstacles
* Squares with the same `f(x)` score are ordered by the `h(x)` score, with smaller `h(x)` scores first
* Squares with the same `f(x)` and `h(x)` scores are ordered by row, with smaller rows first
* Squares with the same `f(x)`, `h(x)` and row should be ordered by column, with smaller columns first

## Compile and test the code

1.  Compile the code. Run the following command:

        mvn clean compile

    Everything should compile and you should see a message like:

        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

2.  Test the code. Run the following command:

        mvn clean test

    You should now see a message like:

        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

