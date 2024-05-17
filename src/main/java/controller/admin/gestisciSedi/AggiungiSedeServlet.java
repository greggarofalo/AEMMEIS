package controller.admin.gestisciSedi;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Reparto;
import model.libroService.RepartoDAO;
import model.libroService.Sede;
import model.libroService.SedeDAO;

import java.io.IOException;

@WebServlet("/aggiungi-sede")
public class AggiungiSedeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Sede sede = new Sede();
        String citta = request.getParameter("citta");
        sede.setCitta(citta);
        String via = request.getParameter("via");
        sede.setVia(via);
        int civico = Integer.parseInt(request.getParameter("civico"));
        sede.setCivico(civico);
        String cap = request.getParameter("cap");
        sede.setCap(cap);

        SedeDAO sedeService = new SedeDAO();
        sedeService.doSave(sede);
        response.sendRedirect("gestisci-sedi");

    }
}