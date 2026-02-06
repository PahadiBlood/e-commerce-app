package xyz.manojraw.ecommerce.notification.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import xyz.manojraw.ecommerce.notification.enumeration.EmailTemplate;
import xyz.manojraw.ecommerce.notification.kafka.Product;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(String to, String customerName,
                                        BigDecimal amount, String orderReference) {

        try {
            String sender = "manojrawat615@gmail.com";
            final String templateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();
            final String subject = EmailTemplate.PAYMENT_CONFIRMATION.getSubject();

            Map<String, Object> variables = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("amount", amount);
            variables.put("orderReference", orderReference);

            Context context = new Context();
            context.setVariable("variables", variables);

            String htmlTemplate = templateEngine.process(templateName, context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.addTo(to);
            helper.setFrom(sender);
            helper.setSubject(subject);
            helper.setText(htmlTemplate);

            log.info("Mail send process started for EmailService.sendPaymentSuccessEmail to <{}>", to);
            mailSender.send(mimeMessage);
            log.info("Mail send process end for EmailService.sendPaymentSuccessEmail to <{}>", to);

        } catch (MessagingException e) {
            log.error("Error in EmailService.sendPaymentSuccessEmail", e);
        }
    }


    @Async
    public void sendOrderSuccessEmail(String to, String customerName,
                                      BigDecimal totalAmount, String orderReference, List<Product> products) {

        try {
            String sender = "manojrawat615@gmail.com";
            final String templateName = EmailTemplate.ORDER_CONFIRMATION.getTemplate();
            final String subject = EmailTemplate.ORDER_CONFIRMATION.getSubject();

            Map<String, Object> variables = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("totalAmount", totalAmount);
            variables.put("orderReference", orderReference);
            variables.put("products", products);

            Context context = new Context();
            context.setVariable("variables", variables);

            String htmlTemplate = templateEngine.process(templateName, context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.addTo(to);
            helper.setFrom(sender);
            helper.setSubject(subject);
            helper.setText(htmlTemplate);

            log.info("Mail send process started for EmailService.sendOrderSuccessEmail to <{}>", to);
            mailSender.send(mimeMessage);
            log.info("Mail send process end for EmailService.sendOrderSuccessEmail to <{}>", to);

        } catch (MessagingException e) {
            log.error("Error in EmailService.sendOrderSuccessEmail", e);
        }
    }
}
