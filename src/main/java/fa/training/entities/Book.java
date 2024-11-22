package fa.training.entities;

import java.util.Date;
import java.util.Set;

public class Book extends Publication {
    private String isbn;
    private Set<String> authors;
    private String publicationPlace;

    public Book() {

    }

    public Book(int publicationYear, String publisher, Date publicationDate, String isbn, Set<String> authors, String publicationPlace) {
        super(publicationYear, publisher, publicationDate);
        this.isbn = isbn;
        this.authors = authors;
        this.publicationPlace = publicationPlace;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public String getPublicationPlace() {
        return publicationPlace;
    }

    public void setPublicationPlace(String publicationPlace) {
        this.publicationPlace = publicationPlace;
    }

    @Override
    public void display() {
        System.out.println("Book: publication year=" + getPublicationYear() +
                ", publisher=" + getPublisher() + ", publicationDate=" + getPublicationDate().toString() +
                ", isbn=" + getIsbn() + ", authors=" + getAuthors() +
                ", publicationPlace=" + getPublicationPlace());
    }
}
