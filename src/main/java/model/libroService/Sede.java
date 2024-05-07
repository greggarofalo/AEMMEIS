package model.libroService;

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
}
