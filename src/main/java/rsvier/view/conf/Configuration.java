package rsvier.view.conf;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import rsvier.dao.SupportedDatabase;

public class Configuration {

	private static final String LOCALE = "locale";
	private static final String DATABASE = "database";
	private static final String BUNDLE_LOC = "lang/languages";

	// I would prefer using a directory in the user home directory!
	private final String location = "conf/configuration.properties";

	private static Configuration configuration = null;

	private final ResourceBundle bundle;
	private final SupportedDatabase database;
	private final Properties properties = new Properties();

	private Configuration(Locale locale, SupportedDatabase database) throws IOException {
		this.bundle = ResourceBundle.getBundle(BUNDLE_LOC, locale);
		this.database = database;

		properties.setProperty(LOCALE, locale.toLanguageTag());
		properties.setProperty(DATABASE, database.name());

		save();
	}

	private Configuration() throws IOException {
		load();
		this.bundle = ResourceBundle.getBundle(BUNDLE_LOC,
				Locale.forLanguageTag(properties.getProperty(LOCALE)));
		this.database = SupportedDatabase.valueOf(properties.getProperty(DATABASE));
	}
	
	public static Configuration getInstance() throws IOException {
		init();
		return configuration;
	}
	
	public static Configuration getInstance(Locale locale, SupportedDatabase database) throws IOException {
		configuration = new Configuration(locale, database);
		return configuration;
	}

	private static void init() throws IOException {
		if (configuration == null) {
			configuration = new Configuration();
		}
	}

	private void load() throws IOException {
		Path path = Paths.get(location);
		InputStream in;
		boolean b = Files.exists(path);
		if (Files.exists(path)) {
			in = new FileInputStream(location);
		}
		else {
			in = getClass().getClassLoader().getResourceAsStream(location);
		}
		properties.load(in);
	}

	private void save() throws IOException {
		Path path = Paths.get(location);
		if (Files.notExists(path)) {
			Files.createDirectories(path.getParent());
		}
		properties.store(new FileWriter(location), "");
	}

	public String getLangString(String key) {
		return bundle.getString(key);
	}

	public SupportedDatabase getDatabase() {
		return database;
	}
}
