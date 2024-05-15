package model.libroService;

import java.util.Objects;

public class Sede {
    private int idSede;
    private String citta;
    private String via;
    private int civico;
    private String cap;

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {this.citta = citta;}

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    @Override
    public boolean equals(Object o) { //per poter utilizzare il metodo contains
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sede sede = (Sede) o;
        return idSede == sede.idSede && civico == sede.civico && citta.equals(sede.citta) && via.equals(sede.via) && cap.equals(sede.cap);
    }

}
