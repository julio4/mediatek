package services.init;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value="/initializeResources", loadOnStartup=1)
public class InjectionServlet extends HttpServlet {

    @Override
    public void init() {
        try {
            Class.forName("persitant.MediatekData");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    public void destroy() {

    }
}