<%@page  import="kagoyume.LinkHelper"
         import="kagoyume.UserData"
         import="kagoyume.CartBox"
%>
<%-- 
    Document   : index
    Created on : 2017/01/27, 14:16:02
    Author     : mypc
--%>
<%      HttpSession hs = request.getSession();
        LinkHelper lh = new LinkHelper();
        UserData ud = new UserData();
        CartBox cartBox = new CartBox();
            //カートデータ表示テスト用コード
        if (session.getAttribute("cart") != null){
            cartBox = (CartBox)session.getAttribute("cart");
        }
            //ユーザーデータ表示テスト用コード
        if (hs.getAttribute("ud") != null){
            ud = (UserData)hs.getAttribute("ud");
        }
            //ページ移動のデータ
        
            hs.setAttribute("page", "/index.jsp");
        
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Topページ</title>
    </head>
    <body>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <a href="<%=lh.cartURL()%>"><%=lh.cartSize(cartBox.getCartBox().size())%></a>
        <a href="<%=lh.myDateURL()%>">マイプロフィール</a>
        <h1>ようこそ<%if(ud.getName() != null){out.print(ud.getName()+"さん");}else{out.print("ゲストさん");}%></h1>
        <form action="Search" method="get">
            <input type="txt" name="txtkeyword">
            <input type="submit" value="検索">
        </form>
    </body>
</html>
