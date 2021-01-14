<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@include file="../home/header.jsp" %>
</header>
<section class="login-page">
    <h2>Edytuj dane</h2>
    <form method="post" action="/profile/edit">
        <div class="form-group">
            <input type="text" name="firstName" placeholder="Imię" value="${user.firstName}"/>
        </div>
        <div class="form-group">
            <input type="text" name="lastName" placeholder="Nazwisko" value="${user.lastName}"/>
        </div>
        <div class="form-group form-group--inline">
            <input type="text" name="email" value="${user.email}"/><br/>
            </label>
        </div>
        <div class="form-group form-group--inline">
            <input type="text" name="phoneNumber" value="${user.phoneNumber}"/><br/>
        </div>
        <div class="form-group">
            <label>
                <c:forEach items="${errors}" var="error">
                    <div class="alert-danger">${error.message}</div>
                </c:forEach>
            </label>
        </div>
        <div class="form-group form-group--buttons">
            <input type="submit" value="Edytuj"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
    </form>
</section>

<%@include file="../home/footer.jsp" %>
</body>
</html>
