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
        return entityManger.createQuery("SELECT c FROM Contact c WHERE id = ?1", Contact.class)
                .setParameter(1, contactId)
                .getSingleResult();
    }

    @Override
    public int addContact(Contact contact) {
        return 0;
    }

    @Override
    public boolean removeContact(Contact contact) {
        return removeContact(contact.getId());
    }

    @Override
    @Transactional
    public boolean removeContact(int contactId) {
        return entityManger.createQuery("DELETE FROM Contact c WHERE id = ?1")
                .setParameter(1, contactId)
                .executeUpdate() == 1;
    }

    @Override
    public boolean editContact(Contact contactToEdit, Contact contactEdited) {
        return false;
    }

    @Override
    public int getContactsCount() {
        return getAllContacts().size();
    }
}
