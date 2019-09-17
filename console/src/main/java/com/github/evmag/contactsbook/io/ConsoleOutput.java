package com.github.evmag.contactsbook.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.PrintStream;

@Component
public class ConsoleOutput implements Output{
    // === Constants ===
    private static final Logger logger = LoggerFactory.getLogger(ConsoleOutput.class);
    private static final PrintStream out = System.out;

    // === Public methods ===
    @Override
    public void printString(String string) {
        out.println(string);
    }
}
