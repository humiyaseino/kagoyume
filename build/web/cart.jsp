<%@page  import="kagoyume.LinkHelper"
         import="kagoyume.CartBox"
    
%>
<%-- 
    Document   : cart
    Created on : 2017/02/03, 9:41:53
    Author     : mypc
--%>
<%
    LinkHelper lh = new LinkHelper();
    HttpSession hs = request.getSession();
    
   
    CartBox cb = (CartBox)hs.getAttribute("cart");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カート確認ページ</title>
    </head>
    <body>
        <a href="<%=lh.topURL()%>">トップページへ</a>
        <a href="<%=lh.myDateURL()%>">マイプロフィール</a>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <h1>カートの中身</h1>
        <%if (cb != null){%>
            <%for (int i = 0; i< cb.getCartBox().size();i++){%>
            <table border="1">
                <tr>
                    <th>画像</th>
                    <th>名前</th>
                    <th>値段</th>
                    <th>カート</th>
                </tr>
                <tr>
                    <td><a href="Item?cartID=<%=i%>"><img src="<%=cb.getCartBoxSdb(i).getImage()%>"</a></td>
                    <td><a href="Item?cartID=<%=i%>"><%=cb.getCartBoxSdb(i).getName()%></a></td>
                    <td><%=cb.getCartBoxSdb(i).getPrice()%></td>
                    <td>
                        <form action="Cartdelete" method="post">
                            <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
                            <input type="hidden" name="cartID" value="<%=i%>">
                            <input type="submit" name="Cartdelete" value="削除する">
                        </form>
                    </td>
                </tr>
            </table>
            <%}%>
            <form action="Buyconfirm" method="post">
                <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
                <input type="submit" name="Buyconfirm" value="購入確認画面へ">
            </form>
        <%}else{
            out.println("カートには商品がありません。");
        }%>
    </body>
</html>
