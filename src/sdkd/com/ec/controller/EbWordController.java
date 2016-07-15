package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbWordDao;
import sdkd.com.ec.model.EbWord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/14.
 */
@WebServlet(name = "EbWordController")
public class EbWordController extends HttpServlet {
    public void allList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<EbWord> ebWords=new EbWordDao().selectAll();
        request.setAttribute("ebWordList", ebWords);
    }
    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String ew_name=request.getParameter("guestName");
        String ew_content=request.getParameter("guestContent");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ew_creat_time=df.format(new Date());
        List<String> params=new ArrayList<String>();
        params.add(ew_name);
        params.add(ew_content);
        params.add(ew_creat_time);
        new EbWordDao().insert(params);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String ew_id=request.getParameter("ew_id");
        new EbWordDao().delete(ew_id);
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String ew_id=request.getParameter("ewId");
        String ew_reply=request.getParameter("replyContent");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ew_reply_time=df.format(new Date());
        List<String> params=new ArrayList<String>();
        params.add(ew_reply);
        params.add(ew_reply_time);
        params.add(ew_id);
        new EbWordDao().update(params);
    }
    public void jump2Modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String ew_id=request.getParameter("ew_id");
        String ew_name=request.getParameter("ew_name");
        String ew_content=request.getParameter("ew_content");
        String ew_reply=request.getParameter("ew_reply");
        request.setAttribute("ew_id",ew_id);
        request.setAttribute("ew_name",ew_name);
        request.setAttribute("ew_content",ew_content);
        request.setAttribute("ew_reply",ew_reply);
        request.getRequestDispatcher("/manage/guestbook-modify.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("wordList".equals(action))
        {
            allList(request,response);
            request.getRequestDispatcher("/manage/guestbook.jsp").forward(request, response);
        }
        else if("delete".equals(action))
        {
            delete(request,response);
            allList(request,response);
            request.getRequestDispatcher("/manage/guestbook.jsp").forward(request, response);
        }
        else if("jump2Modify".equals(action))
        {
            jump2Modify(request,response);
        }
        else if("wordListF".equals(action))
        {
            allList(request,response);
            request.getRequestDispatcher("/guestbook.jsp").forward(request, response);
        }
        else
        {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String submit = request.getParameter("submit");
            if ("更新".equals(submit)) {
                update(request,response);
                allList(request,response);
                request.getRequestDispatcher("/manage/guestbook.jsp").forward(request, response);
            } else if ("提交留言".equals(submit)) {
                insert(request,response);
                allList(request,response);
                request.getRequestDispatcher("/guestbook.jsp").forward(request, response);
            }
        }
    }
}
