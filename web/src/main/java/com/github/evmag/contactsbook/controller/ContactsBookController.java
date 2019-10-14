package com.github.evmag.contactsbook.controller;

import com.github.evmag.contactsbook.model.Contact;
import com.github.evmag.contactsbook.service.ContactsBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactsBookController {
    private final ContactsBookService contactsBookService;
    private static final Logger log = LoggerFactory.getLogger(ContactsBookController.class);

    @Autowired
    public ContactsBookController(ContactsBookService contactsBookService) {
        this.contactsBookService = contactsBookService;
    }

    @GetMapping({"/","/contacts-book"})
    public String showAllContacts(Model model) {
        model.addAttribute("contacts", contactsBookService.getContacts());
        return "contactsbook/display_contacts";
    }

    @GetMapping("/remove")
    public String removeContact(@RequestParam int id) {
        contactsBookService.removeContact(id);
        return "redirect:/contacts-book";
    }

    @GetMapping({"/edit", "/add"})
    public String addEditContact(@RequestParam(required = false, defaultValue = "-1") int id, Model model) {
        Contact contact = contactsBookService.getContact(id);
        if(contact == null) {
            contact = new Contact();
            contact.setId(-1);
        }
        model.addAttribute("contact", contact);
        return "contactsbook/modify_contact";
    }

    @PostMapping("/process_contact")
    public String processContact(@ModelAttribute("contact") Contact contact) {
        log.debug("Received contact for processing: {}", contact);
        if (contact.getId() == -1) {
            contactsBookService.addContact(contact);
        } else {
            contactsBookService.editContact(contact, contact);
        }
        return "redirect:/";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("message", "Thymeleaf test successful.");
        return "test/test";
    }
}
