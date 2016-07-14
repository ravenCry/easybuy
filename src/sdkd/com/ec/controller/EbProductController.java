package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbNewsDao;
import sdkd.com.ec.dao.impl.EbProductDao;
import sdkd.com.ec.model.EbProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/6.
 */
@WebServlet(name = "EbProductController")
@MultipartConfig(location = "G:\\story\\easybuy\\web\\images")
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
    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String ep_name=request.getParameter("productName");
        Part part=request.getPart("photo");
        String header=part.getHeader("Content-Disposition");
        String ep_file_name=header.substring(header.indexOf("filename=\"")+10,header.lastIndexOf("\""));
        part.write(ep_file_name);
        String ep_price=request.getParameter("productPrice");
        String epc_id=request.getParameter("epc_id");
        String epc_child_id=null;
        String ep_stock=request.getParameter("productStock");
        String []epc=epc_id.split(",");
        if(epc.length==1)
        {
            epc_id=epc[0];
        }
        else
        {
            epc_id=epc[0];
            epc_child_id=epc[1];
        }
        List<String> params=new ArrayList<String>();
        params.add(ep_name);
        params.add(ep_price);
        params.add(ep_stock);
        params.add(epc_id);
        params.add(epc_child_id);
        params.add(ep_file_name);
        new EbProductDao().insert(params);
        request.getRequestDispatcher("/manage/manage-result.jsp").forward(request,response);
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String ep_id=request.getParameter("productId");
        String ep_name=request.getParameter("productName");
        String epc_id=request.getParameter("epc_id");
        String ep_file_name=request.getParameter("photo");
        String ep_price=request.getParameter("productPrice");
        String epc_child_id=null;
        String ep_stock=request.getParameter("productStock");
        List<String> params=new ArrayList<String>();
        String []epc=epc_id.split(",");
        if(epc.length==1)
        {
            epc_id=epc[0];
        }
        else
        {
            epc_id=epc[0];
            epc_child_id=epc[1];
        }
        params.add(ep_name);
        params.add(ep_price);
        params.add(ep_stock);
        params.add(epc_id);
        params.add(epc_child_id);
        params.add(ep_file_name);
        params.add(ep_id);
        new EbProductDao().update(params);
    }
    public void jump2modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String ep_id=request.getParameter("ep_id");
        String ep_name=request.getParameter("ep_name");
        request.setAttribute("ep_id",ep_id);
        request.setAttribute("ep_name",ep_name);
        request.getRequestDispatcher("/manage/product-modify.jsp").forward(request,response);


    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String productId=request.getParameter("productId");
        new EbProductDao().delete(productId);
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
        else if("delete".equals(action))
        {
            delete(request,response);
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
            String submit=request.getParameter("submit");
            if("更新".equals(submit))
            {
                update(request,response);
                allList(request,response);
            }
            else if("添加".equals(submit))
            {
                insert(request,response);
                allList(request,response);
            }
            else
                list(request,response);
        }
    }
}
