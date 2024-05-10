package model.ordineService;

import model.ConPool;
import model.libroService.Libro;
import model.utenteService.Utente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {
    public void doSave(Ordine ordine){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Ordine (idOrdine, costo, indirizzoSpedizione, citta, puntiOttenuti, puntiSpesi, dataEffettuazione, stato, matricola, email) VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, ordine.getIdOrdine());
            ps.setDouble(2, ordine.getCosto());
            ps.setString(3, ordine.getIndirizzoSpedizione());
            ps.setString(4, ordine.getCitta());
            ps.setInt(5, ordine.getPuntiOttenuti());
            ps.setInt(6, ordine.getPuntiSpesi());
            ps.setDate(7, java.sql.Date.valueOf(ordine.getDataEffettuazione()));
            ps.setString(8, ordine.getStato());
            ps.setString(9, ordine.getMatricola());
            ps.setString(9, ordine.getEmail());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Ordine doRetrieveById(String idOrdine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Ordine WHERE idOrdine=?");
            ps.setString(1, idOrdine);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ordine p = new Ordine();
                p.setIdOrdine(rs.getString(1));
                p.setCosto(rs.getDouble(2));
                p.setIndirizzoSpedizione(rs.getString(3));
                p.setCitta(rs.getString(4));
                p.setPuntiOttenuti(rs.getInt(5));
                p.setPuntiSpesi(rs.getInt(6));
                Date dataArrivoSQL = rs.getDate(7);
                if (dataArrivoSQL != null) {
                    LocalDate dataArrivo = dataArrivoSQL.toLocalDate();
                    p.setDataArrivo(dataArrivo);
                } else {
                    // Gestione del caso in cui il valore sia null
                    // Ad esempio, assegnare un valore predefinito o fare qualcos'altro
                    p.setDataArrivo(null); // oppure assegna un valore predefinito
                }
                p.setDataEffettuazione(rs.getDate(8).toLocalDate());
                p.setStato(rs.getString(9));
                p.setMatricola(rs.getString(10));
                p.setEmail(rs.getString(11));

                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ordine> doRetrieveByUtente(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Ordine WHERE email=?");
            ps.setString(1, email);
            List<Ordine> ordini=new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ordini.add(doRetrieveById(rs.getString(1)));
            }
            return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //modifico stato e data arrivo dell'ordine
    public void updateOrdine(Ordine ordine){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE Ordine SET stato = ?, dataArrivo = ? WHERE idOrdine = ?");
            ps.setString(1, ordine.getStato());
            ps.setDate(2, Date.valueOf(ordine.getDataArrivo()));
            ps.setString(3, ordine.getIdOrdine());
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
