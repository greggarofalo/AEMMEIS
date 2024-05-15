package controller.admin;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.LibroDAO;

import java.io.IOException;

@WebServlet("/elimina-libro")
public class EliminaLibroServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String isbn = request.getParameter("isbn");
        LibroDAO serviceLibro = new LibroDAO();
        serviceLibro.deleteLibro(isbn);

        response.sendRedirect("/WEB-INF/results/admin/gestisciProdotti.jsp");
    }
}
