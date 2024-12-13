<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 16/04/2024
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="data" name="data">
    Name: <input type="text" id="name" name="name"><br>
    Surname: <input type="text" id="surname" name="surname"><br>
    Age: <input type="number" id="age" name="age"><br>
    <input type="button" id="save" value="Save">
</form>
<div id="message" style="display: none;"></div>

<script>
    function saveData() {
        var nameValue = document.getElementById('name').value;
        var surnameValue = document.getElementById('surname').value;
        var ageValue = document.getElementById('age').value;

        if (nameValue && surnameValue && ageValue) {
            var data = {
                name: nameValue,
                surname: surnameValue,
                age: ageValue
            };

            localStorage.setItem('userData', JSON.stringify(data));

            var messageElement = document.getElementById('message');
            messageElement.innerHTML = "Settings have been saved correctly";
            messageElement.style.display = "block"; // Muestra el mensaje
        } else {
            console.log("Please fill in all fields.");
        }
    }

    function loadSavedData() {
        var userData = localStorage.getItem('userData');
        if (userData) {
            var data = JSON.parse(userData);
            document.getElementById('name').value = data.name;
            document.getElementById('surname').value = data.surname;
            document.getElementById('age').value = data.age;
        }
    }

    var saveButton = document.getElementById('save');
    saveButton.addEventListener('click', saveData);

    window.addEventListener('load', loadSavedData);
</script>