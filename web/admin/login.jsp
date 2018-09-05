<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: 13056
  Date: 2018/6/10
  Time: 22:41
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
                out.println("<script>alert('欢迎回来');window.location.href='article_manage.jsp'</script>");
            }
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>大大Frank</title>
    <link rel="icon" href="../img/logo_fox.png" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="../css/reset.css" />
    <link rel="stylesheet" type="text/css" href="../css/logn_in.css" />
    <meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="logn">
    <form action="/Adminlogin" method="post" class="form">
        <input type="text" placeholder="用户名" class="logn_name" name="name" />
        <br />
        <input type="password" placeholder="密码" class="logn_pass" name="password" />
        <br />
        <input type="submit" value="　登录" class="logn_sub" />
    </form>
</div>
</body>
</html>