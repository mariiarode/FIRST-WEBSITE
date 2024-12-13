<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css" type="text/css" />
</head>
<body>
<div id="container">
    <div id="middle">
        <h4>Enter the name:</h4>
        <input id="field" type="text" onkeyup="getSuggestions();" />
        <div id="results"></div>
    </div>
</div>

<script>
    function sendAsynchronously(url, method, dataType, uploadedDocument) {
        if (!window.XMLHttpRequest) return null;

        let requester = new XMLHttpRequest();
        method = method || 'GET';
        dataType = dataType || 'text/plain';
        requester.open(method, url);
        requester.setRequestHeader('Content-Type', dataType);

        requester.onreadystatechange = function() {
            let el = document.getElementById("results");
            el.style.display = 'block';
            el.innerHTML = 'Loading data...';
            if (requester.readyState == 4) {
                if (requester.status == 200) {
                    let responseText = requester.responseText;
                    let response = JSON.parse(responseText);
                    let results = "";
                    for (let value of response.suggestions) {
                        results += '<div class="list">' + value + '</div>';
                    }
                    el.innerHTML = results;
                } else {
                    console.log("status error " + requester.status);
                }
            }
        }

        requester.send(uploadedDocument);
        return requester;
    }

    function getSuggestions() {
        let input = document.getElementById("field").value.trim();
        if (input === "") {
            document.getElementById("results").innerHTML = "";
            return;
        }
        let value = { "value": input };
        sendAsynchronously("suggestions", "POST", "application/json", JSON.stringify(value));
    }

    $(document).on("click", "#results .list", function(e) {
        e.stopPropagation();
        let selectedValue = $(this).text();
        $('#field').val(selectedValue);
        $("#results").empty();
    });

</script>
</body>
</html>
