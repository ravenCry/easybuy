package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.model.EbProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id=request.getParameter("id");
        List<String> param=new ArrayList<String>();
        param.add(id);
        List<EbProduct> list=new EbProductDao().getProduct("select * from ebproduct where ep_id = ?",param);
        EbProduct ebProduct=list.get(0);
        ebProduct.setView(ebProduct.getView()+1);
        new EbProductDao().update(ebProduct);
        request.setAttribute("ebProduct",ebProduct);

        int ids = Integer.parseInt(id);

        //jinqiliulan
        recent(request,ebProduct,ids);

        request.getRequestDispatcher("/product-view.jsp").forward(request,response);
    }

    public void recent(HttpServletRequest request, EbProduct ebProduct, int id) throws ServletException, IOException
    {
        List<EbProduct> recentList = (List<EbProduct>)request.getSession().getAttribute("recent");
        if(recentList==null){
            recentList=new ArrayList<EbProduct>();
        }else{
            for(EbProduct pro:recentList){
                if(pro.getId()==id){
                    // System.out.println(pro.getName()+"****");
                    recentList.remove(pro);
                }
            }
        }
        recentList.add(ebProduct);
        if(recentList.size()>5)
        {
            //System.out.println(recentList.get(0).getName());
            recentList.remove(0);

        }
        request.getSession().setAttribute("recent",recentList);
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
    public void allList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EbProductDao productDao = new EbProductDao();
        List<EbProduct> list =productDao.getProduct("select * from ebproduct",null);
        request.setAttribute("productList",list);
        request.getRequestDispatcher("/manage/product.jsp").forward(request,response);
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
        else if("manageList".equals(action))
        {
            allList(request,response);
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
