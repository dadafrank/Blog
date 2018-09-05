package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;

@WebServlet(name = "Adminlogin",urlPatterns = {"/Adminlogin"})
public class Adminlogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf8");

        String login_name = request.getParameter("name");
        String login_pass = request.getParameter("password");

        if(login_name.equals("frank") && login_pass.equals("1314520ccc")) {
            Cookie usercookie = new Cookie("user","frank");
//            Cookie usercookie1 = new Cookie("user1","frank");
//            Cookie usercookie2 = new Cookie("user","frank1");
//            Cookie usercookie3 = new Cookie("us","frk");
//            response.addCookie(usercookie3);
//            response.addCookie(usercookie2);
//            response.addCookie(usercookie1);
            response.addCookie(usercookie);
            response.sendRedirect("/admin/article_manage.jsp");

        }
        else {
            RequestDispatcher died = getServletContext().getRequestDispatcher("/admin/login.jsp");
            died.include(request,response);

            PrintWriter alert = response.getWriter();
            alert.print("<script>alert('密码或账号错误')</script>");
            alert.close();
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
