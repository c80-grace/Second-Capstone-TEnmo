package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

    private AuthenticatedUser currentUser;
    private final AccountService accountService = new AccountService();
    private final TransferService transferService = new TransferService();
    private final UserService userService = new UserService();
    private final Account account = new Account();

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
//            } else if (menuSelection == 3) {
//                viewPendingRequests();
            } else if (menuSelection == 3) {
                sendBucks();
//            } else if (menuSelection == 5) {
//                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            //consoleService.pause();
        }
    }

	private void viewCurrentBalance() {

        int userId = userService.findIdByUsername(currentUser.getUser().getUsername());
        Account currentAccount = accountService.getAccountByUserId(userId);

        if (currentAccount.getBalance() >= 0) {
            consoleService.printBalance(currentAccount);
        } else {
            consoleService.printErrorMessage();
        }

	}

	private void viewTransferHistory() {
        int userId = userService.findIdByUsername(currentUser.getUser().getUsername());
        Account currentAccount = accountService.getAccountByUserId(userId);

        consoleService.printTransfers(currentAccount);
        consoleService.printTransferDetails();;
		
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
        int userId = userService.findIdByUsername(currentUser.getUser().getUsername());
        Account currentAccount = accountService.getAccountByUserId(userId);
        int accountId = currentAccount.getAccountId();

		consoleService.printUserList();
        Transfer transfer = consoleService.handleTransfer(accountId);
        transferService.transferFrom(transfer);


	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
