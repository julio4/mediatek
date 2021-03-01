package services;

import mediatek2021.Document;
import mediatek2021.Mediatek;
import persistance.Session;
import persistance.modèle.PDocument;

import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="liste", urlPatterns = {"/list"})
public class ListeServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public ListeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Session.isStarted(request.getSession())) {
            List<Document> list;

            String type = request.getParameter("type");
            if(type != null && Pattern.compile("\\d+").matcher(type).matches() && Integer.parseInt(type) < 3) {
                list = Mediatek.getInstance().catalogue(Integer.parseInt(type));
            }
            else {
                list = Mediatek.getInstance().catalogue(0);
            }

            request.setAttribute("list", list);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/listView.jsp");
            dispatcher.forward(request, response);
        }
        else
            response.sendRedirect(request.getContextPath() + "/login");
    }
}