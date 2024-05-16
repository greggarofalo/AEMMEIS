package controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.RepartoDAO;
import model.libroService.SedeDAO;

import java.io.IOException;
@WebServlet("/aggiungiLibro-reparto")
public class AggiungiLibroReparto extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String[] idReparti = request.getParameterValues("repartoSelezionato");
        String isbn = request.getParameter("isbn");

        RepartoDAO repartoService = new RepartoDAO();
        if(idReparti!=null) {
            for (String idReparto : idReparti) {
                int id= Integer.parseInt(idReparto);
                repartoService.doSaveAppartenenza(id,isbn);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("modifica-libro");
        dispatcher.forward(request, response);

    }
}
