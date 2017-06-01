package rsvier.model;

public class Address {

	private final String streetName;
	private final int number;
	private final String zipCode;
	private final String city;

	public Address(String streetName, int number, String zipCode, String city) {
		this.streetName = streetName;
		this.number = number;
		this.zipCode = zipCode;
		this.city = city;
	}

	public String getStreetName() {
		return streetName;
	}

	public int getNumber() {
		return number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCity() {
		return city;
	}

	public static class AddressBuilder {

		String streetName;
		int number;
		String zipCode;
		String city;

		public AddressBuilder() {
		}

		public AddressBuilder streetName(String streetName) {
			this.streetName = streetName;
			return this;
		}

		public AddressBuilder number(int number) {
			this.number = number;
			return this;
		}

		public AddressBuilder zipCode(String zipCode) {
			this.zipCode = zipCode;
			return this;
		}

		public AddressBuilder city(String city) {
			this.city = city;
			return this;
		}

		public Address build() {
			return new Address(streetName, number, zipCode, city);
		}
	}

}
