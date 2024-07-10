package controller.utente;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.carrelloService.Carrello;
import model.carrelloService.CarrelloDAO;
import model.tesseraService.Tessera;
import model.tesseraService.TesseraDAO;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

@WebServlet(name = "insertUtente", value = "/insert-utente")
public class RegistroUtente extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Utente utente = new Utente();
        String nomeUtente = request.getParameter("nomeUtente");
        String email = request.getParameter("email");
        String codiceSicurezza = request.getParameter("pw");
        String tipo = request.getParameter("tipo");
        if (nomeUtente == null || nomeUtente.length() == 0 || (email == null || email.length() == 0 || !email.contains("@")) ||
                codiceSicurezza == null || codiceSicurezza.length() == 0 || tipo == null) {
            //pagina di errore per inserimento parametri errato
            response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");//forse
        }
        String[] numeriTelefono = request.getParameterValues("telefono");
        List<String> telefoni = new ArrayList<>();
        for (String telefono : numeriTelefono) {
            if (telefono.length() != 10) {
                //pagina di errore per inserimento parametri errato
                response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");//forse
            } else telefoni.add(telefono);
        }


        utente.setNomeUtente(nomeUtente);
        utente.setEmail(email);
        utente.setTipo(tipo);
        utente.setCodiceSicurezza(codiceSicurezza);
        utente.setTelefoni(telefoni);


        if (codiceSicurezza.length() > 16) {
            //rimanda a una pagina di errore per password troppo lunga
            //pagina di errore per inserimento parametri errato
            response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");//forse
        } else {
            UtenteDAO utenteService = new UtenteDAO();
            if (utenteService.doRetrieveById(utente.getEmail()) == null) {
                utenteService.doSave(utente);

                if (utente.getTipo().equalsIgnoreCase("premium")) {
                    TesseraDAO tesseraService = new TesseraDAO();
                    Tessera tessera = new Tessera();
                    tessera.setEmail(utente.getEmail());
                    tessera.setDataCreazione(LocalDate.now());
                    tessera.setDataScadenza(LocalDate.now().plusYears(2));
                    List<String> numeri = tesseraService.doRetrivedAllByNumero();
                    String numT;
                    Random random = new Random();
                    do {
                        numT = "T" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
                    } while (numeri.contains(numT));
                    tessera.setNumero(numT);

                    tesseraService.doSave(tessera);
                }
                //request.getSession().setAttribute("utente", utente);
                CarrelloDAO carrelloService = new CarrelloDAO();
                Carrello carrello = new Carrello();
                carrello.setEmail(utente.getEmail());
                carrello.setTotale(0);
                List<String> id = carrelloService.doRetrivedAllIdCarrelli();
                String newId;
                Random random = new Random();
                do {
                    char a = (char) (65 + random.nextInt(90 - 65));
                    char b = (char) (65 + random.nextInt(90 - 65));
                    char c = (char) (65 + random.nextInt(90 - 65));
                    newId = a + b + c + String.valueOf(10 + random.nextInt(100 - 10));
                } while (id.contains(newId));

                carrello.setIdCarrello(newId);
                carrelloService.doSave(carrello);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/login.jsp");
                dispatcher.forward(request, response);
            } else {
                //creare pagina di errore dicendo che è già presente un utente con la stessa email, chiedere se vuole fare il login
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}