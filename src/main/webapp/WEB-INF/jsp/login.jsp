<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Вхід - KPI JobSearch</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container">
        <div class="search-panel" style="max-width: 400px; margin: 50px auto;">
            <h2 style="color: #0056b3; text-align: center;">Вхід в систему</h2>
            
          
            <c:if test="${not empty errorMessage}">
                <div style="background-color: #f8d7da; color: #721c24; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center;">
                    ${errorMessage}
                </div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div style="background-color: #f8d7da; color: #721c24; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center;">
                    ${errorMessage}
                </div>
            </c:if>
            
            <c:if test="${not empty successMessage}">
                <div style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center;">
                    ${successMessage}
                </div>
            </c:if>
            
            <form action="login" method="POST" style="text-align: left;">
                <p><b>Ваш Email:</b></p>
                <input type="email" name="email" required placeholder="name@example.com" style="width: 100%; box-sizing: border-box; margin-bottom: 15px;">
                
                <p><b>Пароль:</b></p>
                <input type="password" name="password" required placeholder="Введіть пароль" style="width: 100%; box-sizing: border-box; margin-bottom: 20px;">
                
                <input type="submit" value="Увійти" class="btn btn-blue" style="width: 100%;">
            </form>
            
            <p style="text-align: center; margin-top: 20px; font-size: 14px;">
                Немає акаунта? <a href="register" style="color: #0056b3;">Зареєструватися</a>
            </p>
        </div>
    </div> <jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>