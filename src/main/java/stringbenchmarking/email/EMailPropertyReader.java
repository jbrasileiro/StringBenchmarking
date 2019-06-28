package stringbenchmarking.email;

import java.io.InputStream;
import java.util.Properties;

import stringbenchmarking.commons.exception.PropertyNotFound;
import stringbenchmarking.commons.io.DefaultPropertyReader;
import stringbenchmarking.commons.io.PropertyLoader;
import stringbenchmarking.commons.io.PropertyReader;
import stringbenchmarking.commons.io.ResourceClassLoader;

public final class EMailPropertyReader
	implements
	PropertyReader {

	private static final String FILE = "properties/email.properties";
	private final DefaultPropertyReader reader;

	public EMailPropertyReader() {
		super();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = ResourceClassLoader.getResourceAsStream(classLoader, FILE);
		Properties properties = PropertyLoader.load(input);
		reader = new DefaultPropertyReader(properties);
	}

	@Override
	public boolean hasProperty(
		String name) {
		return reader.hasProperty(name);
	}

	@Override
	public String readProperty(
		String name)
		throws PropertyNotFound {
		try {
			return reader.readProperty(name);
		} catch (PropertyNotFound e) {
			throw new PropertyNotFound(FILE, name);
		}
	}
}
