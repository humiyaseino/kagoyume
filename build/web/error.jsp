<%@page import="kagoyume.LinkHelper" %>
<%-- 
    Document   : error
    Created on : 2017/01/30, 13:43:08
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
        <title>エラーページ</title>
    </head>
    <body>
        <a href="<%=lh.topURL()%>">Topページへ</a>
        <a href="<%=lh.loginURL()%>"><%if(hs.getAttribute("ud")==null){%>ログイン<%}else{%>ログアウト<%}%></a>
        <a href="<%=lh.cartURL()%>">カートの中身</a>
        <a href="<%=lh.myDateURL()%>">マイプロフィール</a>
        <h1>エラーが発生しました。以下の項目を確認してください</h1><br>
        <%=request.getAttribute("error")%>
    </body>
</html>
