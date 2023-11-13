package org.spielhagen;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse BankService stellt Bankdienstleistungen bereit, darunter das Erstellen von Konten,
 * Einzahlungen, Auszahlungen, Überweisungen und die Aufteilung von Kontoständen auf mehrere Konten.
 */
public class BankService {
    // Eine Liste von Bankkonten, die von diesem BankService verwaltet wird.
    private List<Account> accounts;

    /**
     * Der Konstruktor erstellt ein neues BankService-Objekt und initialisiert die Liste der Konten.
     */
    public BankService() {
        this.accounts = new ArrayList<>();
    }

    /**
     * Die Methode split teilt den Kontostand eines Kontos auf mehrere Konten auf.
     * @param accountNumber Die Kontonummer des zu teilenden Kontos.
     * @return Ein Array von neuen Kontonummern, auf die der Kontostand aufgeteilt wurde.
     */
    public String[] split(String accountNumber) {
        // Findet das Konto anhand der Kontonummer.
        Account account = findAccount(accountNumber);
        // Eine Liste zur Speicherung der neuen Kontonummern.
        List<String> newAccountNumbers = new ArrayList<>();

        // Überprüft, ob das Konto gefunden wurde.
        if (account != null) {
            // Teilt die Namen der Kontoinhaber, falls vorhanden.
            String[] accountOwners = account.getClient().getLastName().split(" ");

            // Berechnet den Betrag, der auf jedes Konto aufgeteilt wird.
            double splitAmount = Math.floor(account.getBalance() / accountOwners.length);
            double remainder = account.getBalance() % accountOwners.length;

            // Schleife zum Erstellen neuer Konten und Hinzufügen zu den Kontenliste.
            for (int i = 0; i < accountOwners.length; i++) {
                String newAccountNumber = generateAccountNumber();
                Account newAccount = new Account(newAccountNumber, splitAmount, new Client("", accountOwners[i], ""));
                accounts.add(newAccount);
                newAccountNumbers.add(newAccountNumber);
            }

            // Berücksichtigt einen eventuellen Restbetrag und fügt ihn dem ersten Konto hinzu.
            for (int i = 0; i < remainder; i++) {
                accounts.get(i).setBalance(accounts.get(i).getBalance() + 1);
            }
        }
        // Gibt das Array der neuen Kontonummern zurück.
        return newAccountNumbers.toArray(new String[0]);
    }

    /**
     * Die Methode openAccount erstellt ein neues Konto für einen Kunden und gibt die Kontonummer zurück.
     * @param customer Der Kunde, für den das Konto erstellt wird.
     * @return Die neu generierte Kontonummer.
     */
    public String openAccount(Client customer) {
        // Generiert eine neue Kontonummer.
        String accountNumber = generateAccountNumber();
        // Erstellt ein neues Konto mit dem Kunden und einem anfänglichen Kontostand von 0.
        Account account = new Account(accountNumber, 0, customer);
        // Fügt das neue Konto zur Kontenliste hinzu.
        accounts.add(account);
        // Gibt die neu generierte Kontonummer zurück.
        return accountNumber;
    }

    /**
     * Die Methode deposit führt eine Einzahlung auf ein Konto durch.
     * @param accountNumber Die Kontonummer des Kontos, auf das eingezahlt wird.
     * @param amount Der Betrag, der eingezahlt wird.
     * @return True, wenn die Einzahlung erfolgreich war, sonst False.
     */
    public boolean deposit(String accountNumber, double amount) {
        // Findet das Konto anhand der Kontonummer.
        Account account = findAccount(accountNumber);
        // Überprüft, ob das Konto gefunden wurde.
        if (account != null) {
            // Erhöht den Kontostand des Kontos um den Einzahlungsbetrag.
            account.setBalance(account.getBalance() + amount);
            return true; // Die Einzahlung war erfolgreich.
        }
        return false; // Die Einzahlung war nicht erfolgreich, da das Konto nicht gefunden wurde.
    }

    /**
     * Die Methode withdraw führt eine Auszahlung von einem Konto durch.
     * @param accountNumber Die Kontonummer des Kontos, von dem abgehoben wird.
     * @param amount Der Betrag, der abgehoben wird.
     * @return True, wenn die Auszahlung erfolgreich war, sonst False.
     */
    public boolean withdraw(String accountNumber, double amount) {
        // Findet das Konto anhand der Kontonummer.
        Account account = findAccount(accountNumber);
        // Überprüft, ob das Konto gefunden wurde und ob ausreichend Guthaben vorhanden ist.
        if (account != null && account.getBalance() >= amount) {
            // Verringert den Kontostand des Kontos um den Auszahlungsbetrag.
            account.setBalance(account.getBalance() - amount);
            return true; // Die Auszahlung war erfolgreich.
        }
        return false; // Die Auszahlung war nicht erfolgreich, entweder das Konto wurde nicht gefunden oder es fehlt Guthaben.
    }

    /**
     * Die Methode transfer führt eine Überweisung von einem Konto auf ein anderes durch.
     * @param fromAccountNumber Die Kontonummer des sendenden Kontos.
     * @param toAccountNumber Die Kontonummer des empfangenden Kontos.
     * @param amount Der Betrag, der überwiesen wird.
     * @return True, wenn die Überweisung erfolgreich war, sonst False.
     */
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        // Findet das sendende Konto anhand der Kontonummer.
        Account fromAccount = findAccount(fromAccountNumber);
        // Findet das empfangende Konto anhand der Kontonummer.
        Account toAccount = findAccount(toAccountNumber);

        // Überprüft, ob beide Konten gefunden wurden und ob ausreichend Guthaben auf dem sendenden Konto vorhanden ist.
        if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
            // Verringert den Kontostand des sendenden Kontos um den Überweisungsbetrag.
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            // Erhöht den Kontostand des empfangenden Kontos um den Überweisungsbetrag.
            toAccount.setBalance(toAccount.getBalance() + amount);
            return true; // Die Überweisung war erfolgreich.
        }
        return false; // Die Überweisung war nicht erfolgreich, entweder die Konten wurden nicht gefunden oder es fehlt Guthaben auf dem sendenden Konto.
    }

    /**
     * Die Methode generateAccountNumber generiert eine zufällige Kontonummer.
     * @return Die neu generierte Kontonummer.
     */
    private String generateAccountNumber() {
        // Generiert eine zufällige Kontonummer und gibt sie als String zurück.
        return String.valueOf((int) (Math.random() * 1000000000));
    }

    /**
     * Die Methode findAccount sucht ein Konto anhand seiner Kontonummer.
     * @param accountNumber Die zu suchende Kontonummer.
     * @return Das gefundene Konto oder null, wenn kein Konto mit der angegebenen Kontonummer gefunden wurde.
     */
    private Account findAccount(String accountNumber) {
        // Iteriert durch die Liste der Konten und sucht nach einem Konto mit der angegebenen Kontonummer.
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account; // Gibt das gefundene Konto zurück.
            }
        }
        return null; // Gibt null zurück, wenn kein Konto mit der angegebenen Kontonummer gefunden wurde.
    }
}
