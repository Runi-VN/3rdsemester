<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form>
            <div>
                <label for="name">Your name:</label>
                <input type="text" id="name">
            </div>
            <br>
            <div>
                <label for="age">Your age:</label>
                <input type="number" id="age">
            </div>
            <br/>
            <button id="btn" type="button" onclick="preparePerson()">Submit</button>

            <h2 id="outputtxt"></h2>
            <div style="border: 1px">
                <span id="output"></span>
            </div>
        </form>

        <script>
             getPerson = function () {
                var personImpl = new person();
                personImpl.setName(document.getElementById('name').value);
                personImpl.setAge(document.getElementById('age').value);
                return personImpl.getInfo();
            };
             preparePerson = function () {
                 var values = getPerson();
                $("#output").text(values);
            };
        </script>
        <!-- JQUERY JS -->
        <script src="https://code.jquery.com/jquery-3.4.0.min.js" crossorigin="anonymous"></script>
        <script src="js/personModule.js"></script>
    </body>
</html>
