<%@page import="kagoyume.LinkHelper"
        import="kagoyume.UserData"
%>
<%-- 
    Document   : myupdateresult
    Created on : 2017/02/06, 11:34:54
    Author     : mypc
--%>
<%
    LinkHelper lh = new LinkHelper();
    UserData ud = (UserData)session.getAttribute("ud");
    HttpSession hs = request.getSession();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>更新完了ページ</title>
    </head>
    <body>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <a href="<%=lh.cartURL()%>">カートの中身</a>
        <a href="<%=lh.myDateURL()%>">マイプロフィール</a>
        <h1>更新しました</h1>
        <h2>更新内容</h2>
        お名前:<%=ud.getName()%><br>
        パスワード:<%=ud.getPass()%><br>
        メールアドレス:<%=ud.getMail()%><br>
        ご住所:<%=ud.getAddress()%><br>
    </body>
</html>
