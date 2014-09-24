/* Inha Joo
 * CS411
 * HW 3 - fifteen puzzle
 * AStar Algorithm
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;


public class AStar {
	// trial boards
	static Puzzle board = new Puzzle();
	static Puzzle board2 = new Puzzle( new int[] {1, 0, 2, 4, 5, 7, 3, 8, 9, 6, 11, 12, 13, 10, 14, 15});
	

	public static void main(String[] args) {
		
		// get input from text file
		BufferedReader br = null;
		String sCurrentLine;
		 
		try{
		br = new BufferedReader(new FileReader("C:\\Users\\rejoovenation\\workspace\\CS411_HW3\\src\\numbers.txt"));
		
		sCurrentLine = br.readLine();
		System.out.println("Input Board: " + sCurrentLine);
		
		

		String tar = sCurrentLine;
		String[] splitUp = tar.split(" ");
		
		int [] b = new int[16];
		
		for(int i = 0; i < 16; i++)
		{
			b[i] = Integer.parseInt(splitUp[i]);
		}
	    
	    Puzzle boardIn = new Puzzle(b);

		puzzleAStarDemo(boardIn);
		puzzleAStarManhattanDemo(boardIn);
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		

	    
	}


	private static void puzzleAStarManhattanDemo(Puzzle b) {
		System.out.println("\nA* Heuristic 2-->");
		try {
			Problem problem = new Problem(b, puzzleFactory
					.getActionsFunction(), puzzleFactory
					.getResultFunction(), new goalTest());
			Search search = new AStarSearch(new GraphSearch(),
					new MHFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void puzzleAStarDemo(Puzzle b) {
		System.out.println("\nA* Heuristic 1-->");
		try {
			Problem problem = new Problem(b, puzzleFactory
					.getActionsFunction(), puzzleFactory
					.getResultFunction(), new goalTest());
			Search search = new AStarSearch(new GraphSearch(),
					new MTHFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			if(key.equals("pathCost"))
				System.out.println("Number of Moves : " + property);
			if(key.equals("nodesExpanded"))
				System.out.println("Nodes Expanded: " + property);
		}

	}

	private static void printActions(List<Action> actions) {
		System.out.println("\nActions taken:");
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println(action);
		}
		System.out.println();
	}

}