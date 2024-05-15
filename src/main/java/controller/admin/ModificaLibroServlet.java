package controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/modifica-libro")
public class ModificaLibroServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        LibroDAO service = new LibroDAO();
        Libro libro= service.doRetrieveById(isbn);
        SedeDAO sedeService = new SedeDAO();

        List<Sede> sedi= service.getPresenzaSede(libro.getIsbn());
        List<Reparto> reparti= service.getAppartenenzaReparto(libro.getIsbn());
        List<Sede> sediNonPresenti = sedeService.doRetrivedAll();
        for(int i=0; i<sediNonPresenti.size(); i++){
            Sede sede=sediNonPresenti.get(i);
            if(sedi.contains(sede)) {
                sediNonPresenti.remove(i);
            }
        }

        request.setAttribute("libro", libro);
        request.setAttribute("sedi", sedi);
        request.setAttribute("reparti", reparti);
        request.setAttribute("sediNonPresenti", sediNonPresenti);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/modificaLibro.jsp");
        dispatcher.forward(request, response);
    }

}
