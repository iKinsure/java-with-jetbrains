package org.ikinsure.contacts;

import org.ikinsure.contacts.model.*;
import org.ikinsure.contacts.view.Menu;
import org.ikinsure.contacts.view.MenuController;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {

    private final Scanner scanner;
    private final MenuController view;
    private final PhoneBook phoneBook;
    private final ContactManager manager;

    public App(Scanner scanner) {
        this.scanner = scanner;
        this.view = new MenuController(scanner);
        this.phoneBook = new PhoneBook();
        this.manager = new ContactManager();

        Menu mainMenu = new Menu.Builder("Enter action (add, remove, edit, count, info, exit): ")
                .addItem("add", this::add)
                .addItem("remove", this::remove)
                .addItem("edit", this::edit)
                .addItem("count", this::count)
                .addItem("info", this::info)
                .addItem("exit", view::exitAll)
                .build();
        view.run(mainMenu);
    }

    private void add() {
        String input = enter("Enter the type (person, organization): ");
        Contact contact = manager.createContact(input);
        contact.setFields(scanner);
        phoneBook.add(contact);
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
//        if (phoneBook.size() == 0) {
//            System.out.println("No records to edit!");
//        } else {
//            System.out.println(phoneBook);
//            System.out.print("Select a record: ");
//            int index = Integer.parseInt(scanner.nextLine()) - 1;
//            Contact contact = phoneBook.get(index);
//            contact.setTimeUpdated(LocalDateTime.now());
//
//            System.out.println(Printable.select(contact.getOwner()));
//
//            if (contact.getOwner() instanceof Person) {
//                Person owner = (Person) contact.getOwner();
//                //System.out.print("Select a field (name, surname, birth, gender, number): ");
//                String input = scanner.nextLine();
//                if ("name".equals(input)) {
//                    owner.setName(enter("Enter the name: "));
//                } else if ("surname".equals(input)) {
//                    owner.setSurname(enter("Enter the surname: "));
//                } else if ("birth".equals(input)) {
//                    enter("Enter the birth date: ");
//                    System.out.println("Bad birth date!");
//                    owner.setBirth("[no data]");
//                } else if ("gender".equals(input)) {
//                    String gender = enter("Enter the gender (M, F):");
//                    if ("F".equals(gender) || "M".equals(gender)) {
//                        owner.setGender(gender);
//                    } else {
//                        owner.setGender("[no data]");
//                        System.out.println("Bad gender!");
//                    }
//                } else if ("number".equals(input)) {
//                    String number = enter("Enter the number: ");
//                    contact.setNumber(validate(number) ? number : "[no number]");
//                }
//            } else if (contact.getOwner() instanceof Organization) {
//                Organization owner = (Organization) contact.getOwner();
//
//                //System.out.print("Select a field (name, address, number): ");
//
//
//                String input = scanner.nextLine();
//                if ("name".equals(input)) {
//                    owner.setName(enter("Enter the organization name: "));
//                } else if ("address".equals(input)) {
//                    owner.setAddress(enter("Enter the address: "));
//                } else if ("number".equals(input)) {
//                    String number = enter("Enter the number: ");
//                    contact.setNumber(validate(number) ? number : "[no number]");
//                }
//            }
//            System.out.println("The record updated!");
//        }
    }

    private void count() {
        System.out.println("The Phone Book has " + phoneBook.size() + " records.");
    }

    private void info() {
        System.out.println(phoneBook.record());
        System.out.print("Select a record: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.println(phoneBook.get(index).info());
    }

    ///////////////////////////////////////////////////////////////////////////////////

    private String enter(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }



}
