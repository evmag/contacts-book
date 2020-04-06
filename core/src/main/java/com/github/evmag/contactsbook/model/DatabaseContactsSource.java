package com.github.evmag.contactsbook.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Primary //TODO: move bean selection elsewhere
@Repository
public class DatabaseContactsSource implements ContactsSource{
    // === Constants ===
    private static final Logger log = LoggerFactory.getLogger(DatabaseContactsSource.class);

    // === Fields ==

    @PersistenceContext
    private EntityManager entityManger;

    // === Public methods ==

    @Override
    @Transactional
    public List<Contact> getAllContacts() {
        return entityManger.createQuery("select c from Contact c", Contact.class).getResultList();
    }

    @Override
    public Contact getContact(int contactId) {
        return entityManger.find(Contact.class, contactId);
    }

    @Override
    @Transactional
    public int addContact(Contact contact) {
        contact.setId(null);
        entityManger.persist(contact);
        return contact.getId() ;
    }

    @Override
    public boolean removeContact(Contact contact) {
        return removeContact(contact.getId());
    }

    @Override
    @Transactional
    public boolean removeContact(int contactId) {
        Contact contact = getContact(contactId);

        if (contact == null) {
            return false;
        } else {
            entityManger.remove(contact);
            return true;
        }
    }

    @Override
    @Transactional
    public boolean editContact(Contact contactToEdit, Contact contactEdited) {
        if (getContact(contactToEdit.getId()) == null || contactToEdit.getId() != contactEdited.getId()) {
            return false;
        } else {
            entityManger.merge(contactEdited);
            return true;
        }
    }

    @Override
    public int getContactsCount() {
        return getAllContacts().size();
    }
}
