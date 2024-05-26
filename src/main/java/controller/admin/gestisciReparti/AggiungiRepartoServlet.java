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
        String nome = request.getParameter("nome");
        String decrizione = request.getParameter("descrizione");
        String immagine = request.getParameter("immagine");
        reparto.setDescrizione(decrizione);
        reparto.setNome(nome);
        reparto.setImmagine(immagine);

        RepartoDAO repartoService = new RepartoDAO();
        repartoService.doSave(reparto);
        response.sendRedirect("gestisci-reparti");

    }
}
