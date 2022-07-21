<%-- 
    Document   : display
    Created on : Jun 25, 2021, 9:47:57 AM
    Author     : duchi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${requestScope.newses}" var="news">
            title:  <input type="text"id="title" name="title" value="${news.title}"></br>
            <img src="data:image/png;base64,${news.encode}"/></br>
         
            content:  <input type="text"id="content" name="content" value="${news.content}"></br>
            writer:  <input type="text"id="writer" name="writer" value="${news.writer}"></br>
            date: <input type="date"id="date" name="date" value="${news.date}"></br>
        </c:forEach>
    </body>
</html>
