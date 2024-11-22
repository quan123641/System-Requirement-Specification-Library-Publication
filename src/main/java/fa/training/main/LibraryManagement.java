package fa.training.main;

import fa.training.entities.Book;
import fa.training.entities.Magazine;
import fa.training.entities.Publication;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static fa.training.services.BookService.addAuthor;
import static fa.training.services.BookService.addBook;
import static fa.training.services.BookService.searchBook;
import static fa.training.services.MagazineService.addMagazine;
import static fa.training.services.MagazineService.displayTop10Volumn;


public class LibraryManagement {
    private static void displayPublicationsWithSameYearAndPublisher(List<Publication> publications) {
        Map<String, List<Publication>> groupedPublications = new HashMap<>();

        for (Publication publication : publications) {
            String key = publication.getPublicationYear() + " - " + publication.getPublisher();
            groupedPublications.computeIfAbsent(key, k -> new ArrayList<>()).add(publication);
        }

        for (Map.Entry<String, List<Publication>> entry : groupedPublications.entrySet()) {
            List<Publication> publicationList = entry.getValue();
            if (publicationList.size() > 1) {
                System.out.println("Publications with same year and publisher: " + entry.getKey());
                int count = 0;
                for (Publication publication : publicationList) {
                    System.out.printf("%d. ", count);
                    if (publication instanceof Book) {
                        Book book = (Book) publication;
                        book.display();
                    }
                    if (publication instanceof Magazine) {
                        Magazine magazine = (Magazine) publication;
                        magazine.display();
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        List<Book> books = new ArrayList<>();
        List<Magazine> magazines = new ArrayList<>();

        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("-------------------------------------");
            System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add a book");
            System.out.println("2. Add a magazine");
            System.out.println("3. Display books and magazines");
            System.out.println("4. Add author to book");
            System.out.println("5. Display top 10 of magazines by volumn");
            System.out.println("6. Search book by (isbn, author, publisher)");
            System.out.println("7. Exit");
            System.out.println();
            System.out.print("Please choose function you'd like to do: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ignored) {}

            switch (choice) {
                case 1:
                    if (addBook(sc, books)) {
                        System.out.println("Adding was successfully.");
                    } else {
                        System.out.println("Adding was fail.");
                    }
                    break;
                case 2:
                    magazines.add(addMagazine(sc));
                    break;
                case 3:
                    System.out.println("List of books and magazines:");
                    List<Publication> publications = new ArrayList<>();
                    publications.addAll(books);
                    publications.addAll(magazines);
                    displayPublicationsWithSameYearAndPublisher(publications);

                    break;
                case 4:
                    System.out.print("Enter isbn to find book: ");
                    String isbn = sc.nextLine();
                    int count = 0;
                    for (int i = 0; i < books.size(); i++) {
                        if (books.get(i).getIsbn().equalsIgnoreCase(isbn)) {
                            if (addAuthor(sc, i, books)) {
                                System.out.println("Add successfully");
                            } else {
                                System.out.println("Author existed");
                            }
                            count++;
                            break;
                        }
                    }
                    if (count == 0) {
                        System.out.println("Book not found");
                    }
                    break;
                case 5:
                    displayTop10Volumn(magazines);
                    break;
                case 6:
                    List<Book> result = searchBook(sc, books);
                    int counter = 0;
                    if (result.isEmpty()) {
                        System.out.println("Book not found");
                    } else {
                        for (Book book : result) {
                            counter++;
                            System.out.printf("%d. ", counter);
                            book.display();
                        }
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose valid function");
                    break;
            }
        } while (true);
    }
}