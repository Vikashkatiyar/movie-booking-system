package com.moviebooking.notificationservice.service.impl;

import com.moviebooking.notificationservice.dto.NotificationRequest;
import com.moviebooking.notificationservice.dto.NotificationResponse;
import com.moviebooking.notificationservice.dto.NotificationStatus;
import com.moviebooking.notificationservice.exception.NotificationException;
import com.moviebooking.notificationservice.service.NotificationService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;

    @Value("${MAIL_USERNAME}")
    private String username;

    @Override
    public NotificationResponse sendBookingNotification(NotificationRequest request) {

        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(request.getEmail());
            helper.setFrom(username);
            helper.setSubject("Movie Booking Confirmation: " + request.getUserId());

            String emailTemplate = buildEmailTemplate(request);

            helper.setText(emailTemplate, true);

            mailSender.send(message);

            return NotificationResponse.builder()
                    .status(NotificationStatus.SUCCESS)
                    .message("Email sent successfully")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new NotificationException("EMAIL_SENDING_FAILED");
        }
    }

    private String buildEmailTemplate(NotificationRequest request) {

        return """
        <html>
        <body style="font-family: Arial, sans-serif; background-color:#f4f4f4; padding:20px;">
        
            <table align="center" width="600" style="background:white;border-radius:8px;padding:20px;">
            
                <tr>
                    <td align="center" style="padding-bottom:20px;">
                        <h2 style="color:#2c3e50;">🎬 Movie Booking Confirmed!</h2>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <p>Hello,</p>
                        
                        <p>Your movie booking has been successfully confirmed.</p>
                        
                        <table style="width:100%%;border-collapse:collapse;margin-top:15px;">
                            <tr>
                                <td style="padding:8px;font-weight:bold;">Booking ID:</td>
                                <td style="padding:8px;">%s</td>
                            </tr>
                            
                            <tr>
                                <td style="padding:8px;font-weight:bold;">User ID:</td>
                                <td style="padding:8px;">%s</td>
                            </tr>
                        </table>
                        
                        <p style="margin-top:20px;">
                            🎉 We hope you enjoy your movie experience!
                        </p>
                        
                        <p>
                            Regards,<br>
                            <b>Movie Booking Team</b>
                        </p>
                    </td>
                </tr>
                
                <tr>
                    <td align="center" style="padding-top:20px;color:gray;font-size:12px;">
                        This is an automated message. Please do not reply.
                    </td>
                </tr>
            
            </table>
        
        </body>
        </html>
        """.formatted(request.getBookingId(), request.getUserId());
    }
}