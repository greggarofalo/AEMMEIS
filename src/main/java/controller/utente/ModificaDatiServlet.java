package controller.utente;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

@WebServlet("/modifica-dati")
public class ModificaDatiServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        UtenteDAO services = new UtenteDAO();
        services.updateUtente(utente); //cambio l'email nel db


    }
}
