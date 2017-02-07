<%@page import="kagoyume.LinkHelper"
        import="kagoyume.UserData"
    %>
<%-- 
    Document   : mydelete
    Created on : 2017/02/06, 12:00:50
    Author     : mypc
--%>
<%
        HttpSession hs = request.getSession();
        LinkHelper lh = new LinkHelper();
        UserData ud = (UserData)hs.getAttribute("ud");
%>        
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除確認ページ</title>
    </head>
    <body>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <a href="<%=lh.cartURL()%>">カートの中身</a>
        <a href="<%=lh.myDateURL()%>">マイプロフィールへ</a>
        <h1>削除確認</h1>
        <p>マジで削除しますか?</p>
        お名前:<%=ud.getName()%><br>
        メールアドレス:<%=ud.getMail()%><br>
        ご住所:<%=ud.getAddress()%><br>
        <form action="Mydeleteresult" method="post">
            <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
            <input type="submit" name="mydeleteresult" value="はい">
        </form>
        <form action="index.jsp" method="post">
            <input type="submit" name="mydeleteresult" value="いいえ">
        </form>
    </body>
</html>
