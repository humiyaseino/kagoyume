<%@page import="kagoyume.SearchDateBeans" 
        import="kagoyume.LinkHelper"
%>
<%-- 
    Document   : item
    Created on : 2017/01/29, 14:46:57
    Author     : mypc
--%>
<%
    HttpSession hs = request.getSession();
    LinkHelper lh = new LinkHelper();
    SearchDateBeans sdb = (SearchDateBeans)hs.getAttribute("sdb");
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>商品詳細ページ</title>
    </head>
    <body>
        <a href="<%=lh.topURL()%>">トップページへ</a>
        <a href="<%=lh.myDateURL()%>">マイプロフィール</a>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <a href="<%=lh.cartURL()%>">カートを見る</a>
        <h1><img src="<%=sdb.getImage()%>/>"</h1>
        <h2>名前</h2>
        <%=sdb.getName()%>
        <h3>詳細</h3>
        <%=sdb.getDescription()%>
        <h4>値段</h4>
        <%=sdb.getPrice()%>
        <form action="Add" method="post">
            <input type="hidden" Name="ac" value="<%=hs.getAttribute("ac")%>">
            <input type="submit" value="カートに入れる">
        </form>
            <form action="Search" method="get">
                <input type="hidden" name="txtkeyword" value="<%=sdb.getKeyword()%>">
                <input type="submit" name="search" value="検索へ戻る">
            </form>    
    </body>
</html>
