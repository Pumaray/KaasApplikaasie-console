package rsvier.dao;

import java.util.List;

import javax.management.relation.Role;

import rsvier.model.User;

public interface UserDAO extends PersistenceDAO<Long, User> {

	
	public User findByNameAndPassword(String name, char[] password);
	
	public List<User> findAllUserWithRole(Role role);
}
