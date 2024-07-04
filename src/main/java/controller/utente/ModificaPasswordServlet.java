package controller.utente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

import java.io.IOException;

@WebServlet("/modifica-password")
public class ModificaPasswordServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String password = request.getParameter("password");
        if (password == null || password.length() == 0 || password.length() > 16) {
            response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");
        }
        UtenteDAO serviceUtente = new UtenteDAO();
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        utente.setCodiceSicurezza(password);
        serviceUtente.updateUtente(utente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("area-personale");
        dispatcher.forward(request, response);
    }

}
