package controller.HomePage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Libro;
import model.libroService.LibroDAO;
import model.libroService.Reparto;
import model.libroService.RepartoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/mostra-reparto")
public class MostraRepartoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idReparto = Integer.parseInt(request.getParameter("id"));

        Reparto reparto;
        RepartoDAO service = new RepartoDAO();
        reparto = service.doRetrieveById(idReparto);
        request.setAttribute("reparto", reparto);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/results/reparto.jsp");
        dispatcher.forward(request, response);
    }
}

