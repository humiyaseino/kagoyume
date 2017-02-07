<%@page  import="kagoyume.SearchDateBeans"
         import="kagoyume.LinkHelper"
%>
<%-- 
    Document   : add
    Created on : 2017/01/31, 13:00:12
    Author     : mypc
--%>
<%
    LinkHelper lh = new LinkHelper();
    SearchDateBeans sdb = (SearchDateBeans)request.getAttribute("addCart");
    HttpSession hs =request.getSession();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カート追加確認ページ</title>
    </head>
    <body>
        <a href="<%=lh.topURL()%>">トップページへ</a>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <a href="<%=lh.cartURL()%>">カートを見る</a>
        <a href="<%=lh.myDateURL()%>">マイプロフィール</a>
        <h1>以下の商品を追加しました</h1>
        名前:<%=sdb.getName()%><br>
            
    </body>
</html>
