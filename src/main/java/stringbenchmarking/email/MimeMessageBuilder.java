package stringbenchmarking.email;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

public final class MimeMessageBuilder {

	private static final String DEFAULT_CHARSET = "UTF-8";

	public MimeMessage buildDefault(
		Session session,
		String subject,
		Address from,
		Address[] replyTo,
		Address[] to,
		Address[] cc,
		Address[] bcc)
		throws MessagingException,
		UnsupportedEncodingException,
		AddressException {
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		message.addHeader("Content-type", "text/HTML; charset=UTF-8");
		message.addHeader("format", "flowed");
		message.addHeader("Content-Transfer-Encoding", "8bit");
		message.setSentDate(new Date());
		message.setSubject(subject, DEFAULT_CHARSET);
		message.setFrom(from);
		message.setReplyTo(replyTo);
		message.setRecipients(Message.RecipientType.TO, to);
		if (notEmpty(cc)) {
			message.setRecipients(Message.RecipientType.CC, cc);
		}
		if (notEmpty(bcc)) {
			message.setRecipients(Message.RecipientType.BCC, bcc);
		}
		return message;
	}

	private boolean notEmpty(
		Object[] array) {
		return array != null && !Arrays.asList(array).isEmpty();
	}
}
