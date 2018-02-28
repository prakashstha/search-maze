package edu.uab.cis.search.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Solves a maze using A* search with an L1 heuristic.
 * 
 * Specifically, squares are explored with the following strategy:
 * <ul>
 * <li>Squares are ordered for exploration using the score f(x) = g(x) + h(x),
 * with the smallest f(x) squares being explored first</li>
 * <li>g(x) is the length of the path so far, from the start square to the
 * current square, including the steps necessary to avoid obstacles</li>
 * <li>h(x) is the L1 estimate of the path to the goal, that is, the Manhattan
 * distance to the goal, ignoring potential obstacles</li>
 * <li>Squares with the same f(x) score are ordered by the h(x) score, with
 * smaller h(x) scores first</li>
 * <li>Squares with the same f(x) and h(x) scores are ordered by row, with
 * smaller rows first</li>
 * <li>Squares with the same f(x), h(x) and row should be ordered by column,
 * with smaller columns first</li>
 * </ul>
 */
public class Solver {

  private Set<Square> explored;

  private List<Square> path;

  private Maze maze;
  /**
   * Solves the given maze, determining the path to the goal.
   * 
   * @param maze
   *          The maze to be solved.
   */
  public Solver(Maze maze) {
        this.maze = maze;
        findOptimalPath();  
// TODO
  }
 
  /**
   * @return The squares along the path from the start to the goal, not
   *         including the start square and the goal square.
   */
  public List<Square> getPathFromStartToGoal() {
    return this.path;
  }

  /**
   * @return All squares that were explored during the search process. This is
   *         always a superset of the squares returned by
   *         {@link #getPathFromStartToGoal()}.
   */
  public Set<Square> getExploredSquares() {
    return this.explored;
  }

