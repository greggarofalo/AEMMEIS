package controller.admin.gestisciProdotti;

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
        RepartoDAO repartoService = new RepartoDAO();

        List<Sede> sedi= service.getPresenzaSede(libro.getIsbn());
        List<Sede> sediNonPresenti = sedeService.doRetrivedAll();
        for(int i=0; i<sediNonPresenti.size(); i++){
            Sede sede=sediNonPresenti.get(i);
            if(sedi.contains(sede)) {
                sediNonPresenti.remove(i);
            }
        }


        List<Reparto> reparti= service.getAppartenenzaReparto(libro.getIsbn());
        List<Reparto> repartiNonPresenti = repartoService.doRetrivedAll();
        for(int i=0; i<repartiNonPresenti.size(); i++){
            Reparto reparto=repartiNonPresenti.get(i);
            if(reparti.contains(reparto)) {
                repartiNonPresenti.remove(i);
            }
        }

        request.setAttribute("libro", libro);
        request.setAttribute("sedi", sedi);
        request.setAttribute("reparti", reparti);
        request.setAttribute("sediNonPresenti", sediNonPresenti);
        request.setAttribute("repartiNonPresenti", repartiNonPresenti);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/prodotti/modificaLibro.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
