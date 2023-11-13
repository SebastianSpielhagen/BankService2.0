package org.spielhagen;

/**
 * Die Klasse Account repräsentiert ein Bankkonto mit einer Kontonummer, einem Kontostand und einem Kunden.
 */
public class Account {
    // Die Kontonummer des Kontos.
    private String accountNumber;

    // Der aktuelle Kontostand des Kontos.
    private double balance;

    // Der Kunde, dem das Konto gehört.
    private Client client;

    /**
     * Der Konstruktor erstellt ein neues Account-Objekt mit den gegebenen Parametern.
     * @param accountNumber Die Kontonummer des Kontos.
     * @param balance Der anfängliche Kontostand des Kontos.
     * @param client Der Kunde, dem das Konto gehört.
     */
    public Account(String accountNumber, double balance, Client client) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.client = client;
    }

    /**
     * Getter-Methode für die Kontonummer.
     * @return Die Kontonummer des Kontos.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Setter-Methode für die Kontonummer.
     * @param accountNumber Die neue Kontonummer für das Konto.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Getter-Methode für den Kontostand.
     * @return Der aktuelle Kontostand des Kontos.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Setter-Methode für den Kontostand.
     * @param balance Der neue Kontostand für das Konto.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Getter-Methode für den Kunden.
     * @return Der Kunde, dem das Konto gehört.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Setter-Methode für den Kunden.
     * @param client Der neue Kunde für das Konto.
     */
    public void setClient(Client client) {
        this.client = client;
    }
}
