package servlet;

import dao.Articledao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Search_article",urlPatterns = {"/Search_article"})
public class Search_article extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf8");

        String name = request.getParameter("name");

        PrintWriter pw =response.getWriter();
        pw.println("<script>window.location.href='search_page.jsp?name="+name+"'</script>");
    }





    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
