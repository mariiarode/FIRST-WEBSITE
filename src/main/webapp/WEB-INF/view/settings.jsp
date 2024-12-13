<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="pk.wieik.it.model.MRuser" %>
<%@ page import="pk.wieik.it.model.Tools" %>
<%@ page import="java.util.*" %>

<jsp:useBean id="user" class="pk.wieik.it.model.MRuser" scope="session"/>
<div>
    <form action="MR?action=saveSettings" method="post">

        Name: <input type="text" name="name" value="<%=   user.getName()  %>"/><br/>
        Surname: <input type="text" name="surname" value="<%=  user.getSurname()  %>"/><br/>
        Age: <input type="number" name="age" min="0" value="<%=  user.getAge()  %>"/><br/>
        Background color: <input type="text" name="backgroundColor" value="${applicationScope.backgroundColor}"/><br/>
        <input type="submit" value="Save"/>
    </form>
</div>