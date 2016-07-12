package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbProCategoryDao;
import sdkd.com.ec.model.EbProCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/7.
 */
@WebServlet(name = "EbProCategoryController")
public class EbProCategoryController extends HttpServlet {
    public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EbProCategoryDao proCategoryDao = new EbProCategoryDao();
        List<EbProCategory> list = proCategoryDao.getProCategory();
        request.setAttribute("proCategoryList",list);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    public void allList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EbProCategoryDao proCategoryDao = new EbProCategoryDao();
        List<EbProCategory> list = proCategoryDao.getProCategory();
        request.setAttribute("proCategory-List",list);
        request.getRequestDispatcher("/manage/productClass.jsp").forward(request,response);
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
        else if("productClass-List".equals(action)){
            allList(request,response);
        }
        else
        {
            select(request,response);
            request.getRequestDispatcher("/comment.do").forward(request,response);
        }
    }
}
