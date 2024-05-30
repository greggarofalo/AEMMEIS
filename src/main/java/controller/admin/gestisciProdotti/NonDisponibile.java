package controller.admin.gestisciProdotti;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Libro;
import model.libroService.LibroDAO;
import model.libroService.RepartoDAO;
import model.libroService.SedeDAO;

import java.io.IOException;

@WebServlet("/non-disponibile")
public class NonDisponibile extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String isbn= request.getParameter("isbn");
        LibroDAO service = new LibroDAO();
        Libro libro = service.doRetrieveById(isbn);
        libro.setDisponibile(false);

        service.updateDisponibile(libro);


        RequestDispatcher dispatcher = request.getRequestDispatcher("gestisci-prodotti");
        dispatcher.forward(request, response);
    }
}
