package rsvier.controller;

import rsvier.view.Screen;
import rsvier.view.View;

public abstract class AbstractController {

	protected Screen screen;
	protected final View view;

	protected AbstractController(View view) {
		this.view = view;
	}
	
	protected abstract boolean canDoAction();
	
	protected abstract void doAction();
	
	public void performActionOn(Screen screen) {
		this.screen = screen;
		if(canDoAction()) {
			doAction();
		}
	}

}
