package ro.teamnet.zth.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Vlad.Bulimac on 5/5/2015.
 */
public class HttpSessionZTH extends HttpServlet {

    //Default username and password
    private final String defaultUser = "admin";
    private final String defaultPass = "pass";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username;
        String password;
        username = req.getParameter("username");
        password=req.getParameter("password");
        Cookie[] x = req.getCookies();

        if(defaultUser.equals(username)&& (defaultPass.equals(password))){
            resp.getWriter().write("Welcome back! \n"+"Username:"+username);

        }
else {
            HttpSession session=req.getSession();
            session.setAttribute("user", username);
            session.setAttribute("session",req.getSession());
            resp.sendRedirect("loginFail.jsp");

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
