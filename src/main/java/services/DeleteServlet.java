package services;

import mediatek2021.Mediatek;
import mediatek2021.SuppressException;
import persistance.Session;
import persistance.modèle.PDocument;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="delete", urlPatterns = { "/delete" })
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Session.isStarted(request.getSession())) {
            int docId = Integer.parseInt(request.getParameter("id"));
            PDocument document = (PDocument) Mediatek.getInstance().getDocument(docId);

            try {
                Mediatek.getInstance().suppressDoc(docId);
                request.setAttribute("success", "Document " + document + " supprimé avec succès.");
            } catch (SuppressException e) {
                request.setAttribute("error", e.getMessage());
            }

            response.sendRedirect(request.getContextPath() + "/");
        }
        else
            response.sendRedirect(request.getContextPath() + "/login");
    }
}
