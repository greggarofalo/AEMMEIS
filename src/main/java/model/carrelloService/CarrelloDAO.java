package model.carrelloService;

import model.ConPool;
import model.utenteService.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarrelloDAO {
    public void doSave(Carrello carrello){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO carrello (idCarrello, totale, email) VALUES(?,?,?)");
            ps.setString(1, carrello.getIdCarrello());
            ps.setDouble(2, carrello.getTotale());
            ps.setString(3, carrello.getEmail());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCarrello(String idCarrello){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM carrello WHERE idCarrello=?");
            ps.setString(1, idCarrello);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Carrello doRetriveById(String idCarrello){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM carrello WHERE idCarrello=?");
            ps.setString(1, idCarrello);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrello carrello = new Carrello();
                RigaCarrelloDAO rigaService = new RigaCarrelloDAO();
                carrello.setIdCarrello(idCarrello);
                carrello.setTotale(rs.getDouble(2));
                carrello.setEmail(rs.getString(3));
                carrello.setRigheCarrello(rigaService.doRetrieveByIdCarrello(idCarrello));
                return carrello;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Carrello doRetriveByUtente(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM carrello WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrello carrello = new Carrello();
                RigaCarrelloDAO rigaService = new RigaCarrelloDAO();
                carrello.setIdCarrello(rs.getString(1));
                carrello.setTotale(rs.getDouble(2));
                carrello.setEmail(rs.getString(3));
                carrello.setRigheCarrello(rigaService.doRetrieveByIdCarrello(carrello.getIdCarrello()));
                return carrello;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
