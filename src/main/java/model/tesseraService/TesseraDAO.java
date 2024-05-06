package model.tesseraService;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TesseraDAO {
    public void doSave(Tessera tessera){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO libro (numero, dataCreazione, dataScadenza, punti, email) VALUES(?,?,?,?,?)");
            ps.setString(1, tessera.getNumero());
            ps.setDate(2, java.sql.Date.valueOf(tessera.getDataCreazione()));
            ps.setDate(3, java.sql.Date.valueOf(tessera.getDataScadenza()));
            ps.setInt(4, tessera.getPunti());
            ps.setString(5, tessera.getEmail());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTessera(int numero){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM tessera WHERE numero=?");
            ps.setInt(1, numero);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTessera(Tessera tessera){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE tessera SET punti = ? WHERE numero = ?");
            ps.setInt(1, tessera.getPunti());
            ps.setString(2, tessera.getNumero());
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Tessera> doRetrivedAll(){
        List<Tessera> tessere = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM tessera");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tessera p = new Tessera();
                p.setNumero(rs.getString(1));
                p.setDataCreazione(rs.getDate(2).toLocalDate());
                p.setDataScadenza(rs.getDate(3).toLocalDate());
                p.setPunti(rs.getInt(4));
                p.setEmail(rs.getString(5));
                tessere.add(p);
            }
            return tessere;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Tessera doRetrieveById(String numero) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM tessera WHERE numero=?");
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Tessera p = new Tessera();
                p.setNumero(rs.getString(1));
                p.setDataCreazione(rs.getDate(2).toLocalDate());
                p.setDataScadenza(rs.getDate(3).toLocalDate());
                p.setPunti(rs.getInt(4));
                p.setEmail(rs.getString(5));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
