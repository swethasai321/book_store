package com.bookstoreapplication.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import com.bookstoreapplication.email.EmailService;
@SpringBootApplication
public class SprinfEmail {
    @Autowired
    private EmailService senderService;
    public static void main(String[] args){
        SpringApplication.run(SprinfEmail.class,args);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void sendmail(){
        senderService.sendEmail("swethasaichakrala@gmail.com",
                 "order is succefull",
                  "congratulations");
    }
}
