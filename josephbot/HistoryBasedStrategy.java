import java.util.List;

import com.google.common.collect.Lists;


public abstract class HistoryBasedStrategy implements Strategy {

	protected List<ActionPair> history = Lists.newArrayList();
	
	protected Action myAction;
	
	public Action createNextAction() {
		Action nextAction = determineNextAction();
		myAction = nextAction;
		return nextAction;
	}
	
	protected abstract Action determineNextAction();
	
	public void acceptOpponentsLastAction(Action yourAction) {
		history.add(new ActionPair(myAction, yourAction));
	}
	
	public List<ActionPair> getHistory() {
		return history;
	}
	
}
