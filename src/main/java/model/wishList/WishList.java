package model.wishList;

import model.libroService.Libro;

import java.util.List;

public class WishList {
    private String email;
    List<Libro> libri;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }

    public void addLibro(Libro libro){
        libri.add(libro);
    }
}
