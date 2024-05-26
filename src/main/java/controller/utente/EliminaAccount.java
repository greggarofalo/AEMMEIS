package controller.utente;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

import java.io.IOException;

@WebServlet("/elimina-account")
public class EliminaAccount extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) request.getAttribute("utente");
        UtenteDAO utenteDAO = new UtenteDAO();
        utenteDAO.deleteUtente(utente.getEmail());

        session.invalidate();
        response.sendRedirect("index.html");
    }
}
