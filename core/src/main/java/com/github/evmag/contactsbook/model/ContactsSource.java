package com.github.evmag.contactsbook.model;

import java.util.List;
// TODO: add documentation
public interface ContactsSource {
    List<Contact> getAllContacts();
    Contact getContact(int contactId);
    int addContact(Contact contact);
    boolean removeContact(Contact contact);
    boolean removeContact(int contactId);
    boolean editContact(Contact contactToEdit, Contact contactEdited);
    int getContactsCount();
}
