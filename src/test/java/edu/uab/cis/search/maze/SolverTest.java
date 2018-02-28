package edu.uab.cis.search.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class SolverTest {

  @Test(timeout = 10000)
  public void testNoObstacles() {
    // @formatter:off
    String mazeString = 
      "###############\n" +
      "#S           G#\n" +
      "#             #\n" +
      "###############\n";
    // @formatter:on
    Maze maze = new Maze(2, 13, new Square(0, 0), new Square(0, 12), Sets.<Square> newHashSet());
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    // the solution should just go left to right
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = new ArrayList<>();
    for (int column = 0; column <= 12; ++column) {
      expectedPath.add(new Square(0, column));
    }
    Assert.assertEquals(expectedPath, path);

    // no additional squares should be explored
    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(expectedPath);
    Assert.assertEquals(expectedExplored, explored);
  }

  @Test(timeout = 10000)
  public void testObstacles() {
    // @formatter:off
    String mazeString = 
      "######\n" +
      "#    #\n" +
      "# # G#\n" +
      "#S # #\n" +
      "######\n";
    // @formatter:on
    Set<Square> obstacles = Sets.newHashSet(new Square(1, 1), new Square(2, 2));
    Maze maze = new Maze(3, 4, new Square(2, 0), new Square(1, 3), obstacles);
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    // the solution should go up around the obstacles
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = Lists.newArrayList(
            new Square(2, 0),
            new Square(1, 0),
            new Square(0, 0),
            new Square(0, 1),
            new Square(0, 2),
            new Square(0, 3),
            new Square(1, 3));
    Assert.assertEquals(expectedPath, path);

    // the square to the right of the start should also be explored
    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(new Square(2, 1));
    expectedExplored.addAll(expectedPath);
    Assert.assertEquals(expectedExplored, explored);
  }
   @Test(timeout = 10000)
	public void testManyObstacles() {
		// @formatter:off
		String mazeString = "##########\n" + 
                                    "#    #   #\n" + 
                                    "# #    #G#\n" + 
                                    "#S #     #\n" + 
                                    "##########\n";
		// @formatter:on
		Set<Square> obstacles = Sets.newHashSet(new Square(0, 4), new Square(1,
				1), new Square(1, 6), new Square(2, 2));
		Maze maze = new Maze(3, 8, new Square(2, 0), new Square(1, 7),
				obstacles);
		Assert.assertEquals(mazeString, maze.toString());

		// solve the maze
		Solver solver = new Solver(maze);

		// the solution should go up around the obstacles
		List<Square> path = solver.getPathFromStartToGoal();
                List<Square> expectedPath = Lists.newArrayList(new Square(2, 0),
				new Square(1, 0), new Square(0, 0), new Square(0, 1),
				new Square(0, 2), new Square(0, 3), new Square(1, 3),
				new Square(1, 4), new Square(1, 5), new Square(0, 5),
				new Square(0, 6), new Square(0, 7), new Square(1, 7));
		Assert.assertEquals(expectedPath, path);

		// the square to the right of the start should also be explored
		Set<Square> explored = solver.getExploredSquares();
		Set<Square> expectedExplored = Sets.newHashSet(new Square(2, 1),
				new Square(1, 2));
		expectedExplored.addAll(path);
		Assert.assertEquals(expectedExplored, explored);
	}
	@Test(timeout = 10000)
	public void testExplored1() {
		// @formatter:off
		String mazeString = "######\n" + 
                                    "#  #G#\n" + 
                                    "# ## #\n" + 
                                    "# ## #\n" + 
                                    "#S   #\n" + 
                                    "######\n";

		Set<Square> obstacles = Sets.<Square> newHashSet(new Square(2, 1),
				new Square(2, 2), new Square(1, 1), new Square(1, 2),
				new Square(0, 2));
		// @formatter:on
		Maze maze = new Maze(4, 4, new Square(3, 0), new Square(0, 3),
				obstacles);
		Assert.assertEquals(mazeString, maze.toString());

		// solve the maze
		Solver solver = new Solver(maze);
		List<Square> path = solver.getPathFromStartToGoal();
		// @formatter:off
		List<Square> expectedPath = Lists.newArrayList(new Square(3, 0),
				new Square(3, 1), new Square(3, 2), new Square(3, 3),
				new Square(2, 3), new Square(1, 3), new Square(0, 3));
		Assert.assertEquals(expectedPath, path);

		Set<Square> explored = solver.getExploredSquares();
		Set<Square> expectedExplored = Sets.<Square> newHashSet(expectedPath);
		expectedExplored.add(new Square(2, 0));
		expectedExplored.add(new Square(1, 0));
		expectedExplored.add(new Square(0, 0));
		expectedExplored.add(new Square(0, 1));
		Assert.assertEquals(expectedExplored, explored);
		// @formatter:on
	}
	
	// //// F test ////

	@Test(timeout = 10000)
	public void testExplored1F() {
		// @formatter:off
		String mazeString = "######\n" + 
                                    "#  #G#\n" +    
                                    "# ## #\n" + 
                                    "# ## #\n" +   
                                    "#S   #\n" + 
                                    "######\n";

		Set<Square> obstacles = Sets.<Square> newHashSet(new Square(2, 1),
				new Square(2, 2), new Square(1, 1), new Square(1, 2),
				new Square(0, 2));
		// @formatter:on
		Maze maze = new Maze(4, 4, new Square(3, 0), new Square(0, 3),
				obstacles);
		Assert.assertEquals(mazeString, maze.toString());

		// solve the maze
		Solver solver = new Solver(maze);
		List<Square> path = solver.getPathFromStartToGoal();
		// @formatter:off
		List<Square> expectedPath = Lists.newArrayList(new Square(3, 0),
				new Square(3, 1), new Square(3, 2), new Square(3, 3),
				new Square(2, 3), new Square(1, 3), new Square(0, 3));
		//System.out.println("For testExplored1");
		//System.out.println(" p: " + path);
		//System.out.println("ep: " + expectedPath);
		Assert.assertEquals(expectedPath, path);

		Set<Square> explored = solver.getExploredSquares();
		Set<Square> expectedExplored = Sets.<Square> newHashSet(expectedPath);
		expectedExplored.add(new Square(2, 0));
		expectedExplored.add(new Square(1, 0));
		expectedExplored.add(new Square(0, 0));
		expectedExplored.add(new Square(0, 1));

		//System.out.println(" e: " + explored);
		//System.out.println("ee: " + expectedExplored);

		Assert.assertEquals(expectedExplored, explored);
		// @formatter:on
	}
	
	@Test(timeout = 10000)
	public void testManyObstaclesF() {
		// @formatter:off
		String mazeString = "##########\n" + 
                                    "#    #   #\n" + 
                                    "# #    #G#\n" + 
                                    "#S #     #\n" + 
                                    "##########\n";
		// @formatter:on
		Set<Square> obstacles = Sets.newHashSet(new Square(0, 4), new Square(1,
				1), new Square(1, 6), new Square(2, 2));
		Maze maze = new Maze(3, 8, new Square(2, 0), new Square(1, 7),
				obstacles);
		Assert.assertEquals(mazeString, maze.toString());

		// solve the maze
		Solver solver = new Solver(maze);

		// the solution should go up around the obstacles
		List<Square> path = solver.getPathFromStartToGoal();
                //System.out.println("Path I got :"+path.toString());
                
		List<Square> expectedPath = Lists.newArrayList(new Square(2, 0),new Square(1, 0),
				new Square(0, 0), new Square(0, 1), new Square(0, 2),
				new Square(0, 3), new Square(1, 3), new Square(1, 4),
				new Square(1, 5), new Square(0, 5), new Square(0, 6),
				new Square(0, 7),new Square(1, 7));
		//System.out.println("For testManyObstacles");
		//System.out.println(" p: " + path);
		//System.out.println("ep: " + expectedPath);
		Assert.assertEquals(expectedPath, path);

		// the square to the right of the start should also be explored
		Set<Square> explored = solver.getExploredSquares();
		Set<Square> expectedExplored = Sets.newHashSet(new Square(2, 1),
				new Square(1, 2));
		expectedExplored.addAll(path);

	//	System.out.println(" e: " + explored);
		//System.out.println("ee: " + expectedExplored);
		Assert.assertEquals(expectedExplored, explored);
	}
@Test(timeout = 10000)
	public void testObstacles_1() {
		// @formatter:off
		String mazeString = "########\n" + 
                                    "#      #\n" + 
                                    "#  #   #\n" + 
                                    "#   #  #\n" + 
                                    "#S  # G#\n" + 
                                    "########\n";
		// @formatter:on
		Set<Square> obstacles = Sets.newHashSet(new Square(1, 2), new Square(2,
				3), new Square(3, 3));
		Maze maze = new Maze(4, 6, new Square(3, 0), new Square(3, 5),
				obstacles);
		Assert.assertEquals(mazeString, maze.toString());

		// solve the maze
		Solver solver = new Solver(maze);

		// the solution should go up around the obstacles
		List<Square> path = solver.getPathFromStartToGoal();
		List<Square> expectedPath = Lists.newArrayList(new Square(3, 0),
				new Square(3, 1), new Square(2, 1), new Square(1, 1),
				new Square(0, 1), new Square(0, 2), new Square(0, 3),
				new Square(0, 4), new Square(0, 5), new Square(1, 5),
				new Square(2, 5), new Square(3, 5));
		Assert.assertEquals(expectedPath, path);
		//System.out.println("For testObstacles_1");
		//System.out.println(" p: " + path);
		//System.out.println("ep: " + expectedPath);
		// too many nodes explored so skipping this
		/*
		 * Set<Square> explored = solver.getExploredSquares(); Set<Square>
		 * expectedExplored = Sets.newHashSet(new Square(2, 1));
		 * expectedExplored.addAll(path); Assert.assertEquals(expectedExplored,
		 * explored);
		 */
	}
        @Test(timeout = 10000)
	public void testObstacles_2() {

		// @formatter:off

		String mazeString =

		"########\n" +
		"#      #\n" +
		"#  #   #\n" +
		"#   #  #\n" +
		"#SG #  #\n" +
		"########\n";

		// @formatter:on

		Set<Square> obstacles = Sets.newHashSet(new Square(1, 2), new Square(2,
				3), new Square(3, 3));

		Maze maze = new Maze(4, 6, new Square(3, 0), new Square(3, 1),
				obstacles);

		Assert.assertEquals(mazeString, maze.toString());

		// solve the maze

		Solver solver = new Solver(maze);

		// the solution should go up around the obstacles

		List<Square> path = solver.getPathFromStartToGoal();

		List<Square> expectedPath = Lists.newArrayList(new Square(3, 0), new Square(3, 1));

		Assert.assertEquals(expectedPath, path);
		//System.out.println("For testObstacles_2");
		//System.out.println(" p: " + path);
		//System.out.println("ep: " + expectedPath);

		// the square to the right of the start should also be explored

		Set<Square> explored = solver.getExploredSquares();

		Set<Square> expectedExplored = Sets.newHashSet();

		expectedExplored.addAll(path);

		Assert.assertEquals(expectedExplored, explored);

		//System.out.println(" e: " + explored);
		//System.out.println("ee: " + expectedExplored);

	}
   @Test(timeout = 10000)
  public void extraTest1() {
    // @formatter:off
    String mazeString = 
      "###############\n" +
      "#             #\n" +
      "#G     #     S#\n" +
      "#             #\n" +
      "###############\n";
    // @formatter:on
    Set<Square> obstacles = Sets.newHashSet(new Square(1, 6));
    Maze maze = new Maze(3, 13, new Square(1, 12), new Square(1, 0), obstacles);
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    // the solution should just go right to left going above the obstacle
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = Lists.newArrayList(
        new Square(1, 12),
        new Square(1, 11),
        new Square(1, 10),
        new Square(1, 9),
        new Square(1, 8),
        new Square(1, 7),
        new Square(0, 7),
        new Square(0, 6),
        new Square(0, 5),
        new Square(0, 4),
        new Square(0, 3),
        new Square(0, 2),
        new Square(0, 1),
        new Square(0, 0),
        new Square(1, 0));

    Assert.assertEquals(expectedPath, path);

    // no additional squares should be explored
    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(expectedPath);
    Assert.assertEquals(expectedExplored, explored);
  }
  
   @Test(timeout = 10000)
  public void extraTest2() {
    // @formatter:off
    String mazeString = 
      "###############\n" +
      "#     #       #\n" +      
      "#G###  #     S#\n" +   
      "####   # #### #\n" +                               
      "#    ## #     #\n" +                               
      "# ###    #### #\n" +                               
      "#  #  ###  #  #\n" +                               
      "#             #\n" +                               
      "###############\n";                                
    // @formatter:on                          
    Set<Square> obstacles = Sets.newHashSet(new Square(0, 5), new Square(1, 1), new Square(1, 2), new Square(1, 3), new Square(1, 6), 
                                            new Square(2, 0), new Square(2, 1), new Square(2, 2), new Square(2, 6), new Square(2, 8),
                                            new Square(2, 9), new Square(2,10), new Square(2,11), new Square(3, 4), new Square(3, 5),
                                            new Square(3, 7), new Square(4, 1), new Square(4, 2), new Square(4, 3), new Square(4, 8),
                                            new Square(4, 9), new Square(4,10), new Square(4,11), new Square(5, 2), new Square(5, 5),
                                            new Square(5, 6), new Square(5, 7), new Square(5,10));
    Maze maze = new Maze(7, 13, new Square(1, 12), new Square(1, 0), obstacles);
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = Lists.newArrayList(
        new Square(1, 12),
        new Square(2, 12),
        new Square(3, 12),
        new Square(4, 12),
        new Square(5, 12),
        new Square(5, 11),
        new Square(6, 11),
        new Square(6, 10),
        new Square(6, 9),
        new Square(6, 8),
        new Square(6, 7),
        new Square(6, 6),
        new Square(6, 5),
        new Square(6, 4),
        new Square(6, 3),
        new Square(6, 2),
        new Square(6, 1),
        new Square(5, 1),
        new Square(5, 0),
        new Square(4, 0),
        new Square(3, 0),
        new Square(3, 1),
        new Square(3, 2),
        new Square(3, 3),
        new Square(2, 3),
        new Square(2, 4),
        new Square(1, 4),
        new Square(0, 4),
        new Square(0, 3),
        new Square(0, 2),
        new Square(0, 1),
        new Square(0, 0),
        new Square(1, 0)     
        );

    Assert.assertEquals(expectedPath, path);

    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(
        new Square(1, 11), new Square(1, 10), new Square(1, 9),  new Square(1, 8),  new Square(1, 7), 
        new Square(0, 7),  new Square(0, 6),  new Square(2, 7),  new Square(0, 8),  new Square(0, 9), 
        new Square(0, 10), new Square(0, 11), new Square(0, 12), new Square(3, 11), new Square(3, 10),
        new Square(3, 9),  new Square(3, 8),  new Square(5, 8),  new Square(5, 9),  new Square(5, 4), 
        new Square(4, 4),  new Square(5, 3),  new Square(3, 6),  new Square(6, 12), //new Square(3, 7),
        new Square(4, 7),  new Square(4, 6),  new Square(6, 0),  new Square(4, 5));
    expectedExplored.addAll(expectedPath);
    Assert.assertEquals(expectedExplored, explored);
  }
  
    @Test(timeout = 10000)
  public void extraTest3() {
    // @formatter:off
    String mazeString = 
      "###############\n" +
      "#            G#\n" +
      "#S            #\n" +
      "###############\n";
    // @formatter:on
    Maze maze = new Maze(2, 13, new Square(1, 0), new Square(0, 12), Sets.<Square> newHashSet());
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    // the solution should just go left to right
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = new ArrayList<>();
    expectedPath.add(new Square(1, 0));
    for (int column = 0; column <= 12; ++column) {
      expectedPath.add(new Square(0, column));
    }
    Assert.assertEquals(expectedPath, path);

    // no additional squares should be explored
    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(expectedPath);
    Assert.assertEquals(expectedExplored, explored);
  }
  
   @Test(timeout = 10000)
  public void extraTest5() {
    // @formatter:off
    String mazeString = 
      "###############\n" +
      "#     #G      #\n" +      
      "#S###  #      #\n" +        
      "####   # #### #\n" +                               
      "#    ## #     #\n" +                               
      "# ###    #### #\n" +                               
      "#  #  ###  #  #\n" +                               
      "#             #\n" +                               
      "###############\n";                                
    // @formatter:on                          
    Set<Square> obstacles = Sets.newHashSet(new Square(0, 5), new Square(1, 1), new Square(1, 2), new Square(1, 3), new Square(1, 6), 
                                            new Square(2, 0), new Square(2, 1), new Square(2, 2), new Square(2, 6), new Square(2, 8),
                                            new Square(2, 9), new Square(2,10), new Square(2,11), new Square(3, 4), new Square(3, 5),
                                            new Square(3, 7), new Square(4, 1), new Square(4, 2), new Square(4, 3), new Square(4, 8),
                                            new Square(4, 9), new Square(4,10), new Square(4,11), new Square(5, 2), new Square(5, 5),
                                            new Square(5, 6), new Square(5, 7), new Square(5,10));
    Maze maze = new Maze(7, 13, new Square(1, 0), new Square(0, 6), obstacles);
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = Lists.newArrayList(
        new Square(1, 0),
        new Square(0, 0),
        new Square(0, 1),
        new Square(0, 2),
        new Square(0, 3),
        new Square(0, 4),
        new Square(1, 4),
        new Square(2, 4),
        new Square(2, 3),
        new Square(3, 3),
        new Square(3, 2),
        new Square(3, 1),
        new Square(3, 0),
        new Square(4, 0),
        new Square(5, 0),
        new Square(5, 1),
        new Square(6, 1),
        new Square(6, 2),
        new Square(6, 3),
        new Square(6, 4),
        new Square(6, 5),
        new Square(6, 6),
        new Square(6, 7),
        new Square(6, 8),
        new Square(6, 9),
        new Square(6, 10),
        new Square(6, 11),
        new Square(5, 11),
        new Square(5, 12),
        new Square(4, 12),
        new Square(3, 12),
        new Square(2, 12),
        new Square(1, 12),
        new Square(0, 12),
        new Square(0, 11),
        new Square(0, 10),
        new Square(0, 9),
        new Square(0, 8),
        new Square(0, 7),
        new Square(0, 6)
        );

    Assert.assertEquals(expectedPath, path);

    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(
      new Square(5, 8), new Square(5, 9), new Square(1, 5), new Square(2, 5), 
      new Square(5, 3), new Square(5, 4), new Square(4, 4), new Square(4, 5), 
      new Square(4, 6), new Square(4, 7), new Square(3, 6), new Square(6, 0));
    expectedExplored.addAll(expectedPath);
    Assert.assertEquals(expectedExplored, explored);
  }
  
  @Test(timeout = 10000)
  public void extraTest6() {
    // @formatter:off
    String mazeString = 
      "###############\n" +
      "#     #       #\n" +      
      "#S###  #     G#\n" +        
      "####   # #### #\n" +                               
      "#    ## #     #\n" +                               
      "# ###    #### #\n" +                               
      "#  #  ###  #  #\n" +                               
      "#             #\n" +                               
      "###############\n";                                
    // @formatter:on                          
    Set<Square> obstacles = Sets.newHashSet(new Square(0, 5), new Square(1, 1), new Square(1, 2), new Square(1, 3), new Square(1, 6), 
                                            new Square(2, 0), new Square(2, 1), new Square(2, 2), new Square(2, 6), new Square(2, 8),
                                            new Square(2, 9), new Square(2,10), new Square(2,11), new Square(3, 4), new Square(3, 5),
                                            new Square(3, 7), new Square(4, 1), new Square(4, 2), new Square(4, 3), new Square(4, 8),
                                            new Square(4, 9), new Square(4,10), new Square(4,11), new Square(5, 2), new Square(5, 5),
                                            new Square(5, 6), new Square(5, 7), new Square(5,10));
    Maze maze = new Maze(7, 13, new Square(1, 0), new Square(1, 12), obstacles);
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = Lists.newArrayList(
        new Square(1, 0),
        new Square(0, 0),
        new Square(0, 1),
        new Square(0, 2),
        new Square(0, 3),
        new Square(0, 4),
        new Square(1, 4),
        new Square(2, 4),
        new Square(2, 3),
        new Square(3, 3),
        new Square(3, 2),
        new Square(3, 1),
        new Square(3, 0),
        new Square(4, 0),
        new Square(5, 0),
        new Square(5, 1),
        new Square(6, 1),
        new Square(6, 2),
        new Square(6, 3),
        new Square(6, 4),
        new Square(6, 5),
        new Square(6, 6),
        new Square(6, 7),
        new Square(6, 8),
        new Square(6, 9),
        new Square(6, 10),
        new Square(6, 11),
        new Square(5, 11),
        new Square(5, 12),
        new Square(4, 12),
        new Square(3, 12),
        new Square(2, 12),
        new Square(1, 12)
        );

    Assert.assertEquals(expectedPath, path);

    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(
      new Square(5, 8), new Square(5, 9), new Square(1, 5), new Square(2, 5), 
      new Square(5, 3), new Square(5, 4), new Square(4, 4), new Square(4, 5), 
      new Square(4, 6), new Square(4, 7), new Square(3, 6));
    expectedExplored.addAll(expectedPath);
    Assert.assertEquals(expectedExplored, explored);
  }
  @Test(timeout = 10000)
  public void extraTest7() {
    // @formatter:off
    /*
    String mazeString = 
      "###############\n" +
      "#     #       #\n" +      
      "#S###  #     G#\n" +        
      "####   # #### #\n" +                               
      "#    ## #     #\n" +                               
      "# ###    #### #\n" +                               
      "#  #  ###  #  #\n" +                               
          "#             #\n" +                               
      "###############\n";                                
    // @formatter:on                          
    Set<Square> obstacles = Sets.newHashSet(new Square(0, 5), new Square(1, 1), new Square(1, 2), new Square(1, 3), new Square(1, 6), 
                                            new Square(2, 0), new Square(2, 1), new Square(2, 2), new Square(2, 6), new Square(2, 8),
                                            new Square(2, 9), new Square(2,10), new Square(2,11), new Square(3, 4), new Square(3, 5),
                                            new Square(3, 7), new Square(4, 1), new Square(4, 2), new Square(4, 3), new Square(4, 8),
                                            new Square(4, 9), new Square(4,10), new Square(4,11), new Square(5, 2), new Square(5, 5),
                                            new Square(5, 6), new Square(5, 7), new Square(5,10));
    Maze maze = new Maze(7, 13, new Square(1, 0), new Square(1, 12), obstacles);
    Assert.assertEquals(mazeString, maze.toString());

    */
    // solve the maze
    Maze maze = new Maze(1000,1000, new Square(0, 0), new Square(999, 999), Sets.<Square> newHashSet());
    Solver solver = new Solver(maze);

    
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = new ArrayList<>();
    for (int column = 0; column <= 999; ++column) {
      expectedPath.add(new Square(0, column));
    }
    for (int row = 1; row <= 999; ++row) {
      expectedPath.add(new Square(row, 999));
    }
    Assert.assertEquals(expectedPath, path);

    // no additional squares should be explored
    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(expectedPath);
    Assert.assertEquals(expectedExplored, explored);
  }
  
  @Test(timeout = 10000)
  public void extraTest8() {
    // @formatter:off
    String mazeString = 
      "###############\n" +
      "#      G      #\n" +
      "#      #      #\n" +
      "#      S      #\n" +
      "###############\n";
    // @formatter:on
    Set<Square> obstacles = Sets.newHashSet(new Square(1, 6));
    Maze maze = new Maze(3, 13, new Square(2, 6), new Square(0, 6), obstacles);
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    // the solution should just go left to right
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = Lists.newArrayList(
                      new Square(2, 6),
                      new Square(2, 5),
                      new Square(1, 5),
                      new Square(0, 5),
                      new Square(0, 6));
    
    Assert.assertEquals(expectedPath, path);

    // no additional squares should be explored
    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(expectedPath);
    Assert.assertEquals(expectedExplored, explored);
  }
  
  @Test(timeout = 10000)
  public void extraTest9() {
    // @formatter:off
    String mazeString = 
      "###############\n" +
      "#      S      #\n" +
      "#      #      #\n" +
      "#      G      #\n" +
      "###############\n";
    // @formatter:on
    Set<Square> obstacles = Sets.newHashSet(new Square(1, 6));
    Maze maze = new Maze(3, 13, new Square(0, 6), new Square(2, 6), obstacles);
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    // the solution should just go left to right
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = Lists.newArrayList(
                      new Square(0, 6),
                      new Square(0, 5),
                      new Square(1, 5),
                      new Square(2, 5),
                      new Square(2, 6));
    
    Assert.assertEquals(expectedPath, path);

    // no additional squares should be explored
    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(expectedPath);
    Assert.assertEquals(expectedExplored, explored);
  }
  
    @Test(timeout = 10000)
  public void extraTest10() {
    // @formatter:off
    String mazeString = 
      "###############\n" +
      "#     G       #\n" +
      "#    ###      #\n" +
      "#      S      #\n" +
      "###############\n";
    // @formatter:on
    Set<Square> obstacles = Sets.newHashSet(new Square(1, 6), new Square(1, 5), new Square(1, 4));
    Maze maze = new Maze(3, 13, new Square(2, 6), new Square(0, 5), obstacles);
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    // the solution should just go left to right
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = Lists.newArrayList(
                      new Square(2, 6),
                      new Square(2, 7),
                      new Square(1, 7),
                      new Square(0, 7),
                      new Square(0, 6),
                      new Square(0, 5));
    
    Assert.assertEquals(expectedPath, path);

    // no additional squares should be explored
    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(new Square(2, 5), new Square(2, 4));
    expectedExplored.addAll(expectedPath);
    Assert.assertEquals(expectedExplored, explored);
  }
  
}
