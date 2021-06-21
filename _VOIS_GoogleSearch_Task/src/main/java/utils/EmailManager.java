/**
 * provide different utilities such reporting , sending emails , data driven testing  to be used in multiple different
 * classes to increase the re-usability of the code
 */
package utils;

import org.apache.commons.mail.*;

/**
 * EmailManager class is used to send emails to inform the stakeholder
 * about the project status and test results
 *
 * @author Kareem Mohamed
 * @version 1.0
 * @since 18/6/2021
 */
public class EmailManager {
    /**
     * Send an e-mail
     *
     * @param hostName
     * @param portNumber
     * @param userName
     * @param password
     * @param sslConnection
     * @param senderEmail
     * @param receiverEmail
     * @param subject
     * @param messageContent
     */
    public static void sendEmail(String hostName, int portNumber, String userName,
                                 String password, boolean sslConnection, String senderEmail,
                                 String receiverEmail, String subject, String messageContent) {
        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName(hostName); /*"smtp.gmail.com" for example*/
            email.setSmtpPort(portNumber);         /*465*/
            /*your username and password of your email account*/
            email.setAuthenticator(new DefaultAuthenticator(userName, password));
            email.setSSLOnConnect(sslConnection);
            email.setFrom(senderEmail);
            email.setSubject(subject);
            email.setMsg(messageContent);
            email.addTo(receiverEmail);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send an e-mail with an attachment
     *
     * @param hostName
     * @param userName
     * @param password
     * @param senderEmail
     * @param receiverEmail
     * @param subject
     * @param messageContent
     * @param attachmentPath
     * @param attachmentName
     */
    public static void sendEmail(String hostName, String userName,
                                 String password, String senderEmail,
                                 String receiverEmail, String subject, String messageContent,
                                 String attachmentPath, String attachmentName) {
        /* Create  Attachment specifications  */
        try {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(attachmentPath);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setName(attachmentName);
            /* Create the email message */
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName(hostName);
            email.addTo(receiverEmail);
            email.setFrom(senderEmail);
            email.setSubject(subject);
            email.setMsg(messageContent);
            /* add the attachment */
            email.attach(attachment);
            /* send the email */
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }


    }


}
