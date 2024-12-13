<%@ page import="pk.wieik.it.model.MRuser" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="user" class="pk.wieik.it.model.MRuser" scope="session"/>

<input type="checkbox" id="hamburger-input" class="burger-shower" />
<label id="hamburger-menu" for="hamburger-input">
    <nav id="sidebar-menu">
        <h3>Menu</h3>
        <ul>
            <li><a href="?page=main">Main page</a></li>
            <li><a href="?page=quadratic">ax<sup>2</sup>+bx+c=0</a></li>
            <li><a href="?page=third">Link3</a></li>
            <% if (user.getPrivileges() >= 0) { %>
            <li><a href="?page=settings">Settings</a></li>
            <% } %>
            <% if(user.getPrivileges()==2){ %>
            <li><a href="?page=administration">Administration</a></li>
            <% } %>
            <% if(user.getPrivileges()==-1){ %>
            <li><a href="?page=registration">Registration</a></li>
            <% } %>
            <% if (user.getPrivileges() >= 0) { %>
            <li><a href="?page=nameDay">Name Day</a></li>
            <% } %>
        </ul>
    </nav>
</label>

