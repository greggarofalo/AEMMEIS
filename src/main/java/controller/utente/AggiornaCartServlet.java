package controller.utente;

import com.oracle.wls.shaded.org.apache.xml.utils.SystemIDResolver;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.carrelloService.Carrello;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.script.ScriptContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/aggiorna-carrello")
public class AggiornaCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        if (carrello == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Carrello non trovato");
            return;
        }

        // JSON parser object to parse read JSON data from request
        JSONParser jsonParser = new JSONParser();

        try (InputStreamReader reader = new InputStreamReader(request.getInputStream())) {
            // Parse JSON data
            Object obj = jsonParser.parse(reader);
            JSONArray cartData = (JSONArray) obj;
            int i=0;
            // Itera l'array JSON e aggiorna cart
            for (Object cartItem : cartData) {
                JSONObject item = (JSONObject) cartItem;
                String isbn = (String) item.get("isbn");
                System.out.println(isbn);
                // Ottieni la stringa dalla chiave "quantity" nell'oggetto JSON
                String quantityStr = (String) item.get("quantity");
                // Converte la stringa in un intero
                int quantity = Integer.parseInt(quantityStr);
                System.out.println(quantity);
                carrello.getRigheCarrello().get(i).setQuantita(quantity);
                i++;
            }

            // Update cart in session
            session.setAttribute("carrello", carrello);


            // Send success response

        } catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());//"Formato JSON non valido"
            e.printStackTrace();
        }
    }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
