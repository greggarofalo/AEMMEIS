package controller.utente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.carrelloService.Carrello;
import model.carrelloService.CarrelloDAO;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

@WebServlet(name = "insertUtente", value = "/insert-utente")
public class RegistroUtente extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Utente utente = new Utente();
        String nomeUtente = request.getParameter("nomeUtente");
        String email = request.getParameter("email");
        String codiceSicurezza = request.getParameter("pw");
        String tipo = request.getParameter("tipo");
        String numero = request.getParameter("telefono");
        List<String> telefoni=new ArrayList<>();
        telefoni.add(numero);
        utente.setNomeUtente(nomeUtente);
        utente.setEmail(email);
        utente.setTipo(tipo);
        utente.setCodiceSicurezza(codiceSicurezza);
        utente.setTelefoni(telefoni);

        if(codiceSicurezza.length() > 16){
            //rimanda a una pagina di errore per password troppo lunga
        }
        else{
            //aggiungere il controllo se è già presente un utente con la stessa email
            UtenteDAO utenteService = new UtenteDAO();
            utenteService.doSave(utente);
            //request.getSession().setAttribute("utente", utente);
            CarrelloDAO carrelloService = new CarrelloDAO();
            Carrello carrello = new Carrello();
            carrello.setEmail(utente.getEmail());
            carrello.setTotale(0);
            List<String> id = carrelloService.doRetrivedAllIdCarrelli();
            String newId;
            Random random =new Random();
            do {
                char a = (char)(65+ random.nextInt(90-65));
                char b = (char) (65+ random.nextInt(90-65));
                char c = (char) (65+ random.nextInt(90-65));
                newId = a + b + c + String.valueOf( 10+ random.nextInt(100-10));
            }while(id.contains(newId));

            carrello.setIdCarrello(newId);
            carrelloService.doSave(carrello);

            RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/results/login.jsp");
            dispatcher.forward(request, response);
        }

    }

    public void destroy() {
    }
}