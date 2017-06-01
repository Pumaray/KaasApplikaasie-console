package rsvier.view;

public class Option<V> {

	private final String valueAsString;
	private final V value;

	public Option(String valueAsString, V value) {
		this.valueAsString = valueAsString;
		this.value = value;
	}

	public String getValueAsString() {
		return valueAsString;
	}

	public V getValue() {
		return value;
	}

}
