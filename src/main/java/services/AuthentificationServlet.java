package services;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2021.Mediatek;
import persistance.Session;
import persistance.modèle.LoggedUser;

@WebServlet(name="login", urlPatterns = { "/login" })
public class AuthentificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AuthentificationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        if(Session.isStarted(request.getSession()))
            dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/indexView.jsp");
        else
            dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

        dispatcher.forward(request, response);
    }

    // Lorsque le formulaire de connexion est envoyé
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoggedUser user = null;
        boolean invalid = false;
        String error = null;

        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            invalid = true;
            error = "Veuillez indiquer votre nom d'utilisateur et votre mot de passe!";
        } else {

            user = (LoggedUser) Mediatek.getInstance().getUser(username, password);

            if (user == null) {
                invalid = true;
                error = "Utilisateur ou mot de passe non valide";
            }
        }

        if (invalid) {

            // On enregistre le message d'erreur
            request.setAttribute("error", error);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            Session.startSession(request, user);

            // Connexion avec succès, redirection vers la page d'accueil !
            request.setAttribute("success", "Connexion avec succès ! Bienvenue " + user.login() +".");
            response.sendRedirect(request.getContextPath());
        }
    }
}
