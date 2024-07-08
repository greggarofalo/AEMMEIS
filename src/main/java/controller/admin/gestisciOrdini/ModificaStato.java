package controller.admin.gestisciOrdini;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ordineService.Ordine;
import model.ordineService.OrdineDAO;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/modifica-stato")

public class ModificaStato extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrdineDAO ordineDAO = new OrdineDAO();
     //   UtenteDAO utenteDAO = new UtenteDAO();
     //   Utente utente = utenteDAO.doRetrieveById(request.getParameter("utenteScelto"));
        Ordine ordine = ordineDAO.doRetrieveById(request.getParameter("ordineID"));

        ordine.setStato(request.getParameter("stato"));
        if(request.getParameter("stato").equals("Consegnato")){
            ordine.setDataArrivo(LocalDate.now());
            ordineDAO.updateOrdine(ordine);
        }else{
            ordineDAO.updateStato(ordine);
        }
        request.setAttribute("utenteScelto", request.getParameter("utenteScelto"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("gestisci-ordiniByUtente");
        dispatcher.forward(request, response);
    }
}
