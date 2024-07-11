package controller.admin.gestisciReparti;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Reparto reparto = new Reparto();
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String immagine = request.getParameter("immagine");
        if(nome==null || nome.isEmpty() || descrizione==null || descrizione.isEmpty() || immagine==null || immagine.isEmpty()){
            //pagina di errore per inserimento parametri errato
            response.sendRedirect("/WEB-INF/errorJsp/erroreForm.jsp");//forse
        }

        reparto.setDescrizione(descrizione);
        reparto.setNome(nome);
        reparto.setImmagine(immagine);

        RepartoDAO repartoService = new RepartoDAO();
        List<Reparto> reparti= repartoService.doRetrivedAll();
        boolean flag=true;
        for (Reparto rep:reparti){
            if(rep.getNome().equals(reparto.getNome())){
                request.setAttribute("esito", "non riuscito");//per poter mostrare un errore nell'inserimento
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/reparti/aggiungiReparto.jsp");
                dispatcher.forward(request, response);
                flag=false;
            }
        }
        if(flag) {
            repartoService.doSave(reparto);
            response.sendRedirect("gestisci-reparti");
        }

    }
}
