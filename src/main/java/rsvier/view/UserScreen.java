package rsvier.view;

import java.io.Console;
import java.io.IOException;
import java.time.LocalDateTime;

import rsvier.model.Address;
import rsvier.model.Person;
import rsvier.model.Person.Gender;
import rsvier.model.User;
import rsvier.model.User.Role;
import rsvier.view.conf.Configuration;

public class UserScreen implements Screen<User> {

	private final Console console;
	private final Configuration conf;

	private User user;
	private Input input;

	public UserScreen(Console console) throws IOException {
		this.console = console;
		this.conf = Configuration.getInstance();
		this.input = new Input(console, conf);
	}

	@Override
	public void show() {
		Options<Role> options = new Options<Role>(conf.getLangString("label.select.role"),
				console, conf);
		options.add(new Option<Role>(conf.getLangString("role.admin"), Role.ADMIN));
		options.add(new Option<Role>(conf.getLangString("role.user"), Role.USER));
		options.add(new Option<Role>(conf.getLangString("role.client"), Role.CLIENT));
		options.add(new Option<Role>(conf.getLangString("role.employee"), Role.EMPLOYEE));
		user = new User(-1,
				input.readString(conf.getLangString("label.name")),
				input.readVerifyPassword(conf.getLangString("label.password")),
				options.getSelection(),
				createPerson());
		System.out.println(user);

	}

	// public Person build() {
	// return new Person(id,surname, lastname, gender, birthdate, address,
	// mailAddress, creator, creationDate);

	private Person createPerson() {
		Options<Gender> options = new Options<Gender>(conf.getLangString("label.select.gender"),
				console, conf);
		options.add(new Option<Gender>(conf.getLangString("gender.male"), Gender.MALE));
		options.add(new Option<Gender>(conf.getLangString("gender.female"), Gender.FEMALE));
		options.add(new Option<Gender>(conf.getLangString("gender.other"), Gender.OTHER));
		return new Person.PersonBuilder(-1)
				.surname(input.readString(conf.getLangString("label.surname")))
				.lastname(input.readString(conf.getLangString("label.lastname")))
				.gender(options.getSelection())
				.birthdate(input.readDate(conf.getLangString("label.birthdate")))
				.address(new Address.AddressBuilder()
					.streetName(input.readString("label.streetname"))
					.number(input.readNumber("label.number"))
					.zipCode(input.readString("label.zipcode", "([1-9]){4} ([A-Z]){2}"))
					.city(input.readString("label.city"))
					.build())
				.creationDate(LocalDateTime.now())
				.mailAddress(input.readString("label.mailaddress"))
				.build();
				
					
					
				
	}

	@Override
	public void setBean(User bean) {
		this.user = bean;
	}

	@Override
	public User getBean() {
		return user;
	}

	public Console getConsole() {
		return console;
	}

}
