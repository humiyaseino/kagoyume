<%@page import="kagoyume.LinkHelper"

    %>
<%-- 
    Document   : registretion
    Created on : 2017/01/29, 19:48:11
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
        <title>新規登録ページ</title>
    </head>
    <body>
        <a href="<%=lh.topURL()%>">トップページへ</a>
        <h1>ユーザー新規登録ページ</h1>
        <form action="RegistrationConfirm" method="post">
            名前<input type="txt" name="txtName">
            パスワード<input type="txt" name="txtPass">
            メールアドレス<input type="txt" name="txtMail">
            住所<input type="txt" name="txtAddress">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" value="確認画面へ">
        </form>
    </body>
</html>
