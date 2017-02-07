<%@page import="kagoyume.LinkHelper"
        import="kagoyume.UserData"
    %>
<%-- 
    Document   : mydata
    Created on : 2017/02/05, 14:47:02
    Author     : mypc
--%>
<%
    LinkHelper lh = new LinkHelper();
    HttpSession hs = request.getSession();
    UserData ud = (UserData)session.getAttribute("ud");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>マイプロフィール</title>
    </head>
    <body>
        <a href="<%=lh.topURL()%>">トップページへ</a>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <a href="<%=lh.cartURL()%>">カートの中身</a>
        <h1>あなたのプロフィールです</h1>
        お名前:<%=ud.getName()%><br>
        パスワード:<%=ud.getPass()%><br>
        メールアドレス:<%=ud.getMail()%><br>
        ご住所:<%=ud.getAddress()%><br>
        総購入金額:<%=ud.getTotal()%><br>
        登録日時:<%=ud.getNewData()%><br>
        <form action="Myhistory" method="post">
            <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
            <input type="submit" name="myhistory" value="購入履歴">
        </form>
        <form action="MyUpdate" method="post">
            <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
            <input type="submit" name="myUpdate" value="ユーザーデータ更新">
        </form>
            <form action="Mydelete" method="post">
            <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
            <input type="submit" name="mydelete" value="ユーザーデータ削除">
        </form>  
    </body>
</html>
