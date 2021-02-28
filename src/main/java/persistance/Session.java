package persistance;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import persistance.modèle.LoggedUser;

public class Session {

    private static HttpSession session;

    public static final String ATT_CONNEXION = "connexion_id";

    public static void endSession() {
        DatabaseConnection.close(getConnection());
        session.invalidate();
        session = null;
    }

    public static void storeConnection(Connection co) {
        session.setAttribute(ATT_CONNEXION, co);
    }

    public static Connection getConnection() {
        return (Connection) session.getAttribute(ATT_CONNEXION);
    }

    public static void startSession(HttpServletRequest request, LoggedUser user) {
        session = request.getSession(true);

        //enregistrement de l'utilisateur connecté
        session.setAttribute("user", user);

        //Connexion à la base de donnée unique
        storeConnection(DatabaseConnection.getConnection());
    }

    public static boolean isStarted() {
        return session != null;
    }
}