package sdkd.com.ec.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.model.EbProduct;
import java.util.*;

/**
 * Created by HellCrow on 2016/7/6.
 */
@WebServlet(name = "EbProductController")
public class EbProductController extends HttpServlet {
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EbProductDao productDao = new EbProductDao();
        List<EbProduct> list =productDao.getProduct("select * from ebproduct where ep_discount=1 order by ep_price desc limit 0,8",null);
        request.setAttribute("productDiscountList",list);
        List<EbProduct> list2 =productDao.getProduct("select * from ebproduct order by ep_view desc limit 0,8",null);
        request.setAttribute("productHotList",list2);
        request.getRequestDispatcher("/proCategory.do").forward(request,response);
    }
    public void cateList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String epc_child_id=request.getParameter("pc_id");
        List<String> params=new ArrayList<String>();
        params.add(epc_child_id);
        EbProductDao productDao = new EbProductDao();
        List<EbProduct> list =productDao.getProduct("select * from ebproduct where epc_child_id =?",params);
        request.setAttribute("productKindList",list);
        request.getRequestDispatcher("/product-list.jsp").forward(request,response);
    }
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id=request.getParameter("id");
        List<String> param=new ArrayList<String>();
        param.add(id);
        List<EbProduct> list=new EbProductDao().getProduct("select * from ebproduct where ep_id = ?",param);
        EbProduct ebProduct=list.get(0);
        request.setAttribute("ebProduct",ebProduct);
        request.getRequestDispatcher("/product-view.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if("list".equals(action))
        {
            list(request,response);
        }
        else if("detail".equals(action))
        {
            detail(request,response);
        }
        else if("epc_child_id".equals(action))
        {
            cateList(request,response);
        }
        else
        {
            list(request,response);
        }
    }
}
