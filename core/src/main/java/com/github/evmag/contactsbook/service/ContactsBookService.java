package com.github.evmag.contactsbook.service;

import com.github.evmag.contactsbook.model.Contact;

import java.util.List;

/**
 * Contacts book service interface
 * Provides create, remove, update and display operations for a "Contacts" book
 *
 */
public interface ContactsBookService {
    /**
     * Retrieves the list of all contacts from the contacts book.
     * @return List<Contact> Returns the list of all contacts.
     */
    List<Contact> getContacts();

    /**
     * Retrieves a single contact from the contacts book.
     * @param contactId The id of the contact to be retrieved.
     * @return Contact Returns the contact with the specified id.
     */
    Contact getContact(int contactId);

    /**
     * Adds a new contact in the contacts book.
     * @param contact The contact to be added.
     * @return int Returns the id of the successfully added contact. Returns -1 if operation was unsuccessful.
     */
    int addContact(Contact contact);

    /**
     * Removed a contact from the contacts book.
     * @param contactId The id of the contact to be removed.
     * @return boolean Returns true if operation was successful.
     */
    boolean removeContact(int contactId);

    /**
     * Updates a selected contact in the contacts book.
     * @param contactToEdit The contact to be edited.
     * @param contactEdited The updated contact.
     * @return boolean Returns true if operation was successful.
     */
    boolean editContact(Contact contactToEdit, Contact contactEdited);

    /**
     * Retrieves the total count of contacts in the contacts book.
     * @return int Returns the total count of contacts.
     */
    int getContactsCount();
}
