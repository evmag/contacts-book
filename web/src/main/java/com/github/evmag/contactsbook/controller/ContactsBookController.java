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
    // === Fields ===

    private static final Logger log = LoggerFactory.getLogger(ContactsBookController.class);
    private final ContactsBookService contactsBookService;

    // === Constructors ===

    @Autowired
    public ContactsBookController(ContactsBookService contactsBookService) {
        log.trace("ContactsBookController constructed.");
        this.contactsBookService = contactsBookService;
    }

    // === Mappings ===

    @GetMapping({"/","/contacts-book"})
    public String showAllContacts(Model model) {
        model.addAttribute("contacts", contactsBookService.getContacts());
        return "contactsbook/display_contacts";
    }

    @GetMapping("/remove")
    public String removeContact(@RequestParam int id) {
        log.trace("Removing contact with id = {}", id);
        boolean result = contactsBookService.removeContact(id);
        log.trace("Removed contact with id = {} : {}", id, result);

        // Redirect to main page
        return "redirect:/";
    }

    @GetMapping({"/edit", "/add"})
    public String addEditContact(@RequestParam(required = false, defaultValue = "-1") int id, Model model) {
        log.trace("Modifying contact with id = {}", id);
        Contact contact = contactsBookService.getContact(id);

        if(contact == null) {
            // No existing contact found - adding a new contact
            // Create a new contact with id -1 to add to the model
            log.trace("No existing contact with id = {} found. Creating new contact.", id);
            contact = new Contact();
            contact.setId(-1);
        }

        log.trace("Adding contact to the model: {}", contact);
        model.addAttribute("contact", contact);
        return "contactsbook/modify_contact";
    }

    @PostMapping("/process_contact")
    public String processContact(@ModelAttribute("contact") Contact contact) {
        log.trace("Received contact for processing: {}", contact);
        if (contact.getId() == -1) {
            log.trace("Adding new contact.");
            int newId = contactsBookService.addContact(contact);
            boolean result = (newId != -1);
            log.trace("Adding new contact result: {}. New id = {}", result, newId);
        } else {
            log.trace("Editing contact: {}", contact);
            boolean result = contactsBookService.editContact(contact, contact);
            log.trace("Editing result: {}", result);
        }
        return "redirect:/";
    }

    // Test mapping TODO: remove
    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("message", "Thymeleaf test successful.");
        return "test/test";
    }
}
