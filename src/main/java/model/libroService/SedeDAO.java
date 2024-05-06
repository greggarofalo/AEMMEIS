package model.libroService;

import model.ConPool;
import model.gestoreService.Gestore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SedeDAO {
    public void doSave(Sede sede){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Sede (citta, via, numeroCivico, cap) VALUES(?,?,?,?)");
            ps.setString(1, sede.getCitta());
            ps.setString(2, sede.getVia());
            ps.setInt(3, sede.getCivico());
            ps.setString(4, sede.getCap());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSede(int IdSede){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM Sede WHERE IdSede=?");
            ps.setInt(1, IdSede);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSede(Sede sede){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE Sede SET citta = ?, via = ?, numeroCivico = ?, cap = ? WHERE idSede = ?");
            ps.setString(1, sede.getCitta());
            ps.setString(2, sede.getVia());
            ps.setInt(3, sede.getCivico());
            ps.setString(4, sede.getCap());
            ps.setInt(5, sede.getIdSede());
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Sede> doRetrivedAll(){
        List<Sede> sedi = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM sede");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sede p = new Sede();
                p.setIdSede(rs.getInt(1));
                p.setCitta(rs.getString(2));
                p.setVia(rs.getString(3));
                p.setCivico(rs.getInt(4));
                p.setCap(rs.getString(5));
                sedi.add(p);
            }
            return sedi;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Sede doRetrieveById(int IdSede) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM sede WHERE IdSede=?");
            ps.setInt(1, IdSede);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Sede p = new Sede();
                p.setIdSede(rs.getInt(1));
                p.setCitta(rs.getString(2));
                p.setVia(rs.getString(3));
                p.setCivico(rs.getInt(4));
                p.setCap(rs.getString(5));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Libro getPresenza(int IdSede){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT isbn FROM presenza WHERE idSede=?");
            ps.setInt(1, IdSede);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String isbn = rs.getString(1);
                LibroDAO service = new LibroDAO();
                return service.doRetrieveById(isbn);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
