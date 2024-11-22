package fa.training.services;

import fa.training.entities.Book;
import fa.training.utils.ValidationRule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BookService {
    public static boolean addBook(Scanner sc, List<Book> books) {

        System.out.println("Enter information for adding a new book: ");

        System.out.print("Publication year: ");
        int publicationYear;
        try {
            publicationYear = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid publication year!");
            return false;
        }

        System.out.print("Publisher: ");
        String publisher = sc.nextLine();

        System.out.print("Publication date: ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInput = sc.nextLine();
        Date publicationDate;
        try {
            publicationDate = sdf.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Invalid publication date!");
            return false;
        }

        System.out.print("ISBN (Unique with 10-17 digit number and '-' quote. Ex: 678-3-16-1486): ");
        String isbn = sc.nextLine();
        while (!ValidationRule.ValidateIsbn(isbn, books)) {
            System.out.print("ISBN (Unique with 10-17 digit number and '-' quote. Ex: 678-3-16-1486): ");
            isbn = sc.nextLine();
        }

        System.out.println("List of Authors: ");
        Set<String> authors = new HashSet<>();
        int count = 0;
        while (true) {
            count++;
            System.out.printf("Author %d (do not input and Enter to Exit): ", count);
            String authorName = sc.nextLine();
            if (authorName.isEmpty()) {
                break;
            }
            authors.add(authorName);
        }

        System.out.print("Publication place: ");
        String place = sc.nextLine();

        books.add(new Book(publicationYear, publisher, publicationDate, isbn, authors, place));

        return true;
    }

    public static boolean addAuthor(Scanner sc, int index, List<Book> books) {
        int count = 0;
        System.out.print("Add new author: ");
        String authorName = sc.nextLine();
        for (String author : books.get(index).getAuthors()) {
            if (author.equalsIgnoreCase(authorName)) {
                count++;
                break;
            }
        }
        if (count == 0) {
            books.get(index).getAuthors().add(authorName);
            return true;
        }
        return false;
    }

    public static List<Book> searchBook(Scanner sc, List<Book> books) {
        List<Book> result = new ArrayList<>();
        Comparator<Book> comparator;
        comparator = Comparator.comparing(Book::getIsbn).thenComparing(Book::getPublicationDate);

        System.out.println("===== Search Book =====");
        System.out.println("1. Search Book by ISBN");
        System.out.println("2. Search Book by author");
        System.out.println("3. Search Book by publisher");
        System.out.print("Please choose function: ");
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException ignored) {}

        switch (choice) {
            case 1:
                System.out.println("Enter ISBN: ");
                String isbn = sc.nextLine();
                for (Book book : books) {
                    if (book.getIsbn().equals(isbn)) {
                        result.add(book);
                        break;
                    }
                }
                break;
            case 2:
                System.out.print("Enter author: ");
                String author = sc.nextLine();
                for (Book book : books) {
                    if (book.getAuthors().contains(author)) {
                        result.add(book);
                    }
                }
                break;
            case 3:
                System.out.print("Enter publisher: ");
                String publisher = sc.nextLine();
                for (Book book : books) {
                    if (book.getPublisher().equals(publisher)) {
                        result.add(book);
                    }
                }
                break;
            default:
                System.out.println("Please choose valid function");
                break;
        }
        Collections.sort(result, comparator);
        return result;
    }
}
