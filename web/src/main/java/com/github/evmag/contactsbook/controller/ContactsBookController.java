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

    @GetMapping("/contacts-book")
    public String showAllContacts(Model model) {
        model.addAttribute("contacts", contactsBookService.getContacts());
        return "contactsbook/display_contacts";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("message", "Thymeleaf test successful.");
        return "test/test";
    }
}
