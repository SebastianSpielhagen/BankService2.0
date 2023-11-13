package org.spielhagen;

/**
 * Die Hauptklasse des Bankanwendungsprogramms.
 */
public class Main {

    /**
     * Der Einstiegspunkt des Programms.
     * @param args Ein String-Array von Befehlszeilenargumenten.
     */
    public static void main(String[] args) {
        // Erstelle einen BankService
        BankService bankService = new BankService();
        // Instanziiert ein neues BankService-Objekt, das Bankoperationen ermöglicht.

        // Erstelle einen Kunden
        Client client = new Client("Max", "Mustermann", "1234567890");
        // Instanziiert ein neues Client-Objekt mit dem Namen Max Mustermann und der Kundennummer 1234567890.

        // Eröffne ein Konto für den Kunden
        String accountNumber = bankService.openAccount(client);
        // Ruft die Methode openAccount des BankService-Objekts auf, um ein Konto für den Kunden zu erstellen.

        System.out.println("Konto eröffnet. Kontonummer: " + accountNumber);
        // Gibt die Meldung "Konto eröffnet. Kontonummer: " gefolgt von der generierten Kontonummer auf der Konsole aus.

        // Einzahlung auf das Konto
        bankService.deposit(accountNumber, 1000);
        // Ruft die Methode deposit des BankService-Objekts auf, um 1000 auf das Konto einzuzahlen.

        System.out.println("Einzahlung erfolgreich.");
        // Gibt die Meldung "Einzahlung erfolgreich." auf der Konsole aus.

        // Aufteilung des Kontostands auf mehrere Konten
        String[] newAccountNumbers = bankService.split(accountNumber);
        // Ruft die Methode split des BankService-Objekts auf, um den Kontostand auf mehrere Konten aufzuteilen.

        for (String newAccountNumber : newAccountNumbers) {
            // Iteriert durch die Liste der neuen Kontonummern.
            System.out.println("Neue Kontonummer: " + newAccountNumber);
            // Gibt jede neue Kontonummer auf der Konsole aus.
        }
        // Abhebung vom Konto
        bankService.withdraw(accountNumber, 500);
        // Ruft die Methode withdraw des BankService-Objekts auf, um 500 vom Konto abzuheben.

        System.out.println("Abhebung erfolgreich.");
        // Gibt die Meldung "Abhebung erfolgreich." auf der Konsole aus.

        // Überweisung von einem Konto auf ein anderes
        String toAccountNumber = bankService.openAccount(new Client("Erika", "Musterfrau", "0987654321"));
        // Ruft die Methode openAccount des BankService-Objekts auf, um ein Konto für den Kunden Erika Musterfrau zu erstellen.

        bankService.transfer(accountNumber, toAccountNumber, 200);
        // Ruft die Methode transfer des BankService-Objekts auf, um 200 von einem Konto auf ein anderes zu überweisen.

        System.out.println("Überweisung erfolgreich.");
        // Gibt die Meldung "Überweisung erfolgreich." auf der Konsole aus.
    }
}
