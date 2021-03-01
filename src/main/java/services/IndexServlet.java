package services;

import mediatek2021.Document;
import mediatek2021.Mediatek;
import persistance.Session;
import persistance.modèle.PDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="index", urlPatterns = {"/"})
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IndexServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(Session.isStarted(request.getSession())) {

            List<Document> list = Mediatek.getInstance().catalogue(0);
            request.setAttribute("list", list);

            int emprunts = 0;
            for (Document doc : list) {
                if(((PDocument) doc).estEmprunté())
                    emprunts++;
            }
            request.setAttribute("emprunts", emprunts);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/indexView.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}