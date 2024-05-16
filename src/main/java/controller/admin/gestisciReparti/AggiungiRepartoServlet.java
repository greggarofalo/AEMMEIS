package controller.admin.gestisciReparti;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Reparto;
import model.libroService.RepartoDAO;

import java.io.IOException;

@WebServlet("/aggiungi-reparto")
public class AggiungiRepartoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Reparto reparto = new Reparto();
        String decrizione = request.getParameter("descrizione");
        reparto.setDescrizione(decrizione);

        RepartoDAO repartoService = new RepartoDAO();
        repartoService.doSave(reparto);
        response.sendRedirect("gestisci-reparti");

    }
}
