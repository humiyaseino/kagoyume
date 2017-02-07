<%@page import="kagoyume.LinkHelper"
        
%>
<%-- 
    Document   : buycomplete
    Created on : 2017/02/05, 14:32:35
    Author     : mypc
--%>
<%
    LinkHelper lh = new LinkHelper();
    HttpSession hs = request.getSession();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入完了ページ</title>
    </head>
    <body>
        <h1>購入完了しました</h1>
        <a href="<%=lh.topURL()%>">トップページへ</a>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <a href="<%=lh.myDateURL()%>">マイプロフィール</a>
    </body>
</html>
