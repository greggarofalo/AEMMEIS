package controller.HomePage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.wishList.WishList;
import model.wishList.WishListDAO;

import java.io.IOException;
@WebServlet("/show-wishlist")
public class ShowWishList extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session= request.getSession();
        String address=null;

        if (session.getAttribute("utente") != null) {
            address="/WEB-INF/results/showWishList.jsp";
        } else {
            // L'utente non ha effettuato l'accesso, reindirizzalo alla pagina di login
            address="/WEB-INF/results/login.jsp";
        }

        RequestDispatcher dispatcher=request.getRequestDispatcher(address);
        dispatcher.forward(request, response);


    }
}
