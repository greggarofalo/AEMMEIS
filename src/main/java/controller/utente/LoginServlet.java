package controller.utente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.carrelloService.Carrello;
import model.carrelloService.CarrelloDAO;
import model.carrelloService.RigaCarrello;
import model.carrelloService.RigaCarrelloDAO;
import model.libroService.Libro;
import model.libroService.LibroDAO;
import model.utenteService.Utente;
import model.utenteService.UtenteDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/login-servlet")

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = new Utente();
        utente.setEmail(request.getParameter("email"));
        utente.setNomeUtente(request.getParameter("nomeUtente"));
        utente.setCodiceSicurezza(request.getParameter("pw"));
        utente.setTipo(request.getParameter("tipo"));

        UtenteDAO service = new UtenteDAO();

        if(service.doRetrieveByEmailPassword(utente.getEmail(), utente.getCodiceSicurezza()) == null){
            RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/results/errore.jsp");
            dispatcher.forward(request, response); //provvisorio
        }
        else{
            HttpSession session = request.getSession();
            session.setAttribute("utente", utente);

            Carrello carrelloLocale = (Carrello) session.getAttribute("carrello");// Recupera il carrello locale dalla sessione
            List<RigaCarrello> righeLocali = carrelloLocale.getRigheCarrello();

            CarrelloDAO carrelloService= new CarrelloDAO();
            Carrello carrelloDb = carrelloService.doRetriveByUtente(utente.getEmail());// Recupera il carrello dal database
           //RigaCarrelloDAO rigaCarrelloService= new RigaCarrelloDAO();
            List<RigaCarrello> rigaCarrelloDb = carrelloDb.getRigheCarrello();


            if (righeLocali != null) {
                // Fusiona i carrelli
                for (int i=0; i<righeLocali.size(); i++) {
                    RigaCarrello riga = righeLocali.get(i);
                    for(int j=0; j<rigaCarrelloDb.size(); j++){
                        RigaCarrello riga2=rigaCarrelloDb.get(j);
                        if(riga2.getLibro().getIsbn().equals(riga.getLibro().getIsbn())){ //se l'isbn è già presente nel carrello del DB
                            riga2.setQuantita(riga2.getQuantita()+riga.getQuantita()); //incremento semplicemente la quantità
                            /*LibroDAO libroService = new LibroDAO();
                            Libro libro= libroService.doRetrieveById(riga2.getIsbn());
                            Double prezzoAggiuntivo;
                            if(libro.getSconto()>0){
                                Double sconto=(libro.getPrezzo()* libro.getSconto())/100;
                            }
                                prezzoAggiuntivo =
                                =(libro.getPrezzo()*);
                            carrelloDb.setTotale(carrelloDb.getTotale());*/
                        }
                        else{
                            riga.setIdCarrello(carrelloDb.getIdCarrello());
                            rigaCarrelloDb.add(riga); //altrimenti lo aggiungo nel carrello
                        }
                    }
                }
                session.setAttribute("carrello", carrelloDb);
                //session.setAttribute("righeCarrello", rigaCarrelloDb);
            }
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/results/homepage.jsp");
        dispatcher.forward(request, response);





    }

}
