package controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Libro;
import model.libroService.LibroDAO;

import java.io.IOException;

@WebServlet("/modifica-libro")
public class ModificaLibroServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        LibroDAO service = new LibroDAO();
        Libro libro = new Libro();
        libro = service.doRetrieveById(isbn);


        request.setAttribute("libro", libro);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/modificaLibro.jsp");
        dispatcher.forward(request, response);
    }

}
