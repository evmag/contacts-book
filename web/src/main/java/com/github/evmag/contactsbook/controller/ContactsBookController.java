package com.github.evmag.contactsbook.controller;

import com.github.evmag.contactsbook.config.Mappings;
import com.github.evmag.contactsbook.config.Views;
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

    @GetMapping({Mappings.ROOT, Mappings.CONTACTS_BOOK})
    public String showAllContacts(Model model) {
        model.addAttribute("contacts", contactsBookService.getContacts());
        return Views.DISPLAY_CONTACTS;
    }

    @GetMapping(Mappings.REMOVE)
    public String removeContact(@RequestParam int id) {
        boolean result = contactsBookService.removeContact(id);
        log.trace("Removed contact with id = {} : {}", id, result);

        // Redirect to main page
        return "redirect:" + Mappings.ROOT;
    }

    @GetMapping({Mappings.EDIT, Mappings.ADD}) //TODO: Add cancel
    public String addEditContact(@RequestParam(required = false, defaultValue = "-1") int id, Model model) {
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
        return Views.MODIFY_CONTACT;
    }

    @PostMapping(Mappings.PROCESS)
    public String processContact(@ModelAttribute("contact") Contact contact) {
        // log.trace("Received contact for processing: {}", contact);
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
        return "redirect:" + Mappings.ROOT;
    }

    // Test mapping TODO: remove
    @GetMapping(Mappings.TEST)
    public String test(Model model) {
        model.addAttribute("message", "Thymeleaf test successful.");
        return Views.TEST;
    }
}
