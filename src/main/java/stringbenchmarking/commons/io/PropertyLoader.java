package stringbenchmarking.commons.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import stringbenchmarking.commons.AutoCloser;
import stringbenchmarking.commons.exception.NoNewInstanceAllowed;

public final class PropertyLoader {

	private PropertyLoader() {
		super();
		throw new NoNewInstanceAllowed(getClass());
	}

	public static Properties load(
		final Class<?> clazz,
		final String name) {
		InputStream in = null;
		try {
			in = ResourceClassLoader.getResourceAsStream(clazz, name);
			return load(in);
		} finally {
			new AutoCloser().executeClose(in);
		}
	}

	public static Properties load(
		final InputStream in) {
		try {
			Properties prop = new Properties();
			prop.load(in);
			return prop;
		} catch (IOException e) {
			throw new RuntimeException("Error loading properties", e);
		} finally {
			new AutoCloser().executeClose(in);
		}
	}

	public static Properties load(
		final File file) {
		try {
			return load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error loading properties", e);
		}
	}
}
