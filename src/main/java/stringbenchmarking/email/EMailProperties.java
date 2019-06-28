package stringbenchmarking.email;

import java.util.Properties;

import stringbenchmarking.commons.exception.JMHRuntimeException;
import stringbenchmarking.commons.exception.PropertyNotFound;
import stringbenchmarking.commons.io.PropertyReader;

public class EMailProperties
	extends
	Properties {

	private PropertyReader reader;

	public EMailProperties(
		PropertyReader reader) {
		this.reader = reader;
		put("mail.smtp.host", readProperty("mail.smtp.host"));
		put("mail.smtp.socketFactory.port", readProperty("mail.smtp.socketFactory.port"));
		put("mail.smtp.socketFactory.class", readProperty("mail.smtp.socketFactory.class"));
		put("mail.smtp.socketFactory.fallback", readProperty("mail.smtp.socketFactory.fallback"));
		put("mail.smtp.auth", readProperty("mail.smtp.auth"));
		put("mail.smtp.port", readProperty("mail.smtp.port"));
		put("mail.smtp.starttls.enable", readProperty("mail.smtp.starttls.enable"));
		put("mail.transport.protocol", readProperty("mail.transport.protocol"));
	}

	public String getUser() {
		return readProperty("mail.smtp.auth.user");
	}

	public String getPassword() {
		return readProperty("mail.smtp.auth.pwd");
	}

	private String readProperty(
		String value) {
		try {
			return reader.readProperty(value);
		} catch (PropertyNotFound e) {
			throw new JMHRuntimeException(e);
		}
	}
}
