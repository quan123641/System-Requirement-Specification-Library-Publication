package fa.training.utils;

import fa.training.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class ValidationRule {
    public static boolean ValidateIsbn(String isbn, List<Book> books) {
        int count = 0;
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                count++;
            }
        }
        if (count == 0  && isbn.matches("^\\d[0-9-]{8,15}\\d$") ) {
            return true;
        }
        return false;
    }
}
