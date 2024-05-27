package controller.utente.ordine;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ordineService.Ordine;
import model.ordineService.OrdineDAO;
import model.ordineService.RigaOrdine;
import model.ordineService.RigaOrdineDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/riepilogo-ordine")
public class RiepilogoOrdine extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();;
        String idOrdine = request.getParameter("idOrdine");
        OrdineDAO ordineDAO = new OrdineDAO();
        Ordine ordine = ordineDAO.doRetrieveById(idOrdine);
        session.setAttribute("ordine", ordine);
        //    RigaOrdineDAO rigaOrdineDAO = new RigaOrdineDAO();
        //    List<RigaOrdine> righeOrdine = rigaOrdineDAO.doRetrivedByOrdine(idORdine);
        //    session.setAttribute("righeOrdine", righeOrdine);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/areaPservices/riepilogoOrdine.jsp");
        dispatcher.forward(request, response);
    }
}
