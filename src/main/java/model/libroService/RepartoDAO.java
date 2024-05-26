package model.libroService;

import model.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartoDAO {
    public void doSave(Reparto reparto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO reparto (nome,descrizione,immagine) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, reparto.getNome());
            ps.setString(2, reparto.getDescrizione());
            ps.setString(3, reparto.getImmagine());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            reparto.setIdReparto(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteReparto(int idReparto){
        try (Connection con = ConPool.getConnection()) {

            //prima cancello da appartenenza (che ha riferimenti a reparto) solo se ci sono elementi
            List<Libro> l = this.getAppartenenza(idReparto);
            Reparto r = this.doRetrieveById(idReparto);
            if (l!=null && !l.isEmpty()) {
                PreparedStatement ps =
                    con.prepareStatement("DELETE FROM appartenenza WHERE idReparto=?");
                ps.setInt(1, idReparto);
                r.setLibri(null);
                if(ps.executeUpdate() < 1)
                    throw new RuntimeException("DELETE error from appartenenza.");
            }
            //poi elimino il reparto in questione
            PreparedStatement ps = con.prepareStatement("DELETE FROM reparto WHERE idReparto=?");
            ps.setInt(1, idReparto);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error from reparto.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateReparto(Reparto reparto){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE reparto SET nome = ?, descrizione = ? WHERE idReparto = ?");
            ps.setString(1,reparto.getNome());
            ps.setString(2, reparto.getDescrizione());
            ps.setInt(3, reparto.getIdReparto());

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeLibroReparto(int idReparto, String isbn){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM appartenenza WHERE idReparto=? AND isbn = ?");
            ps.setInt(1,idReparto);
            ps.setString(2, isbn);

            Reparto p = this.doRetrieveById(idReparto);
            LibroDAO libroService = new LibroDAO();
            Libro l = libroService.doRetrieveById(isbn);
            p.getLibri().remove(l); //ho tolto il contains perchè credo lo faccia da solo.

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void aggiungiLibroReparto(Reparto reparto, String isbn){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO appartenenza (idReparto, isbn) VALUES(?, ?)");
            ps.setInt(1, reparto.getIdReparto());
            ps.setString(2, isbn);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            LibroDAO libroService = new LibroDAO();
            reparto.getLibri().add(libroService.doRetrieveById(isbn));

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
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setImmagine(rs.getString(4));
                p.setLibri(this.getAppartenenza(p.getIdReparto()));
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
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setImmagine(rs.getString(4));
                p.setLibri(this.getAppartenenza(idReparto));
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
                    con.prepareStatement("SELECT isbn FROM appartenenza WHERE idReparto=?");
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
    public void deleteFromAppartenenzaLibro(int idReparto, String isbn){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM Appartenenza WHERE idReparto=? AND isbn=?");
            ps.setInt(1, idReparto);
            ps.setString(2, isbn);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSaveAppartenenza(int idReparto, String isbn){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Appartenenza (idReparto, isbn) VALUES(?,?)");
            ps.setInt(1, idReparto);
            ps.setString(2, isbn);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
