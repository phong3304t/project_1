package com.library.view.cli;

import com.library.controller.*;
import com.library.model.exceptions.DocumentNotFoundException;
import com.library.model.exceptions.InsufficientQuantityException;

import java.util.Scanner;

public class CLI {
    private Scanner scanner;
    private Menu menu;
    private DocumentController documentController;
    private UserController userController;
    private BorrowController borrowController;
    private ReturnController returnController;

    public CLI() {
        scanner = new Scanner(System.in);
        menu = new Menu();
        documentController = new DocumentController();
        userController = new UserController();
        borrowController = new BorrowController();
        returnController = new ReturnController();
    }

    public void start() throws DocumentNotFoundException, InsufficientQuantityException {
        boolean running = true;
        while (running) {
            menu.showMainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    running = true;
                    System.out.println("Exiting the application!");
                    break;
                case 1:
                    documentController.addDocument();
                    break;
                case 2:
                    documentController.removeDocument();
                    break;
                case 3:
                    documentController.updateDocument();
                    break;
                case 4:
                    documentController.findDocumentByTitle();
                    break;
                case 5:
                    documentController.showAllDocuments();
                    break;
                case 6:
                    userController.addUser();
                    break;
                case 7:
                    borrowController.borrowDocument();
                    break;
                case 8:
                    returnController.returnDocument();
                    break;
                case 9:
                    userController.showAllUsers();
                    break;
                default:
                    System.out.println("Action is not supported.");
            }
        }
    }
}

