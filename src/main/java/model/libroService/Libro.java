package model.libroService;

import java.util.List;
import java.util.Objects;

public class Libro {
    private String isbn;
    private String titolo;
    private String genere;
    private String annoPubblicazioni;
    private double prezzo;
    private int sconto;
    private String trama;
    private String immagine;
    private boolean disponibile;
    private List<Autore> autori;


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        this.sconto = sconto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getAnnoPubblicazioni() {
        return annoPubblicazioni;
    }

    public void setAnnoPubblicazioni(String annoPubblicazioni) {
        this.annoPubblicazioni = annoPubblicazioni;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public List<Autore> getAutori() {
        return autori;
    }

    public void setAutori(List<Autore> autori) {
        this.autori = autori;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return isbn.equals(libro.isbn);
        //si potrebbe fare anche solo sull'isbn
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }
}
