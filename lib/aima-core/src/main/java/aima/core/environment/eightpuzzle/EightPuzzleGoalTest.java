package aima.core.environment.puzzle;

import aima.core.search.framework.GoalTest;

/**
 * Inha Joo - ijoo2  
 */
public class EightPuzzleGoalTest implements GoalTest {
	Puzzle goal = new Puzzle(new int[] { 1, 2, 3, 4, 5,
			6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0});

	public boolean isGoalState(Object state) {
		Puzzle board = (Puzzle) state;
		return board.equals(goal);
	}
}