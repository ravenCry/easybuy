package sdkd.com.ec.controller;

import sdkd.com.ec.dao.impl.EbUserDao;
import sdkd.com.ec.model.EbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcx on 2016/7/11.
 */
@WebServlet(name = "EbUserController" )
public class EbUserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EbUserDao userDao = new EbUserDao();
        List<EbUser> list = userDao.getUser();
        request.setAttribute("userList",list);

        //跳转
        request.getRequestDispatcher("/user.do").forward(request,response);
    }
    public void allList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EbUserDao userDao = new EbUserDao();
        List<EbUser> list = userDao.getUser();
        request.setAttribute("user_List",list);
        request.getRequestDispatcher("/manage/user.jsp").forward(request,response);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userId=request.getParameter("userId");
        new EbUserDao().delete(userId);
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
        String eb_user_id=request.getParameter("userName");
        String eb_user_name=request.getParameter("name");
        String eu_password=request.getParameter("passWord");
        String eu_sex=request.getParameter("sex");
        if("1".equals(eu_sex))
        {
            eu_sex="男";
        }
        else if("0".equals(eu_sex))
        {
            eu_sex="女";
        }
        String eu_birthday;
        eu_birthday=request.getParameter("birthyear")+"-"+request.getParameter("birthmonth")
                +"-"+request.getParameter("birthday");
        String eu_mobile=request.getParameter("mobile");
        String eb_address=request.getParameter("address");
        List<String> params=new ArrayList<String>();
        params.add(eb_user_name);
        params.add(eu_password);
        params.add(eu_sex);
        params.add(eu_birthday);
        params.add(eu_mobile);
        params.add(eb_address);
        params.add(eb_user_id);
        new EbUserDao().update(params);
    }
    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String eb_user_name=request.getParameter("name");
        String eu_password=request.getParameter("passWord");
        String eu_sex=request.getParameter("sex");
        if("1".equals(eu_sex))
        {
            eu_sex="男";
        }
        else if("0".equals(eu_sex))
        {
            eu_sex="女";
        }
        String eu_birthday;
        eu_birthday=request.getParameter("birthyear")+"-"+request.getParameter("birthmonth")
                +"-"+request.getParameter("birthday");
        String eu_mobile=request.getParameter("mobile");
        String eb_address=request.getParameter("address");
        List<String> params=new ArrayList<String>();
        params.add(eb_user_name);
        params.add(eu_password);
        params.add(eu_sex);
        params.add(eu_birthday);
        params.add(eu_mobile);
        params.add(eb_address);
        new EbUserDao().insert(params);
        request.getRequestDispatcher("/manage/manage-result.jsp").forward(request,response);
    }
    public void jump2modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String eb_user_id=request.getParameter("eb_user_id");
        String eb_user_name=request.getParameter("eb_user_name");
        String eu_password=request.getParameter("eu_password");
        String eu_mobile=request.getParameter("eu_mobile");
        String eb_address=request.getParameter("eb_address");
        request.setAttribute("eb_user_id",eb_user_id);
        request.setAttribute("eb_user_name",eb_user_name);
        request.setAttribute("eu_password",eu_password);
        request.setAttribute("eu_mobile",eu_mobile);
        request.setAttribute("eb_address",eb_address);
        request.getRequestDispatcher("/manage/user-modify.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");

        if("list".equals(action))
        {
            list(request,response);
        }
        else if("detail".equals(action))
        {

        }
        else if("userList".equals(action))
        {
           allList(request,response);
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
