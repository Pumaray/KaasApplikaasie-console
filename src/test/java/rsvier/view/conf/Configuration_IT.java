package rsvier.view.conf;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import rsvier.dao.SupportedDatabase;

public class Configuration_IT {

	private static final Logger logger = Logger.getLogger(Configuration_IT.class);

	private Configuration configuration;

	@Before
	public void setup() throws IOException {
		try {
			configuration = Configuration.getInstance();
		}
		catch (Exception ex) {
			logger.error("Error creating Configuration", ex.fillInStackTrace());
			throw(ex);
		}
	}

	@Test
	public void testCreate() {
		try {
			assertNotNull(configuration);
		}
		catch (Exception ex) {
			logger.error("Error creating Configuration", ex.fillInStackTrace());
			fail(ex.getMessage());
		}
	}
}
