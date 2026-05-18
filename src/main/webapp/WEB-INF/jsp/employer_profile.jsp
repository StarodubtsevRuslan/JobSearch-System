<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Профіль роботодавця</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container">
        <h2 align="center">Профіль компанії</h2>
        <p align="center" style="margin-bottom: 30px;">Тут зібрані всі активні вакансії цього роботодавця</p>

        <c:forEach items="${empVacancies}" var="v">
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
                        <a href="details?id=${v.id}" class="btn btn-blue">Детальніше</a>
                    </td>
                </tr>
            </table>
        </c:forEach>
        
        <p align="center"><a href="javascript:history.back()" class="btn">Назад</a></p>
    </div> 
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>