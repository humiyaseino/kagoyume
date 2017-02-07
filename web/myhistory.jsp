<%@page import="kagoyume.LinkHelper"
        import="kagoyume.BuyLogBeans"
        import="kagoyume.UserData"
%>
<%-- 
    Document   : myhistory
    Created on : 2017/02/05, 18:18:58
    Author     : mypc
--%>
<%
    LinkHelper lh = new LinkHelper();
    BuyLogBeans blb = (BuyLogBeans)request.getAttribute("blb");
    UserData ud = (UserData)session.getAttribute("ud");
    HttpSession hs = request.getSession();

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入履歴ページ</title>
    </head>
    <body>
        <a href="<%=lh.topURL()%>">トップページ</a>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <a href="<%=lh.cartURL()%>">カートの中身</a>
        <a href="<%=lh.myDateURL()%>">マイプロフィールへ</a>
        <h1>購入履歴</h1>
        <p>総購入金額:<%=ud.getTotal()%></p>
        <%for(int i = 0;i<blb.getBuyIDList().size();i++){%>
        <table border="1">
            <tr>
                <th>商品コード</th>
                <th>発送方法</th>
                <th>購入日時</th>
            </tr>
            <tr>
                <td><%=blb.getItemCodeList(i)%></td>
                <td><%=lh.typechk(blb.getTypeList(i))%></td>
                <td><%=blb.getBuyDateList(i)%></td>
            </tr>
        </table>
        <%}%>
    </body>
</html>
