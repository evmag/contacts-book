package com.github.evmag.contactsbook.io;

import java.time.LocalDate;

public interface Input {
    String getCommand();
    String getString();
    LocalDate getDate();
    int getInt();
}
