package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {
    private UserService userService = new UserService();
    private TransferService transferService = new TransferService();
    private AccountService accountService = new AccountService();



    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void printBalance(Account account){
        System.out.println("Your current Balance is: " + account.getBalance());
    }

    public void printUserList(){
        User[] users = userService.listUsers();


        System.out.println("-------------------------------------------");
        System.out.println("Users ID          Name");
        System.out.println("-------------------------------------------");
        for (User user: users) {
            System.out.println(user.getId() +"              " + user.getUsername());
        }
        System.out.println("-------------------------------------------");

    }
    public Transfer handleTransfer(int accountFrom) {
        User[] users = userService.listUsers();
        Account[] accounts = accountService.listAccounts();

        Transfer transfer = new Transfer();
        System.out.print("Enter ID of user you are ending to (0 to cancel): ");
        int userId = scanner.nextInt();
        Account account = accountService.getAccountByUserId(userId);
        int accountTo = account.getAccountId();

        for (Account account1 : accounts) {
            if (account1.getAccountId() == accountTo) {
                if (accountTo != 0) {
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();

                    transfer.setAccountFrom(accountFrom);
                    transfer.setAccountTo(accountTo);
                    transfer.setAmount(amount);

                    return transfer;
                }
            }
        }
        System.out.println();
        System.out.println("Invalid User");
        return transfer;
    }

    public void printTransfers() {
        Transfer[] transfers = transferService.listTransfers();
        if (transfers != null) {
            System.out.println("--------------------------------------------");
            System.out.println("Transfers");
            System.out.println("ID              From/To               Amount");
            System.out.println("--------------------------------------------");
            for (Transfer transfer: transfers) {
                System.out.println(transfer.getTransferId() +"            " + transfer.getAccountTo() +"                $ " + transfer.getAmount());
            }
            System.out.println("-------------------------------------------");
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

}
