package com.sendmail.service;

import com.sendmail.dto.InvoiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailService {

    private final TemplateEngine templateEngine; // Engine of thymeleaf

    private final JavaMailSender javaMailSender;

    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    // Just send to 1 customer
    public void sendMail(InvoiceDTO dto) throws MessagingException {
        Context context = new Context();
        context.setVariable("INVOICE", dto); // Set attribute for email_send_one.html (templates/common/email_send_one.html)

        String template = templateEngine.process("common/email_send_one", context); // Get path template email
        if (template!=null){
            try {

                MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // Create mail content
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage); // Enable -> True if u want to send with file
                helper.setSubject("Electronic invoice of the order #"+dto.getCode()); // set Subject email
                helper.setText(template, true); // Enable content email with html format
                helper.setTo(dto.getCustomerDTO().getEmail()); // Send to email customer

//                FileSystemResource file = new FileSystemResource(new File("c:/Sample.jpg")); // Send file
//                helper.addAttachment("CoolImage.jpg", file);

                javaMailSender.send(mimeMessage); // Send
            }catch (MailException e){
                log.error(e.getMessage());
                log.error("Service -> EmailService -> Send error");
            }
        }else log.error("Service -> EmailService -> template null");

    }

    // Send to all email customer
    public void sendAll(String [] emails) throws MessagingException {
        Context context = new Context();
        String template = templateEngine.process("common/email_send_multiple", context); // Get path template email
        if (template!=null){
            try {

                MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // Create mail content
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage); // Enable -> True if u want to send with file
                helper.setSubject("Shop Discounts"); // set Subject email
                helper.setText(template, true); // Enable content email with html format
//                helper.setTo(dto.getCustomerDTO().getEmail()); // Send to email customer
                helper.setTo(emails);

//                FileSystemResource file = new FileSystemResource(new File("c:/Sample.jpg")); // Send file
//                helper.addAttachment("CoolImage.jpg", file);

                javaMailSender.send(mimeMessage); // Send
            }catch (MailException e){
                log.error(e.getMessage());
                log.error("Service -> EmailService -> Send error");
            }
        }else log.error("Service -> EmailService -> template null");

    }
}