package model.libroService;

import java.util.List;
import java.util.Objects;

public class Reparto {
    private int idReparto;
    private String descrizione;
    private List<Libro> libri;

    public int getIdReparto() {
        return idReparto;
    }

    public void setIdReparto(int idReparto) {
        this.idReparto = idReparto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }

    @Override
    public boolean equals(Object o) { //per il metodo contains
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reparto reparto = (Reparto) o;
        return idReparto == reparto.idReparto && descrizione.equals(reparto.descrizione);
    }

}
