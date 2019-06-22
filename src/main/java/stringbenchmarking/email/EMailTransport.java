package stringbenchmarking.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface EMailTransport {

	void send(
		MimeMessage message)
		throws MessagingException;
}
