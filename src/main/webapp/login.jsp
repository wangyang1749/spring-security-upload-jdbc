<%--
  Created by IntelliJ IDEA.
  User: wy
  Date: 2019/11/30
  Time: 下午6:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Spring Security jsp自定义登录页面</h2>
<p>
    .formLogin()
    .loginPage("/login.html")
    .loginProcessingUrl("/login")
    .usernameParameter("username")
    .passwordParameter("password")
</p>
<p><a href="test/01">测试是否登录</a> </p>
<form action="login" method="post">
    <input type="text" name="username" value="ls">
    <input type="text" name="password" value="123456">
    <input name="remember-me" type="checkbox" id="remember-me" value="true">
    <input type="submit" value="登录" >
</form>
</body>
</html>
