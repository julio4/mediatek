package persistance;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import persistance.modèle.LoggedUser;

public class Session {

    public static void endSession(HttpSession session) {
        session.invalidate();
    }

    public static void startSession(HttpServletRequest request, LoggedUser user) {
        HttpSession session = request.getSession(true);

        //enregistrement de l'utilisateur connecté
        session.setAttribute("user", user);
    }

    public static boolean isStarted(HttpSession session) {
        return session != null && session.getAttribute("user") != null && session.getAttribute("user") != "";
    }
}