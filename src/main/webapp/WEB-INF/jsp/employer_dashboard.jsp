<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Кабінет роботодавця - JobSearch</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container">
        <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 30px;">
            <h1>Управління вакансіями</h1>
            <a href="add_vacancy" class="btn" style="background-color: #28a745; color: white; padding: 12px 20px;">+ Створити вакансію</a>
        </div>

        <p style="color: #666;">Компанія: <b>${sessionScope.loggedUser.companyName}</b></p>

        <c:choose>
            <c:when test="${empty myVacancies}">
                <div class="search-panel" style="text-align: center; padding: 50px;">
                    <h3>Ви ще не опублікували жодної вакансії.</h3>
                    <p>Натисніть кнопку вище, щоб знайти найкращих співробітників!</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="search-panel">
                    <table style="width: 100%; border-collapse: collapse;">
                        <thead>
                            <tr style="border-bottom: 2px solid #eee; text-align: left;">
                                <th style="padding: 10px;">Назва посади</th>
                                <th style="padding: 10px;">Дата</th>
                                <th style="padding: 10px;">Зарплата</th>
                                <th style="padding: 10px; text-align: center;">Відгуки</th>
                                <th style="padding: 10px; text-align: right;">Дії</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${myVacancies}" var="v">
                                <tr style="border-bottom: 1px solid #f9f9f9;">
                                    <td style="padding: 15px;"><b>${v.title}</b></td>
                                    <td style="padding: 15px; color: #888;">${v.creationDate}</td>
                                    <td style="padding: 15px;">${v.salary} грн</td>
                                    <td style="padding: 15px; text-align: center;">
                                        <a href="view_applications?vacancyId=${v.id}" style="text-decoration: none; color: #0056b3; font-weight: bold;">
                                            📩 Переглянути
                                        </a>
                                    </td>
                                    <td style="padding: 15px; text-align: right;">
                                        <a href="details?id=${v.id}" class="btn" style="padding: 5px 10px; font-size: 13px;">Перегляд</a>
                                        <a href="edit_vacancy?id=${v.id}" class="btn btn-blue" style="padding: 5px 10px; font-size: 13px; margin-left: 5px;">Редагувати</a>
                                        <a href="delete_vacancy?id=${v.id}" class="btn" style="background-color: #dc3545; color: white; padding: 5px 10px; font-size: 13px; margin-left: 5px;" onclick="return confirm('Ви впевнені?');">Видалити</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div> 
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>