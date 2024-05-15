package model.libroService;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepartoDAO {
    public void doSave(Reparto reparto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO reparto (descrizione) VALUES(?)");
            ps.setString(1, reparto.getDescrizione());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteReparto(int idReparto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM reparto WHERE idReparto=?");
            ps.setInt(1, idReparto);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateReparto(Reparto reparto){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE reparto SET descrizione = ? WHERE idReparto = ?");
            ps.setString(1, reparto.getDescrizione());
            ps.setInt(2, reparto.getIdReparto());

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Reparto> doRetrivedAll(){
        List<Reparto> reparti = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM reparto");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reparto p = new Reparto();
                p.setIdReparto(rs.getInt(1));
                p.setDescrizione(rs.getString(2));
                reparti.add(p);
            }
            return reparti;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Reparto doRetrieveById(int idReparto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM reparto WHERE idReparto=?");
            ps.setInt(1, idReparto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Reparto p = new Reparto();
                p.setIdReparto(rs.getInt(1));
                p.setDescrizione(rs.getString(2));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Libro> getAppartenenza(int idReparto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT isbn FROM apparteneza WHERE idReparto=?");
            ps.setInt(1, idReparto);
            List<Libro> lista=new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String isbn = rs.getString(1);
                LibroDAO p = new LibroDAO();
                Libro libro=p.doRetrieveById(isbn);
                lista.add(libro);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
