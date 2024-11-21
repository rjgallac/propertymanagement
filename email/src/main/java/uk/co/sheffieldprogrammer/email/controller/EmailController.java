package uk.co.sheffieldprogrammer.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.sheffieldprogrammer.email.service.EmailService;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping
    public void email(){
        emailService.email();
    }
}
