package model.carrelloService;

import java.util.List;

public class Carrello {
    private String idCarrello;
    private double totale;
    private String email;
    private List<RigaCarrello> righeCarrello;

    public String getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(String idCarrello) {
        this.idCarrello = idCarrello;
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

    public List<RigaCarrello> getRigheCarrello() {
        return righeCarrello;
    }

    public void setRigheCarrello(List<RigaCarrello> righeCarrello) {
        this.righeCarrello = righeCarrello;
    }
}
