package stringbenchmarking.email;

import java.io.File;
import java.util.Properties;

import stringbenchmarking.commons.io.DefaultPropertyReader;

public class EMailProperties
	extends
	Properties {

	private final DefaultPropertyReader reader;

	public EMailProperties() {
		reader = new DefaultPropertyReader(new File("/properties/email"));
		put("mail.smtp.host", readProperty("mail.smtp.host"));
		put("mail.smtp.socketFactory.port", readProperty("mail.smtp.socketFactory.port"));
		put("mail.smtp.socketFactory.class", readProperty("mail.smtp.socketFactory.class"));
		put("mail.smtp.socketFactory.fallback", readProperty("mail.smtp.socketFactory.fallback"));
		put("mail.smtp.auth", readProperty("mail.smtp.auth"));
		put("mail.smtp.port", readProperty("mail.smtp.port"));
		put("mail.smtp.starttls.enable", readProperty("mail.smtp.starttls.enable"));
		put("mail.transport.protocol", readProperty("mail.transport.protocol"));
	}

	private String readProperty(
		String name) {
		return reader.getProperty(name);
	}

	public String getUser() {
		return reader.getProperty("mail.smtp.auth.user");
	}

	public String getPassword() {
		return reader.getProperty("mail.smtp.auth.pwd");
	}
}
