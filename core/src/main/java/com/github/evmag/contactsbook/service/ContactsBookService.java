package com.github.evmag.contactsbook.service;

import com.github.evmag.contactsbook.model.Contact;

import java.util.List;

public interface ContactsBookService {
    List<Contact> getContacts();
    Contact getContact(int contactId);
    int addContact(Contact contact);
    boolean removeContact(int contactId);
    boolean editContact(Contact contactToEdit, Contact contactEdited);
    int getContactsCount();
}
