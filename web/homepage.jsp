<%-- 
    Document   : searchPage
    Created on : 22-05-2021, 13:20:17
    Author     : dinhd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="root/css/homepage.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <div class="container">
            <div class="header-1">

            </div>
            <div class="header-2">
                <h3>My Digital News</h3>
            </div>
            <div class="nav">
                <div class="nav-content"><a href="loadNews">
                        News
                    </a>
                </div>
            </div>
            <div class="main">
                <div class="left">

                    <c:forEach items="${requestScope.newsPapers}" var="o">
                        <h3 class="title">
                            <a class="a-title" href="News?id=${o.id}">${o.title}</a>

                        </h3>
                        <div class="container-2">
                            <div>
                                <c:if test = "${fn:length(o.image) > 0}">
                                    <img src="data:image/png;base64,${o.encode}"/>
                                </c:if></div>
                            <div class="left-p-1">
                                <p>
                                    ${o.content}
                                </p>
                                <p class="left-p-2">
                                    Do BY ${o.writer} | ${o.date}
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                   
                </div>
                <div class="right">
                    <div class="div-right">
                    <div class="div-renc">
                        <h3>Digital News</h3>
                        <p>
                            ${sessionScope.shortContent}
                        </p>
                    </div>
                    <div class="div-2">
                        <div class="div-search">
                            <h3>Search</h3>
                            <form action="HomePage" method="GET">
                                <input class="div-input" type="text" name="search" id="search" value="${requestScope.keyword}">
                               
                                <input class="div-btn" type="submit" value="Go">
                            </form>
                        </div>
                        <div class="div-last">
                            <h3>Last Articles</h3>
                            <c:forEach items="${sessionScope.top5newsPapers}" var="o">
                                <div class="div-a">
                                    <a href="News?id=${o.id}">${o.title}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    </div>            
                </div>
            </div>
            <div class="footer">
            </div>
        </div>
    </body>
</html>
