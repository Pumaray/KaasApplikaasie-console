package rsvier.dao;

public enum SupportedDatabase {

	MAPDB_TEST("mapdb-test.properties", "sa", "".toCharArray());
	
	public static final String URL = "url";

	private final String configurationFile;
	private final String username;
	private final char[] password;

	private SupportedDatabase(String configurationFile, String username, char[] password) {
		this.configurationFile = configurationFile;
		this.password = password;
		this.username = username;
	}

	public String getConfigurationFile() {
		return configurationFile;
	}

	public String getUsername() {
		return username;
	}

	public char[] getPassword() {
		return password;
	}

}
