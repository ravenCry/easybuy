package sdkd.com.ec.controller;

import sdkd.com.ec.dao.BaseDao;
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
 * Created by HellCrow on 2016/7/7.
 */
@WebServlet(name = "RegisterController")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username=request.getParameter("userName");
        String password=request.getParameter("passWord");
        String rePassword=request.getParameter("rePassWord");
        String msg;
        if(password.equals(rePassword))
        {
            List<String> params=new ArrayList<String>();
            params.add(username);
            params.add(password);
            try
            {
                new BaseDao().exeucteModify("insert into ebuser(eb_user_name,eu_password) values(?,?)",params);
                EbUser currentUser=new EbUser();
                currentUser.setName(username);
                currentUser.setPassword(password);
                request.setAttribute("currentUser",currentUser);
                msg="成功";
            }
            catch (Exception e)
            {
                msg="该用户名已被占用";
            }
        }
        else
        {
            msg="密码不一致";
        }
        //System.out.println(username+" "+password+" "+rePassword);
        request.setAttribute("msg",msg);
        request.getRequestDispatcher("/reg-result.jsp").forward(request,response);
    }
}
