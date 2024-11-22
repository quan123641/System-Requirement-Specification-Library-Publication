package fa.training.services;

import fa.training.entities.Magazine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MagazineService {
    public static Magazine addMagazine(Scanner sc) throws ParseException {
        System.out.println("Enter information for adding a new book: ");

        System.out.print("Publication year: ");
        int publicationYear = Integer.parseInt(sc.nextLine());

        System.out.print("Publisher: ");
        String publisher = sc.nextLine();

        System.out.print("Publication date: ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInput = sc.nextLine();
        Date publicationDate = sdf.parse(dateInput);

        System.out.print("Author: ");
        String author = sc.nextLine();

        System.out.print("Volumn: ");
        int volumn = Integer.parseInt(sc.nextLine());

        System.out.print("Edition: ");
        int edition = Integer.parseInt(sc.nextLine());

        return new Magazine(publicationYear, publisher, publicationDate, author, volumn, edition);
    }

    public static void displayTop10Volumn(List<Magazine> magazine) {
        System.out.println("List of top 10 magazine volumes: ");
        Collections.sort(magazine, (a, b) -> (a.getVolumn() > b.getVolumn()) ? -1 : (a.getVolumn() < b.getVolumn()) ? 1 : 0);
        if (magazine.size() < 10) {
            for (int i = 0; i < magazine.size(); i++) {
                System.out.printf("%d. ", i + 1);
                magazine.get(i).display();
            }
        } else {
            for (int i = 0; i < 10; i++) {
                System.out.printf("%d. ", i + 1);
                magazine.get(i).display();
            }
        }
    }
}
