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
    <%@include file="header.jsp" %>

    <div class="slogan container container--90">
        <h2>
            Przekazane przez Ciebie dary
        </h2>
    </div>
</header>

<section class="login-page">
    <div class="container">
            <c:forEach items="${donationsList}" var="donation">
                <h2>Darowizna:</h2>
                <h1>
                    Organizacja:
                    <c:set value="${donation.institution}" var="institution"/>
                    ${institution.name} <br/>
                    Rodzaje przekazanych darów:
                    <c:forEach items="${donation.categories}" var="category">${category.name}, </c:forEach><br/>

                    Ilość worków: ${donation.quantity} <br/>
                    Data: ${donation.pickUpDate} <br/>
                    <a href="/donation/${donation.id}" class="btn">Pokaż</a> <br/><br/>
                </h1>
            </c:forEach>
    </div>
</section>

<%@include file="footer.jsp" %>

<script src="../../resources/js/app.js"></script>
</body>
</html>
