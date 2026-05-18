<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.Cookie"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KPI JobSearch - Головна</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <% 
        String lastSearch = ""; 
        Cookie[] cookies = request.getCookies(); 
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("lastSearch".equals(c.getName())) { 
                    lastSearch = c.getValue(); 
                    break;
                }
            }
        }
    %>


    <jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container">
        <h1 align="center" style="font-size: 36px; margin-top: 50px;">Інформаційна система пошуку роботи</h1>
        <p align="center" style="font-size: 18px; color: #666;">База актуальних вакансій та швидкий пошук</p>

        <div class="search-panel" style="margin-top: 40px;">
            <form action="search" method="GET">
                <input type="text" name="keyword" placeholder="Посада або ключове слово..." size="40">
                <select name="category">
                    <option value="Усі категорії">Усі категорії</option>
                    <c:forEach var="cat" items="${applicationScope.appCategories}">
                        <option value="${cat}">${cat}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Знайти роботу" class="btn">
            </form>
            
            <% if (!lastSearch.isEmpty()) { %>
                <p style="font-size: 12px; color: #888;">Ви нещодавно шукали: <b><%= lastSearch %></b></p>
            <% } %>
        </div>
    </div> <jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html> 