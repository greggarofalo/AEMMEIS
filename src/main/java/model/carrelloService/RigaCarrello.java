package model.carrelloService;

public class RigaCarrello {
    private String idCarrello;
    private String isbn;
    private double prezzoUnitario;
    private int quantita;

    public String getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(String idCarrello) {
        this.idCarrello = idCarrello;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