   /**
   * Finds the neighboring square that is not blocked and has not been 
   * explored.
   * 
   * @param sq
   *          A square in a maze whose neighbor is to be find.
   * @return 
   *         List of neighboring square in a maze
   */
  public List<Square> getNeighbor(Square sq)
  {
        List<Square> neighbours = new ArrayList<>();
        int row = sq.getRow();
        int col = sq.getColumn();
        //possible neighbors are up, down, left and right square of current square
        Square up = new Square(row-1, col);
        Square down = new Square(row+1, col);
        Square right = new Square(row, col+1);
        Square left = new Square(row, col-1);
        
        //check if neighbors are blocked/explored or not
        if (!maze.isBlocked(up) && !explored.contains(up))
            neighbours.add(up);
        if (!maze.isBlocked(down) && !explored.contains(down))
            neighbours.add(down);
        if (!maze.isBlocked(left) && !explored.contains(left))
            neighbours.add(left);
        if (!maze.isBlocked(right) && !explored.contains(right))
            neighbours.add(right);
      
      return neighbours;
  }
/**
 * Finds the best path from start to goal square in maze using A* search
 */
    private void findOptimalPath() {
        explored = new HashSet<>();
        //initialize gx = 0 and hx for the start square
        int gx = 0;
        int hx = calculateManHattamDistance(maze.getStart());
        List<Square> branchSquareList = new ArrayList<>();
        //create start node
        Node n = new Node(maze.getStart(),null, hx, gx);
        PriorityQueue pq = new PriorityQueue();
        //add start node in priority queue
        pq.add(n);
        
        while(true)
        {
            //fetch node from priority queue
            Node pop = (Node) pq.poll();
            //add the fetched node in explored list
            explored.add(pop.sq);
                
            //if fetched node is goal, track the parent node until you get the path from goal to start node 
            if(maze.getGoal().equals(pop.sq))
            {
                path = new ArrayList<>();
                path.add(pop.sq);
                Node par = pop.parentNode;
                while(par!= null)
                {
                    path.add(par.sq);
                    par = par.parentNode;
                }
                //reverse the path to get path from start to goal
                Collections.reverse(path);
                break;
            }
            //if current node is not goal node
            else
            {
                //get the neighbors 
                branchSquareList = getNeighbor(pop.sq);
                gx = pop.gx+1;
                //for each neighbors calculate cost function and add it to a priority queue
                for(Square sqr: branchSquareList)
                {
                    hx = calculateManHattamDistance(sqr);
                    Node m = new Node(sqr, pop, hx, gx);
                    pq.add(m);
                }
            }
            
        }
     }
    /**
     * 
     * @param sq Square
     * @return ManhattamDistance from current square 'sq' to goal square
     */
    private int calculateManHattamDistance(Square sq) {
        Square goal = maze.getGoal();
        return (Math.abs(sq.getRow()-goal.getRow())+Math.abs(sq.getColumn()-goal.getColumn()));
    }

/*
    Method to test the priorityQueue functioning
*/
//    public static void testPriorityQueue()
//    {
//        PriorityQueue pq = new PriorityQueue();
//        Node a = new Node(new Square(5, 1), 12, 10);
//        pq.add(a);
//        a = new Node(new Square(1, 7), 12, 10);
//        pq.add(a);
//        a = new Node(new Square(1, 5), 12, 10);
//        pq.add(a);        
//        a = new Node(new Square(3, 1), 10, 15);
//        pq.add(a);
//        a = new Node(new Square(-1, 1), 12, 10);
//        pq.add(a);
//        a = new Node(new Square(1, 1), 15, 14);
//        pq.add(a);
//        a = new Node(new Square(1, 1), 10, 13);
//        pq.add(a);
//        a = new Node(new Square(1, 1), 10, 15);
//        pq.add(a);
//        a = new Node(new Square(1, 2), 12, 10);
//        pq.add(a);        
//        Node m = (Node)pq.poll();
//        System.out.println(m.toString());
//        m = (Node)pq.poll();
//        System.out.println(m.toString());
//        m = (Node)pq.poll();
//        System.out.println(m.toString());
//        m = (Node)pq.poll();
//        System.out.println(m.toString());
//        m = (Node)pq.poll();
//        System.out.println(m.toString());
//        m = (Node)pq.poll();
//        System.out.println(m.toString());
//        m = (Node)pq.poll();
//        System.out.println(m.toString());
//    
//    }
   /**
   * Solves the given maze, determining the path to the goal.
   * 
   * @param maze
   *          The maze to be solved.
   */
  static class Node implements Comparable
    {
        Square sq;
        int hx;
        int gx;
        Node parentNode;
        /**
         * 
         * @param sq Square
         * @param parentNode Parent node of current node
         * @param hx Heuristic for the search 
         *           Manhattan distance from current node to Goal node
         * @param gx The cost it took to get to the node. 
         *           Number of squares passed from the start
         */
        public Node(Square sq, Node parentNode, int hx, int gx) {
            this.sq = sq;
            this.hx = hx;
            this.gx = gx;
            this.parentNode = parentNode;
            
        }
        /**
         * Orders the node in queue on the basis of its cost function
         * 
         * @param o
         * @return 
         */
                  
        @Override
        public int compareTo(Object o) {
            Node that = (Node)o;
            int f1 = this.gx + this.hx;
            int f2 = that.gx + that.hx;
            
            if (f1>f2)
                return 1;
            else if (f1<f2)
                return -1;
            else
            {
                if (this.hx>that.hx)
                    return 1;
                else if (this.hx<that.hx)
                    return -1;
                else
                {
                    if (this.sq.getRow()>that.sq.getRow())
                        return 1;
                    else if(this.sq.getRow()<that.sq.getRow())
                        return -1;
                    else
                    {
                        if (this.sq.getColumn()>that.sq.getColumn())
                            return 1;
                        else if(this.sq.getColumn()<that.sq.getColumn())
                            return -1;
                        else
                            return 0;
                    }
                }
                    
            }
               
        }
    }

}
