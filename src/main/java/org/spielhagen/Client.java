package org.spielhagen;
/**
 * Die Klasse Client repräsentiert einen Bankkunden mit Vornamen, Nachnamen und einer Kundennummer.
 */
public class Client {
    // Der Vorname des Kunden.
    private String firstName;

    // Der Nachname des Kunden.
    private String lastName;

    // Die Kundennummer des Kunden.
    private String customerNumber;

    /**
     * Der Konstruktor erstellt ein neues Client-Objekt mit den gegebenen Parametern.
     * @param firstName Der Vorname des Kunden.
     * @param lastName Der Nachname des Kunden.
     * @param customerNumber Die Kundennummer des Kunden.
     */
    public Client(String firstName, String lastName, String customerNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = customerNumber;
    }

    /**
     * Getter-Methode für den Vornamen.
     * @return Der Vorname des Kunden.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter-Methode für den Vornamen.
     * @param firstName Der neue Vorname für den Kunden.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter-Methode für den Nachnamen.
     * @return Der Nachname des Kunden.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter-Methode für den Nachnamen.
     * @param lastName Der neue Nachname für den Kunden.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter-Methode für die Kundennummer.
     * @return Die Kundennummer des Kunden.
     */
    public String getCustomerNumber() {
        return customerNumber;
    }

    /**
     * Setter-Methode für die Kundennummer.
     * @param customerNumber Die neue Kundennummer für den Kunden.
     */
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
