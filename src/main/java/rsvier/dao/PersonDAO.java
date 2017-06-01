package rsvier.dao;

import java.util.List;

import rsvier.model.Person;

public interface PersonDAO extends PersistenceDAO<Person> {

	public Person findByName(String name);

	public List<Person> findAll();

}
