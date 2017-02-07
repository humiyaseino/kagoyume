<%@page    import="kagoyume.LinkHelper"
           import="kagoyume.CartBox"
%>        
<%-- 
    Document   : buyconfirm
    Created on : 2017/02/03, 15:04:26
    Author     : mypc
--%>
<%
    HttpSession hs = request.getSession();
    CartBox cb = (CartBox)hs.getAttribute("cart");
    LinkHelper lh = new LinkHelper();
%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入確認ページ</title>
    </head>
    <body>
        <a href="<%=lh.topURL()%>">トップページへ</a>
        <a href="<%=lh.myDateURL()%>">マイプロフィール</a>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <h1>商品リスト</h1>
        <%for (int i = 0;i < cb.getCartBox().size();i++){%>
        名前:<%=cb.getCartBoxSdb(i).getName()%><br>
        値段:<%=cb.getCartBoxSdb(i).getPrice()%><br>
        <%}%>合計金額
        <%=cb.getTotal()%>
        <form action="Buycomplete" method="post">
            <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
            <h2>発送方法</h2>
            <% for(int i = 0;i <cb.getCartBox().size();i++){%>
            <%=cb.getCartBoxSdb(i).getName()%><br>
            普通に配達<input type="radio" name="<%="Shipping"+i%>"value="1" checked><br>
            急いで配達<input type="radio" name="<%="Shipping"+i%>"value="2"><br>
            <%}%>
            <input type="submit" value="購入する">
        </form>
    </body>
</html>
