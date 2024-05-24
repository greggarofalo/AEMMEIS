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

import java.io.IOException;
import java.util.ArrayList;
@WebServlet("/modifica-preferiti")
public class ModificaPrefServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        String address="index.html";
        LibroDAO libroService = new LibroDAO();
        Libro libro = libroService.doRetrieveById(isbn);

        if(utente==null) {
            address = "/WEB-INF/results/login.jsp";
        }

        else {
            if(request.getParameter("rimuoviPref") != null){// controllo se il bottone è stato selezionato nella show WishList
                address="/WEB-INF/results/showWishList.jsp";
            }
            WishList wishList = (WishList) session.getAttribute("wishList");
            boolean flag = true; // libro non presente
            if (wishList != null && wishList.getLibri() != null) {
                for (int i = 0; i < wishList.getLibri().size() && flag; i++) {
                    if (wishList.getLibri().get(i).equals(libro)) {
                        wishList.getLibri().remove(i); // libro già presente, lo rimuovo
                        flag = false;
                    }
                }
                if (flag)// se il libro non è presente, lo aggiungo
                    wishList.getLibri().add(libro);
            } else {
                wishList.setLibri(new ArrayList<>());
                wishList.getLibri().add(libro);
            }
            session.setAttribute("wishList", wishList);

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
