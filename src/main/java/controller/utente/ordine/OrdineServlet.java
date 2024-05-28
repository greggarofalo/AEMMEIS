package controller.utente.ordine;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.carrelloService.Carrello;
import model.carrelloService.RigaCarrello;
import model.libroService.Libro;
import model.libroService.LibroDAO;
import model.libroService.Sede;
import model.libroService.SedeDAO;
import model.ordineService.Ordine;
import model.ordineService.OrdineDAO;
import model.ordineService.RigaOrdine;
import model.ordineService.RigaOrdineDAO;
import model.utenteService.Utente;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/do-ordine")
public class OrdineServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Utente utente = (Utente) session.getAttribute("utente");
        List<RigaCarrello> righeCarrello = carrello.getRigheCarrello();
        List<String> libri = new ArrayList<>();

        if(libri != null){
            OrdineDAO ordineDAO = new OrdineDAO();
            LibroDAO libroDAO = new LibroDAO();
            RigaOrdineDAO rigaOrdineDAO = new RigaOrdineDAO();
            SedeDAO sedeDAO = new SedeDAO();

            Ordine ordine = new Ordine();
            double costo = 0;
            int puntiAcquisiti = 0;
            //creo idOrdine
            List<String> idOrdini = ordineDAO.doRetrivedAllByIdOrdini();
            String idOrdine;
            Random random =new Random();
            do {
                idOrdine = "T" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
            }while(idOrdini.contains(idOrdine));
            ordine.setIdOrdine(idOrdine);

            List<Sede> sediPresenza = new ArrayList<>();
            List<RigaOrdine> righeOrdine = new ArrayList<>();

            //prendo i libri selezionati dalle righe carrello e calcolo il costo e i punti
            for(String isbn : libri){
                for(RigaCarrello rigaCarrello : righeCarrello){
                    Libro l = rigaCarrello.getLibro();
                    if(l.getIsbn().equals(isbn)){
                        RigaOrdine riga = new RigaOrdine();
                        riga.setLibro(l);

                        double prezzoUnitario = (l.getPrezzo() - (l.getPrezzo()*l.getSconto()/100));
                        riga.setPrezzoUnitario(prezzoUnitario);
                        riga.setQuantita(rigaCarrello.getQuantita());
                        riga.setIdOrdine(ordine.getIdOrdine());
                        righeOrdine.add(riga);

                        costo += rigaCarrello.getQuantita() * prezzoUnitario;
                        puntiAcquisiti += 5* rigaCarrello.getQuantita();
                        List<Sede> sedi = libroDAO.getPresenzaSede(l.getIsbn());
                        if(!sedi.isEmpty()) {
                            for (Sede s : sedi) {
                                sediPresenza.add(s);
                            }
                        }
                    }
                }
            }
            ordine.setCosto(costo);
            ordine.setPuntiOttenuti(puntiAcquisiti);

            //utente che effettua l'ordine
            ordine.setEmail(utente.getEmail());
            //quando si fa l'ordine: nel momento di invocazione della servlet
            ordine.setDataEffettuazione(LocalDate.now());
            //righeOrdine
            ordine.setRigheOrdine(righeOrdine);

            request.setAttribute("sedi", sediPresenza);
            request.setAttribute("ordine", ordine);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/effettuaOrdine.jsp");
            dispatcher.forward(request, response);

        }
    }

}
