package controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.SedeDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/aggiungiLibro-sede")
public class AggiungiLibroSede extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String[] idSedi= request.getParameterValues("sedeSelezionata");
            String isbn = request.getParameter("isbn");

            SedeDAO sedeService = new SedeDAO();
            if(idSedi!=null) {
                for (String idSede : idSedi) {
                    int id = Integer.parseInt(idSede);
                    sedeService.doSavePresenza(id, isbn);
                }
            }

        RequestDispatcher dispatcher = request.getRequestDispatcher("modifica-libro");
        dispatcher.forward(request, response);

    }
}
