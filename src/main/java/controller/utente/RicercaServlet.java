package controller.utente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Libro;
import model.libroService.LibroDAO;

import java.io.IOException;
import java.util.List;
@WebServlet("/ricerca-servlet")
public class RicercaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q");
        LibroDAO libroService = new LibroDAO();
        String address=null;
        if(query==null){
            response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");//forse
            return;
        }
        else if(query.isEmpty()){
            address="index.html";
        }
        else {
            address="/WEB-INF/results/ricerca.jsp";
            List<Libro> results = libroService.Search(query);
            request.setAttribute("results", results);
            request.setAttribute("q", query);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }
}
