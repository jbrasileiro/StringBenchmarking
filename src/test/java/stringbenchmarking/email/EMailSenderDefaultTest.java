package stringbenchmarking.email;

import java.util.Collection;
import java.util.Collections;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EMailSenderDefaultTest {

	private static final Collection<CustomAttachment> EMPTY = Collections.emptyList();
	private EMailSenderDefault sender;
	@Mock
	private EMailProperties eMailProperties;
	@Mock
	private EMailTransport eMailTransport;
	@Mock
	private MimeMessageBuilder mimeMessageBuilder;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		sender = new EMailSenderDefault(eMailProperties, mimeMessageBuilder, eMailTransport);
	}

	@Ignore
	@Test
	public void test() {
		sender.send("", "", EMPTY);
	}
}
