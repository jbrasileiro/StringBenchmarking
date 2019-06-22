package stringbenchmarking.commons.io;

public interface PropertyReader {

    boolean hasProperty(
        String name);

    String getProperty(
        String name);
}
