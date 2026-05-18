<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Відгуки на вакансію - JobSearch</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container">
        <div style="margin-top: 30px;">
            <a href="dashboard" style="text-decoration: none; color: #666;">← Назад до кабінету</a>
            <h1 style="color: #0056b3; margin-top: 10px;">Відгуки на вакансію: "${vacancy.title}"</h1>
        </div>

        <c:choose>
            <c:when test="${empty applications}">
                <div class="search-panel" style="text-align: center; padding: 50px;">
                    <h3>На цю вакансію поки немає відгуків.</h3>
                    <p>Спробуйте оновити опис або додати ключові слова для кращого пошуку.</p>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${applications}" var="app">
                    <div class="search-panel" style="margin-bottom: 20px; text-align: left; border-left: 5px solid #0056b3;">
                        <div style="display: flex; justify-content: space-between;">
                            <h3 style="margin-top: 0; color: #333;">${app.candidateName}</h3>
                            <span style="color: #888; font-size: 14px;">📅 ${app.getFormattedDate()}</span>
                        </div>
                        <p>📧 Email для зв'язку: <a href="mailto:${app.candidateEmail}">${app.candidateEmail}</a></p>
                        <hr style="border: 0; border-top: 1px solid #eee; margin: 15px 0;">
                        <p><b>Супровідний лист:</b></p>
                        <p style="white-space: pre-wrap; background: #f9f9f9; padding: 15px; border-radius: 4px; line-height: 1.6;">${app.coverLetter}</p>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div> 
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>