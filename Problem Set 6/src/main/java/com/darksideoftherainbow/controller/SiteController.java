package com.darksideoftherainbow.controller;

import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.time.LocalDateTime;

@Controller
public class SiteController {

    public EmailService emailService;

    public SiteController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/about")
    public String aboutUs() {
        return "about";
    }

    @GetMapping("/contact-us")
    public String contact() {
        return "contact";
    }

    //Learned about sending emails from https://www.linkedin.com/pulse/sending-scheduling-mime-emails-java-using-spring-boot-roberto-trunfio
    @PostMapping("/contact-us")
    public String contactSubmit(Model model, @RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name,
                                @RequestParam("email_address") String email_address, @RequestParam("message") String message) {
        /*
        NOTE: Since I am using a dummy Gmail account for sending emails, there is no guarantee that Gmail won't block a log-in from a new location.
        As such, I have turned on 'Less secure app access' for that account, but Gmail may turn that off without notice; thus, rendering the email
        sending functionality void. Dummy account's credentials can be found in application.properties.
        */
        try {
            final Email email1 = DefaultEmail.builder()
                    .from(new InternetAddress("admin@darksideoftherainbow.com"))
                    .to(Lists.newArrayList(new InternetAddress(email_address)))
                    .subject("Welcome to Dark Side of the Rainbow Music Store")
                    .body(String.format("Name: %s \nEmail Address: %s \n%s \nThe above message has been sent.", first_name + " " + last_name, email_address, message))
                    .encoding("UTF-8").build();
            final Email email2 = DefaultEmail.builder()
                    .from(new InternetAddress(email_address))
                    .to(Lists.newArrayList(new InternetAddress("admin@darksideoftherainbow.com")))
                    .subject("New email from darksideoftherainbow.com")
                    .body(String.format("Name: %s \nEmail Address: %s \n%s \nThe above message was sent at %s.", first_name + " " + last_name, email_address, message, LocalDateTime.now().toString()))
                    .encoding("UTF-8").build();

            emailService.send(email1);
            emailService.send(email2);
        } catch (AddressException ae) {
            ae.printStackTrace();
        }
        return "redirect:/successemail";
    }
}
