<%@ page import="pk.wieik.it.model.MRuser" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    MRuser user = (MRuser) session.getAttribute("user");
    if (user == null) {
        user = new MRuser();
        session.setAttribute("user", user);
    }
%>


<div>
    <ul>
        <li><a href="?page=main">Main page</a></li>
        <li><a href="?page=quadratic">ax<sup>2</sup>+bx+c=0</a></li>
        <li><a href="?page=third">Link3</a></li>
        <% if (user.getPrivileges() >= 0) { %>
        <li><a href="?page=settings">Settings</a></li>
        <% } %>
    </ul>
</div>

</ul>
</nav>
</label>
<% if (user.getPrivileges() < 0) { %>
<form action="MR?action=login" method="post">
    Login: <input type="text" name="login"><br/>
    Password: <input type="password" name="password"><br/>
    <input type="submit" value="Login"><br/>
</form>
<% } else { %>
<form action="MR?action=logout" method="post">
    You are logged in as <b><%=user.getLogin()%></b>
    <input type="submit" value="Logout"> <br/>
</form>
<% } %>
<div id="news">

    <p id="news1"></p>
    <p id="news2"></p>
</div>


