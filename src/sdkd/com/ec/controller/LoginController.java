package sdkd.com.ec.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.dao.impl.EbUserDao;
import sdkd.com.ec.model.EbUser;

/**
 * Created by HellCrow on 2016/7/6.
 */
@WebServlet(name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("userName");
        String password = request.getParameter("passWord");
        if (username == null)
            username = "";
        if (password == null)
            password = "";
        //username = new String(username.getBytes("iso-8859-1"), "utf-8");
        //password = new String(password.getBytes("iso-8859-1"), "utf-8");
        EbUserDao ebUserDao=new EbUserDao();
        EbUser ebUser=ebUserDao.match(username,password);
        //request.getRequestDispatcher("homepage.jsp").forward(request, response);
        if (ebUser!=null) {
            HttpSession session=request.getSession();
            session.setAttribute("currentUser",ebUser);
            request.getRequestDispatcher("/all.do").forward(request,response);
        }
        //response.sendRedirect("/all.do");

    }

}
