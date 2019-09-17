package com.github.evmag.contactsbook.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput implements Input{
    // === Constants ===
    private static final Logger logger = LoggerFactory.getLogger(ConsoleInput.class);
    private static final Scanner input = new Scanner(System.in);


    // === Public methods ===
    @Override
    public String getCommand() {
        String command = input.nextLine();
        return command.trim().toLowerCase();
    }
}
