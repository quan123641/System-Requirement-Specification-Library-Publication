package fa.training.entities;

import java.util.Date;

public class Magazine extends Publication{
    private String author;
    private int volumn;
    private int edition;

    public Magazine() {
    }

    public Magazine(int publicationYear, String publisher, Date publicationDate, String author, int volumn, int edition) {
        super(publicationYear, publisher, publicationDate);
        this.author = author;
        this.volumn = volumn;
        this.edition = edition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVolumn() {
        return volumn;
    }

    public void setVolumn(int volumn) {
        this.volumn = volumn;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    @Override
    public void display() {
        System.out.println("Manazine: publication year=" + getPublicationYear() +
                ", publisher=" + getPublisher() + ", publicationDate=" + getPublicationDate() +
                ", author=" + getAuthor() + ", volumn=" + getVolumn() +
                ", edition=" + getEdition());
    }
}
