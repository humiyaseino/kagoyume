<%@page import="kagoyume.LinkHelper"
        import="kagoyume.UserData"
%>
<%-- 
    Document   : myupdate
    Created on : 2017/02/06, 6:06:57
    Author     : mypc
--%>
<%
    UserData ud = (UserData)session.getAttribute("ud");
    LinkHelper lh = new LinkHelper();
    HttpSession hs = request.getSession();
    //エラーメッセージ
    String error = (String)request.getAttribute("error");
    if (error == null){
        error = "必要な項目にご記入ください";
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="<%=lh.topURL()%>">トップページへ</a>
        <h1>ユーザー情報更新ページ</h1>
        <p><%=error%></p>
        <form action="Myupdateresult" method="post">
            名前<input type="txt" name="txtName" value="<%=ud.getName()%>">
            パスワード<input type="txt" name="txtPass" value="<%=ud.getPass()%>">
            メールアドレス<input type="txt" name="txtMail" value="<%=ud.getMail()%>">
            住所<input type="txt" name="txtAddress" value="<%=ud.getAddress()%>">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" value="確認画面へ">
        </form>
    </body>
</html>
