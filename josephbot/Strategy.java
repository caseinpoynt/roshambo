
public interface Strategy {

	public Action createNextAction();
	public void acceptOpponentsLastAction(Action action);
	
}
