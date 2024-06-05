package controller.utente.ordine;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ordineService.Ordine;
import model.tesseraService.Tessera;
import model.tesseraService.TesseraDAO;
import model.utenteService.Utente;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/pagamento-effettuato")
public class PagamentoEffettuato extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        TesseraDAO tesseraDAO = new TesseraDAO();
        Ordine ordine = new Ordine();
        //sto salvando sempre sulla request questi parametri poichè li devo mantenere fino a salvataggio ordine
        //dubbio sul metterlo direttamente in sessione...anche se non credo si agiusto salvarlo in sessione.
        ordine.setCitta(request.getParameter("citta"));
        ordine.setIndirizzoSpedizione(request.getParameter("indirizzo"));

        if(utente.getTipo().equalsIgnoreCase("premium")){
            String puntiString = request.getParameter("punti");
            int punti = 0;
            if(puntiString != null)
                punti = Integer.parseInt(puntiString);
            Tessera tessera = tesseraDAO.doRetrieveByEmail(utente.getEmail());
            if(tessera.getDataScadenza().isBefore(LocalDate.now())){
                ordine.setPuntiSpesi(punti);
                tessera.setPunti(tessera.getPunti() - punti);
                Tessera newTess = new Tessera();
                newTess.setPunti(tessera.getPunti());
                newTess.setNumero(tessera.getNumero());
                newTess.setDataScadenza(tessera.getDataScadenza());
                newTess.setEmail(tessera.getEmail());
                newTess.setDataCreazione(tessera.getDataCreazione());

                tesseraDAO.updateTessera(newTess);
            } else{
                ordine.setPuntiSpesi(0); //non può spendere punti poichè la tessera è scaduta.
            }

        }


        //effettuare controlli su dati dell'uetnet che acquista
        String cardName = request.getParameter("cardName");
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");

        request.setAttribute("ordine", ordine);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/ordineEffettuato.jsp");
        dispatcher.forward(request, response);
    }
}