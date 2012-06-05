import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class WhatHaveYouDoneForMeLatelyStrategy implements Strategy {

	protected static final Set<? extends HistoryBasedStrategy> POTENTIAL_STRATEGIES = ImmutableSet.of(
			new LastActionPairStrategy.CounterHighTiebreak(),
			new LastActionPairStrategy.CounterLowTiebreak(),
			new LastActionPairStrategy.DoubleCounterHighTiebreak(),
			new LastActionPairStrategy.DoubleCounterLowTiebreak(),
			new LastActionPairStrategy.DoubleCounterMinusCounterHighTiebreak(),
			new LastActionPairStrategy.DoubleCounterMinusCounterLowTiebreak()
			);
	
	protected Strategy initialStrategy = new LastActionPairStrategy.CounterHighTiebreak();
	
	public Action createNextAction() {
		Strategy chosenStrategy = chooseStrategy();
		for(Strategy strategy : POTENTIAL_STRATEGIES)
		{
			if(strategy != chosenStrategy)
			{
				strategy.createNextAction();
			}
		}
		return chosenStrategy.createNextAction();
	}

	public void acceptOpponentsLastAction(Action action) {
		for(Strategy strategy : POTENTIAL_STRATEGIES)
		{
			strategy.acceptOpponentsLastAction(action);
		}
	}
	
	protected Strategy chooseStrategy()
	{
		if(initialStrategy != null)
		{
			Strategy returnStrategy = initialStrategy;
			initialStrategy = null;
			return returnStrategy;
		}
		else
		{
			return Ordering.natural().onResultOf(new Function<HistoryBasedStrategy, Integer>() {
				public Integer apply(HistoryBasedStrategy input) {
					int multiplier = 16;
					int sum = 0;
					for(ActionPair actionPair : Lists.partition(Lists.reverse(input.getHistory()), 5).get(0))
					{
						sum += multiplier * actionPair.getResult();
						multiplier /= 2;
					}
					return sum;
				}
			}).max(POTENTIAL_STRATEGIES);
		}
	}

}
