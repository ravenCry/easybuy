package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbUserDao;
import sdkd.com.ec.model.EbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by zcx on 2016/7/11.
 */
@WebServlet(name = "EbUserController" )
public class EbUserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EbUserDao userDao = new EbUserDao();
        List<EbUser> list = userDao.getUser();
        request.setAttribute("userList",list);

        //跳转
        request.getRequestDispatcher("/user.do").forward(request,response);
    }
    public void allList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EbUserDao userDao = new EbUserDao();
        List<EbUser> list = userDao.getUser();
        request.setAttribute("userList1",list);
        request.getRequestDispatcher("/manage/user.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");

        if("list".equals(action))
        {
            list(request,response);
        }
        else if("detail".equals(action))
        {

        }
        else if("userList".equals(action))
        {
           allList(request,response);
        }
        else
        {
            list(request,response);
        }
    }
}
