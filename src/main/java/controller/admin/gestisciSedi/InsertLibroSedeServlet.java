package controller.admin.gestisciSedi;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.RepartoDAO;
import model.libroService.SedeDAO;

import java.io.IOException;

@WebServlet("/insert-libroSede")
public class InsertLibroSedeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] libriIsbn = (request.getParameterValues("isbn"));
        SedeDAO sedeDAO = new SedeDAO();
        if(libriIsbn!=null){
            for(String isbn : libriIsbn){
                sedeDAO.addLibroSede(sedeDAO.doRetrieveById(Integer.parseInt(request.getParameter("idSede")
                )), isbn);
            }
        }
        response.sendRedirect("gestisci-sedi");
    }
}