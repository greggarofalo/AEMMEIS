package controller.admin.gestisciReparti;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Libro;
import model.libroService.LibroDAO;
import model.libroService.Reparto;
import model.libroService.RepartoDAO;

import java.awt.datatransfer.DataFlavor;
import java.io.IOException;

@WebServlet("/aggiorna-reparto")
public class AggiornaRepartoServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            int id= Integer.parseInt(request.getParameter("idReparto"));
            String nome=request.getParameter("nome");
            String descrizione=request.getParameter("descrizione");
            String immagine=request.getParameter("immagine");

            RepartoDAO repartoService = new RepartoDAO();
            Reparto reparto= new Reparto();
            reparto.setIdReparto(id);
            reparto.setNome(nome);
            reparto.setDescrizione(descrizione);
            reparto.setImmagine(immagine);

            repartoService.updateReparto(reparto);
            RequestDispatcher dispatcher = request.getRequestDispatcher("modifica-libro");
            dispatcher.forward(request, response);
    }
}
