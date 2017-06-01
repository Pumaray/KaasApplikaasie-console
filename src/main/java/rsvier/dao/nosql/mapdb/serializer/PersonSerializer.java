package rsvier.dao.nosql.mapdb.serializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.serializer.GroupSerializerObjectArray;

import rsvier.model.Address;
import rsvier.model.Person;
import rsvier.model.Person.Gender;
import rsvier.model.User;

public class PersonSerializer extends GroupSerializerObjectArray<Person> {

	final DateTimeFormatter timeF  = DateTimeFormatter.ISO_DATE_TIME;
	final DateTimeFormatter dateF =  DateTimeFormatter.ISO_DATE;

	@Override
	public Person deserialize(DataInput2 in, int index) throws IOException {
		return new Person.PersonBuilder(in.readLong())
				.surname(in.readLine())
				.lastname(in.readLine())
				.gender(Gender.valueOf(in.readLine()))
				.birthdate(LocalDate.parse(in.readLine(), dateF))
				.address(deserializeAddress(in, index))
				.mailAddress(in.readLine())
				//.creator(new User(in.readLong(), null, null, null, null))
				.creationDate(LocalDateTime.parse(in.readLine(), timeF))
				.build();
	}

	@Override
	public void serialize(DataOutput2 out, Person person) throws IOException {
		out.writeLong(person.getId());
		out.writeChars(person.getSurname());
		out.writeChars(person.getLastname());
		out.writeChars(person.getGender().name());
		out.writeChars(dateF.format(person.getBirthdate()));
		serializeAddress(out, person.getAddress());
		out.writeChars(person.getMailAddress());
		//out.writeLong(person.getCreator().getId());
		out.writeChars(timeF.format(person.getCreationDate()));

	}

	public void serializeAddress(DataOutput2 out, Address address) throws IOException {
		out.writeChars(address.getStreetName());
		out.writeChars(address.getCity());
		out.writeChars(address.getZipCode());
		out.writeInt(address.getNumber());
	}

	public Address deserializeAddress(DataInput2 in, int index) throws IOException {
		return new Address.AddressBuilder()
				.streetName(in.readLine())
				.number(in.readInt())
				.zipCode(in.readLine())
				.city(in.readLine())
				.build();
	}
}
