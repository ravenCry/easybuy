package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbProCategoryDao;
import sdkd.com.ec.model.EbProCategory;

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
 * Created by HellCrow on 2016/7/7.
 */
@WebServlet(name = "EbProCategoryController")
public class EbProCategoryController extends HttpServlet {
    public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        EbProCategoryDao proCategoryDao = new EbProCategoryDao();
        List<EbProCategory> list = proCategoryDao.getProCategory();
        session.setAttribute("proCategoryList",list);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    public void allList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EbProCategoryDao proCategoryDao = new EbProCategoryDao();
        List<EbProCategory> list = proCategoryDao.getProCategory();
        request.setAttribute("proCategory_List",list);
        request.getRequestDispatcher("/manage/productClass.jsp").forward(request,response);
    }
    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       String epc_parent_id=request.getParameter("parentId");
        String epc_name=request.getParameter("className");
        List<String> params=new ArrayList<String>();
        params.add(epc_name);
        if("0".equals(epc_parent_id))
        {
            params.add(null);
        }
        else
        {
            params.add(epc_parent_id);
        }
        new EbProCategoryDao().insert(params);

    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String epc_id=request.getParameter("epcId");
        String epc_name=request.getParameter("className");
        List<String> params=new ArrayList<String>();
        params.add(epc_name);
        params.add(epc_id);
        new EbProCategoryDao().update(params);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String proId=request.getParameter("proId");
        new EbProCategoryDao().delete(proId);
    }
    public void jump2modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String epc_id=request.getParameter("epc_id");
        String epc_name=request.getParameter("epc_name");
        request.setAttribute("epc_id",epc_id);
        request.setAttribute("epc_name",epc_name);
        request.getRequestDispatcher("/manage/productClass-modify.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if("manageSelect".equals(action))
        {

        }
        else if("manageInsert".equals(action))
        {

        }
        else if("managerUpdate".equals(action))
        {

        }
        else if("managerDelete".equals(action))
        {

        }
        else if("productClass_List".equals(action)){
            allList(request,response);
        }
        else if("jump2modify".equals(action))
        {
            jump2modify(request,response);
        }
        else if("delete".equals(action))
        {
            delete(request,response);
            allList(request,response);
        }
        else
        {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String submit=request.getParameter("submit");
            if("更新".equals(submit))
            {
                update(request,response);
                select(request,response);
                allList(request,response);
            }
            else if("添加".equals(submit))
            {
                insert(request,response);
                select(request,response);
                allList(request,response);
            }
            else {
                select(request, response);
                request.getRequestDispatcher("/comment.do").forward(request, response);
            }
        }
    }
}
