package org.ikinsure.contacts;

import org.ikinsure.contacts.view.Menu;
import org.ikinsure.contacts.view.MenuController;

import java.util.Scanner;
import java.util.regex.Pattern;

public class App {

    private final Scanner scanner;
    private final MenuController view;
    private final PhoneBook phoneBook;

    public App(Scanner scanner) {
        this.scanner = scanner;
        this.view = new MenuController(scanner);
        this.phoneBook = new PhoneBook();

        Menu mainMenu = new Menu.Builder("Enter action (add, remove, edit, count, list, exit): ")
                .addItem("add", this::add)
                .addItem("remove", this::remove)
                .addItem("edit", this::edit)
                .addItem("count", this::count)
                .addItem("list", this::list)
                .addItem("exit", view::exitAll)
                .build();
        view.run(mainMenu);
    }

    private void add() {
        String name = enter("Enter the name: ");
        String surname = enter("Enter the surname: ");
        String number = enter("Enter the number: ");
        phoneBook.add(new Contact(name, surname, validate(number) ? number : "[no number]"));
        System.out.println("The record added.");
    }

    private void remove() {
        if (phoneBook.size() == 0) {
            System.out.println("No records to remove!");
        } else {
            System.out.println(phoneBook);
            System.out.print("Select a record: ");
            phoneBook.remove(Integer.parseInt(scanner.nextLine()) - 1);
            System.out.println("The record removed!");
        }
    }

    private void edit() {
        if (phoneBook.size() == 0) {
            System.out.println("No records to edit!");
        } else {
            System.out.println(phoneBook);
            System.out.print("Select a record: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            Contact prev = phoneBook.get(index);
            System.out.print("Select a field (name, surname, number): ");
            String input = scanner.nextLine();
            if ("name".equals(input)) {
                String name = enter("Enter the name: ");
                phoneBook.edit(index, new Contact(name, prev.getSurname(), prev.getNumber()));
            } else if ("surname".equals(input)) {
                String surname = enter("Enter the surname: ");
                phoneBook.edit(index, new Contact(prev.getName(), surname, prev.getNumber()));
            } else if ("number".equals(input)) {
                String number = enter("Enter the number: ");
                phoneBook.edit(index, new Contact(
                        prev.getName(),
                        prev.getSurname(),
                        validate(number) ? number : "[no number]"));
            }
            System.out.println("The record updated!");
        }
    }

    private void count() {
        System.out.println("The Phone Book has " + phoneBook.size() + " records.");
    }

    private void list() {
        System.out.println(phoneBook);
    }

    ///////////////////////////////////////////////////////////////////////////////////

    private String enter(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private boolean validate(String number) {
        Pattern pattern = Pattern.compile("^\\+?([\\da-zA-Z]+[\\s-]?)?(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$");
        boolean matches = pattern.matcher(number).matches();
        if (!matches) {
            System.out.println("Wrong number format!");
        }
        return matches;
    }

}
