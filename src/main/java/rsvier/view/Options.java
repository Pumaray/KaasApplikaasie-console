package rsvier.view;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import rsvier.view.conf.Configuration;

public class Options<E> {

	private final Console console;
	private final Map<Character, Option<E>> options = new HashMap<Character, Option<E>>();
	private final Configuration conf;
	private final String title;

	public Options(String title, Console console, Configuration conf) {
		this.console = console;
		this.conf = conf;
		this.title = title;
	}

	private Character getCharacter(String option) {
		char[] chars = option.toCharArray();
		int index = 0;
		Character result = null;
		boolean found = false;
		while (!found && index < chars.length) {
			char c = chars[index];
			if (!options.keySet().contains(c)) {
				result = c;
				found = true;
			}
			else {
				index++;
			}
		}
		return result;
	}
	
	private void displayOptions() {
		console.writer().println(title);
		options.keySet().stream()
			.forEach(k -> console.writer().println(options.get(k).getValueAsString() +"(" + k + ")"));
	}

	private void displayError(String error) {
		console.writer().println(error);
	}

	public void add(Option<E> option) {
		Character c = getCharacter(option.getValueAsString());
		if (c == null) {
			throw new IllegalArgumentException("All character in " + options +
					"is used. You must specify a charater: use method add(Character char, String option");
		}
		add(c, option);
	}

	public void add(Character c, Option<E> option) {
		options.put(c, option);
	}

	public E getSelection() {
		displayOptions();
		String selection = console.readLine(conf.getLangString("label.selection"));
		Option<E> op = options.get(selection.toUpperCase());
		if (op == null) {
			displayError(conf.getLangString("message.error.invalid.selection"));
			return getSelection();
		}
		else {
			return op.getValue();
		}
	}

}
