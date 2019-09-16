package com.github.evmag.contactsbook.model;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class InMemoryContactsSource implements ContactsSource{
    // === Fields ==
    private final List<Contact> contacts;

    // === Constructors ===
    public InMemoryContactsSource() {
        contacts = new ArrayList<>();
    }

    // === Init ===
    @PostConstruct
    private void init() {
        System.out.println(this.getClass().getSimpleName() + " is initialized.");
        addContact(new Contact("TestName", "TestSurname", "TestPhone", "TestEmail", LocalDate.now(), "Test notes"));
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
