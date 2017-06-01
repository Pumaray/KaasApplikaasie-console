package rsvier.dao.nosql.mapdb;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import rsvier.dao.PersonDAO;
import rsvier.dao.SupportedDatabase;
import rsvier.dao.factory.PersonDAOFactory;
import rsvier.model.User;
import rsvier.test.Generator;

public class MapDBPersonDAO_IT {

	private PersonDAO dao;
	private Generator gen = new Generator();

	@Before
	public void setup() throws IOException {
		try {
			dao = new PersonDAOFactory(SupportedDatabase.MAPDB_TEST).get();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		try {
			assertNotNull(dao.save(gen.generatePerson(1, new User(25, null, null, null, null))));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}