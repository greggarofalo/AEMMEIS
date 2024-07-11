package model.wishList;

import model.ConPool;
import model.libroService.Libro;
import model.libroService.LibroDAO;
import model.utenteService.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WishListDAO {
    public void doSave(WishList wishList, String isbn){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO wishList (email, isbn) VALUES(?,?)");
            ps.setString(1, wishList.getEmail());
            ps.setString(2, isbn);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public WishList doRetrieveByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT email, isbn FROM wishList WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            WishList wishList = new WishList();
            wishList.setLibri(new ArrayList<>());
            while (rs.next()) {
                wishList.setEmail(rs.getString(1));
                LibroDAO libroService = new LibroDAO();
                Libro libro = libroService.doRetrieveById(rs.getString(2));
                wishList.addLibro(libro);
            }
            return wishList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteLibro(String email, String isbn){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM wishList WHERE email=? AND isbn =?");
            ps.setString(1, email);
            ps.setString(2, isbn);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteWishListByEmail(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM wishList WHERE email=?");
            ps.setString(1, email);
            if(ps.executeUpdate() <= 0)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
