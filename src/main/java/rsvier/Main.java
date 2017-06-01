package rsvier;

import org.apache.log4j.Logger;

import rsvier.view.View;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			new View();
		}
		catch (Exception ex) {
			logger.error("Error stating application", ex);
		}
	}

}
