package controller.admin.gestisciReparti;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.RepartoDAO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/insert-libroReparto")
public class InsertLibroRepartoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] libriIsbn = (request.getParameterValues("isbn"));
        RepartoDAO repartoDAO = new RepartoDAO();
        if(libriIsbn!=null){
            for(String isbn : libriIsbn){
                repartoDAO.aggiungiLibroReparto(repartoDAO.doRetrieveById(Integer.parseInt(request.getParameter("idReparto")
                        )), isbn);
            }
        }
        response.sendRedirect("gestisci-reparti");
    }
}
