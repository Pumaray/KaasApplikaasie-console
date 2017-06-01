package rsvier.dao.nosql.mapdb;

import java.util.concurrent.ConcurrentMap;

import org.mapdb.DB;
import org.mapdb.Serializer;

import rsvier.dao.PersistenceDAO;

public abstract class AbstractMapDBPersistence<K,E> {

	protected DB db;
	protected ConcurrentMap<K, E> storage;

	protected AbstractMapDBPersistence(String tableName, DB db, Serializer<K> keySerializer,
			Serializer<E> entitySerializer) {
		storage = db
				.hashMap(tableName, keySerializer, entitySerializer)
				.createOrOpen();
	}

}
