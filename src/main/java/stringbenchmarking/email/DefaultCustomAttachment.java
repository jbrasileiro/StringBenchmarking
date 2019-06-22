package stringbenchmarking.email;

import java.io.File;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.util.ByteArrayDataSource;

public class DefaultCustomAttachment
	implements
	CustomAttachment {

	private String name;
	private DataSource dataSource;

	private DefaultCustomAttachment(
		File file) {
		this(file.getName(), new FileDataSource(file.getPath()));
	}

	private DefaultCustomAttachment(
		String name,
		byte[] bytes) {
		this(name, new ByteArrayDataSource(bytes, "text/plain"));
	}

	private DefaultCustomAttachment(
		String name,
		DataSource dataSource) {
		super();
		this.name = name;
		this.dataSource = dataSource;
	}

	public static CustomAttachment file(
		File file) {
		return new DefaultCustomAttachment(file);
	}

	public static CustomAttachment bytes(
		String name,
		byte[] bytes) {
		return new DefaultCustomAttachment(name, bytes);
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public String getName() {
		return name;
	}
}
