import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;


public enum Action {

	ROCK    ( 0, -1,  1,  1, -1, 1),
	PAPER   ( 1,  0, -1, -1,  1, 2),
	SCISSORS(-1,  1,  0,  1, -1, 3),
	LIZARD  (-1,  1, -1,  0,  1, 4),
	SPOCK   ( 1, -1,  1, -1,  0, 5);
	
	private Map<Action, Integer> resultMap;
	private Set<Action> winSet;
	private Set<Action> loseSet;
	private int tiebreakNumber;
	
	private int versusRock;
	private int versusPaper;
	private int versusScissors;
	private int versusLizard;
	private int versusSpock;
	
	private Action(int versusRock, int versusPaper, int versusScissors, int versusLizard, int versusSpock, int tiebreakNumber)
	{
		this.versusRock = versusRock;
		this.versusPaper = versusPaper;
		this.versusScissors = versusScissors;
		this.versusLizard = versusLizard;
		this.versusSpock = versusSpock;
		this.tiebreakNumber = tiebreakNumber;
	}
	
	public void initialize()
	{
		setupResultMap(versusRock, versusPaper, versusScissors, versusLizard, versusSpock);
		setupWinSet();
		setupLoseSet();
	}
	
	private void setupResultMap(int versusRock, int versusPaper, int versusScissors, int versusLizard, int versusSpock)
	{
		Map<Action, Integer> resultMap = Maps.newLinkedHashMap();
		resultMap.put(ROCK, versusRock);
		resultMap.put(PAPER, versusPaper);
		resultMap.put(SCISSORS, versusScissors);
		resultMap.put(LIZARD, versusLizard);
		resultMap.put(SPOCK, versusSpock);
		this.resultMap = ImmutableMap.copyOf(resultMap);
	}
	
	private void setupWinSet()
	{
		winSet = ImmutableSet.copyOf(Maps.filterEntries(resultMap, new Predicate<Entry<Action, Integer>>() {
			public boolean apply(Entry<Action, Integer> entry) {
				return entry.getValue() == 1;
			}
		}).keySet());
	}

	private void setupLoseSet()
	{
		loseSet = ImmutableSet.copyOf(Maps.filterEntries(resultMap, new Predicate<Entry<Action, Integer>>() {
			public boolean apply(Entry<Action, Integer> entry) {
				return entry.getValue() == -1;
			}
		}).keySet());
	}

	public Set<Action> getWinSet() {
		return winSet;
	}
	
	public Set<Action> getLoseSet() {
		return loseSet;
	}
	
	public Map<Action, Integer> getResultMap() {
		return resultMap;
	}
	
	public int getTiebreakNumber() {
		return tiebreakNumber;
	}
	
	public static Action breakTieHigh(Iterable<Action> potentialActions)
	{
		return Iterables.getFirst(Ordering.natural().onResultOf(new Function<Action, Integer>() {
			public Integer apply(Action input) {
				return input.getTiebreakNumber();
			}
		}).sortedCopy(potentialActions), Action.PAPER);
	}

	public static Action breakTieLow(Iterable<Action> potentialActions)
	{
		return Iterables.getLast(Ordering.natural().onResultOf(new Function<Action, Integer>() {
			public Integer apply(Action input) {
				return input.getTiebreakNumber();
			}
		}).sortedCopy(potentialActions), Action.PAPER);
	}
	
}
