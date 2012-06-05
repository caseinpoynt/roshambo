
public class ActionPair {
	
	protected Action myAction;
	protected Action yourAction;
	
	public ActionPair(Action myAction, Action yourAction) {
		this.myAction = myAction;
		this.yourAction = yourAction;
	}
	
	public Action getMyAction() {
		return myAction;
	}
	
	public Action getYourAction() {
		return yourAction;
	}
	
	public int getResult()
	{
		return myAction.getResultMap().get(yourAction);
	}

}
