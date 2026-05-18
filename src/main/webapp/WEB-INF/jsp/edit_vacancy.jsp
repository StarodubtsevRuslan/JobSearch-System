<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Редагувати вакансію - JobSearch</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/header.jsp" />

        <div class="container">
            <div class="search-panel" style="max-width: 800px; margin: 30px auto; text-align: left;">
                <h2 style="color: #0056b3;">Редагування вакансії</h2>
                <hr style="border: 1px solid #eee; margin-bottom: 25px;">

                <form action="edit_vacancy" method="POST">
                    <input type="hidden" name="id" value="${vacancy.id}">

                    <p><b>Назва посади:</b></p>
                    <input type="text" name="title" value="${vacancy.title}" required style="width: 100%; box-sizing: border-box; margin-bottom: 15px;">

                    <div style="display: flex; gap: 20px; margin-bottom: 15px;">
                        <div style="flex: 1;">
                            <p><b>Категорія:</b></p>
                            <select name="category" required style="width: 100%; padding: 8px;">
                                <c:forEach var="cat" items="${applicationScope.appCategories}">
                                    <option value="${cat}" ${vacancy.category == cat ? 'selected' : ''}>${cat}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div style="flex: 1;">
                            <p><b>Зарплата (грн):</b></p>
                            <input type="number" name="salary" value="${vacancy.salary}" required style="width: 100%; box-sizing: border-box; padding: 7px;">
                        </div>
                    </div>

                    <div style="display: flex; gap: 20px; margin-bottom: 15px;">
                        <div style="flex: 1;">
                            <p><b>Місто:</b></p>
                            <input type="text" name="location" value="${vacancy.location}" required style="width: 100%; box-sizing: border-box;">
                        </div>
                        <div style="flex: 1;">
                            <p><b>Досвід:</b></p>
                            <select name="experience" style="width: 100%; padding: 8px;">
                                <option value="Без досвіду" ${vacancy.experience == 'Без досвіду' ? 'selected' : ''}>Без досвіду</option>
                                <option value="1-3 роки" ${vacancy.experience == '1-3 роки' ? 'selected' : ''}>1-3 роки</option>
                                <option value="3-5 років" ${vacancy.experience == '3-5 років' ? 'selected' : ''}>3-5 років</option>
                                <option value="5+ років" ${vacancy.experience == '5+ років' ? 'selected' : ''}>5+ років</option>
                            </select>
                        </div>
                    </div>

                    <p><b>Опис вакансії:</b></p>
                    <textarea name="description" rows="5" required style="width: 100%; box-sizing: border-box;">${vacancy.description}</textarea>

                    <p><b>Вимоги:</b></p>
                    <textarea name="requirements" rows="5" required style="width: 100%; box-sizing: border-box;">${vacancy.requirements}</textarea>

                    <p><b>Ключові слова (через кому):</b></p>
                    <input type="text" name="keywords" value="${vacancy.keywords}" style="width: 100%; box-sizing: border-box; margin-bottom: 20px;">

                    <div style="text-align: right;">
                        <a href="dashboard" class="btn" style="background-color: #6c757d; margin-right: 10px;">Скасувати</a>
                        <input type="submit" value="Зберегти зміни" class="btn btn-blue">
                    </div>
                </form>
            </div>
        </div> 
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
    </body>
</html>