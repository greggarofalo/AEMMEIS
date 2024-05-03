package model.ordineService;

import java.time.LocalDate;
import java.util.Date;

public class Ordine {
    private String idOrdine;
    private double costo;
    private String indirizzoSpedizione;
    private String citta;
    private int puntiOttenuti;
    private int puntiSpesi;
    private LocalDate dataArrivo;
    private LocalDate dataEffettuazione;
    private String stato;
    private String matricola;
    private String email;

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public LocalDate getDataArrivo() {
        return dataArrivo;
    }

    public void setDataArrivo(LocalDate dataArrivo) {
        this.dataArrivo = dataArrivo;
    }

    public LocalDate getDataEffettuazione() {
        return dataEffettuazione;
    }

    public void setDataEffettuazione(LocalDate dataEffettuazione) {
        this.dataEffettuazione = dataEffettuazione;
    }

    public String getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(String idOrdine) {
        this.idOrdine = idOrdine;
    }

    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public void setIndirizzoSpedizione(String indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }

    public int getPuntiOttenuti() {
        return puntiOttenuti;
    }

    public void setPuntiOttenuti(int puntiOttenuti) {
        this.puntiOttenuti = puntiOttenuti;
    }

    public int getPuntiSpesi() {
        return puntiSpesi;
    }

    public void setPuntiSpesi(int puntiSpesi) {
        this.puntiSpesi = puntiSpesi;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

}
