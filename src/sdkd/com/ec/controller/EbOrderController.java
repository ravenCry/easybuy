package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbOrderDao;
import sdkd.com.ec.dao.impl.EbOrderDetailDao;
import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.dao.impl.EbShoppingCarDao;
import sdkd.com.ec.model.EbOrder;
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
            tempItem.getEbProduct().setStock((Integer.parseInt(tempItem.getEbProduct().getStock())-tempItem.getEsh_quantity())+"");
            new EbProductDao().update(tempItem.getEbProduct());
            ebOrderDetailDao.insertOrderDetail(ebOrder,tempItem.getEbProduct(),tempItem.getEsh_quantity());
        }
        new EbShoppingCarDao().deleteMyShoppingCarByUserId(currentUser.getId()+"");
        request.getRequestDispatcher("shopping-result.jsp").forward(request,response);
    }
    public void singleBuy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("shopping-result.jsp").forward(request,response);
    }
    public void allList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EbOrderDao orderDao = new EbOrderDao();
        String orderId=request.getParameter("orderId");
        String userName=request.getParameter("userName");
        List<String> params=null;
        String sql="";
        if("".equals(orderId))
            orderId=null;
        if("".equals(userName))
            userName=null;
        if(orderId==null&&userName==null)
        {
            sql="select * from eborder";
        }
        else if(orderId==null&&userName!=null)
        {
            params=new ArrayList<String>();
            sql="select * from eborder where eb_user_name=?";
            params.add(userName);
        }
        else if(orderId!=null&&userName==null)
        {
            params=new ArrayList<String>();
            sql="select * from eborder where eo_id=?";
            params.add(orderId);
        }
        else
        {
            params=new ArrayList<String>();
            sql="select * from eborder where eo_id=? and eb_user_name=?";
            params.add(orderId);
            params.add(userName);
        }
        List<EbOrder> list = orderDao.getOrder(sql,params);
        request.setAttribute("orderList", list);
        request.getRequestDispatcher("/manage/order.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eo_id=request.getParameter("orderId");
        String eb_user_name=request.getParameter("name");
        String eo_status=request.getParameter("status");
        List<String> params = new ArrayList<String>();
        params.add(eb_user_name);
        params.add(eo_status);
        params.add(eo_id);
        new EbOrderDao().update(params);


    }
    public void jump2modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String eo_id=request.getParameter("eo_id");
        String eb_user_name=request.getParameter("eb_user_name");
        request.setAttribute("orderId",eo_id);
        request.setAttribute("eb_user_name",eb_user_name);

        request.getRequestDispatcher("/manage/order-modify.jsp").forward(request,response);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String orderID=request.getParameter("orderID");
        new EbOrderDao().delete(orderID);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if("singleBuy".equals(action))
        {
            singleBuy(request,response);
        }
        else if("manageList".equals(action)){
            allList(request,response);
        }
        else if("jump2modify".equals(action))
        {
            jump2modify(request,response);
        }
        else
        {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String submit = request.getParameter("submit");
            if ("更新".equals(submit)) {
                update(request, response);
                allList(request, response);
            }
            else if("查询".equals(submit))
            {
                allList(request,response);
            }
            else
            {
                shoppingCarBuy(request,response);
            }
        }
    }
}
