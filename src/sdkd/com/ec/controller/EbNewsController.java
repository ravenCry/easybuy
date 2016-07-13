package sdkd.com.ec.controller;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.dao.impl.EbNewsDao;
import sdkd.com.ec.model.EbNews;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDUST-132 on 2016/7/6.
 */
public class EbNewsController extends HttpServlet {
    EbNewsDao newsDao = new EbNewsDao();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EbNews> list = newsDao.getNews();
        request.setAttribute("newsList", list);

        //跳转
        request.getRequestDispatcher("/product.do").forward(request, response);
    }

    public void allList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EbNewsDao newsDao = new EbNewsDao();
        List<EbNews> list = newsDao.getNews();
        request.setAttribute("news_List", list);
        request.getRequestDispatcher("/manage/news.jsp").forward(request, response);
    }

    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramId = request.getParameter("id");
        int id = 0;
        if (paramId != null && !paramId.equals("")) {
            id = Integer.parseInt(paramId);
        }
        EbNews news = newsDao.getNewsById(id);
        request.setAttribute("news", news);
        request.getRequestDispatcher("/news-view.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        List<String> params = new ArrayList<String>();
        params.add(title);
        params.add(content);
        new EbNewsDao().insert(params);
        EbNews currentNew = new EbNews();
        currentNew.setEnTitle(title);
        currentNew.setEnContent(content);
        request.setAttribute("currentNew", currentNew);
        request.getRequestDispatcher("/manage/manage-result.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String en_title = request.getParameter("title");
        String en_content = request.getParameter("content");
        List<String> params = new ArrayList<String>();
        params.add(en_title);
        params.add(en_content);
        new EbNewsDao().update(params);
    }

    public void jump2modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String en_id=request.getParameter("en_id");
        String en_title=request.getParameter("en_title");
          request.setAttribute("en_id",en_id);
        request.setAttribute("en_title",en_title);

        request.getRequestDispatcher("/manage/news-modify.jsp").forward(request,response);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String newsId=request.getParameter("newsId");
        new EbNewsDao().delete(newsId);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            list(request, response);
        } else if ("detail".equals(action)) {
            detail(request, response);
        } else if ("newsList".equals(action)) {
            allList(request, response);
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
        else {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String submit = request.getParameter("submit");
            if ("更新".equals(submit)) {
                update(request, response);
                allList(request, response);
            } else if ("添加".equals(submit)) {
                insert(request, response);
                allList(request, response);
            }
            else
                list(request,response);
        }

    }
}
