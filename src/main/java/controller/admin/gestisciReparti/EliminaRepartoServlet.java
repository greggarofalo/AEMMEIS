package controller.admin.gestisciReparti;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Reparto;
import model.libroService.RepartoDAO;

import java.io.IOException;

@WebServlet("/elimina-reparto")
public class EliminaRepartoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RepartoDAO repartoService = new RepartoDAO();
        int idReparto = Integer.parseInt(request.getParameter("idReparto"));
        repartoService.deleteReparto(idReparto);

        response.sendRedirect("gestisci-reparti"); //credo
       /* RequestDispatcher dispatcher = request.getRequestDispatcher("gestisci-reparti");
        dispatcher.forward(request, response);*/
    }

}
