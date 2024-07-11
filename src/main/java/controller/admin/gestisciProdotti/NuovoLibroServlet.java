package controller.admin.gestisciProdotti;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Autore;
import model.libroService.AutoreDAO;
import model.libroService.Libro;
import model.libroService.LibroDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/insert-libro")
public class NuovoLibroServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Libro libro = new Libro();
        LibroDAO libroService = new LibroDAO();

        String isbn = request.getParameter("isbn");
        String titolo = request.getParameter("titolo");
        String genere = request.getParameter("genere");
        String annoPubblicazioni = request.getParameter("annoPubb");
        String price=request.getParameter("prezzo");
        String sconto1 = request.getParameter("sconto");
        String trama = request.getParameter("trama");
        String immagine = request.getParameter("immagine");


        if(isbn==null || isbn.isEmpty() || titolo==null || titolo.isEmpty() || genere==null || genere.isEmpty() ||
                annoPubblicazioni==null || annoPubblicazioni.isEmpty() || price==null || price.isEmpty() ||
                sconto1==null || trama==null || immagine==null){
            response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");
        }
        try {
            double prezzo = Double.parseDouble(price);
            int sconto;
            if(sconto1.isEmpty())
                sconto=0;
            else {
                sconto = Integer.parseInt(sconto1);
            }

            String[] nomiAutori = request.getParameterValues("nome");
            String[] cognomiAutori = request.getParameterValues("cognome");
            String[] cfAutori = request.getParameterValues("cf");

            List<Autore> autori = new ArrayList<>();

            if (nomiAutori != null && cognomiAutori != null && cfAutori != null) {
                for (int i = 0; i < nomiAutori.length; i++) {
                    if(nomiAutori[i].isEmpty() || cognomiAutori[i].isEmpty() || cfAutori[i].isEmpty()) {
                        response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");
                    }
                    Autore autore = new Autore();
                    autore.setNome(nomiAutori[i]);
                    autore.setCognome(cognomiAutori[i]);
                    autore.setCf(cfAutori[i]);
                    autori.add(autore);
                }
            }

            libro.setIsbn(isbn);
            libro.setTitolo(titolo);
            libro.setGenere(genere);
            libro.setAnnoPubblicazioni(annoPubblicazioni);
            libro.setPrezzo(prezzo);
            libro.setSconto(sconto);
            libro.setTrama(trama);
            libro.setImmagine(immagine);
            libro.setDisponibile(true);
            libro.setAutori(autori);

            List<Libro> libri= libroService.doRetriveAll();
            if(!libri.contains(libro)) {
                libroService.doSave(libro);
                response.sendRedirect("gestisci-prodotti");
            }
            else {
                request.setAttribute("esito", "non riuscito");//per poter mostrare un errore nell'inserimento
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/prodotti/nuovoProdotto.jsp");
                dispatcher.forward(request, response);
            }


        }catch(NumberFormatException e){
            response.sendRedirect("/WEB-INF/errorJsp/erroreFormato.jsp");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
