<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 16/04/2024
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<form id = "data" name="data">
    <input placeholder="a" type="number" id="coeff_a" name="ca">x<sup>2</sup>+
    <input placeholder="b" type="number" id="coeff_b" name="cb">x+
    <input placeholder="c" type="number" id="coeff_c" name="cc"><br/>
    <div id="button">
        <input type="button" id="calculate" value="Calculate">
    </div>
</form>
<div id="list">
</div>