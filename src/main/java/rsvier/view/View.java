package rsvier.view;

import java.io.IOException;

public class View {

	public View() throws IOException {

		new UserScreen(System.console()).show();
	}

}
