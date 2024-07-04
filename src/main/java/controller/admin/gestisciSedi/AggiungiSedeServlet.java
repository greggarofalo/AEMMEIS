package controller.admin.gestisciSedi;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Reparto;
import model.libroService.RepartoDAO;
import model.libroService.Sede;
import model.libroService.SedeDAO;

import java.io.IOException;

@WebServlet("/aggiungi-sede")
public class AggiungiSedeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String citta = request.getParameter("citta");
        String via = request.getParameter("via");
        String civ = request.getParameter("civico");
        String cap = request.getParameter("cap");
        //controllo paramentri del form
        if(citta.length()==0 || via.length()==0|| civ.length()==0 || cap.length()==0)
            response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");

        int civico;
        Sede sede = new Sede();
        try{
            civico=Integer.parseInt(civ);
            sede.setCitta(citta);
            sede.setVia(via);
            sede.setCivico(civico);
            sede.setCap(cap);

            SedeDAO sedeService = new SedeDAO();
            sedeService.doSave(sede);
            response.sendRedirect("gestisci-sedi");

        }catch (NumberFormatException e){
            response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");
        }








    }
}