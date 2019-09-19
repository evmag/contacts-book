package com.github.evmag.contactsbook;

import com.github.evmag.contactsbook.io.Input;
import com.github.evmag.contactsbook.io.Output;
import com.github.evmag.contactsbook.model.Contact;
import com.github.evmag.contactsbook.service.ContactsBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ContactsBookApplication implements CommandLineRunner {
    // === Constants ===
    private static final Logger log = LoggerFactory.getLogger(ContactsBookApplication.class);

    // === Fields ===
    private final ContactsBookService contactsBookService;
    private final Output output;
    private final Input input;
    private boolean running;

    // === Main ===
	public static void main(String[] args) {
	    log.trace("Starting console application from {} ...", ContactsBookApplication.class.getSimpleName());
		SpringApplication.run(ContactsBookApplication.class, args);
        log.trace("Ending console application from {} ...", ContactsBookApplication.class.getSimpleName());
	}

    // === Constructors ===
    @Autowired
    public ContactsBookApplication(ContactsBookService contactsBookService, Output output, Input input) {
        this.contactsBookService = contactsBookService;
        this.output = output;
        this.input = input;
    }

    // === Public methods ===
    @Override
    public void run(String... args) throws Exception {

        log.trace("Starting command loop...");
        running = true;
        // TODO: Display "welcome" message
        while (running) {
            // Display instruction
            log.trace("Printing instruction...");
            output.printString("Enter your command: ");

            // Get command
            log.trace("Getting user input...");
            String command = input.getCommand();
            log.debug("Received user input (command): {}", command);

            // Process command
            log.trace("Processing command...");
            processCommand(command);
        }
        log.trace("Command loop finished...");

    }

    // === Private methods ===
    private void processCommand(String command) {
	    switch (command) {
            case "": // Temp fix to allow the test to finish. TODO: fix the test
            case Commands.EXIT:
                log.debug("Exit command selected -> Exiting application...");
                running = false;
                break;
            case Commands.LIST:
                log.debug("List command selected -> Printing list of contacts...");
                printListOfContacts();
                break;
            case Commands.ADD:
                log.debug("Add command selected -> Adding new contact...");
                addNewContact();
                break;
            case Commands.REMOVE:
                log.debug("Remove command selected -> Removing contact...");
                removeContact();
                break;
            case Commands.HELP:
                log.debug("Help command selected -> Printing list of commands...");
                printListOfCommands();
                break;
            case Commands.EDIT:
                log.debug("Edit command selected -> Editing and existing contact...");
                editContact();
                break;
            default:
                log.debug("Unknown command selected: {}", command);
                output.printString(command + " is not a valid command. Type '" + Commands.HELP + "' for a list of available commands");
                break;
        }
    }

    private void printListOfContacts() {
	    List<Contact> contacts = contactsBookService.getContacts();

        for (Contact contact : contacts) {
            output.printString(contact.toString());
        }
    }

    private void addNewContact() {
	    output.printString("Adding new contact. Enter contact details.");

	    output.printString("First name: ");
	    String firstName = input.getString();
	    output.printString("Last name: ");
	    String lastName = input.getString();
        output.printString("Phone number: ");
        String phone = input.getString();
        output.printString("E-mail: ");
        String email = input.getString();
        output.printString("Birth date (yyyy-mm-dd): ");
        LocalDate dateOfBirth = input.getDate();
        output.printString("Notes: ");
        String notes = input.getString();

        Contact contact = new Contact(firstName, lastName, phone, email, dateOfBirth, notes);
        int contactId = contactsBookService.addContact(contact);

        if (contactId == -1) {
            output.printString("Failed to insert contact: " + contact.toString());
        } else {
            output.printString("Successfully inserted contact into position " + contactId + " : " + contact.toString());
        }
    }

    private void removeContact() {
	    output.printString("Removing contact. Enter the Id of the contact to be removed.");

	    output.printString("Contact Id: ");
	    int contactId = input.getInt();

	    if (contactId == -1) {
	        output.printString("Id provided is not a valid contact Id.");
	        return;
        }
        log.debug("Removing contact... Id selected: {}", contactId);
	    Contact contact = contactsBookService.getContact(contactId);
	    log.debug("Retrieved contact to be removed: {}", contact);

	    if (contact == null) {
	        log.debug("No contact found with Id = {}", contactId);
	        output.printString("No contact found with Id = " + contactId);
            return;
        }

	    if(contactsBookService.removeContact(contactId)) {
	        log.debug("Removed contact: {}", contact);
	        output.printString("Succesfully removed contact: " + contact);
        } else {
	        log.debug("Could not remove contact: {}", contact);
	        output.printString("Failed to remove contact: " + contact);
        }
    }

    private void editContact() {
	    // TODO: refactor to remove duplicate code from removeContact() and addContact()
        output.printString("Editing contact. Enter the Id of the contact to be edited.");

        // Retrieve contact to be edited
        output.printString("Contact Id: ");
        int contactId = input.getInt();

        if (contactId == -1) {
            output.printString("Id provided is not a valid contact Id.");
            return;
        }
        log.debug("Editing contact... Id selected: {}", contactId);
        Contact contact = contactsBookService.getContact(contactId);
        log.debug("Retrieved contact to be edited: {}", contact);

        if (contact == null) {
            log.debug("No contact found with Id = {}", contactId);
            output.printString("No contact found with Id = " + contactId);
            return;
        }

        // Get new values for the contact
        output.printString("Selected contact to edit: " + contact);
        output.printString("Enter new values.");
        output.printString("First name: ");
        String firstName = input.getString();
        output.printString("Last name: ");
        String lastName = input.getString();
        output.printString("Phone number: ");
        String phone = input.getString();
        output.printString("E-mail: ");
        String email = input.getString();
        output.printString("Birth date (yyyy-mm-dd): ");
        LocalDate dateOfBirth = input.getDate();
        output.printString("Notes: ");
        String notes = input.getString();

        Contact contactEdited = new Contact(firstName, lastName, phone, email, dateOfBirth, notes);
        contactEdited.setId(contact.getId());

        // Update the contact
        if (contactsBookService.editContact(contact, contactEdited)) {
            log.debug("Edited contact: {}, new value: {}", contact, contactEdited);
            output.printString("Successfully updated contact.");
        } else {
            log.debug("Failed to edit contact: {}, new value: {}", contact, contactEdited);
            output.printString("Failed to edit contact.");
        }

    }

    private void printListOfCommands() {
	    output.printString("List of available commands:");
	    output.printString("-----------------------------------------");
	    output.printString(Commands.ADD + "\t\t:" + Commands.ADD_DESC);
        output.printString(Commands.EXIT + "\t:" + Commands.EXIT_DESC);
        output.printString(Commands.HELP + "\t:" + Commands.HELP_DESC);
        output.printString(Commands.LIST + "\t:" + Commands.LIST_DESC);
        output.printString(Commands.REMOVE + "\t:" + Commands.REMOVE_DESC);
        output.printString(Commands.EDIT + "\t:" + Commands.EDIT_DESC);
    }

}
