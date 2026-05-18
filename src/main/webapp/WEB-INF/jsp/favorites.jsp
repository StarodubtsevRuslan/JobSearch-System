<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Моє Обране - JobSearch</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container">
        <h1 align="center" style="color: #0056b3; margin-top: 30px;">Збережені вакансії 💙</h1>
        
        <c:if test="${empty favoritesList}">
            <div class="search-panel" style="text-align: center; padding: 40px;">
                <h3 style="color: #666;">Ваш список обраного поки порожній.</h3>
                <p>Перейдіть до каталогу, щоб знайти цікаві пропозиції!</p>
                <a href="${pageContext.request.contextPath}/search" class="btn btn-blue" style="margin-top: 15px; display: inline-block;">Шукати роботу</a>
            </div>
        </c:if>

        <c:forEach items="${favoritesList}" var="v">
            <table class="vacancy-card">
                <tr>
                    <td class="card-header-cell">
                        <h2 class="card-title">${v.title}</h2>
                        <span class="tag">💰 ${v.salary} грн</span>
                        <span class="tag">📍 ${v.location}</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><b>Опис:</b> ${v.description}</p>
                        <br>
                        <a href="${pageContext.request.contextPath}/details?id=${v.id}" class="btn btn-blue">Детальніше</a>
                        <a href="${pageContext.request.contextPath}/remove_favorite?id=${v.id}" class="btn" style="background-color: #dc3545; color: white; margin-left: 10px;">Видалити з обраного ✖</a>
                    </td>
                </tr>
            </table>
        </c:forEach>
    </div> 
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>