package com.github.evmag.contactsbook.model;

import java.util.List;

/**
 * Contacts source interface
 * Provides create, remove, update and display operations for a "Contacts" source
 *
 */
public interface ContactsSource {
    /**
     * Retrieves the list of all contacts from the source.
     * @return List<Contact> Returns the list of all contacts.
     */
    List<Contact> getAllContacts();

    /**
     * Retrieves a single contact from the source.
      * @param contactId The id of the contact to be retrieved.
     * @return Contact Returns the contact with the specified id.
     */
    Contact getContact(int contactId);

    /**
     * Adds a new contact to the source
     * @param contact The contact to be added
     * @return int Returns the id of the successfully added contact. Returns -1 if operation was unsuccessful.
     */
    int addContact(Contact contact);

    /**
     * Removes a contact from the source.
      * @param contact The contact to be removed.
     * @return int Returns the id of the successfully removed contact. Returns -1 if operation was unsuccessful.
     */
    boolean removeContact(Contact contact);

    /**
     * Removed a contact from the source.
     * @param contactId The id of the contact to be removed.
     * @return boolean Returns true if operation was successful.
     */
    boolean removeContact(int contactId);

    /**
     * Updates a selected contact on the source.
     * @param contactToEdit The contact to be edited.
     * @param contactEdited The updated contact.
     * @return boolean Returns true if operation was successful.
     */
    boolean editContact(Contact contactToEdit, Contact contactEdited);

    /**
     * Retrieves the total count of contacts on the source.
     * @return int Returns the total count of contacts.
     */
    int getContactsCount();
}
