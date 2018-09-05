<%@ page import="model.Article" %>
<%@ page import="dao.Articledao" %>
<%@ page import="model.Art_mess" %>
<%@ page import="dao.Art_messdao" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13056
  Date: 2018/6/10
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int art_id = Integer.parseInt(request.getParameter("art_id"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>大大Frank</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="css/header.css"/>
    <link rel="stylesheet" type="text/css" href="css/footer.css" />
    <link rel="stylesheet" type="text/css" href="css/article.css"/>

    <link rel="icon" href="img/logo_fox.png" type="image/x-icon"/>
    <meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<header class="header">
    <div class="header_position">
        <div class="header_h1">
            <h1>FRANK</h1>
            <p>人总要不断的进步嘛~&nbsp;&nbsp;&nbsp;对不对？</p>
        </div>
        <nav class="header_nav">
            <div class="icon" id="icon" onclick="iconchange()"></div>

            <ul class="nav_ul1" id="nav_ul1">
                <li class="nav_li1" style="border-radius: 2px;" onclick="changeurl(1)"><a style="color: skyblue;" id="zhuye" href="javascript:">首页</a></li>
                <li class="nav_li1 nav_li1_s" onclick="changeurl(2)"><a>项目</a>
                    <ul class="nav_ul2" id="nav_ul2" >
                        <li class="nav_li2" onclick="changeurl(6)"><a href="javascript:">百度</a></li>
                        <li class="nav_li2" onclick="changeurl(7)"><a href="javascript:">博客</a></li>
                        <li class="nav_li2" onclick="changeurl(8)"><a href="javascript:">音乐播放器</a></li>
                    </ul>
                </li>
                <li class="nav_li1" onclick="changeurl(3)"><a href="javascript:">留言</a></li>
                <li class="nav_li1" onclick="changeurl(4)"><a href="javascript:">关于</a></li>
                <li class="nav_li1" onclick="changeurl(5)" style="border-radius: 2px;"><a href="javascript:">帮助</a></li>
            </ul>
        </nav>
    </div>
</header>
<script>
    // 手机点击消失出现
    function iconchange() {
        var nav_ul1 =document.getElementById("nav_ul1")
        var nav_ul2 =document.getElementById("nav_ul2")
        if(nav_ul1.style.display=="none"){
            nav_ul1.style.display="block";
        }
        else {
            nav_ul1.style.display="none";
            nav_ul2.style.display="none";
        }
    }
    //跳转链接
    function changeurl(obj){
        var nav_ul2 =document.getElementById("nav_ul2")
        switch(obj){
            case 1:window.open("index.jsp");break;//首页
            case 2:if(nav_ul2.style.display=="none"){
                nav_ul2.style.display="block";
            }
            else {
                nav_ul2.style.display="none";
            };break;//项目
            case 3:window.open("blog_mess.jsp");break;//留言
            case 4:location.href="#";break;//关于
            case 5:location.href="#";break;//帮助
            case 6:window.open("http://www.baidu.com");break;//百度
            case 7:window.open("http://www.dadafrank.top");break;//博客
            default :window.open("http://dadafrank.top/Project/music/index.html")//音乐播放器
        }
    }
</script>

<%
    Article article = Articledao.getArticle(art_id);
%>
<div class="page_artilce">
    <h2><%=article.getName()%></h2>
    <p>•&nbsp;&nbsp;<%=article.getAuthor()%>&nbsp;&nbsp;&nbsp;&nbsp;•&nbsp;&nbsp;<%=article.getTime()%>&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <pre><%=article.getContent()%></pre>
</div>

<div class="read_mess">
    <%
        List<Art_mess> art_messs = Art_messdao.getArt_mess(art_id);
        if(art_messs == null){
            System.out.println("is null");
        }
        else {
            for (Art_mess art_mess:art_messs) {
    %>
    <div class="message_body">
        <h4><%=art_mess.getAuthor()%></h4>
        <p>•&nbsp;<%=art_mess.getTime()%></p>
        <pre><%=art_mess.getContent()%></pre>
    </div>
    <%
            }
        }
    %>
</div>


<div class="white_mess">
    <form action="/WriteArt_mess" method="post">
        <input type="hidden" name="art_id" value="<%=art_id%>">
        <textarea style="resize: none" placeholder="评论内容" class="mess_content" name="mess_content"></textarea>
        <br />
        <input type="text" placeholder="昵称" class="mess_name" name="mess_name" />
        <br class="to_phone" />
        <input type="email" placeholder="邮箱" class="mess_email" name="mess_email" />
        <br class="to_phone" />
        <input type="submit" value="提交评论" class="mess_sub" />
    </form>
</div>



<footer class="blog_foot">
    <p class="blog_foot_body">
        <a href="./admin/login.jsp" target="_blank">管理员</a>|
        <a href="https://www.uisdc.com/">优设</a>|
        <a href="http://www.ui.cn/">UI中国</a>|
        <a href="https://diygod.me/">DIYgod</a>
    </p>
    <p class="geyan">记住，生活再艰辛也要微笑面对，笑着面对总比苦着脸好看多了。</p>
    <p class="designer">&copy;designed by Frank</p>
</footer>

<div class="to_top" id="to_top" onclick="tohead()">
    TOP
</div>
<script>
    document.onscroll= function totop() {
        var to_top=document.getElementById("to_top");
        var height=parseInt(document.documentElement.scrollTop) ;
        if(height>0)
        {
            to_top.style.display="block";
        }
        else{
            to_top.style.display="none";
        }
    }

    function tohead(){
        var time=setInterval(function() {
            var height=parseInt(document.documentElement.scrollTop) ;
            if(height>0){
                document.documentElement.scrollTop=parseInt(height-30);
            }
            else {
                document.documentElement.scrollTop=0;
                clearInterval(time);
            }
        },1)

    }
</script>
</body>
</html>