<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"
	+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
  </head>
  <body>
  <div style="background-color:#EEEEEE;"><span>bamboo-activiti</span></div>
  <div style="margin-top:20px;">
   <a href="user/test">Spring-MVC 跳转</a><br/>
     <a href="user/create">activiti-modele-index</a><br/>
  </div>
  <script>
    console.log('<%= path %>');
    console.log('<%= basePath %>');
  </script>
  </body>
</html>
