<%@page import="kagoyume.SearchDateBox"
        import="kagoyume.LinkHelper"

%>
<%-- 
    Document   : search
    Created on : 2017/01/29, 8:42:31
    Author     : mypc
--%>
<%
    LinkHelper lh = new LinkHelper();
    SearchDateBox sdbBox = (SearchDateBox) session.getAttribute("sdbBox");
    HttpSession hs = request.getSession();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>検索結果画面</title>
    </head>
    <body>
        <h1>検索結果</h1>
        <a href="<%=lh.topURL()%>">トップページへ</a>
        <a href="<%=lh.myDateURL()%>">マイプロフィール</a>
        <a href="<%=lh.loginURL()%>"><%=lh.logchk(session.getAttribute("ud"))%></a>
        <a href="<%=lh.cartURL()%>">カートを見る</a>
        <p><form action="Search" method="get">
            <input type="txt" name="txtkeyword">
            <input type="submit" value="検索">
        </form></p>
        <%  out.println("結果数:" + sdbBox.getSdb(0).getTotalHit());
            out.println("キーワード:" + sdbBox.getSdb(0).getKeyword());%>
            <%for (int i = 0; i < sdbBox.getSdbBox().size(); i++) {%>
        <table border=1>
            <tr>
                <th>画像</th>
                <th>名前</th>
                <th>値段</th>
            </tr>
            <tr>
                <td><a href="Item?sdbID=<%=i%>"><img src="<%=sdbBox.getSdb(i).getImage()%>"/></a></td>
                <td><a href="Item?sdbID=<%=i%>"><%=sdbBox.getSdb(i).getName()%></a></td>
                <td><%=sdbBox.getSdb(i).getPrice()%></td>
            </tr>
        </table>
        <%}%>
    </body>
</html>
