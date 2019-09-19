package com.github.evmag.contactsbook;

public final class Commands {
    // === Constants ===
    public static final String EXIT = "exit";
    public static final String EXIT_DESC = "Exits the application.";

    public static final String LIST = "list";
    public static final String LIST_DESC = "Prints the list of all contacts.";

    public static final String ADD = "add";
    public static final String ADD_DESC = "Add a new contact to the list.";

    public static final String REMOVE = "remove";
    public static final String REMOVE_DESC = "Remove specified contact from the list.";

    public static final String HELP = "help";
    public static final String HELP_DESC = "Prints a list with all available commands.";

    public static final String EDIT = "edit";
    public static final String EDIT_DESC = "Edit an existing contact.";

    // === Constructors ===
    // Disable public constructors
    private Commands() {};
}
