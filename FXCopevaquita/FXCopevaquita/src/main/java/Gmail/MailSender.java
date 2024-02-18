/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gmail;

import MainApp.App;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author aleke
 */
public class MailSender {
    final private String PASSWORD = "ibce bdgu txgi qfsi";
    final private String EMAIL = "copevaquitacope@gmail.com";
    
    /**
     *
     * @param toValidate
     * @return is valid
     */
    public static boolean validateCode(String toValidate) {
        System.out.println("Verificando la contrasenia: " + App.getCode().toString() + " " + toValidate.toString());
        
        return App.getCode().equals(toValidate);
    }
    
    public void sendRecoveryCode(String to) {
        // Resetear código de acceso
        App.setCode();
        
        final String username = this.EMAIL;
        final String password = this.PASSWORD;

        // Configuración de las propiedades
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Creación de la sesión
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Creación del mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Recuperación de Contraseña");
            message.setText("Tu código de recuperación es: " + App.getCode());

            // Envío del mensaje
            Transport.send(message);

            System.out.println("Correo electrónico enviado con éxito.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
