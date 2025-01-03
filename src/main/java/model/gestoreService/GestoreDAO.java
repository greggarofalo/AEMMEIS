package model.gestoreService;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestoreDAO {
    public void doSave(Gestore gestore){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO gestore (matricola, stipendio) VALUES(?,?)");
            ps.setString(1, gestore.getMatricola());
            ps.setDouble(2, gestore.getStipendio());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteGestore(String matricola){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM gestore WHERE matricola=?");
            ps.setString(1, matricola);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateGestore(Gestore gestore){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE gestore SET stipendio = ? WHERE matricola = ?");
            ps.setDouble(1, gestore.getStipendio());
            ps.setString(2, gestore.getMatricola());
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Gestore> doRetrivedAll(){
        List<Gestore> gestori = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM gestore");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Gestore p = new Gestore();
                p.setMatricola(rs.getString(1));
                p.setStipendio(rs.getDouble(2));
                gestori.add(p);
            }
            return gestori;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Gestore doRetrieveById(String matricola) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Gestore WHERE matricola=?");
            ps.setString(1, matricola);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Gestore p = new Gestore();
                p.setMatricola(rs.getString(1));
                p.setStipendio(rs.getDouble(2));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
