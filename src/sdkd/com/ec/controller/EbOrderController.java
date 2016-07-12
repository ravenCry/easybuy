package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbOrderDao;
import sdkd.com.ec.dao.impl.EbOrderDetailDao;
import sdkd.com.ec.dao.impl.EbShoppingCarDao;
import sdkd.com.ec.model.EbOrder;
import sdkd.com.ec.model.EbShoppingCarItem;
import sdkd.com.ec.model.EbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/12.
 */
@WebServlet(name = "EbOrderController")
public class EbOrderController extends HttpServlet {
    public void shoppingCarBuy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        EbUser currentUser=(EbUser)session.getAttribute("currentUser");
        EbOrderDao ebOrderDao=new EbOrderDao();
        EbOrderDetailDao ebOrderDetailDao=new EbOrderDetailDao();
        List<EbShoppingCarItem> myShoppingCarItems=(List<EbShoppingCarItem>)session.getAttribute("myShoppingCarItems");
        Iterator<EbShoppingCarItem> i=myShoppingCarItems.iterator();
        double sum=0;
        while(i.hasNext())
        {
            EbShoppingCarItem tempItem=i.next();
            sum+=tempItem.getEsh_quantity()*tempItem.getEbProduct().getPrice();
        }
        ebOrderDao.insertOrder(currentUser,sum,"待收货","支付宝");
        EbOrder ebOrder = ebOrderDao.getOrderRecent();
        ebOrder.setEbUser(currentUser);
        i=myShoppingCarItems.iterator();
        while(i.hasNext())
        {
            EbShoppingCarItem tempItem=i.next();
            ebOrderDetailDao.insertOrderDetail(ebOrder,tempItem.getEbProduct(),tempItem.getEsh_quantity());
        }
        new EbShoppingCarDao().deleteMyShoppingCarByUserId(currentUser.getId()+"");
        request.getRequestDispatcher("shopping-result.jsp").forward(request,response);
    }
    public void singleBuy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("shopping-result.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if("singleBuy".equals(action))
        {
            singleBuy(request,response);
        }
        else
        {
            shoppingCarBuy(request,response);
        }
    }
}
