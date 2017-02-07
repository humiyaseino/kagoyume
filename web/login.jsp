<%@page import="kagoyume.LinkHelper"
 
%>
<%-- 
    Document   : login
    Created on : 2017/01/29, 15:28:53
    Author     : mypc
--%>
<%
    HttpSession hs = request.getSession();
    LinkHelper lh = new LinkHelper();
    //エラーメッセージ
    String error = (String)request.getAttribute("error");
    if (error == null){
        error = "お名前とパスワードをご記入ください。";
    }
%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログイン</title>
    </head>
    <body>
        <a href="<%=lh.registrationURL()%>">ユーザー新規登録はこちら</a><br>
        <a href="<%=lh.topURL()%>">トップページへ戻る</a>
        <h1>ログイン管理画面</h1><br>
        <p><%=error%></p>
        <form action="loginForm" method="post">
            名前<input type="txt" name="txtName">
            パスワード<input type="password" name="txtPass">
            <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
            <input type="submit" value="ログイン">
        </form><br>   
    </body>
</html>
