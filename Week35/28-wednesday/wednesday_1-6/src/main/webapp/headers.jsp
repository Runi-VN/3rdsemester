<%-- 
    Document   : headers
    Created on : 28-08-2019, 19:37:00
    Author     : runin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise 5</title>
        <base href="${pageContext.request.contextPath}/" />
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <h1>Lets display headers</h1>
        <table border="1">
            <thead>
            <th>HEADER</th>
            <th>VALUE</th>
        </thead>
        <tbody>
            <c:forEach items="${headers}" var="headers">
                <tr>
                    <td>${headers.key}</td>
                    <td>${headers.value}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
