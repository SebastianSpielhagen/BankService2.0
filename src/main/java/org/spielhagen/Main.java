package org.spielhagen;

public class Main {
    public static void main(String[] args) {
        // Erstelle einen BankService
        BankService bankService = new BankService();

        // Erstelle einen Kunden
        Client client = new Client("Max", "Mustermann", "1234567890");

        // Eröffne ein Konto für den Kunden
        String accountNumber = bankService.openAccount(client);
        System.out.println("Konto eröffnet. Kontonummer: " + accountNumber);

        // Einzahlung auf das Konto
        bankService.deposit(accountNumber, 1000);
        System.out.println("Einzahlung erfolgreich.");

        // Aufteilung des Kontostands auf mehrere Konten
        String[] newAccountNumbers = bankService.split(accountNumber);
        for (String newAccountNumber : newAccountNumbers) {
            System.out.println("Neue Kontonummer: " + newAccountNumber);
        }

        // Abhebung vom Konto
        bankService.withdraw(accountNumber, 500);
        System.out.println("Abhebung erfolgreich.");

        // Überweisung von einem Konto auf ein anderes
        String toAccountNumber = bankService.openAccount(new Client("Erika", "Musterfrau", "0987654321"));
        bankService.transfer(accountNumber, toAccountNumber, 200);
        System.out.println("Überweisung erfolgreich.");
    }
}
