package rsvier.view;

import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Pattern;

import rsvier.view.conf.Configuration;

class Input {

	private final Console console;
	private final Configuration conf;

	public Input(Console console, Configuration conf) {
		this.console = console;
		this.conf = conf;
	}

	public char[] readPassword(String label) {
		char[] password = console.readPassword("[%s]", label);
		if (password.length == 0) {
			displayError(label + " " + conf.getLangString("message.error.empthy.field"));
			readPassword(label);
		}
		return password;
	}

	public char[] readVerifyPassword(String label) {
		char[] pass1 = readPassword(label);
		char[] pass2 = readPassword(conf.getLangString("label.retype") + " " + label);
		if (!Arrays.equals(pass1, pass2)) {
			displayError(conf.getLangString("message.error.not.equals.password"));
			readVerifyPassword(label);
		}
		return pass1;
	}

	public String readString(String label) {
		String result = console.readLine(label);
		if (result.trim().equals("")) {
			displayError(label + " " + conf.getLangString("message.error.empthy.field"));
			readString(label);
		}
		return result;
	}

	public String readString(String label, String pattern) {
		String result = readString(label);
		if (!Pattern.matches(pattern, result)) {
			displayError(conf.getLangString("message.error.invalid") + label);
			return readString(label, pattern);
		}
		return result;

	}

	public LocalDate readDate(String label) {
		String result = readString(label);
		try {
			return LocalDate.parse(result);
		}
		catch (DateTimeParseException ed) {
			displayError(conf.getLangString("message.error.invalid") + label);
			return readDate(label);
		}

	}

	public Integer readNumber(String label) {
		String tmp = readString(label);
		Integer result = null;
		try {
			result = Integer.parseInt(tmp);
		}
		catch (NumberFormatException en) {
			displayError(conf.getLangString("message.error.invalid") + label);
			return readNumber(label);
		}
		return result;

	}

	private void displayError(String error) {
		if (error.endsWith(":")) {
			error = error.substring(0, error.length() - 1);
		}
		console.writer().println(error);
	}

	public Console getConsole() {
		return console;
	}

}
