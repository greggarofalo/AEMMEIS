package controller.utente;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

@WebServlet("/login-servlet")

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){

        Utente utente = new Utente();
        utente.setEmail(request.getParameter("email"));
        utente.setNomeUtente(request.getParameter("nomeUtente"));
        utente.setCodiceSicurezza(request.getParameter("pw"));
        utente.setTipo(request.getParameter("tipo"));

        UtenteDAO service = new UtenteDAO();
        if(service.doRetrieveById(utente.getEmail()) == null){
            //pagina di errore --> registrazione
        }



    }

}
