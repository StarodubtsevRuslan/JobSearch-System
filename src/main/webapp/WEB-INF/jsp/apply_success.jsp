<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Успішно відправлено</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container">
        <div class="search-panel" style="max-width: 600px; margin: 50px auto; text-align: center;">
            <h1 style="color: #28a745; font-size: 60px; margin: 10px 0;">✅</h1>
            <h2 style="color: #333;">Ваш відгук успішно надіслано!</h2>
            
            <p style="font-size: 16px; line-height: 1.6;">
                Шановний(а) <b>${candidateName}</b>,<br>
                ваше резюме на посаду <b>"${vacancyTitle}"</b> було передано роботодавцю.
            </p>
            <p style="color: #666; margin-bottom: 30px;">
                Очікуйте зворотного зв'язку на вказаний вами email. Бажаємо успіхів!
            </p>
            
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-blue">Повернутися на головну</a>
        </div>
    </div> 
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>