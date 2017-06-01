package rsvier.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person {

	private final long id;
	private final String surname;
	private final String lastname;
	private final Gender gender;
	private final LocalDate birthdate;
	private final Address address;
	private final String mailAddress;
	//private final User creator;
	private final LocalDateTime creationDate;

	public Person(long id, String surname, String lastname, Gender gender,
			LocalDate birthdate, Address address, String mailAddress,
			/*User creator,*/ LocalDateTime creationDate) {
		this.id = id;
		this.surname = surname;
		this.lastname = lastname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.address = address;
		this.mailAddress = mailAddress;
		//this.creator = creator;
		this.creationDate = creationDate;
	}

	public long getId() {
		return id;
	}

	public String getSurname() {
		return surname;
	}

	public String getLastname() {
		return lastname;
	}

	public Gender getGender() {
		return gender;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public Address getAddress() {
		return address;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	/*public User getCreator() {
		return creator;
	}
	*/

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public enum Gender {

		MALE, FEMALE, OTHER,
	}

	public static class PersonBuilder {

		long id;
		String surname;
		String lastname;
		Gender gender;
		LocalDate birthdate;
		Address address;
		String mailAddress;
		//User creator;
		LocalDateTime creationDate;

		public PersonBuilder(long id) {
		}

		public PersonBuilder surname(String surname) {
			this.surname = surname;
			return this;
		}

		public PersonBuilder lastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public PersonBuilder birthdate(LocalDate birthdate) {
			this.birthdate = birthdate;
			return this;
		}

		public PersonBuilder gender(Gender gender) {
			this.gender = gender;
			return this;
		}

		public PersonBuilder address(Address address) {
			this.address = address;
			return this;
		}

		public PersonBuilder mailAddress(String mailAddress) {
			this.mailAddress = mailAddress;
			return this;
		}

		/*public PersonBuilder creator(User creator) {
			this.creator = creator;
			return this;
		}*/

		public PersonBuilder creationDate(LocalDateTime creationDate) {
			this.creationDate = creationDate;
			return this;
		}

		public Person build() {
			return new Person(id,surname, lastname, gender, birthdate, address,
					mailAddress, /*creator,*/ creationDate);
		}
	}

}
