package com.github.evmag.contactsbook.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class ConsoleInput implements Input{
    // === Constants ===
    private static final Logger log = LoggerFactory.getLogger(ConsoleInput.class);
    private static final Scanner input = new Scanner(System.in);


    // === Public methods ===
    @Override
    public String getCommand() {
//        String command = "";
//        if (input.hasNextLine()) {
//            command = input.nextLine();
//        }
        return getString().trim().toLowerCase();
    }

    @Override
    public String getString() {
        String line = "";
        if (input.hasNextLine()) {
            line = input.nextLine();
        }
        return line;
    }

    @Override
    public LocalDate getDate() {
        LocalDate date;
        try {
            date = LocalDate.parse(getString());
        } catch (DateTimeException e) {
            log.error("Error in parsing input date... {}", e.getMessage());
            date = LocalDate.now();
        }
        return date;
    }

    @Override
    public int getInt() {
        int value;
        try {
            value = input.nextInt();

        } catch (InputMismatchException e) {
            value = -1; // Return -1 TODO: handle this differently?
        }
        input.nextLine();
        return value;
    }
}
