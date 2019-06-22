package stringbenchmarking.commons.exception;

public final class NoNewInstanceAllowed
	extends
	JMHRuntimeException {

	private static final long serialVersionUID = 1733552079754957175L;

	public NoNewInstanceAllowed(
		final Class<?> clazz) {
		super("No instance allowed to this class :".concat(clazz.getName()));
	}
}
