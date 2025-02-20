package controller.utente.ordine;

import controller.utils.Validator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.carrelloService.Carrello;
import model.carrelloService.RigaCarrello;
import model.libroService.*;
import model.ordineService.Ordine;
import model.ordineService.OrdineDAO;
import model.ordineService.RigaOrdine;
import model.utenteService.Utente;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@WebServlet("/do-pagamento")
public class Pagamento extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if(Validator.checkIfUserAdmin(utente)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/homepageAdmin.jsp");
            dispatcher.forward(request, response);
        }

        List<RigaCarrello> righe = (List<RigaCarrello>) session.getAttribute("righeDisponibili");
        Ordine ordine = new Ordine();
      //  OrdineDAO ordineDAO = new OrdineDAO();
        SedeDAO sedeDAO = new SedeDAO();
        String type = request.getParameter("typeForm");
        String address = null;

        double costo = 0.00;
        for(RigaCarrello r : righe){
            Libro libro = r.getLibro();
            double prezzoUnitario = libro.getPrezzo() - (libro.getPrezzo() * libro.getSconto()/100.00);
            costo += r.getQuantita() * prezzoUnitario;
        }

        BigDecimal bd = new BigDecimal(costo).setScale(2, RoundingMode.HALF_UP);
        double costoArrotondato = bd.doubleValue();

        ordine.setCosto(costoArrotondato);

        if(type.equals("indirizzo")){
            String indirizzo = request.getParameter("indirizzo") + ", " + request.getParameter("cap");
            String citta = request.getParameter("citta");
            if(request.getParameter("indirizzo")==null|| request.getParameter("cap")==null
                    || request.getParameter("indirizzo").isEmpty()|| request.getParameter("cap").isEmpty()
                    || citta==null || citta.isEmpty() || !isNumeric(request.getParameter("cap"))
                    || request.getParameter("cap").length()>5) {
                //pagina di errore per inserimento parametri errato
                address = "/WEB-INF/errorJsp/erroreForm.jsp";
              //  response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");//forse
            }
            else {
                ordine.setCitta(citta);
                ordine.setIndirizzoSpedizione(indirizzo);
                if(utente.getTipo().equalsIgnoreCase("Standard"))
                    address = "/WEB-INF/results/pagamentoOrdine.jsp";
                else{
                    address = "/WEB-INF/results/puntiPremium.jsp";
                }
            }
        }
        else{
            if(request.getParameter("sede")==null || request.getParameter("sede").isEmpty())
                //pagina di errore per inserimento parametri errato
                address = "/WEB-INF/errorJsp/erroreForm.jsp";
               // response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");//forse
            else {
                Sede sede = sedeDAO.doRetrieveById(Integer.parseInt(request.getParameter("sede")));
                ordine.setCitta(sede.getCitta());
                ordine.setIndirizzoSpedizione(sede.getVia() + ", " + sede.getCivico() + ", " + sede.getCap());
                if(utente.getTipo().equalsIgnoreCase("Standard"))
                    address = "/WEB-INF/results/pagamentoOrdine.jsp";
                else{
                    address = "/WEB-INF/results/puntiPremium.jsp";
                }
            }
        }
        //inizio a salvare dati per l'ordine e l'ordine in sessione, così dopo il pagamento la servlet lavora su quest'ordine
        request.setAttribute("ordine", ordine);

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private static boolean isNumeric(String str) {//metodo che utilizza espressione regolare per verificare che una stringa contenga solo numeri
        return str != null && str.matches("\\d+");
    }
}
