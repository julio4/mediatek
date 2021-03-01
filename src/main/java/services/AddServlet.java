package services;

import mediatek2021.Mediatek;
import mediatek2021.NewDocException;
import persistance.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="add", urlPatterns = { "/add" })
public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(Session.isStarted(request.getSession())) {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/addView.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Session.isStarted(request.getSession())) {
            String titre = request.getParameter("titre");
            String autheur = request.getParameter("autheur");
            int type = Integer.parseInt(request.getParameter("type"));

            boolean invalid = false;
            String error = null;

            if (titre == null || autheur == null || titre.length() == 0 || autheur.length() == 0) {
                invalid = true;
                error = "Veuillez remplir tout les champs.";
            } else {
                try {
                    Mediatek.getInstance().newDocument(type, titre, autheur);
                } catch (NewDocException e) {
                    invalid = true;
                    error = e.getMessage();
                }
            }

            if (invalid)
                request.setAttribute("error", error);
            else
                request.setAttribute("sucess", "Le document a été ajouté avec succès");

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/addView.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath() + "/login");
    }
}
