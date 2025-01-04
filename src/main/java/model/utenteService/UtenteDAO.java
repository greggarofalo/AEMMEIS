package model.utenteService;

import model.ConPool;
import model.carrelloService.Carrello;
import model.carrelloService.CarrelloDAO;
import model.carrelloService.RigaCarrelloDAO;
import model.ordineService.OrdineDAO;
import model.tesseraService.TesseraDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UtenteDAO {

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
                p.setTelefoni(this.cercaTelefoni(p.getEmail()));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByEmailPassword(String email, String password) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("SELECT nomeUtente, email, codiceSicurezza, tipo FROM utente WHERE email=? AND  codiceSicurezza=?");//SHA1(?)
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente p = new Utente();
                p.setNomeUtente(rs.getString(1));
                p.setEmail(rs.getString(2));
                p.setCodiceSicurezza(rs.getString(3));
                p.setTipo(rs.getString(4));
                p.setTelefoni(this.cercaTelefoni(p.getEmail()));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (nomeUtente, email, codiceSicurezza, tipo) VALUES(?,?,?,?)");
            ps.setString(1, utente.getNomeUtente());
            ps.setString(2, utente.getEmail());
            ps.setString(3, utente.getCodiceSicurezza());
            ps.setString(4, utente.getTipo());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            for(String tel : utente.getTelefoni()){
                this.addTelefono(utente.getEmail(), tel);
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
            PreparedStatement ps = con.prepareStatement("UPDATE utente SET nomeUtente = ?, tipo = ? WHERE email = ?");
            ps.setString(1, utente.getNomeUtente());
            ps.setString(2, utente.getTipo());
            ps.setString(3, utente.getEmail());
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE error.");
            List<String> telefoni = this.cercaTelefoni(utente.getEmail());
            for (String tel : utente.getTelefoni() ){
                if(!(telefoni.contains(tel))){
                    this.addTelefono(utente.getEmail(), tel);
                }
            }
            for (String tel : telefoni ){
                if(!(utente.getTelefoni().contains(tel))){
                    this.deleteTelefono(utente.getEmail(), tel);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUtentePassword(Utente utente){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE utente SET codiceSicurezza = ? WHERE email = ?");
            ps.setString(1, utente.getCodiceSicurezza());
            ps.setString(2, utente.getEmail());
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE error.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUtente(String email){

        if(this.doRetrieveById(email).getTipo().equalsIgnoreCase("premium")){
            TesseraDAO tesseraDAO = new TesseraDAO();
            tesseraDAO.deleteTessera(tesseraDAO.doRetrieveByEmail(email).getNumero()); //cancello eventuale tessera
        }
        if(!this.doRetrieveById(email).getTelefoni().isEmpty())
            this.deleteTelefoni(email); //relazione con telefoni

        RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        OrdineDAO ordineDAO = new OrdineDAO();
        if(!ordineDAO.doRetrieveByUtente(email).isEmpty())
            ordineDAO.deleteOrdiniByEmail(email);
        Carrello carrello = carrelloDAO.doRetriveByUtente(email);
        if(!rigaCarrelloDAO.doRetrieveByIdCarrello(carrello.getIdCarrello()).isEmpty())
            rigaCarrelloDAO.deleteRigheCarrelloByIdCarrello(carrello.getIdCarrello());
        carrelloDAO.deleteCarrello(carrello.getIdCarrello());


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

    public void deleteTelefono(String email, String numeroTelefono){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM telefono WHERE email=? AND numeroTelefono=?");
            ps.setString(1, email);
            ps.setString(2, numeroTelefono);
            if(ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE error.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTelefoni(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM telefono WHERE email=?");//Per farlo funzionare bisogna togliere la safe mode dal db
            ps.setString(1, email);
            if(ps.executeUpdate() < 1)
                throw new RuntimeException("DELETE error. Email: " + email + " not present in the db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTelefono(String email, String numeroTelefono){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO telefono (numeroTelefono, email) VALUES(?,?)");
            ps.setString(1, numeroTelefono);
            ps.setString(2, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//mi serve una funzione che cerchi i numeri di telefono di un utente e li salvi nella lista
//così da non perdere l'informazione quando si fa il login.
    public List<String> cercaTelefoni(String email) {
        List<String> telefoni = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT numeroTelefono FROM telefono WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                telefoni.add(rs.getString(1));
            }
            return telefoni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> doRetrieveAllTelefoni() {
        List<String> telefoni = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT numeroTelefono FROM telefono");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               telefoni.add(rs.getString(1));
            }
            return telefoni;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}