package controller.HomePage;

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

import java.io.IOException;
import java.util.List;

@WebServlet("/cart-servlet")
public class CarrelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //da cambiare tutto
        //potrebbe non servire
        HttpSession session = request.getSession();

            Carrello carrello = (Carrello) session.getAttribute("carrello");
            //List<RigaCarrello> righeCarrello = (List<RigaCarrello>) session.getAttribute("rigaCarrello");
            /*float totale = 0;
            for(RigaCarrello rigaCarrello : righeCarrello){
                LibroDAO serviceLibro = new LibroDAO();
                Libro libro = serviceLibro.doRetrieveById(rigaCarrello.getIsbn());
                float prezzo = (float) (libro.getPrezzo() - (libro.getPrezzo() * libro.getSconto() / 100));
                totale = totale + rigaCarrello.getQuantita()*prezzo;
            }
            carrello.setTotale(totale);*/
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/stampaCarrello.jsp");
            dispatcher.forward(request, response);
    }

}
