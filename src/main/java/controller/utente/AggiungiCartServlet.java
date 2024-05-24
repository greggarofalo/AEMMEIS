package controller.utente;

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
import model.utenteService.Utente;

import java.io.IOException;
import java.util.List;

@WebServlet("/aggiungi-carrello")
public class AggiungiCartServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        String address="index.html";
        LibroDAO libroService = new LibroDAO();
        Libro libro = libroService.doRetrieveById(isbn);


        List<RigaCarrello> righeCarrello = carrello.getRigheCarrello();
        boolean flag=true; // libro non presente
        for(int i=0; i<righeCarrello.size() && flag; i++) {
            Libro libroRiga = righeCarrello.get(i).getLibro(); // libro della rigaCarrello
            if (libroRiga.equals(libro)) {
                righeCarrello.get(i).setQuantita((righeCarrello.get(i).getQuantita())+1); // libro presente, incremento la quantità
                flag = false; // libro presente
            }
        }if(flag) { // se il libro non è presente, lo aggiungo
            RigaCarrello riga = new RigaCarrello();
            riga.setIdCarrello(carrello.getIdCarrello());
            riga.setLibro(libro);
            riga.setQuantita(1);
            righeCarrello.add(riga);
        }
        session.setAttribute("carrello", carrello);
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}
