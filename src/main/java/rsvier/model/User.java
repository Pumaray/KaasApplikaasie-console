package rsvier.model;

public class User {

	private final long id;
	private final String name;
	private final char[] password;
	private final Role role;
	private final Person person;

	public User(long id, String name, char[] password, Role role,
			Person person) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
		this.person = person;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public char[] getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}

	public Person getPerson() {
		return person;
	}

	public enum Role {
		ADMIN, CLIENT, USER, EMPLOYEE;
	}

}
