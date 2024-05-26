package controller.utente;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

import java.io.IOException;

@WebServlet("/modifica-password")
public class ModificaPasswordServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password = request.getParameter("password");
        UtenteDAO serviceUtente = new UtenteDAO();
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        utente.setCodiceSicurezza(password);
        serviceUtente.updateUtente(utente);
        response.sendRedirect("modifica-password-supporto");
    }
}
