package stringbenchmarking.email;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
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

	private static final String DEFAULT_CHARSET = "UTF-8";
	private final EMailProperties eMailProperties;
	private final MimeMessageBuilder mimeMessageBuilder;
	private final EMailTransport eMailTransport;

	public EMailSenderDefault(
		EMailProperties eMailProperties,
		MimeMessageBuilder mimeMessageBuilder,
		EMailTransport eMailTransport) {
		super();
		this.eMailProperties = eMailProperties;
		this.mimeMessageBuilder = mimeMessageBuilder;
		this.eMailTransport = eMailTransport;
	}

	@Override
	public void send(
		String email,
		String subject,
		Collection<CustomAttachment> attachments) {
		Address from = toAddress(eMailProperties.getUser(), eMailProperties.getUser());
		Address[] replyTo = null;
		Address[] to = new Address[]{toAddress(email, email)};
		Address[] cc = null;
		Address[] bcc = null;
		send(from, replyTo, to, cc, bcc, subject, attachments);
	}

	@Override
	public boolean send(
		Address from,
		Address[] replyTo,
		Address[] to,
		Address[] cc,
		Address[] bcc,
		String subject,
		Collection<CustomAttachment> attachments) {
		String text = null;
		if (text == null) {
			text = "";
		}
		try {
			if (replyTo == null || replyTo.length == 0) {
				replyTo = Arrays.asList(from).toArray(new Address[] {});
			}
			MimeMessage message = mimeMessageBuilder.buildDefault(getSession(), subject, from, replyTo, to, cc, bcc);
			if (attachments == null || attachments.isEmpty()) {
				message.setText(text, DEFAULT_CHARSET);
			} else {
				Multipart multipart = new MimeMultipart();
				BodyPart body = new MimeBodyPart();
				body.setText(text);
				multipart.addBodyPart(body);
				for (CustomAttachment customAttachment : attachments) {
					BodyPart attachment = toAttachment(customAttachment);
					multipart.addBodyPart(attachment);
				}
				message.setContent(multipart);
			}
			eMailTransport.send(message);
			return true;
		} catch (MessagingException e) {
			throw new JMHRuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new JMHRuntimeException(e);
		}
	}

	private BodyPart toAttachment(
		CustomAttachment customAttachment)
		throws MessagingException {
		BodyPart attachment = new MimeBodyPart();
		DataSource source = customAttachment.getDataSource();
		attachment.setDataHandler(new DataHandler(source));
		attachment.setFileName(customAttachment.getName());
		return attachment;
	}

	private Address toAddress(
		String address,
		String name) {
		try {
			return new InternetAddress(address, name, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	private Session getSession() {
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
