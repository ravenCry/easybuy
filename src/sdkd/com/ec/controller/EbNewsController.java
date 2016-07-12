package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbNewsDao;
import sdkd.com.ec.model.EbNews;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by SDUST-132 on 2016/7/6.
 */
public class EbNewsController extends HttpServlet {
    EbNewsDao newsDao = new EbNewsDao();
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<EbNews> list = newsDao.getNews();
        request.setAttribute("newsList",list);

        //跳转
        request.getRequestDispatcher("/product.do").forward(request,response);
    }
    public void allList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EbNewsDao newsDao = new EbNewsDao();
        List<EbNews> list = newsDao.getNews();
        request.setAttribute("news-List",list);
        request.getRequestDispatcher("/manage/news.jsp").forward(request,response);
    }
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String paramId=request.getParameter("id");
        int id=0;
        if(paramId!=null&&!paramId.equals(""))
        {
            id=Integer.parseInt(paramId);
        }
        EbNews news=newsDao.getNewsById(id);
        request.setAttribute("news",news);
        request.getRequestDispatcher("/news-view.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        String action=request.getParameter("action");

        if("list".equals(action))
        {
            list(request,response);
        }
        else if("detail".equals(action))
        {
            detail(request,response);
        }
        else if("newsList".equals(action))
        {
            allList(request,response);
        }
        else
        {
            list(request,response);
        }

    }
}
