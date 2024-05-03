package model.libroService;

public class Libro {
    private String isbn;
    private String titolo;
    private String genere;
    private int annoPubblicazioni;
    private double prezzo;
    private int sconto;

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

    public int getAnnoPubblicazioni() {
        return annoPubblicazioni;
    }

    public void setAnnoPubblicazioni(int annoPubblicazioni) {
        this.annoPubblicazioni = annoPubblicazioni;
    }


}
