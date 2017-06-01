package rsvier.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import rsvier.model.Address;
import rsvier.model.Person;
import rsvier.model.Person.Gender;
import rsvier.model.User;

public class Generator {
	
	public Person generatePerson(int index, User creator) {
		return new Person.PersonBuilder(new Long(index))
				.surname("Surname " + index)
				.lastname("Last name" + index)
				.birthdate(LocalDate.now())
				.creationDate(LocalDateTime.now())
			//	.creator(creator)
				.gender(Gender.MALE)
				.mailAddress("Mail Address " + index)
				.address(new Address.AddressBuilder()
						.city("City" + index)
						.number(index)
						.streetName("Street Name " + index)
						.zipCode(new Random().nextInt(9999) + 1000 + "AA")
						.build()
						)
				.build();
						
	}

}
