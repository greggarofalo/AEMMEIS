package controller.admin.gestisciSedi;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.RepartoDAO;
import model.libroService.SedeDAO;

import java.io.IOException;

@WebServlet("/modifica-sede")
public class ModificaSedeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String isbn = request.getParameter("isbn");
        int idSede = Integer.parseInt(request.getParameter("idSede"));

        SedeDAO sedeDAO = new SedeDAO();
        sedeDAO.removeLibroSede(idSede, isbn);

        response.sendRedirect("gestisci-sedi");
    }
}
