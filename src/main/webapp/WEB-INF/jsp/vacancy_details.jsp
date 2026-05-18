<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${vacancy.title} - Деталі</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container">
        <div class="search-panel" style="text-align: left;">
            <h1 style="color: #0056b3; margin-bottom: 5px;">${vacancy.title}</h1>
            
            <div style="margin-bottom: 20px;">
                <span class="tag" style="background-color: #d4edda; color: #155724; font-weight: bold;">💰 ${vacancy.salary} грн</span>
                <span class="tag">📍 ${vacancy.location}</span>
                <span class="tag">💼 ${vacancy.employmentType}</span>
                <span class="tag">🎓 Досвід: ${vacancy.experience}</span>
            </div>
            
            <p style="font-size: 16px;"><b>Компанія:</b> <a href="employer?id=${vacancy.employerId}" style="color: #007bff; text-decoration: none;">ID компанії #${vacancy.employerId} (Переглянути всі вакансії)</a></p>
            
            <hr style="border: 1px solid #eee; margin: 20px 0;">
            
            <h3>Опис вакансії:</h3>
            <p style="line-height: 1.6; font-size: 16px;">${vacancy.description}</p>
            
            <h3>Вимоги до кандидата:</h3>
            <p style="line-height: 1.6; font-size: 16px;">${vacancy.requirements}</p>
            
            <br>
            <p style="color: #888; font-size: 14px;">Ключові слова: ${vacancy.keywords}</p>
            <p style="color: #888; font-size: 14px;">Опубліковано: ${vacancy.creationDate}</p>
            
            <div style="margin-top: 30px;">
                <a href="javascript:history.back()" class="btn" style="background-color: #6c757d;">Повернутися назад</a>
                <a href="apply?id=${vacancy.id}" class="btn btn-blue" style="margin-left: 10px;">Відгукнутися на вакансію</a>
                <a href="add_favorite?id=${vacancy.id}" class="btn" style="margin-left: 10px;">Зберегти 💙</a>
            </div>
        </div>
    </div> 
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>