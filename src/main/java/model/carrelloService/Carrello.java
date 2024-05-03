package model.carrelloService;

public class Carrello {
    private String idCarrello;
    private double totale;
    private String email;

    public String getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(String diCarrello) {
        this.idCarrello = diCarrello;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
