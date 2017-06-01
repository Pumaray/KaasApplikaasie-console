package rsvier.dao.factory;

import java.io.IOException;
import java.util.Properties;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import rsvier.dao.PersonDAO;
import rsvier.dao.SupportedDatabase;
import rsvier.dao.nosql.mapdb.MapDBPersonDAO;
import rsvier.dao.nosql.mapdb.MapIdManager;

public class PersonDAOFactory {

	private final SupportedDatabase database;
	private final Properties properties = new Properties();

	public PersonDAOFactory(SupportedDatabase database) throws IOException {
		this.database = database;
		properties.load(getClass().getResourceAsStream("/db/" + database.getConfigurationFile()));
	}

	public PersonDAO get() {
		PersonDAO dao = null;
		switch (database) {
		case MAPDB_TEST:
			dao = getMAPDBInstance();
			break;
		}
		return dao;
	}

	private DB getMapDBDatabase() {
		String url = properties.getProperty(rsvier.dao.SupportedDatabase.URL);
		if (url.trim().equals("in-memory")) {
			return DBMaker.heapDB()
					.transactionEnable()
					.closeOnJvmShutdown()
					.make();
		}
		else {
			return DBMaker.fileDB(url)
					.transactionEnable()
					.closeOnJvmShutdown()
					.make();
		}
	}

	private PersonDAO getMAPDBInstance() {
		DB db = getMapDBDatabase();
		return new MapDBPersonDAO(new MapIdManager(db), db);
	}

	public SupportedDatabase getDatabase() {
		return database;
	}

}
