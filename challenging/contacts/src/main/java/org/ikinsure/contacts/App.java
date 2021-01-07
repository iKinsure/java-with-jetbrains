package org.ikinsure.contacts;

import org.ikinsure.contacts.model.*;
import org.ikinsure.contacts.view.Menu;
import org.ikinsure.contacts.view.MenuController;

import java.util.List;
import java.util.Scanner;

public class App {

    private final Scanner scanner;
    private final MenuController view;
    private final PhoneBook phoneBook;
    private final ContactManager manager;

    private int index = 0;
    private List<Contact> searchResult;

    private final Menu listMenu;
    private final Menu recordMenu;
    private final Menu searchMenu;

    public App(Scanner scanner, PhoneBook phoneBook) {
        this.scanner = scanner;
        this.view = new MenuController(scanner);
        this.phoneBook = phoneBook;
        this.manager = new ContactManager();

        recordMenu = new Menu.Builder("record")
                .addItem("edit", this::edit)
                .addItem("delete", this::delete)
                .addItem("menu", view::exitMenu)
                .build();
        listMenu = new Menu.Builder("list")
                .addItem("[number]", () -> number(phoneBook.getBook()))
                .addItem("back", view::exitMenu)
                .build();
        searchMenu = new Menu.Builder("search")
                .addItem("[number]", () -> number(searchResult))
                .addItem("back", view::exitMenu)
                .addItem("again", this::again)
                .build();
        view.run(new Menu.Builder("menu")
                .addItem("add", this::add)
                .addItem("list", this::list)
                .addItem("search", this::search)
                .addItem("count", () -> System.out.println("The Phone Book has " + phoneBook.size() + " records."))
                .addItem("exit", view::exitAll)
                .build());
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }

    private void number(List<Contact> list) {
        index = Integer.parseInt(view.getLastId()) - 1;
        System.out.println(list.get(index).getProperties());
        view.setMenu(recordMenu);
    }

    private void edit() {
        Contact contact = phoneBook.get(index);
        contact.updateTime();
        System.out.print("Select a field (" + contact.getPropertiesKeys() + "): ");
        Entry entry = contact.findEntryByKey(scanner.nextLine());
        entry.setValue(scanner);
        System.out.println("Saved");
        System.out.println(contact.getProperties());
        view.exitMenu();
    }

    private void delete() {
        phoneBook.remove(index);
        System.out.println("The record removed!");
        view.exitMenu();
    }

    private void add() {
        System.out.print("Enter the type (person, organization): ");
        Contact contact = manager.createContact(scanner.nextLine());
        contact.setValue(scanner);
        phoneBook.add(contact);
        System.out.println("The record added.");
    }

    private void list() {
        System.out.println(phoneBook.size() == 0 ? "No records to show!" : PhoneBook.list(phoneBook.getBook()));
        view.addMenu(listMenu);
    }

    private void again() {
        view.exitMenu();
        search();
    }

    private void search() {
        System.out.print("Enter search query: ");
        String input = scanner.nextLine();
        searchResult = phoneBook.search(input);
        System.out.println("Found " + searchResult.size() + " results:");
        System.out.println(PhoneBook.list(searchResult));
        view.addMenu(searchMenu);
    }
}
