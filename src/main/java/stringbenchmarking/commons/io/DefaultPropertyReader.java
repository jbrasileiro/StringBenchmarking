package stringbenchmarking.commons.io;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.builder.ToStringBuilder;

import stringbenchmarking.commons.exception.PropertyNotFound;

public final class DefaultPropertyReader
    implements
    PropertyReader {

    private final Properties properties;
    private final File file;

    public DefaultPropertyReader(
        final File file) {
        super();
        this.file = file;
        properties = PropertyLoader.load(file);
    }

    @Override
    public boolean hasProperty(
        final String name) {
        return properties.getProperty(name) != null;
    }

    @Override
    public String getProperty(
        final String name) {
        String property = properties.getProperty(name);
        if (property == null) {
            throw new PropertyNotFound(file.getPath(), name);
        } else {
            return property;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
