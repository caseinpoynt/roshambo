import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Roshambo {

	public static void main(String[] args) throws Exception {
		initializeActions();
		Strategy strategy = new WhatHaveYouDoneForMeLatelyStrategy();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			Action myAction = strategy.createNextAction();
			System.out.println(myAction.name().toLowerCase());
			String yourActionString = reader.readLine();
			if(yourActionString.equals("goodbye")) return;
			Action yourAction = Action.valueOf(yourActionString.toUpperCase());
			strategy.acceptOpponentsLastAction(yourAction);
		}
	}

	protected static void initializeActions()
	{
		for(Action action : Action.values())
		{
			action.initialize();
		}
	}
	
}
