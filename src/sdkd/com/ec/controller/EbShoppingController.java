package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbShoppingCarDao;
import sdkd.com.ec.model.EbProduct;
import sdkd.com.ec.model.EbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/8.
 */
@WebServlet(name = "EbShoppingController")
public class EbShoppingController extends HttpServlet {
    EbShoppingCarDao ebShoppingCarDao=new EbShoppingCarDao();
    EbUser currentUser=null;
    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<EbProduct> myShoppingCarProducts=ebShoppingCarDao.getShoppingCarByUserId(currentUser.getId()+"");
        HttpSession session=request.getSession();
        session.setAttribute("myShoppingCarProducts",myShoppingCarProducts);
        request.getRequestDispatcher("/shopping.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        HttpSession session=request.getSession();
        currentUser=(EbUser)session.getAttribute("currentUser");
        if(currentUser!=null)
        {
            if("insert".equals(action))
            {
                list(request,response);
            }
            else if("list".equals(action))
            {

            }
        }
        else
        {
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
