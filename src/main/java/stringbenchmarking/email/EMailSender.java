package stringbenchmarking.email;

import java.util.Collection;

public interface EMailSender {

	void send(
		String email,
		String subject,
		Collection<CustomAttachment> attachments);
}
