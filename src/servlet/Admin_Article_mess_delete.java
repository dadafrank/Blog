package servlet;

import dao.Art_messdao;
import model.Art_mess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Admin_Article_mess_delete",urlPatterns = {"/Admin_Article_mess_delete"})
public class Admin_Article_mess_delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        int art_id =Integer.parseInt(request.getParameter("art_id")) ;
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(art_id);
        if (Art_messdao.delete(art_id,id)){
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('删除成功');window.location.href='/admin/article_message.jsp?art_id="+art_id+"'</script>");
            System.out.println(art_id);
            pw.close();
        }
        else {
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('删除失败');window.location.href='/admin/article_message.jsp?art_id="+art_id+"'</script>");
            pw.close();
        }
    }
}
