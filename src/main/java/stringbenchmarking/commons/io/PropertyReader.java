package stringbenchmarking.commons.io;

import stringbenchmarking.commons.exception.PropertyNotFound;

public interface PropertyReader {

	boolean hasProperty(
		String name);

	String readProperty(
		String name)
		throws PropertyNotFound;
}
