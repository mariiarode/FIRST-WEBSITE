<%@ page import="java.util.Map" %>
<%@ page import="pk.wieik.it.model.MRuser" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<!-- Administration Form -->
<form action="MR?action=administration" method="post">
    Background color: <input type="text" name="backgroundColor" value="<%= application.getAttribute("backgroundColor") %>"/><br/>
    <input type="submit" value="Save"/>
</form>

<!-- User Permissions Table -->
<h2>User Permissions</h2>
<table border="1">
    <tr>
        <th>User</th>
        <th>Change Permissions</th>
    </tr>
    <%
        HashMap<String, MRuser> users = (HashMap<String, MRuser>) application.getAttribute("users");
        for (Map.Entry<String, MRuser> entry : users.entrySet()) { %>
    <% MRuser user = entry.getValue(); %>
    <tr>
        <td><%= user.getLogin() %></td>
        <td>
            <form action="MR" method="post">
                <input type="hidden" name="action" value="changePermissions">
                <input type="hidden" name="login" value="<%= user.getLogin() %>">
                <input type="radio" name="permissions" value="1" <% if (user.getPrivileges() == 1) out.print("checked"); %> > User
                <input type="radio" name="permissions" value="2" <% if (user.getPrivileges() == 2) out.print("checked"); %> > Administrator
                <input type="submit" value="Change">
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>

