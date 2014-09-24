
import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

/**
 * Jed Siripun - jsirip2
 */
public class puzzleFactory {
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	private static class EPActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {
			Puzzle board = (Puzzle) state;

			Set<Action> actions = new LinkedHashSet<Action>();

			if (board.canMoveGap(Puzzle.UP)) {
				actions.add(Puzzle.UP);
			}
			if (board.canMoveGap(Puzzle.DOWN)) {
				actions.add(Puzzle.DOWN);
			}
			if (board.canMoveGap(Puzzle.LEFT)) {
				actions.add(Puzzle.LEFT);
			}
			if (board.canMoveGap(Puzzle.RIGHT)) {
				actions.add(Puzzle.RIGHT);
			}

			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			Puzzle board = (Puzzle) s;

			if (Puzzle.UP.equals(a)
					&& board.canMoveGap(Puzzle.UP)) {
				Puzzle newBoard = new Puzzle(board);
				newBoard.moveGapUp();
				return newBoard;
			} else if (Puzzle.DOWN.equals(a)
					&& board.canMoveGap(Puzzle.DOWN)) {
				Puzzle newBoard = new Puzzle(board);
				newBoard.moveGapDown();
				return newBoard;
			} else if (Puzzle.LEFT.equals(a)
					&& board.canMoveGap(Puzzle.LEFT)) {
				Puzzle newBoard = new Puzzle(board);
				newBoard.moveGapLeft();
				return newBoard;
			} else if (Puzzle.RIGHT.equals(a)
					&& board.canMoveGap(Puzzle.RIGHT)) {
				Puzzle newBoard = new Puzzle(board);
				newBoard.moveGapRight();
				return newBoard;
			}

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}