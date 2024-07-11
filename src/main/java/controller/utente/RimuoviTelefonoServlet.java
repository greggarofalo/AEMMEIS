package controller.utente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.utenteService.UtenteDAO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/rimuovi-telefono")
public class RimuoviTelefonoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // JSON parser object to parse read JSON data from request
        JSONParser jsonParser = new JSONParser();

        try (InputStreamReader reader = new InputStreamReader(request.getInputStream())) {
            // Parse JSON data
            Object obj = jsonParser.parse(reader);
            JSONObject item = (JSONObject) obj;

            // Ottieni i valori da JSON
            String email = (String) item.get("email");
            String telefono = (String) item.get("telefono");

            // Chiamata alla funzione del DAO per rimuovere il telefono
            UtenteDAO service = new UtenteDAO();
            service.deleteTelefono(email, telefono);

            // Invia una risposta al client
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Telefono rimosso con successo.");
        } catch (ParseException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Errore nella lettura del JSON.");
        }
    }
}