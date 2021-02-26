package persistance;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Utilisateur;
import persistance.modèle.LoggedUser;

public class Session {

    /*
    On stock dans les cookies la connexion et les information de l'utilisateur connecté
     */
    public static final String ATT_CONNEXION = "connexion_id";

    public static void resetSession(HttpSession session) {
        session.setAttribute("user", null);
        session.setAttribute("logged", false);
    }

    public static void storeConnection(ServletRequest request, Connection co) {
        request.setAttribute(ATT_CONNEXION, co);
    }

    public static Connection getConnection(ServletRequest request) {
        return (Connection) request.getAttribute(ATT_CONNEXION);
    }

    public static void startSession(HttpSession session, LoggedUser user) {
        if(session.getAttribute("logged") == null)
            resetSession(session);
        session.setAttribute("user", user);
        session.setAttribute("logged", true);
    }

    // Get the user information stored in the session.
    public static LoggedUser getUser(HttpSession session) {
        return (LoggedUser) session.getAttribute("user");
    }

    public static boolean isStarted(HttpSession session) {
        if(session.getAttribute("logged") == null)
            resetSession(session);
        return (boolean) session.getAttribute("logged");
    }
}