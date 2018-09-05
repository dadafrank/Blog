<%@ page import="java.net.URLDecoder" %>
<%@ page import="dao.Otherdao" %>
<%@ page import="model.Other" %>
<%@ page import="model.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.Articledao" %><%--
  Created by IntelliJ IDEA.
  User: 13056
  Date: 2018/6/14
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //检查是否已经登录
    Cookie cookie = null;
    Cookie[] cookies = null;
    // 获取cookies的数据,是一个数组
    cookies = request.getCookies();
    if( cookies != null ){
        for (int i = 0; i < cookies.length; i++){
            cookie = cookies[i];
            if(cookie.getName().equals("user")&&URLDecoder.decode(cookie.getValue(),"utf-8").equals("frank")){
                break;
            }
            if(i==cookies.length-1){
                out.println("<script>alert('请返回登录');window.location.href='login.jsp'</script>");
            }
        }
    }else{
        out.println("<script>alert('请返回登录');window.location.href='login.jsp'</script>");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>大大Frank</title>
    <link rel="icon" href="../img/logo_fox.png" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css"href="../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../css/admin.header.css"/>
    <link rel="stylesheet" type="text/css" href="../css/admin.section.css"/>
    <link rel="stylesheet" type="text/css" href="../css/page.css" />
    <meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<header class="header">
    <div class="header_position">
        <h2>FRANK</h2>
        <nav class="header_nav">
            <div class="icon" id="icon" onclick="iconchange()"></div>
            <ul class="nav_ul1" id="nav_ul1">
                <li class="nav_li1" style="border-radius: 2px;" onclick="changeurl(1)"><a href="article_manage.jsp" style="color: rgb(52,152,219);">&nbsp;文章管理&nbsp;</a></li>
                <li class="nav_li1 nav_li1_s" onclick="changeurl(2)"><a href="article_add.jsp">&nbsp;文章发布&nbsp;</a></li>
                <li class="nav_li1" onclick="changeurl(3)"><a href="article_mess.jsp">&nbsp;留言管理&nbsp;</a></li>
                <li class="nav_li1" onclick="changeurl(4)"><a href="../index.jsp">&nbsp;返回主页&nbsp;</a></li>
            </ul>
        </nav>
    </div>
</header>
<script>
    // 手机点击消失出现
    function iconchange() {
        var nav_ul1 =document.getElementById("nav_ul1")
        if(nav_ul1.style.display=="none"){
            nav_ul1.style.display="block";
        }
        else {
            nav_ul1.style.display="none";
        }
    }
    //跳转链接
    function changeurl(obj){
        var nav_ul2 =document.getElementById("nav_ul2")
        switch(obj){
            case 1:location.href="article_manage.jsp";break;//文章管理
            case 2:location.href="article_add.jsp";break;
            case 3:location.href="article_mess.jsp";break;//留言
            default :location.href="../index.jsp";//主页
        }
    }
</script>
<%
    //    读取文章分页后的个数
    int pagess =(int)Math.ceil((double)(Articledao.getArticle().size())/20);
    if(pagess==1||pagess==0){
%>
<style>
    .page_cut {
        display: none;
    }
    .page_add {
        display: none;
    }
</style>
<%
    }
%>

<%
    int pages;
    if (request.getParameter("pages")==null){
        pages = 1;
%>
<style>
    .page_cut {
        display: none;
    }
</style>
<%
}
else {
    pages = Integer.parseInt(request.getParameter("pages"));
    if(pages <=1 ){
%>
<style>
    .page_cut {
        display: none;
    }
</style>
<%
    }
    if(pages == pagess){
%>
<style>
    .page_add {
        display: none;
    }
</style>
<%
        }
    }
%>

<section class="article_section">
    <div class="article_manage">
        <%
            List<Article> articles = Articledao.getArticle();
            if (articles == null) {
                System.out.println("is null");
            } else {
                int perPage = 20;
                int start = (pages-1)*perPage;
                for (int i = start;i<start+perPage && i < articles.size();i++) {
        %>
        <div class="article_inf">
            <p class="article_name"><%=articles.get(i).getName()%></p>
            <div class="doit">
                <a style="color: blue " href="article_change.jsp?art_id=<%=articles.get(i).getId()%>">修改</a>
                <a style="color: blue " href="/Admin_Article_delete?art_id=<%=articles.get(i).getId()%>">删除</a>
                <a style="color: blue " href="article_message.jsp?art_id=<%=articles.get(i).getId()%>">评论管理</a>
            </div>
        </div>
        <%
                }
            }
        %>
        <div class="page">
            <div class="page_num">共<%=pagess%>页</div>

            <a  class="page_add" id="page_add" href="article_manage.jsp?pages=<%=pages+1%>">+1</a>
            <a  class="page_cut" id="page_cut" href="article_manage.jsp?pages=<%=pages-1%>">-1</a>
        </div>
    </div>
    <div class="pv">
        <%
            Other other = Otherdao.getLovenum();
        %>
        <div class="pv_inf">
            <span class="pv_sp1">喜欢你博客的人有:</span><span class="pv_sp2">&nbsp;<%=other.getLove()%></span>
        </div>
    </div>
</section>

<footer>

</footer>
</body>
</html>