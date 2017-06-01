package rsvier.dao.nosql.mapdb;

import java.util.ArrayList;
import java.util.List;

import org.mapdb.DB;
import org.mapdb.Serializer;

import rsvier.dao.PersonDAO;
import rsvier.dao.nosql.IdManager;
import rsvier.dao.nosql.mapdb.serializer.PersonSerializer;
import rsvier.model.Person;

public class MapDBPersonDAO extends AbstractMapDBPersistence<Long, Person> implements PersonDAO {

	public final static String TABLE_NAME = "PERSON";

	private IdManager idManager;

	public MapDBPersonDAO(IdManager idManager, DB db) {
		super(TABLE_NAME, db, Serializer.LONG, new PersonSerializer());
		this.idManager = idManager;
	}

	public Person cloneWithNewId(Person person) {
		return new Person.PersonBuilder(idManager.get())
				.address(person.getAddress())
				.birthdate(person.getBirthdate())
				.creationDate(person.getCreationDate())
				// .creator(person.getCreator())
				.gender(person.getGender())
				.lastname(person.getLastname())
				.surname(person.getSurname())
				.mailAddress(person.getMailAddress())
				.build();
	}

	@Override
	public Person save(Person e) {
		Person person = cloneWithNewId(e);
		storage.put(person.getId(), person);
		return person;
	}

	@Override
	public boolean remove(Person e) {
		return storage.remove(e.getId()) != null;
	}

	@Override
	public Person findById(Long id) {
		return storage.get(id);
	}

	@Override
	public Person merge(Person e) {
		return storage.replace(e.getId(), e);
	}

	@Override
	public Person findByName(String name) {
		return storage.values().stream()
				.filter(p -> p.getSurname().equals(name))
				.findAny()
				.orElse(null);
	}

	@Override
	public List<Person> findAll() {
		return new ArrayList<Person>(storage.values());
	}
}
