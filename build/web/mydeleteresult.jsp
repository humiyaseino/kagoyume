<%@page import="kagoyume.LinkHelper"

    %>
<%-- 
    Document   : mydeleteresult
    Created on : 2017/02/06, 13:29:03
    Author     : mypc
--%>
<%
    LinkHelper lh = new LinkHelper();

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除完了ページ</title>
    </head>
    <body>
        <h1>マジで削除しました</h1>
        <a href="<%=lh.topURL()%>">トップページへ</a>
    </body>
</html>
