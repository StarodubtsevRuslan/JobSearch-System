<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<div class="header">
    <h2><a href="${pageContext.request.contextPath}/index.jsp" style="margin-left: 0;">JobSearch</a></h2>
    <div>
        <c:choose>
            <c:when test="${not empty sessionScope.loggedUser}">
                <span style="margin-right: 20px; font-size: 16px;">
                    Привіт, <b>${sessionScope.loggedUser.login}</b>!
                </span>
                
                <a href="${pageContext.request.contextPath}/favorites" style="margin-right: 15px;">Моє Обране 💙</a>
                
                <c:if test="${sessionScope.loggedUser.role == 'EMPLOYER'}">
                    <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-blue" style="margin-right: 15px;">Кабінет компанії</a>
                </c:if>
                
                <a href="${pageContext.request.contextPath}/logout" class="btn" style="background-color: #dc3545; color: white;">Вийти</a>
            </c:when>
            
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login" class="btn btn-blue">Увійти</a>
                <a href="${pageContext.request.contextPath}/register" class="btn" style="margin-left: 10px; background-color: #6c757d;">Реєстрація</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>