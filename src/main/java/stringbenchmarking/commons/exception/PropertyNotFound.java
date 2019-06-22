package stringbenchmarking.commons.exception;

public final class PropertyNotFound
    extends
    JMHRuntimeException {

    private static final long serialVersionUID = 2224442095254416154L;

    public PropertyNotFound(
        final String file,
        final String name) {
        super(String.format("Property [%s] not found in file [%s]", name, file));
    }
}
