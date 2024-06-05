package controller.utente.ordine;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.carrelloService.Carrello;
import model.carrelloService.RigaCarrello;
import model.carrelloService.RigaCarrelloDAO;
import model.libroService.Libro;
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
        //prendo ciò che ho in  sessione
        HttpSession session = request.getSession();

        //non faccio il controllo poichè lo faccio negli step prima...quindi se non ci fossero righe non dovrebbe
        //proprio arrivare a questo punto.
        List<RigaCarrello> righeCarrello = (List<RigaCarrello>) session.getAttribute("righeDisponibili");

        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Utente utente = (Utente) session.getAttribute("utente");

        //credo gli oggetti che mi serviranno e i relativi services
        OrdineDAO ordineDAO = new OrdineDAO();
        Ordine ordine = new Ordine();
        RigaOrdineDAO rigaOrdineDAO = new RigaOrdineDAO();
        RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();

        //parametri passati da servlet a jsp...
        ordine.setIndirizzoSpedizione(request.getParameter("indirizzo"));
        ordine.setCitta(request.getParameter("citta"));

        if(utente.getTipo().equalsIgnoreCase("premium")){
            String puntiString = request.getParameter("punti");
            int punti = 0;
            if(puntiString != null)
                punti = Integer.parseInt(puntiString);
            ordine.setPuntiSpesi(punti);
        }

        //parametri calcolati
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


        List<RigaOrdine> righeOrdine = new ArrayList<>();
        //prendo i libri selezionati dalle righe carrello e calcolo il costo e i punti

        for(RigaCarrello rigaCarrello : righeCarrello){
            Libro l = rigaCarrello.getLibro();
            RigaOrdine riga = new RigaOrdine();
            riga.setIdOrdine(ordine.getIdOrdine());
            riga.setLibro(l);

            double prezzoUnitario = (l.getPrezzo() - (l.getPrezzo()*l.getSconto()/100));
            riga.setPrezzoUnitario(prezzoUnitario);
            riga.setQuantita(rigaCarrello.getQuantita());

        //    rigaOrdineDAO.doSave(riga); //salvo nel db la riga ordine
            righeOrdine.add(riga);

            costo += ((rigaCarrello.getQuantita() * prezzoUnitario) - (ordine.getPuntiSpesi() * 0.10)); //tolgo
            puntiAcquisiti += 5* rigaCarrello.getQuantita();

            rigaCarrelloDAO.deleteRigaCarrello(l.getIsbn(), carrello.getIdCarrello()); //cancello la riga carrello poichè ho acquistato

        }

       // ordine.setCosto(Double.parseDouble(request.getParameter("costo")));
        ordine.setCosto(costo); //lo ricalcolo per sicurezza
        ordine.setPuntiOttenuti(puntiAcquisiti);

            //utente che effettua l'ordine
        ordine.setEmail(utente.getEmail());
            //quando si fa l'ordine: nel momento di invocazione della servlet
        ordine.setDataEffettuazione(LocalDate.now());
            //righeOrdine
        ordine.setRigheOrdine(righeOrdine);

        ordine.setStato("In Lavorazione");


        ordineDAO.doSave(ordine);

        for(RigaOrdine o : ordine.getRigheOrdine()){

            rigaOrdineDAO.doSave(o);
        }

        response.sendRedirect("cart-servlet");
    }
}

