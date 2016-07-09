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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EbProCategoryDao proCategoryDao = new EbProCategoryDao();
        List<EbProCategory> list = proCategoryDao.getProCategory();
        request.setAttribute("proCategoryList",list);

        request.getRequestDispatcher("/comment.do").forward(request,response);
    }
}
