package controller.HomePage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/show-wishlist")
public class ShowWishList extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session= request.getSession(false);
        String address=null;
        if (session != null && session.getAttribute("email") != null) {
            // L'utente ha effettuato l'accesso, puoi reindirizzarlo alla pagina del dashboard
        } else {
            // L'utente non ha effettuato l'accesso, reindirizzalo alla pagina di login
            address="/WEB-INF/results/login.jsp";
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher(address);
        dispatcher.forward(request, response);


    }
}
