package controller.admin.gestisciProdotti;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Libro;
import model.libroService.LibroDAO;

import java.io.IOException;

@WebServlet("/insert-libro")
public class NuovoLibroServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Libro libro = new Libro();

        String isbn = request.getParameter("isbn");
        String titolo = request.getParameter("titolo");
        String genere = request.getParameter("genere");
        String annoPubblicazioni = request.getParameter("annoPubb");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        int sconto = Integer.parseInt(request.getParameter("sconto"));
        String trama = request.getParameter("trama");
        String immagine = request.getParameter("immagine");
        Boolean disponibile = Boolean.parseBoolean(request.getParameter("disponibile"));

        libro.setIsbn(isbn);
        libro.setTitolo(titolo);
        libro.setGenere(genere);
        libro.setAnnoPubblicazioni(annoPubblicazioni);
        libro.setPrezzo(prezzo);
        libro.setSconto(sconto);
        libro.setTrama(trama);
        libro.setImmagine(immagine);
        libro.setDisponibile(disponibile);

        LibroDAO libroService = new LibroDAO();
        libroService.doSave(libro);
        response.sendRedirect("gestisci-prodotti");
    }
}
