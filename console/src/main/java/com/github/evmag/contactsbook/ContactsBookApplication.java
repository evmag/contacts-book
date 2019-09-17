package com.github.evmag.contactsbook;

import com.github.evmag.contactsbook.model.Contact;
import com.github.evmag.contactsbook.service.ContactsBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ContactsBookApplication implements CommandLineRunner {
    // === Constants ===
    private static final Logger log = LoggerFactory.getLogger(ContactsBookApplication.class);

    // === Fields ===
    private final ContactsBookService contactsBookService;

    // === Main ===
	public static void main(String[] args) {
	    log.trace("Starting console application from {} ...", ContactsBookApplication.class.getSimpleName());
		SpringApplication.run(ContactsBookApplication.class, args);
        log.trace("Ending console application from {} ...", ContactsBookApplication.class.getSimpleName());
	}

    // === Constructors ===
    @Autowired
    public ContactsBookApplication(ContactsBookService contactsBookService) {
        this.contactsBookService = contactsBookService;
    }

    // === Public methods ===
    @Override
    public void run(String... args) throws Exception {
        List<Contact> contacts = contactsBookService.getContacts();

        for (Contact contact : contacts) {
            System.out.println(contact);
        }

    }

}
