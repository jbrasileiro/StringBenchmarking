package stringbenchmarking.commons.exception;

public final class ResourceNotFound
    extends
    JMHRuntimeException {

    private static final long serialVersionUID = -6756051656255228313L;

    public ResourceNotFound(
        final String name) {
        super("Resource not found: ".concat(name));
    }
}
