package controller.utente;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

@WebServlet(name = "insertUtente", value = "/insert-utente")
public class RegistroUtente extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Utente utente = new Utente();
        String nomeUtente = request.getParameter("nomeUtente");
        String email = request.getParameter("email");
        String codiceSicurezza = request.getParameter("pw");
        String tipo = request.getParameter("tipo");
        utente.setNomeUtente(nomeUtente);
        utente.setEmail(email);
        utente.setTipo(tipo);
        utente.setCodiceSicurezza(codiceSicurezza);

        //aggiungere il controllo se è già presente un utente con la stessa email
        UtenteDAO service = new UtenteDAO();
        service.doSave(utente);
        //request.getSession().setAttribute("utente", utente);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/results/login.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}