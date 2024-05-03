package model.carrelloService;

import model.ConPool;
import model.libroService.Libro;
import model.utenteService.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RigaCarrelloDAO {

    public List<RigaCarrello> doRetrieveByIdCarrello(String idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM rigaCarrello WHERE idCarrello=?");
            ps.setString(1, idCarrello);
            ResultSet rs = ps.executeQuery();
            List<RigaCarrello> lista = new ArrayList<>();
            if (rs.next()) {
                RigaCarrello p = new RigaCarrello();
                p.setIdCarrello(rs.getString(1));
                p.setIsbn(rs.getString(2));
                p.setPrezzoUnitario(rs.getDouble(3));
                p.setQuantita(rs.getInt(4));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public RigaCarrello doRetriveById(String idCarrello, String isbn){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM rigaCarrello WHERE idCarrello=? AND isbn=?");
            ps.setString(1, idCarrello);
            ps.setString(2, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                RigaCarrello p = new RigaCarrello();
                p.setIdCarrello(rs.getString(1));
                p.setIsbn(rs.getString(2));
                p.setPrezzoUnitario(rs.getDouble(3));
                p.setQuantita(rs.getInt(4));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doSave(RigaCarrello rigaCarrello){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO rigaCarrello (idCarrello, isbn, prezzoUnitario, quantita) VALUES(?,?,?,?)");
            ps.setString(1, rigaCarrello.getIdCarrello());
            ps.setString(2, rigaCarrello.getIsbn());
            ps.setDouble(3, rigaCarrello.getPrezzoUnitario());
            ps.setInt(4, rigaCarrello.getQuantita());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteRigaCarrello(String isbn, String idCarrello){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM rigaCarrello WHERE idCarrello=? AND isbn =?");
            ps.setString(1, idCarrello);
            ps.setString(2, isbn);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateQuantita(RigaCarrello rigaCarrello){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE rigaCarrello SET quantita = ? WHERE isbn = ? AND idCarrello=?");
            ps.setInt(1,rigaCarrello.getQuantita());
            ps.setString(2, rigaCarrello.getIsbn());
            ps.setString(3, rigaCarrello.getIdCarrello());
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
