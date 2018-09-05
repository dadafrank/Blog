package servlet;

import dao.Otherdao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Add_Love",urlPatterns = {"/Add_Love"})
public class Add_Love extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf8");

//        String num = request.getParameter("num");
//        System.out.println(num);
        int num = Otherdao.getLovenum().getLove()+1;
        Otherdao.updatelove(num);
        PrintWriter pw =response.getWriter();
        pw.println("<script>alert('点赞成功，谢谢支持');window.location.href='index.jsp'</script>");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
