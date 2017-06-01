package rsvier.dao.nosql.mapdb;

import org.mapdb.DB;
import org.mapdb.Serializer;

import rsvier.dao.nosql.IdManager;

public class MapIdManager extends AbstractMapDBPersistence<String, Long> implements IdManager {

	private static final String TABLE_NAME = "ID";

	private final String ID = "id";
	private long key = 0;

	public MapIdManager(DB db) {
		super(TABLE_NAME, db, Serializer.STRING, Serializer.LONG);

		init();
	}

	private void init() {
		if (!storage.containsKey(ID)) {
			save();
		}
		else {
			key = storage.get(ID);
		}
	}

	private void save() {
		storage.put(ID, key);
	}

	public long get() {
		key++;
		save();
		return key;
	}
}
