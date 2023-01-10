package com.example.demo.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Usuario;

import jakarta.mail.internet.MimeMessage;

@Service
@Transactional
public class EmailServiceImplementation implements EmailService{
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String email;

    @Override
    public void sendEmail(String emailTo){
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email);
            helper.setTo(emailTo);
            helper.setSubject("Correo de pruebas");
            helper.setText("Estimado cliente este es un correo de pruebas");
            javaMailSender.send(message);

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void sendEmailReserva(Usuario user, Date fecha, String hora, int cancha){
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM");
            String strDate = dateFormat.format(fecha);
            System.out.println(strDate);
            helper.setFrom(email);
            helper.setTo(user.getCorreo());
            helper.setSubject("Reserva realizada!!");
            helper.setText("Estimado "+user.getNombre()+" su reserva ha sido realizada con exito para el día "+strDate+" a las "+hora+" en a cancha "+cancha);
            javaMailSender.send(message);

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
