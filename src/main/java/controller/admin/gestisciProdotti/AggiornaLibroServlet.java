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

@WebServlet("/aggiorna-libro")
public class AggiornaLibroServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String titolo=request.getParameter("titolo");
        String isbn=request.getParameter("isbn");
        String annoPubb=request.getParameter("annoPubb");
        String genere=request.getParameter("genere");
        Double prezzo = Double.valueOf(request.getParameter("prezzo"));
        int sconto = Integer.parseInt(request.getParameter("sconto"));
        String trama=request.getParameter("trama");
        String immagine=request.getParameter("immagine");

        LibroDAO libroService = new LibroDAO();
        Libro libro = new Libro();
        libro.setTitolo(titolo);
        libro.setIsbn(isbn);
        libro.setGenere(genere);
        libro.setAnnoPubblicazioni(annoPubb);
        libro.setPrezzo(prezzo);
        libro.setSconto(sconto);
        libro.setTrama(trama);
        libro.setImmagine(immagine);

        libroService.updateLibro(libro);
        RequestDispatcher dispatcher = request.getRequestDispatcher("modifica-libro");
        dispatcher.forward(request, response);
    }
}
