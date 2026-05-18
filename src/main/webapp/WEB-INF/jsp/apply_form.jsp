<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Відгук на вакансію</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container">
        <div class="search-panel" style="max-width: 600px; margin: 0 auto; text-align: left;">
            <h2 style="color: #0056b3; margin-top: 0;">Подача заявки</h2>
            <p>Ви відгукуєтесь на посаду: <b>${vacancy.title}</b></p>
            
            <hr style="border: 1px solid #eee; margin: 20px 0;">
            
            <form action="apply" method="POST">
                <input type="hidden" name="vacancyId" value="${vacancy.id}">
                <input type="hidden" name="vacancyTitle" value="${vacancy.title}">
                
                <p><b>Ваше ім'я та прізвище:</b></p>
                <input type="text" name="candidateName" required placeholder="Наприклад: Іван Франко" style="width: 100%; box-sizing: border-box;">
                
                <p><b>Ваш Email:</b></p>
                <input type="email" name="candidateEmail" required placeholder="ivan@example.com" style="width: 100%; box-sizing: border-box;">
                
                <p><b>Супровідний лист (досвід, навички):</b></p>
                <textarea name="coverLetter" rows="6" required placeholder="Напишіть кілька слів про свій досвід..." style="width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-family: inherit;"></textarea>
                
                <div style="margin-top: 20px; text-align: right;">
                    <a href="javascript:history.back()" class="btn" style="background-color: #6c757d; margin-right: 10px;">Скасувати</a>
                    <input type="submit" value="Відправити резюме" class="btn btn-blue">
                </div>
            </form>
        </div>
    </div> 
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>