<%-- 
    Document   : add
    Created on : Jun 25, 2021, 9:47:45 AM
    Author     : duchi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Admin" method="POST"enctype="multipart/form-data">

            
            title:  <input type="text"id="title" name="title"></br>
            title Images:  <input type="file"id="image" name="image" multiple></br>
            content:  <input type="text"id="content" name="content"></br>
            writer:  <input type="text"id="writer" name="writer"></br>
            date: <input type="date"id="date" name="date"></br>
            <button type="submit">Submit</button>
        </form>

    </body>
</html>
