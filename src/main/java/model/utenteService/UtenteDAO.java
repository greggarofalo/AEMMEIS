package model.utenteService;

import model.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A small table of banking customers for testing.
 */

public class UtenteDAO {


    /**
     * Finds the customer with the given ID.
     * Returns null if there is no match.
     */

    public Utente doRetrieveById(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT nomeUtente, email, codiceSicurezza, tipo FROM utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente p = new Utente();
                p.setNomeUtente(rs.getString(1));
                p.setEmail(rs.getString(2));
                p.setCodiceSicurezza(rs.getString(3));
                p.setTipo(rs.getString(4));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // la funzione seguente � inutile perch� il DB � riempito tramite tool esterno
    // sarebbe utile se l'applicazione fornisse un form per riempirlo. IDEA! aggiungi questa feature all'applicazione
    // � un buon modo per verificare la sua correttezza

    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (nomeUtente, email, codiceSicurezza, tipo) VALUES(?, ?,?,?)");
            ps.setString(1, utente.getNomeUtente());
            ps.setString(2, utente.getEmail());
            ps.setString(3, utente.getCodiceSicurezza());
            ps.setString(4, utente.getTipo());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Utente> doRetrieveAll() {
        List<Utente> utenti = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM utente");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utente p = new Utente();
                p.setNomeUtente(rs.getString(1));
                p.setEmail(rs.getString(2));
                p.setCodiceSicurezza(rs.getString(3));
                p.setTipo(rs.getString(4));
                utenti.add(p);
            }
            return utenti;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateUtente(Utente utente){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE utente SET nomeUtente = ?, codiceSicurezza = ?, tipo = ? WHERE email = ?");
            ps.setString(1, utente.getNomeUtente());
            ps.setString(2, utente.getCodiceSicurezza());
            ps.setString(3, utente.getTipo());
            ps.setString(4, utente.getEmail());
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteUtente(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM utente WHERE email=?");
            ps.setString(1, email);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


