<%@page import="java.util.ArrayList"
        import="kagoyume.LinkHelper"
        import="kagoyume.UserData"
        
%>
<%-- 
    Document   : registrationConfirm
    Created on : 2017/01/30, 10:37:10
    Author     : mypc
--%>
<%
    LinkHelper lh = new LinkHelper();
    HttpSession hs = request.getSession();
    UserData ud = (UserData)session.getAttribute("udres");
    ArrayList<String> chkList = ud.nullChk();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録確認画面</title>
    </head>
    <body>
        <a href="<%=lh.topURL()%>">トップページへ</a>
        <%if(chkList.size()==0){%>
            <H1>登録確認</H1>
            名前:<%=ud.getName()%><br>
            パスワード:<%=ud.getPass()%><br>
            メールアドレス:<%=ud.getMail()%><br>
            住所:<%=ud.getAddress()%><br>
            上記の内容で登録します。よろしいですか？
            <form action="RegistrationComplete" method="post">
                <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
                <input type="submit" value="登録">
            </form>
        <%}else{%>
            <h1>入力が不完全です</h1>
            <%=chkList%>
        <%}%>
    </body>
</html>
