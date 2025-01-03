import controller.utente.ordine.OrdineServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import model.carrelloService.Carrello;
import model.carrelloService.RigaCarrello;
import model.libroService.Libro;
import model.utenteService.Utente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class OrdineServletTest {

    private OrdineServlet servletUnderTest;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        servletUnderTest = new OrdineServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
    }

    /**
     * Flusso principale: Salvataggio ordine con successo -> redirect index.html
     * UC_GO_4.3 main scenario
     */
    @Test
    void testDoGet_Success() throws ServletException, IOException {
        // Utente standard
        Utente user = new Utente();
        user.setTipo("standard");
        user.setEmail("mario@example.com");
        when(session.getAttribute("utente")).thenReturn(user);

        // Parametri
        when(request.getParameter("indirizzo")).thenReturn("Via Roma 1");
        when(request.getParameter("citta")).thenReturn("Napoli");
        when(request.getParameter("punti")).thenReturn("0"); // se standard

        // righeDisponibili
        List<RigaCarrello> righeDisponibili = new ArrayList<>();
        Libro libro = new Libro();
        libro.setIsbn("1234123412341");
        libro.setPrezzo(10.0);
        libro.setSconto(10);
        libro.setDisponibile(true);
        RigaCarrello rigaCarrello = new RigaCarrello();
        rigaCarrello.setIdCarrello("2");
        rigaCarrello.setLibro(libro);
        righeDisponibili.add(rigaCarrello); // TOT = (10 - 10%)=9 *2=18

        when(session.getAttribute("righeDisponibili")).thenReturn(righeDisponibili);

        // Carrello in session
        Carrello carrello = new Carrello();
        carrello.setIdCarrello("C0001");
        carrello.setEmail("mario@example.com");
        List<RigaCarrello> righeCarrello = new ArrayList<>(righeDisponibili);
        carrello.setRigheCarrello(righeCarrello);

        when(session.getAttribute("carrello")).thenReturn(carrello);

        // Esecuzione
        servletUnderTest.doGet(request, response);

        // Verifica che la servlet fa un redirect alla home
        verify(response).sendRedirect("index.html");
        verify(dispatcher, never()).forward(request, response);

        // Se avessi iniettato OrdineDAO, potresti verify(ordineDAO).doSave(...)
        // Ma qui, la servlet fa new OrdineDAO() => non direttamente testabile con mocking
    }
}