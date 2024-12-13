<%@ page import="pk.wieik.it.model.MRuser" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="user" class="pk.wieik.it.model.MRuser" scope="session"/>
<div id="news">
    <% if (user.getPrivileges() < 0) { %>
    <form action="MR?action=login" method="post">
        Login: <input type="text" name="login" size="17pd"><br/>
        Password: <input type="password" name="password" size="17pd"><br/>
        <input type="submit" value="Login"><br/>
    </form>
    <% } else { %>
    <form action="MR?action=logout" method="post">
        You are logged in as <b><%=user.getLogin()%></b>
        <input type="submit" value="Logout"> <br/>
    </form>
    <% } %>
    <p id="news1"></p>
    <p id="news2"></p>
</div>