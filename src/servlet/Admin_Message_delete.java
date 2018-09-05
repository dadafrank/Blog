package servlet;

import dao.Articledao;
import dao.Messagedao;
import model.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Admin_Message_delete",urlPatterns = {"/Admin_Message_delete"})
public class Admin_Message_delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        int id =Integer.parseInt(request.getParameter("id")) ;
        if (Messagedao.delete(id)){
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('删除成功');window.location.href='/admin/article_mess.jsp'</script>");
            pw.close();
        }
        else {
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('删除失败');window.location.href='/admin/article_mess.jsp'</script>");
            pw.close();
        }
    }
}
