package controller.admin.gestisciReparti;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.RepartoDAO;

import java.io.IOException;

@WebServlet("/modifica-reparto")
public class ModificaRepartoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String isbn = request.getParameter("isbn");
        int idReparto = Integer.parseInt(request.getParameter("idReparto"));

        RepartoDAO repartoDAO = new RepartoDAO();
        repartoDAO.removeLibroReparto(idReparto, isbn);

        response.sendRedirect("gestisci-reparti");
    }
}
