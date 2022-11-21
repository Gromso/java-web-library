package com.example.Library.hibernate.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int books_id;
    private String title;
    private String cover;
    private String synopsis;
    private LocalDate release_date;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id", referencedColumnName = "publishers_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Publisher publisher;


    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name = "authors_books",
              joinColumns = { @JoinColumn(name = "books_id")},
              inverseJoinColumns = { @JoinColumn(name = "authors_id")})
    private List<Author> authors;


    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinTable(name = "books_genres",
            joinColumns = { @JoinColumn(name = "books_id")},
            inverseJoinColumns = { @JoinColumn(name = "genres_id")})
    private List<Genre> genres;



    public Book() {
    }

    public Book(String title, String cover, String synopsis,
                LocalDate release_date,
                Publisher publisher
            , List<Author> authors
            , List<Genre> genres
                ) {
        this.title = title;
        this.cover = cover;
        this.synopsis = synopsis;
        this.release_date = release_date;
       this.publisher = publisher;
        this.authors = authors;
        this.genres = genres;
    }

    public int getBooks_id() {
        return books_id;
    }

    public void setBooks_id(int books_id) {
        this.books_id = books_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
/*
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

*/
    @Override
    public String toString() {
        return "Book{" +
                "books_id=" + books_id +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", releaseDate=" + release_date +
                '}';
    }
}
