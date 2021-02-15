package persistance;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Utilisateur;

public class Utils {

    /*
    On stocke dans les cookies la connexion et les information de l'utilisateur connecté
     */
    public static final String ATT_CONNEXION = "connexion_id";
    private static final String ATT_USERNAME = "current_user_id";

    public static void storeConnection(ServletRequest request, Connection co) {
        request.setAttribute(ATT_CONNEXION, co);
    }

    public static Connection getConnection(ServletRequest request) {
        return (Connection) request.getAttribute(ATT_CONNEXION);
    }

    public static void startSession(HttpSession session, Utilisateur user) {
        // On the JSP can access via ${user}
        session.setAttribute("user", user);
    }

    // Get the user information stored in the session.
    public static Utilisateur getUser(HttpSession session) {
        return (Utilisateur) session.getAttribute("user");
    }

    //TODO : générer token cookie à stocker dans la bdd pour ne pas usurper des connexion (à faire quand tout marche)

    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, Utilisateur user) {
        Cookie cookieUserName = new Cookie(ATT_USERNAME, user.login());
        // 1 day expiration
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_USERNAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_USERNAME, null);
        // 0 seconds (instant expiration)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

}