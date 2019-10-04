package com.github.evmag.contactsbook.controller;

import com.github.evmag.contactsbook.service.ContactsBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactsBookController {
    private final ContactsBookService contactsBookService;

    @Autowired
    public ContactsBookController(ContactsBookService contactsBookService) {
        this.contactsBookService = contactsBookService;
    }

    @GetMapping("test")
    public String test(Model model) {
        model.addAttribute("message", "Thymeleaf test succesfull.");
        return "test/test";
    }
}
