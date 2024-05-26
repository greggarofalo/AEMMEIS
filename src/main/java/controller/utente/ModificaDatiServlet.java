package controller.utente;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

import java.io.IOException;

@WebServlet("/modifica-dati")
public class ModificaDatiServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        UtenteDAO services = new UtenteDAO();
        String nomeUtente = request.getParameter("nomeUtente");
        String[] telefoni = request.getParameterValues("telefono");

        for(String tel : telefoni){
            if(!(utente.getTelefoni().contains(tel))){
                utente.getTelefoni().add(tel);
            }
        }

        if(nomeUtente != null){
            utente.setNomeUtente(nomeUtente);
        }

        services.updateUtente(utente); //cambio tutto nel db
        response.sendRedirect("modifica-dati-supporto");

    }
}
