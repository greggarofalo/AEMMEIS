package controller.admin.gestisciReparti;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.libroService.Reparto;
import model.libroService.RepartoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/aggiungi-reparto")
public class AggiungiRepartoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Reparto reparto = new Reparto();
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String immagine = request.getParameter("immagine");
        if(nome.length()==0 || descrizione.length()==0 || immagine.length()==0){
            //pagina di errore per inserimento parametri errato
            response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");//forse
        }

        reparto.setDescrizione(descrizione);
        reparto.setNome(nome);
        reparto.setImmagine(immagine);

        RepartoDAO repartoService = new RepartoDAO();
        List<Reparto> reparti= repartoService.doRetrivedAll();
        for (Reparto rep:reparti){
            if(rep.getNome().equals(reparto.getNome())){
                //pagina di errore perchè esise già un reparto con lo stesso nome
            }
        }

        repartoService.doSave(reparto);
        response.sendRedirect("gestisci-reparti");

    }
}
