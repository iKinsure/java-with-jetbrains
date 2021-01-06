package org.ikinsure.contacts;

import org.ikinsure.contacts.model.*;
import org.ikinsure.contacts.view.Menu;
import org.ikinsure.contacts.view.MenuController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class App {

    private final Scanner scanner;
    private final MenuController view;
    private final PhoneBook phoneBook;
    private final ContactManager manager;
    private int index = 0;

    public App(Scanner scanner) {
        this.scanner = scanner;
        this.view = new MenuController(scanner);
        this.phoneBook = new PhoneBook();
        this.manager = new ContactManager();

        Menu mainMenu  = new Menu.Builder("\n[menu] Enter action")
                .addItem("add", this::add)
                .addItem("list", this::list)
                .addItem("search", this::search)
                .addItem("count", this::count)
                .addItem("exit", view::exitAll)
                .build();
        view.run(mainMenu);
    }

    private void add() {
        System.out.print("Enter the type (person, organization): ");
        String input = scanner.nextLine();
        Contact contact = manager.createContact(input);
        contact.setValue(scanner);
        phoneBook.add(contact);
        System.out.println("The record added.");
    }

    private void list() {
        System.out.println(phoneBook.size() == 0 ? "No records to show!" : list(phoneBook.getBook()));
        System.out.print("\n[list] Enter action ([number], back): ");
        String input = scanner.nextLine();
        if ("back".equals(input)) {

        } else {
            int index = Integer.parseInt(input) - 1;
            System.out.println(phoneBook.get(index).getInfo());
            Menu recordMenu = new Menu.Builder("\n[record] Enter action")
                    .addItem("edit", this::edit)
                    .addItem("delete", this::delete)
                    .addItem("menu", view::exitMenu)
                    .build();
            view.setMenu(recordMenu);
        }
    }

    private void search() {
        System.out.print("Enter search query: ");
        String input = scanner.nextLine();
        List<Contact> result = phoneBook.search(input);
        System.out.println("Found " + result.size() + " results:");
        System.out.println(list(result));
        System.out.print("\n[search] Enter action ([number], back, again): ");
        input = scanner.nextLine();
        if ("back".equals(input)) {
        } else if ("again".equals(input)) {
            search();
        } else {
            index = Integer.parseInt(input) - 1;
            System.out.println(result.get(index).getInfo());
            Menu recordMenu = new Menu.Builder("\n[record] Enter action")
                    .addItem("edit", this::edit)
                    .addItem("delete", this::delete)
                    .addItem("menu", view::exitMenu)
                    .build();
            view.setMenu(recordMenu);
        }
    }

    private void count() {
        System.out.println("The Phone Book has " + phoneBook.size() + " records.");
    }

    /////////////////////////////////////////////////////////////////////////////////////

    private void edit() {
        Contact contact = phoneBook.get(index);
        contact.setTimeUpdated(LocalDateTime.now());
        System.out.print("Select a field (" + contact.getPropertiesKeysAsString() + "): ");
        Entry entry = contact.findEntryByKey(scanner.nextLine());
        entry.setValue(scanner);
        System.out.println("Saved");
        System.out.println(contact.getInfo());
    }

    private void delete() {
        phoneBook.remove(index);
        System.out.println("The record removed!");
    }

    ////////////////////////////////////////////////////////////////////////

    public String list(List<Contact> book) {
        AtomicInteger counter = new AtomicInteger(1);
        return book.stream()
                .map(c -> counter.getAndIncrement() + ". " + c.toString())
                .collect(Collectors.joining("\n"));
    }
}
