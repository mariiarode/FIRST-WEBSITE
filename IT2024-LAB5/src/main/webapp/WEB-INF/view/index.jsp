<%@ page import="pk.wieik.it.model.*" %>
<%@ page import="pk.wieik.it.model.Tools" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1"/>
    <title>IT-Lab5</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
    <script type="text/javascript" src="script.js"></script>
    <script type="text/javascript" src="quadratic.js"></script>
    <script type="text/javascript" src="settings.js"></script>
</head>
<body onload="functions(); clock(); setInterval(clock, 1000); hook();">
<div id="container">
    <div id="header">
        <jsp:include page="/WEB-INF/view/header.jsp"/>
    </div>
    <div id="middle">
        <div id="menu">
            <jsp:include page="/WEB-INF/view/menu.jsp"/>
        </div>
    </div>

    <jsp:useBean id="user" class="pk.wieik.it.model.MRuser" scope="session"/>

    <%
        String pageName = request.getParameter("page");
        if (user != null && user.getPrivileges() == 1)
            pageName = Tools.parsePage(pageName, "main;quadratic;third;settings");
        else
            pageName = Tools.parsePage(pageName, "main;quadratic;third");
    %>

    <div id="content">
        <jsp:include page="/WEB-INF/view/content.jsp">
            <jsp:param name="what_page" value="<%= pageName %>"/>
        </jsp:include>
    </div>

    <div id="footer">
        <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </div>
</div>
</body>
</html>
