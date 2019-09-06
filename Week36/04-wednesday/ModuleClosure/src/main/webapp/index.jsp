<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Module Closure. Wednesday last two assignments.</h1>

        <button onclick="test1()">Count counter1</button>
        <span id="c1"></span><br><br>
        <button onclick="test2()" >Count counter2</button>
        <span id="c2"></span>

        <script>
            function test1() {
                counter1.increment();
                $("#c1").html(counter1.value());
            }
            function test2() {
                counter2.increment();
                $("#c2").html(counter2.value());
            }
        </script>

        <!-- JQUERY JS -->
        <script src="https://code.jquery.com/jquery-3.4.0.min.js" crossorigin="anonymous"></script>
        <script src="js/counterModule.js"></script>
    </body>
</html>
