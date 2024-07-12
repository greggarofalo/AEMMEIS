package controller.utente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/modifica-dati")
public class ModificaDatiServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        UtenteDAO services = new UtenteDAO();
        String nomeUtente = request.getParameter("nomeUtente");
        String[] telefoni = request.getParameterValues("telefono");
        String address = null;

        if(nomeUtente == null || telefoni == null){
            address = "/WEB-INF/errorJsp/errorForm.jsp";
          //  response.sendRedirect("/WEB-INF/errorJsp/loginError.jsp");
        }else {
            address = "modifica-dati-supporto";


            if (telefoni.length > 0) {
                for (String tel : telefoni) {
                    if (!tel.isEmpty() && !(utente.getTelefoni().contains(tel))) {
                        utente.getTelefoni().add(tel);
                    }
                }
            }

            //non dovrebbe servire più perchè viene fatto dinamicamente con ajax
        /*if(!utente.getTelefoni().equals(tele)){
            for (String tel : utente.getTelefoni()) {
                if (!tel.isEmpty() && !(utente.getTelefoni().contains(tel))) {
                    utente.getTelefoni().remove(tel);
                }
            }
        }*/

            if (!nomeUtente.isEmpty()) {
                utente.setNomeUtente(nomeUtente);
            }

            services.updateUtente(utente); //cambio tutto nel db

            // Aggiorna l'utente in sessione
            session.setAttribute("utente", utente);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
