package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

class Contact implements Serializable {
    String name;
    String phone;
    String email;

    Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Phone: %s, Email: %s", name, phone, email);
    }
}

public class EnhancedContactManagementSystem {
    private static final Logger logger = Logger.getLogger(EnhancedContactManagementSystem.class.getName());
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            logger.info("\n--- Enhanced Contact Management Menu ---");
            logger.info("1. Add Contact");
            logger.info("2. View Contacts");
            logger.info("3. Update Contact");
            logger.info("4. Delete Contact");
            logger.info("5. Search Contact");
            logger.info("6. Sort Contacts");
            logger.info("7. Save Contacts");
            logger.info("8. Load Contacts");
            logger.info("9. Export Contacts to CSV");
            logger.info("10. Exit");
            logger.info("Select an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                logger.warning("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    updateContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    searchContact(scanner);
                    break;
                case 6:
                    sortContacts();
                    break;
                case 7:
                    saveContacts();
                    break;
                case 8:
                    loadContacts();
                    break;
                case 9:
                    exportToCSV(scanner);
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    logger.warning("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addContact(Scanner scanner) {
        logger.info("Enter name: ");
        String name = scanner.nextLine();
        logger.info("Enter phone: ");
        String phone = scanner.nextLine();
        logger.info("Enter email: ");
        String email = scanner.nextLine();
        if (EMAIL_PATTERN.matcher(email).matches()) {
            contacts.add(new Contact(name, phone, email));
            logger.info("Contact added successfully!");
        } else {
            logger.warning("Invalid email format.");
        }
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            logger.info("No contacts available.");
        } else {
            contacts.forEach(contact -> logger.info(contact.toString()));
        }
    }

    private static void updateContact(Scanner scanner) {
        logger.info("Enter name of the contact to update: ");
        String name = scanner.nextLine();
        Contact contact = findContact(name);
        if (contact != null) {
            logger.info("Enter new phone: ");
            contact.phone = scanner.nextLine();
            logger.info("Enter new email: ");
            String email = scanner.nextLine();
            if (EMAIL_PATTERN.matcher(email).matches()) {
                contact.email = email;
                logger.info("Contact updated successfully!");
            } else {
                logger.warning("Invalid email format.");
            }
        } else {
            logger.warning("Contact not found.");
        }
    }

    private static void deleteContact(Scanner scanner) {
        logger.info("Enter name of the contact to delete: ");
        String name = scanner.nextLine();
        Contact contact = findContact(name);
        if (contact != null) {
            contacts.remove(contact);
            logger.info("Contact deleted successfully!");
        } else {
            logger.warning("Contact not found.");
        }
    }

    private static void searchContact(Scanner scanner) {
        logger.info("Enter phone or email to search: ");
        String searchQuery = scanner.nextLine();
        contacts.stream()
                .filter(contact -> contact.phone.equals(searchQuery) || contact.email.equals(searchQuery))
                .forEach(contact -> logger.info(contact.toString()));
    }

    private static void sortContacts() {
        contacts.sort(Comparator.comparing(contact -> contact.name));
        logger.info("Contacts sorted successfully!");
    }

    private static void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("contacts.dat"))) {
            oos.writeObject(contacts);
            logger.info("Contacts saved successfully!");
        } catch (IOException e) {
            logger.severe("Error saving contacts: " + e.getMessage());
        }
    }

    private static void loadContacts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("contacts.dat"))) {
            contacts = (ArrayList<Contact>) ois.readObject();
            logger.info("Contacts loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            logger.severe("Error loading contacts: " + e.getMessage());
        }
    }

    private static void exportToCSV(Scanner scanner) {
        logger.info("Enter filename for CSV export: ");
        String filename = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Name,Phone,Email");
            for (Contact contact : contacts) {
                writer.printf("%s,%s,%s%n", contact.name, contact.phone, contact.email);
            }
            logger.info("Contacts exported to CSV successfully!");
        } catch (IOException e) {
            logger.severe("Error exporting contacts to CSV: " + e.getMessage());
        }
    }

    private static Contact findContact(String name) {
        return contacts.stream().filter(contact -> contact.name.equals(name)).findFirst().orElse(null);
    }
}