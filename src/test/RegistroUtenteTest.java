import static org.mockito.Mockito.*;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;

import controller.utente.RegistroUtente;
import model.utenteService.UtenteDAO;
import model.utenteService.Utente;

import java.io.IOException;

public class RegistroUtenteTest {
    private RegistroUtente servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private UtenteDAO utenteDAO;

    @BeforeEach
    void setUp() {

        servlet = new RegistroUtente();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);
        utenteDAO = mock(UtenteDAO.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoGet_SuccessRegistration() throws ServletException, IOException {
        // Parametri validi
        when(request.getParameter("nomeUtente")).thenReturn("MarioRossi");
        when(request.getParameter("email")).thenReturn("mario@example.com");
        when(request.getParameter("pw")).thenReturn("password");
        when(request.getParameter("tipo")).thenReturn("premium");
        when(request.getParameterValues("telefono")).thenReturn(new String[]{"1234567890"});

        // La mail non è presente nel DB
        when(utenteDAO.doRetrieveById("mario@example.com")).thenReturn(null);

        // Mock del dispatcher
        when(request.getRequestDispatcher("/WEB-INF/results/login.jsp")).thenReturn(dispatcher);

        // Eseguiamo la servlet
        servlet.doGet(request, response);

        // Verifica che venga fatto forward a login.jsp (perché la registrazione è riuscita)
        verify(dispatcher).forward(request, response);
        verify(session, never()).setAttribute(eq("utente"), any(Utente.class));
        // o se in futuro decidi di salvare l’utente in sessione, controlli di riflesso
    }

    @Test
    void testDoGet_AlreadyRegistered() throws ServletException, IOException {
        // Parametri validi
        when(request.getParameter("nomeUtente")).thenReturn("MarioRossi");
        when(request.getParameter("email")).thenReturn("a.raia7@studenti.unisa.it");
        when(request.getParameter("pw")).thenReturn("password");
        when(request.getParameter("tipo")).thenReturn("premium");
        when(request.getParameterValues("telefono")).thenReturn(new String[]{"1234567890"});

        // La mail è già presente nel DB -> utente esistente
        Utente utenteEsistente = new Utente();
        utenteEsistente.setEmail("a.raia7@studenti.unisa.it");
        when(utenteDAO.doRetrieveById("a.raia7@studenti.unisa.it")).thenReturn(utenteEsistente);

        // Mock del dispatcher
        when(request.getRequestDispatcher("/WEB-INF/errorJsp/utentePresente.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        // Verifichiamo che si vada sulla pagina di utentePresente.jsp
        verify(dispatcher).forward(request, response);
        verify(response, never()).sendRedirect(anyString());
    }

    @Test
    void testDoGet_FormNonValido() throws ServletException, IOException {
        // Parametri NON validi (per esempio email vuota)
        when(request.getParameter("nomeUtente")).thenReturn("MarioRossi");
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("pw")).thenReturn("password");
        when(request.getParameter("tipo")).thenReturn("standard");
        when(request.getParameterValues("telefono")).thenReturn(new String[]{"1234567890"});

        // Il dispatcher per la pagina di errore
        when(request.getRequestDispatcher("/WEB-INF/errorJsp/erroreForm.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }
}