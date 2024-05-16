package controller.admin.gestisciProdotti;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Libro;
import model.libroService.LibroDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gestisci-prodotti")
public class GestisciProdottiServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LibroDAO service = new LibroDAO();
        List<Libro> libri = new ArrayList<>();
        libri= service.doRetriveAll();
        request.setAttribute("libri", libri);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/prodotti/gestisciProdotti.jsp");
        dispatcher.forward(request, response);
    }

}
