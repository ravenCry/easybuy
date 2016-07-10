package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbShoppingCarDao;
import sdkd.com.ec.model.EbProduct;
import sdkd.com.ec.model.EbShoppingCarItem;
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
        String id=request.getParameter("id");
        ebShoppingCarDao.insertProductInMyShoppingCar(id,currentUser.getId()+"");
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String item_id=request.getParameter("item_id");
        String item_number=request.getParameter("item_number");
        ebShoppingCarDao.updateProductInMyShoppingCar(item_id,item_number);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String item_id=request.getParameter("item_id");
        ebShoppingCarDao.deleteProductInMyShoppingCar(item_id);
    }
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<EbShoppingCarItem> myShoppingCarItems=ebShoppingCarDao.getShoppingCarByUserId(currentUser.getId()+"");
        HttpSession session=request.getSession();
        session.setAttribute("myShoppingCarItems",myShoppingCarItems);
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
                insert(request,response);
                list(request,response);
            }
            else if("list".equals(action))
            {
                list(request,response);
            }
            else if("update".equals(action))
            {
                update(request,response);
                list(request,response);
            }
            else if("delete".equals(action))
            {
                delete(request,response);
                list(request,response);
            }
            else
            {
                list(request,response);
            }
        }
        else
        {
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
