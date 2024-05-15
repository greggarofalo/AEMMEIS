package model.libroService;
import model.ConPool;
import model.utenteService.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    public void doSave(Libro libro){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO libro (isbn, titolo, genere, annoPubblicazione, prezzo, sconto, trama, immagine) VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, libro.getIsbn());
            ps.setString(2, libro.getTitolo());
            ps.setString(3, libro.getGenere());
            ps.setInt(4, libro.getAnnoPubblicazioni());
            ps.setDouble(5, libro.getPrezzo());
            ps.setInt(6, libro.getSconto());
            ps.setString(7, libro.getTrama());
            ps.setString(8, libro.getImmagine());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteLibro(String isbn){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM libro WHERE isbn=?");
            ps.setString(1, isbn);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLibro(Libro libro){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE libro SET sconto = ? WHERE isbn = ?");
            ps.setInt(1, libro.getSconto());
            ps.setString(2, libro.getIsbn());
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Libro> doRetriveAll(){
        List<Libro> libri = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM libro");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Libro p = new Libro();
                p.setIsbn(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setGenere(rs.getString(3));
                p.setAnnoPubblicazioni(rs.getInt(4));
                p.setPrezzo(rs.getDouble(5));
                p.setSconto(rs.getInt(6));
                p.setTrama(rs.getString(7));
                p.setImmagine(rs.getString(8));
                libri.add(p);
            }
            return libri;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Libro doRetrieveById(String isbn) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM libro WHERE isbn=?");
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Libro p = new Libro();
                p.setIsbn(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setGenere(rs.getString(3));
                p.setAnnoPubblicazioni(rs.getInt(4));
                p.setPrezzo(rs.getDouble(5));
                p.setSconto(rs.getInt(6));
                p.setTrama(rs.getString(7));
                p.setImmagine(rs.getString(8));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Autore getScrittura(String isbn){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT cf FROM scrittura WHERE isbn=?");
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String cf = rs.getString(1);
                AutoreDAO service = new AutoreDAO();
                return service.searchAutore(cf);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Reparto getAppartenenzaReparto(String isbn){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idReparto FROM apparteneza WHERE isbn=?");
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idReparto = rs.getInt(1);
                RepartoDAO service = new RepartoDAO();
                return service.doRetrieveById(idReparto);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Sede getPresenzaSede(String isbn){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idSede FROM presenza WHERE isbn=?");
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idSede = rs.getInt(1);
                SedeDAO service = new SedeDAO();
                return service.doRetrieveById(idSede);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
