<%--
  Created by IntelliJ IDEA.
  User: wei
  Date: 2015-7-22
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <% Exception e = (Exception)request.getAttribute("ex"); %>
  <H2>未知错误: <%= e.getClass().getSimpleName()%></H2>
  <hr />
  <P>错误描述：</P>
  <%= e.getMessage()%>
  <P>错误信息：</P>
  <% e.printStackTrace(new java.io.PrintWriter(out)); %>
</body>
</html>
