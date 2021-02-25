package services;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Mediatek;
import mediatek2021.Utilisateur;
import persistance.Session;
import persistance.modèle.LoggedUser;

@WebServlet(urlPatterns = { "/login" })
public class AuthentificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AuthentificationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        //Si connecté redirection vers la page d'accueil
        if(Session.isStarted(request.getSession())) {
            dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/indexView.jsp");
        }
        else {
            dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
        }

        dispatcher.forward(request, response);
    }

    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoggedUser user = null;
        boolean invalid = false;
        String error = null;

        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            invalid = true;
            error = "Veuillez indiquer votre nom d'utilisateur et votre mot de passe!";
        } else {
            Connection db = Session.getConnection(request);
            // Find the user in the DB.
            user = (LoggedUser) Mediatek.getInstance().getUser(username, password);

            if (user == null) {
                invalid = true;
                error = "Utilisateur ou mot de passe non valide";
            }
        }
        // If error, forward to /WEB-INF/views/login.jsp
        if (invalid) {

            // Store information in request attribute, before forward.
            request.setAttribute("error", error);
            request.setAttribute("user", null);

            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
        }
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            HttpSession session = request.getSession();
            Session.startSession(session, user);

            // Redirect to home page.
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
