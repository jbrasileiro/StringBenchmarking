package stringbenchmarking.email;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import stringbenchmarking.commons.exception.JMHRuntimeException;

public class EMailSenderDefault
	implements
	EMailSender {

	private final EMailProperties eMailProperties;
	private final EMailTransport eMailTransport;

	public EMailSenderDefault(
		EMailProperties eMailProperties,
		EMailTransport eMailTransport) {
		super();
		this.eMailProperties = eMailProperties;
		this.eMailTransport = eMailTransport;
	}

	@Override
	public void send(
		String email,
		String subject,
		Collection<CustomAttachment> attachments) {
		String text = null;
		if (text == null) {
			text = "";
		}
		try {
			Session session = get();
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
			message.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
			message.setSubject(subject, "UTF-8");
			message.setSentDate(new Date());
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
			if (attachments == null || attachments.isEmpty()) {
				message.setText(text, "UTF-8");
			} else {
				Multipart multipart = new MimeMultipart();
				BodyPart body = new MimeBodyPart();
				body.setText(text);
				multipart.addBodyPart(body);
				for (CustomAttachment customAttachment : attachments) {
					BodyPart attachment = new MimeBodyPart();
					DataSource source = customAttachment.getDataSource();
					attachment.setDataHandler(new DataHandler(source));
					attachment.setFileName(customAttachment.getName());
					multipart.addBodyPart(attachment);
				}
				message.setContent(multipart);
			}
			eMailTransport.send(message);
		} catch (MessagingException e) {
			throw new JMHRuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new JMHRuntimeException(e);
		}
	}

	private Session get() {
		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				String user = eMailProperties.getUser();
				String password = eMailProperties.getPassword();
				return new PasswordAuthentication(user, password);
			}
		};
		return Session.getDefaultInstance(eMailProperties, auth);
	}
}
