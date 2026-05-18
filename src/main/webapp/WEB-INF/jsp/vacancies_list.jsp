<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Результати пошуку - JobSearch</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container">
        <div class="search-panel">
            <form action="search" method="GET">
                <input type="text" name="keyword" value="${param.keyword}" placeholder="Посада або ключове слово...">
                
                <select name="category">
                    <option value="Усі категорії" ${param.category == 'Усі категорії' ? 'selected' : ''}>Усі категорії</option>
                    <c:forEach var="cat" items="${applicationScope.appCategories}">
                        <option value="${cat}" ${param.category == cat ? 'selected' : ''}>
                            ${cat} (${categoryCounts[cat] != null ? categoryCounts[cat] : 0})
                        </option>
                    </c:forEach>
                </select>
                
                <input type="number" name="minSalary" value="${param.minSalary}" placeholder="Зарплата від (грн)">
                <input type="submit" value="Оновити пошук" class="btn">
            </form>
            
            <div style="margin-top: 15px; font-size: 14px;">
                <b>Сортувати за:</b> 
                <a href="search?keyword=${param.keyword}&category=${param.category}&minSalary=${param.minSalary}&sortBy=salary&asc=false" style="margin-left: 10px;">Зарплата ↓</a> |
                <a href="search?keyword=${param.keyword}&category=${param.category}&minSalary=${param.minSalary}&sortBy=salary&asc=true">Зарплата ↑</a> |
                <a href="search?keyword=${param.keyword}&category=${param.category}&minSalary=${param.minSalary}&sortBy=date&asc=false" style="margin-left: 10px;">Дата ↓</a> |
                <a href="search?keyword=${param.keyword}&category=${param.category}&minSalary=${param.minSalary}&sortBy=date&asc=true">Дата ↑</a>
            </div>
        </div>

        <c:if test="${empty vacanciesList}">
            <h3 align="center" style="color: #dc3545;">За вашим запитом вакансій не знайдено. Спробуйте змінити критерії.</h3>
        </c:if>

        <c:forEach items="${vacanciesList}" var="v">
            <table class="vacancy-card">
                <tr>
                    <td class="card-header-cell">
                        <h2 class="card-title"><c:out value="${v.title}" /></h2>
                        <span class="tag">💰 <c:out value="${v.salary}" /> грн</span>
                        <span class="tag">📍 <c:out value="${v.location}" /></span>
                        <span class="tag">💼 <c:out value="${v.employmentType}" /></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><b>Опис:</b> <c:out value="${v.description}" /></p>
                        <p style="color: #666; font-size: 14px;">Дата публікації: <c:out value="${v.creationDate}" /></p>
                        <br>
                        <a href="details?id=${v.id}" class="btn btn-blue">Детальніше</a>
                        <a href="add_favorite?id=${v.id}" class="btn" style="margin-left: 10px;">Зберегти 💙</a>
                    </td>
                </tr>
            </table>
        </c:forEach>
    </div> 
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>