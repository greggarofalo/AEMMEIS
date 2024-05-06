package model.tesseraService;
import java.time.LocalDate;

public class Tessera {
    private String Numero;
    private LocalDate DataCreazione;
    private LocalDate DataScadenza;
    private int Punti;
    private String Email;

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        this.Numero = numero;
    }

    public LocalDate getDataCreazione() {
        return DataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.DataCreazione = dataCreazione;
    }

    public LocalDate getDataScadenza() {
        return DataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.DataScadenza = dataScadenza;
    }

    public int getPunti() {
        return Punti;
    }

    public void setPunti(int punti) {
        this.Punti = punti;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
}
