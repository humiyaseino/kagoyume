<%@page import="kagoyume.UserData"
        import="kagoyume.LinkHelper"

%>
<%-- 
    Document   : registrationComplete
    Created on : 2017/01/30, 13:27:26
    Author     : mypc
--%>
<%  
    LinkHelper lh = new LinkHelper();
    UserData ud = (UserData)request.getAttribute("ud");
    HttpSession hs =request.getSession();
%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録完了画面</title>
    </head>
    <body>
        <a href="<%=lh.topURL()%>">トップページへ</a>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <a href="<%=lh.cartURL()%>">カートの中身</a>
        <a href="<%=lh.myDateURL()%>">マイプロフィール</a>
        <h1>以下の内容で登録しました</h1>
        名前:<%=ud.getName()%><br>
        パスワード:<%=ud.getPass()%><br>
        メールアドレス:<%=ud.getMail()%><br>
        住所:<%=ud.getAddress()%><br>
    </body>
</html>
