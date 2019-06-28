package stringbenchmarking.email;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.mail.Address;

public interface EMailSender {

	void send(
		String email,
		String subject,
		Collection<CustomAttachment> attachments)
		throws UnsupportedEncodingException;

	boolean send(
		Address from,
		Address[] replyTo,
		Address[] to,
		Address[] cc,
		Address[] bcc,
		String subject,
		Collection<CustomAttachment> attachments);
}
