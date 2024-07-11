package controller.utente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.carrelloService.Carrello;
import model.carrelloService.CarrelloDAO;
import model.carrelloService.RigaCarrello;
import model.carrelloService.RigaCarrelloDAO;
import model.libroService.Libro;
import model.utenteService.Utente;
import model.wishList.WishList;
import model.wishList.WishListDAO;

import java.io.IOException;
@WebServlet("/log-out")
public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        WishListDAO wishListDAO = new WishListDAO();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        WishList wishList = (WishList) session.getAttribute("wishList");
        Utente utente = (Utente) session.getAttribute("utente");
        wishList.setEmail(utente.getEmail());

        try{
            RigaCarrelloDAO rigaCarrelloService = new RigaCarrelloDAO();
            if(carrelloDAO.doRetriveByUtente(utente.getEmail()) != null && !(carrelloDAO.doRetriveByUtente(utente.getEmail()).getRigheCarrello().isEmpty())) {
                Carrello carrello2=carrelloDAO.doRetriveByUtente(utente.getEmail());
                rigaCarrelloService.deleteRigheCarrelloByIdCarrello(carrelloDAO.doRetriveByUtente(utente.getEmail()).getIdCarrello());//elimino ciò che è presente nel db
            }
            WishListDAO wishListService = new WishListDAO();
            if(wishListDAO.doRetrieveByEmail(utente.getEmail())!= null && !(wishListDAO.doRetrieveByEmail(utente.getEmail()).getLibri().isEmpty())) {
                wishListService.deleteWishListByEmail(utente.getEmail());//elimino ciò che è presente nel db
            }

            if(carrello.getRigheCarrello()!= null) {
                for (RigaCarrello riga : carrello.getRigheCarrello())
                    rigaCarrelloService.doSave(riga); //ripopolo il db con le informazioni presenti in sessione
            }
            if(wishList.getLibri()!= null) {
                for (Libro libro : wishList.getLibri()) {
                    wishListService.doSave(wishList, libro.getIsbn()); //ripopolo il db con le informazioni presenti in sessione
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        session.invalidate();
        response.sendRedirect("index.html");

    }
}
