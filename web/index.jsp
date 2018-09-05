<%--
  Created by IntelliJ IDEA.
  User: 13056
  Date: 2018/6/10
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="dao.Articledao" %>
<%@ page import="model.Article" %>
<%@ page import="model.Message" %>
<%@ page import="dao.Messagedao" %>
<%@ page import="model.Other" %>
<%@ page import="dao.Otherdao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>大大Frank</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="css/header.css"/>
    <link rel="stylesheet" type="text/css" href="css/section.css"/>
    <link rel="stylesheet" type="text/css" href="css/footer.css" />
    <link rel="stylesheet" type="text/css" href="css/page.css" />
    <link rel="icon" href="img/logo_fox.png" type="image/x-icon"/>
    <meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
    <base target="_blank" />
</head>
<%
    //    读取文章分页后的个数
    int pagess =(int)Math.ceil((double)(Articledao.getArticle().size())/5);
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



<section class="section">
    <div class="section_body">
        <div class="article">

            <%
                List<Article> articles = Articledao.getArticle();
                if (articles == null) {
                    System.out.println("is null");
                } else {
                    int perPage = 5;
                    int start = (pages-1)*5;
                    for (int i = start;i<start+perPage && i < articles.size();i++) {
            %>
            <div class="article_body">
                <a href="article.jsp?art_id=<%=articles.get(i).getId()%>"><div class="art_body_img" style="background-image: url('<%=articles.get(i).getUrl()%>');"></div></a>
                <div class="art_body_inf">
                    <h3><a href="article.jsp?art_id=<%=articles.get(i).getId()%>" style="color:rgb(57,57,57);font-family: '等线 Light'"><%=articles.get(i).getName()%></a></h3>
                    <p>•&nbsp;&nbsp;<%=articles.get(i).getAuthor()%>&nbsp;&nbsp;&nbsp;&nbsp;•&nbsp;&nbsp;<%=articles.get(i).getTime()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                    <pre><%=articles.get(i).getDisc()%></pre>
                </div>

            </div>
            <%
                    }
                }
            %>


            <%--<div style="width: 100%;height: 50px;background-color: rgba(255,255,255,0.8)">--%>
                <div class="page">
                    <div class="page_num">共<%=pagess%>页</div>

                    <a  class="page_add" target="_parent" id="page_add" href="index.jsp?pages=<%=pages+1%>">+1</a>
                    <a  class="page_cut" target="_parent" id="page_cut" href="index.jsp?pages=<%=pages-1%>">-1</a>
                </div>
            <%--</div>--%>

        </div>

        <div class="other">
            <%--搜索--%>
            <form class="search" id="search_form" action="javascript:" method="post">
                <input type="text" name="name" id="search_input" placeholder="请输入您想要搜索的文章标题关键字" />
            </form>
            <%--介绍--%>
            <div class="about_me">
                <div class="about_me_img"></div>
                <h2>Frank</h2>
                <p>励志成为一名优秀的前端工程师，喜欢code，喜欢设计，喜欢看书，喜欢轮滑，喜欢LOL。</p>
            </div>
            <div class="like_me">
                <%
                    Other other = Otherdao.getLovenum();
                %>
                <p>Do you like me?</p>
                <div id="like_me_heart" class="like_me_heart" onclick="addlove()"></div>
                <p id="like_me_num" name="num" class="like_me_num"><%=other.getLove()%></p>
            </div>
            <script>
                window.onload = function heartchange() {
                    if(love.getItem("num")!=null) {
                        var love_pic = document.getElementById("like_me_heart");
                        love_pic.style.backgroundImage="url(img/heartred.png)";
                    }
                }
                var love_pic = document.getElementById("like_me_heart");
                var love = window.localStorage;
                // var love_num = document.getElementById("like_me_num");
                function addlove () {

                    if(love.getItem("num")==null) {
                        //设置有值
                        love.setItem("num",1)
                        // 设置图片
                        love_pic.style.backgroundImage="url(img/heartred.png)";
                        //设置数值
                        // var love_love_num = parseInt(love_num.innerText) + 1;
                        // love_num.innerText = num;
                        // //数据库
                        // add_love_num=new XMLHttpRequest();
                        // add_love_num.onreadystatechange=function(){
                        //     if(add_love_num.readyState==4&&add_love_num.status==200){
                        //         alert("接受成功");
                        //     }
                        // }
                        // add_love_num.open("POST","Add_Love",true);
                        // add_love_num.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
                        // add_love_num.send("num="+love_love_num);
                        window.location.href = "/Add_Love"
                    }
                    else {
                        alert("知道你喜欢我了~不要再点了");


                    }
                }
            </script>
            <div class="message">
                <%
                    List<Message> messages = Messagedao.getMessageslimit(10);
                    if (messages == null) {
                        System.out.println("is null");
                    } else {
                        for (Message message:messages) {
                %>
                <div class="message_body">
                    <h4><%=message.getAuthor()%></h4>
                    <p>•&nbsp;<%=message.getTime()%></p>
                    <pre><%=message.getContent()%></pre>

                </div>
                <%
                        }
                    }
                %>

                <p>以上为最新留言</p>
            </div>
        </div>
    </div>
</section>
<script>
    var input_search=document.getElementById("search_input");
    var form=document.getElementById("search_form");
    input_search.onkeydown=function search(e) {
        var e = window.event||e;
        var key = e.keyCode||e.which;
        if(key==13){
            if(input_search.value=='')
            {
                alert("搜索内容不能为空");
            }
            else {
                form.action="/Search_article";
            }
        }
    }
</script>



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
    // 控制display
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
    // 控制上升
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
