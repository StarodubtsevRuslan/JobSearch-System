<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Реєстрація - JobSearch</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    
    <script>
        function toggleCompanyField() {
            var role = document.querySelector('input[name="role"]:checked').value;
            var companyDiv = document.getElementById("companyFieldDiv");
            var companyInput = document.getElementById("companyNameInput");
            
            if (role === 'EMPLOYER') {
                companyDiv.style.display = "block";
                companyInput.required = true;
            } else {
                companyDiv.style.display = "none";
                companyInput.required = false;
            }
        }
    </script>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container">
        <div class="search-panel" style="max-width: 450px; margin: 40px auto; text-align: left;">
            <h2 style="color: #0056b3; text-align: center;">Створення акаунта</h2>
            <hr style="border: 1px solid #eee; margin-bottom: 20px;">
            
            <c:if test="${not empty errorMessage}">
                <div style="background-color: #f8d7da; color: #721c24; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center;">
                    ${errorMessage}
                </div>
            </c:if>

            <form action="register" method="POST">
                <p><b>Як до вас звертатися (Ім'я / ПІБ):</b></p>
                <input type="text" name="login" required style="width: 100%; box-sizing: border-box; margin-bottom: 15px;">
                
                <p><b>Email (буде вашим логіном):</b></p>
                <input type="email" name="email" required placeholder="name@example.com" style="width: 100%; box-sizing: border-box; margin-bottom: 15px;">
                
                <p><b>Пароль:</b></p>
                <input type="password" name="password" required style="width: 100%; box-sizing: border-box; margin-bottom: 20px;">
                
                <p><b>Хто ви?</b></p>
                <div style="margin-bottom: 20px;">
                    <label style="margin-right: 20px;">
                        <input type="radio" name="role" value="SEEKER" checked onchange="toggleCompanyField()"> Я шукаю роботу
                    </label>
                    <label>
                        <input type="radio" name="role" value="EMPLOYER" onchange="toggleCompanyField()"> Я роботодавець
                    </label>
                </div>
                
                <div id="companyFieldDiv" style="display: none; margin-bottom: 20px;">
                    <p><b>Назва вашої компанії:</b></p>
                    <input type="text" id="companyNameInput" name="companyName" placeholder="ТОВ Роги та Копита" style="width: 100%; box-sizing: border-box;">
                </div>
                
                <input type="submit" value="Зареєструватися" class="btn btn-blue" style="width: 100%; padding: 10px; font-size: 16px;">
            </form>
            
            <p style="text-align: center; margin-top: 20px; font-size: 14px;">
                Вже маєте акаунт? <a href="login" style="color: #0056b3;">Увійти</a>
            </p>
        </div>
    </div> 
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>