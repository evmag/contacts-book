package com.github.evmag.contactsbook.controller;

import com.github.evmag.contactsbook.model.Contact;
import com.github.evmag.contactsbook.service.ContactsBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/remove")
    public String removeContact(@RequestParam int id) {
        contactsBookService.removeContact(id);
        return "redirect:/contacts-book";
    }

    @GetMapping("/edit")
    public String addEditContact(@RequestParam(required = false, defaultValue = "-1") int id, Model model) {
        Contact contact = contactsBookService.getContact(id);
        model.addAttribute("contact", contact);
        return "contactsbook/modify_contact";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("message", "Thymeleaf test successful.");
        return "test/test";
    }
}
