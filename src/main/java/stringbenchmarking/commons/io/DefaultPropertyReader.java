package stringbenchmarking.commons.io;

import java.util.Properties;

import org.apache.commons.lang3.builder.ToStringBuilder;

import stringbenchmarking.commons.exception.PropertyNotFound;

public final class DefaultPropertyReader
	implements
	PropertyReader {

	private final Properties properties;

	public DefaultPropertyReader(
		Properties properties) {
		super();
		this.properties = properties;
	}

	@Override
	public boolean hasProperty(
		final String name) {
		return properties.getProperty(name) != null;
	}

	@Override
	public String readProperty(
		String name)
		throws PropertyNotFound {
		String property = properties.getProperty(name);
		if (property == null) {
			throw new PropertyNotFound(name);
		} else {
			return property;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
