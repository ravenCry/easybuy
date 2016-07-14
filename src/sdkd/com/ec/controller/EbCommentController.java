package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbCommentDao;
import sdkd.com.ec.model.EbComment;

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
@WebServlet(name = "EbCommentController")
public class EbCommentController extends HttpServlet {
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        List<EbComment> list=new EbCommentDao().getComment("select * from ebcomment limit 0,7",null);
        session.setAttribute("commentList",list);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramId = request.getParameter("id");
        List<String> params=new ArrayList<String>();
        params.add(paramId);
        EbComment comment = new EbCommentDao().getComment("select * from ebcomment where ec_id=?",params).get(0);
        request.setAttribute("comment", comment);
        request.getRequestDispatcher("/comment-view.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("list".equals(action))
        {
            list(request,response);
        }
        else if("detail".equals(action))
        {
            detail(request,response);
        }
        else
        {
            list(request,response);
        }
    }
}
