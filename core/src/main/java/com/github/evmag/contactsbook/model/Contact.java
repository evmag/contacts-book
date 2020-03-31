package com.github.evmag.contactsbook.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "contacts")
public class Contact {
    // === Fields ===
    @Id
    @Column(name = "id")
    private int id = -1;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Required for displaying the date with Thymeleaf in HTML date form
    private LocalDate dateOfBirth;

    @Column(name = "notes")
    private String notes;

    // === Constructors ===

    public Contact(String firstName, String lastName, String phone, String email, LocalDate dateOfBirth, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.notes = notes;
    }

    public Contact() {}


    // === Public methods ===

    public boolean isIdentical(Object o) {
        if (equals(o)) {
            Contact contact = (Contact) o;
            return firstName.equals(contact.firstName)
                    && lastName.equals(contact.lastName)
                    && phone.equals(contact.phone)
                    && email.equals(contact.email)
                    && dateOfBirth.equals(contact.dateOfBirth)
                    && notes.equals(contact.notes);
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return id == contact.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(id);
        sb.append(") - ");
        sb.append(firstName);
        sb.append(", ");
        sb.append(lastName);
        sb.append(" : ");
        sb.append(phone);
        sb.append(", ");
        sb.append(email);
        sb.append(", ");
        sb.append(dateOfBirth);
        sb.append(".");

        return sb.toString();
    }


    // === Setters & Getters ===

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
