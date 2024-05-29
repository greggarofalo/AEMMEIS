package controller.HomePage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.carrelloService.Carrello;
import model.libroService.Reparto;
import model.libroService.RepartoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart-servlet")
public class CarrelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //da cambiare tutto
        //potrebbe non servire
        //HttpSession session = request.getSession();
        //Carrello carrello = (Carrello) session.getAttribute("carrello");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/stampaCarrello.jsp");
        dispatcher.forward(request, response);
    }

}
