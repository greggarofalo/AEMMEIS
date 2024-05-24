package controller.HomePage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.carrelloService.Carrello;
import model.carrelloService.RigaCarrello;
import model.libroService.Libro;
import model.libroService.LibroDAO;
import model.utenteService.Utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index.html")
public class HomePageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Memorizza il carrello nella sessione
        // Ottieni la sessione corrente, creandone una nuova se non esiste
        HttpSession session = request.getSession();

        Carrello carrello = (Carrello) session.getAttribute("carrello");// Verifica se il carrello è già presente nella sessione
        Utente utente=(Utente) session.getAttribute("utente");

        if (carrello == null) { // Se il carrello non è presente nella sessione, ne crea uno nuovo
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);// Aggiungi il carrello alla sessione
        }
        if(request.getAttribute("libriHome") == null) {
            LibroDAO libroService = new LibroDAO();
            List<Libro> libri = libroService.doRetriveAll();
            request.setAttribute("libriHome", libri);
        }

        if(utente!=null) { // se l'utente ha effettuato il login
            if(utente.getTipo().equals("Amministratore")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/homepageAdmin.jsp");
                dispatcher.forward(request, response);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/homepage.jsp");
        dispatcher.forward(request, response);

    }
}
