package controller.HomePage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.carrelloService.Carrello;
import model.carrelloService.RigaCarrello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index.html")
public class HomePageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Memorizza il carrello nella sessione
        // Ottieni la sessione corrente, creandone una nuova se non esiste
        HttpSession session = request.getSession();
        // Verifica se il carrello è già presente nella sessione
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        //List<RigaCarrello> rigaCarrello = (List<RigaCarrello>) session.getAttribute("rigaCarrello");
        if (carrello == null) {
            // Se il carrello non è presente nella sessione, ne crea uno nuovo
            carrello = new Carrello();
            //rigaCarrello = new ArrayList<>();

            // Aggiungi il carrello alla sessione
            session.setAttribute("carrello", carrello);
            //session.setAttribute("rigaCarrello", rigaCarrello);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/homepage.jsp");
        dispatcher.forward(request, response);
    }
}
