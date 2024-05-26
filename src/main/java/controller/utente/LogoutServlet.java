package controller.utente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.carrelloService.Carrello;
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
        Carrello carrello = (Carrello) session.getAttribute("Carrello");
        WishList wishList = (WishList) session.getAttribute("WishList");
        Utente utente = (Utente) session.getAttribute("Utente");

        try{
            RigaCarrelloDAO rigaCarrelloService = new RigaCarrelloDAO();
            rigaCarrelloService.deleteRigheCarrelloByIdCarrello(carrello.getIdCarrello());//elimino ciò che è presente nel db
            WishListDAO wishListService = new WishListDAO();
            wishListService.deleteWishListByEmail(utente.getEmail());//elimino ciò che è presente nel db

            for(RigaCarrello riga : carrello.getRigheCarrello())
                rigaCarrelloService.doSave(riga); //ripopolo il db con le informazioni presenti in sessione
            for(Libro libro : wishList.getLibri()){
                wishListService.doSave(wishList, libro.getIsbn()); //ripopolo il db con le informazioni presenti in sessione
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        session.invalidate();
        response.sendRedirect("index.html");

    }
}
