import java.util.Set;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public abstract class LastActionPairStrategy extends HistoryBasedStrategy {

	@Override
	protected Action determineNextAction() {
		if(!history.isEmpty())
		{
			ActionPair lastActionPair = Iterables.getLast(history, new ActionPair(Action.LIZARD, Action.SPOCK));
			return determineNextActionBasedOnLastActionPair(lastActionPair);
		}
		else
		{
			return Action.PAPER;
		}
	}

	protected abstract Action determineNextActionBasedOnLastActionPair(ActionPair actionPair);
	
	public static class CounterHighTiebreak extends LastActionPairStrategy {
		@Override
		protected Action determineNextActionBasedOnLastActionPair(ActionPair actionPair) {
			return Action.breakTieHigh(actionPair.getYourAction().getLoseSet());
		}
	}
	
	public static class CounterLowTiebreak extends LastActionPairStrategy {
		@Override
		protected Action determineNextActionBasedOnLastActionPair(ActionPair actionPair) {
			return Action.breakTieLow(actionPair.getYourAction().getLoseSet());
		}
	}

	public static class DoubleCounterHighTiebreak extends LastActionPairStrategy {
		@Override
		protected Action determineNextActionBasedOnLastActionPair(ActionPair actionPair) {
			return Action.breakTieHigh(Iterables.concat(
					Action.breakTieHigh(actionPair.getYourAction().getLoseSet()).getLoseSet(),
					Action.breakTieLow(actionPair.getYourAction().getLoseSet()).getLoseSet()));
		}
	}

	public static class DoubleCounterLowTiebreak extends LastActionPairStrategy {
		@Override
		protected Action determineNextActionBasedOnLastActionPair(ActionPair actionPair) {
			return Action.breakTieLow(Iterables.concat(
					Action.breakTieHigh(actionPair.getYourAction().getLoseSet()).getLoseSet(),
					Action.breakTieLow(actionPair.getYourAction().getLoseSet()).getLoseSet()));
		}
	}
	
	public static class DoubleCounterMinusCounterHighTiebreak extends LastActionPairStrategy {
		@Override
		protected Action determineNextActionBasedOnLastActionPair(ActionPair actionPair) {
			Set<Action> options = Sets.newHashSet(Iterables.concat(
					Action.breakTieHigh(actionPair.getYourAction().getLoseSet()).getLoseSet(),
					Action.breakTieLow(actionPair.getYourAction().getLoseSet()).getLoseSet()));
			options.removeAll(actionPair.getYourAction().getLoseSet());
			return Action.breakTieHigh(options);
		}
	}

	public static class DoubleCounterMinusCounterLowTiebreak extends LastActionPairStrategy {
		@Override
		protected Action determineNextActionBasedOnLastActionPair(ActionPair actionPair) {
			Set<Action> options = Sets.newHashSet(Iterables.concat(
					Action.breakTieHigh(actionPair.getYourAction().getLoseSet()).getLoseSet(),
					Action.breakTieLow(actionPair.getYourAction().getLoseSet()).getLoseSet()));
			options.removeAll(actionPair.getYourAction().getLoseSet());
			return Action.breakTieLow(options);
		}
	}
	
}
