package com.github.evmag.contactsbook.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class InMemoryContactsSource implements ContactsSource{
    // === Constants ===
    private static final Logger log = LoggerFactory.getLogger(InMemoryContactsSource.class);

    // === Fields ==
    private final List<Contact> contacts;

    // === Constructors ===
    public InMemoryContactsSource() {
        contacts = new ArrayList<>();
    }

    // === Init ===
    @PostConstruct
    private void init() {
        log.trace("{} PostConstruct.", getClass().getSimpleName());

        // Initialize some default data for testing since in memory source is not persistent
        log.trace("Initializing default contacts...");
        addContact(new Contact("TestName1", "TestSurname1", "TestPhone1", "TestEmail1", LocalDate.now(), "Test notes1"));
        addContact(new Contact("TestName2", "TestSurname2", "TestPhone2", "TestEmail2", LocalDate.now(), "Test notes2"));
        addContact(new Contact("TestName3", "TestSurname3", "TestPhone3", "TestEmail3", LocalDate.now(), "Test notes3"));
        addContact(new Contact("TestName4", "TestSurname4", "TestPhone4", "TestEmail4", LocalDate.now(), "Test notes4"));
        addContact(new Contact("TestName5", "TestSurname5", "TestPhone5", "TestEmail5", LocalDate.now(), "Test notes5"));
    }


    // === Public methods ==

    @Override
    public List<Contact> getAllContacts() {
        return Collections.unmodifiableList(contacts);
    }

    @Override
    public Contact getContact(int contactId) {
        return contacts.get(contactId);
    }

    @Override
    public int addContact(Contact contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
            int contactId = contacts.indexOf(contact);
            contact.setId(contactId);
            return contactId;
        }
        return -1;
    }

    @Override
    public boolean removeContact(Contact contact) {
        return contacts.remove(contact);
    }

    @Override
    public boolean removeContact(int contactId) {
        return (contacts.remove(contactId) != null);
    }

    @Override
    public boolean editContact(Contact contactToEdit, Contact contactEdited) {
        if (!contacts.contains(contactToEdit) || (contactToEdit.getId() != contactEdited.getId())) {
            return false;
        }
        contacts.set(contactToEdit.getId(), contactEdited);
        return true;
    }

    @Override
    public int getContactsCount() {
        return contacts.size();
    }
}
