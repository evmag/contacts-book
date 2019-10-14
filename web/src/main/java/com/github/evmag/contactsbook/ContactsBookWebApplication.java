package com.github.evmag.contactsbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsBookWebApplication {
    private static final Logger log = LoggerFactory.getLogger(ContactsBookWebApplication.class);

    public static void main(String[] args) {
        log.trace("Starting ContactsBookWebApplication.");
        SpringApplication.run(ContactsBookWebApplication.class, args);
    }
}
