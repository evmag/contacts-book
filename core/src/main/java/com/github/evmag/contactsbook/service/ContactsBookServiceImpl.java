package com.github.evmag.contactsbook.service;

import com.github.evmag.contactsbook.model.Contact;
import com.github.evmag.contactsbook.model.ContactsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsBookServiceImpl implements ContactsBookService {
    // == Constants ==

    // == Fields ==
    private final ContactsSource contactsSource;

    // == Constructors ==
    @Autowired
    public ContactsBookServiceImpl(ContactsSource contactsSource) {
        this.contactsSource = contactsSource;
    }

    // == Public methods ==

    @Override
    public List<Contact> getContacts() {
        return contactsSource.getAllContacts();
    }

    @Override
    public Contact getContact(int contactId) {
        return contactsSource.getContact(contactId);
    }

    @Override
    public int addContact(Contact contact) {
        return contactsSource.addContact(contact);
    }

    @Override
    public boolean removeContact(int contactId) {
        return contactsSource.removeContact(contactId);
    }

    @Override
    public boolean editContact(Contact contactToEdit, Contact contactEdited) {
        return contactsSource.editContact(contactToEdit, contactEdited);
    }

    @Override
    public int getContactsCount() {
        return contactsSource.getContactsCount();
    }
}
