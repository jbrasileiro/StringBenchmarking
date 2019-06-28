package stringbenchmarking.commons.exception;

public final class PropertyNotFound
	extends
	JMHException {

	public PropertyNotFound(
		final String file,
		final String name) {
		super(String.format("Property [%s] not found in file [%s]", name, file));
	}

	public PropertyNotFound(
		final String name) {
		super(String.format("Property [%s] not found", name));
	}
}
