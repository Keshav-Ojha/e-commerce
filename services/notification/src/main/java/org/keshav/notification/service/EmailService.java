package org.keshav.notification.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keshav.notification.kafka.order.Product;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.keshav.notification.entity.notification.email.EmailTemplates.ORDER_CONFIRMATION;
import static org.keshav.notification.entity.notification.email.EmailTemplates.PAYMENT_CONFIRMATION;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
            ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage,
                MULTIPART_MODE_MIXED_RELATED,
                UTF_8.name());
        mimeMessageHelper.setFrom("e-commerce-app@mail.com");

        final String templateName = PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();

        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);
        mimeMessageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try{
            String html = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email sent to %s successfully", destinationEmail));
        }catch(MessagingException e){
            log.warn(String.format("ERROR - Email sent to %s failed", destinationEmail), e);
        }
    }

    @Async
    public void sendOrderSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage,
                MULTIPART_MODE_MIXED_RELATED,
                UTF_8.name());
        mimeMessageHelper.setFrom("e-commerce-app@mail.com");

        final String templateName = ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();

        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);


        Context context = new Context();
        context.setVariables(variables);
        mimeMessageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        try{
            String html = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email sent to %s successfully", destinationEmail));
        }catch(MessagingException e){
            log.warn(String.format("ERROR - Email sent to %s failed", destinationEmail), e);
        }

    }

}
