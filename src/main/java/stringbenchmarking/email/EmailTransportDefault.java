package stringbenchmarking.email;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public final class EmailTransportDefault
	implements
	EMailTransport {

	@Override
	public void send(
		MimeMessage message)
		throws MessagingException {
		Transport.send(message);
	}
}
