<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <%@include file="../home/header.jsp" %>

    <div class="slogan container container--90">
        <h2>
            Dane Twojego konta
        </h2>
    </div>
</header>

<section class="login-page">
    <div class="container">
        <h2>Twoje konto</h2>
        <h1>
            Imię: ${user.firstName} <br/>
            Nazwisko: ${user.lastName} <br/>
            Email: ${user.email} <br/>
            nr telefonu: ${user.phoneNumber} <br/><br/>
        </h1>
        <div class="form-group form-group--buttons">
            <a href="/profile/edit" class="btn">Edytuj</a>
            <a href="/profile/password" class="btn">Zmień hasło</a> <br/><br/>
        </div>
    </div>
</section>

<%@include file="../home/footer.jsp" %>

<script src="../../resources/js/app.js"></script>
</body>
</html>
