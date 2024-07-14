package controller.utente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.libroService.Libro;
import model.libroService.LibroDAO;
import model.utenteService.Utente;
import model.wishList.WishList;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/aggiungi-ai-preferiti")
public class AggiungiAiPrefServlet extends HttpServlet {

    //Nuova servlet per aggiornare i preferiti (creata per utilizzare AJAX)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn"); // Ottieni l'ISBN dal parametro della richiesta
        LibroDAO libroService = new LibroDAO();
        Libro libro = libroService.doRetrieveById(isbn);
        HttpSession session = request.getSession();
        Utente utente= (Utente) session.getAttribute("utente");
        if(utente==null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
        }
        else {
            JSONObject jsonResponse = new JSONObject();
            //boolean
            jsonResponse.put("isInWishList", true); // Indica se il libro è ora nei preferiti

            // Aggiungi l'ISBN alla WishList (o al database)
            WishList wishList = (WishList) session.getAttribute("wishList");
            boolean flag = true; // libro non presente
            if (wishList != null && wishList.getLibri() != null) {
                for (int i = 0; i < wishList.getLibri().size() && flag; i++) {
                    if (wishList.getLibri().get(i).equals(libro)) {
                        wishList.getLibri().remove(i); // libro già presente, lo rimuovo
                        flag = false;
                        jsonResponse.put("isInWishList", false); // Indica che il libro non è più nei preferiti
                    }
                }
                if (flag)// se il libro non è presente, lo aggiungo
                    wishList.getLibri().add(libro);

            } else {
                wishList.setLibri(new ArrayList<>());
                wishList.getLibri().add(libro);

            }

            session.setAttribute("wishList", wishList);
            System.out.println(wishList.getLibri());

            // Invia una risposta al client (ad esempio, un oggetto JSON)
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

