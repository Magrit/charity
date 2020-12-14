<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Header</title>
</head>
<body>

<nav class="container container--70">
    <sec:authorize access="isAnonymous()">
        <ul class="nav--actions">
            <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </ul>
    </sec:authorize>
    <sec:authorize access="hasRole('USER')">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj ${name}
                <ul class="dropdown">
                    <li><a href="/profile">Profil</a></li>
                    <li><a href="/donation">Moje zbiórki</a></li>
                    <sec:authorize access="hasRole('ADMIN')">
                        <li><a href="/admin">Wyświetl użytkowników</a></li>
                    </sec:authorize>
                    <li><a href="/logout">Wyloguj</a></li>
                </ul>
            </li>
        </ul>
    </sec:authorize>
    <ul>
        <li><a href="/" class="btn btn--without-border active">Start</a></li>
        <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="#" class="btn btn--without-border">O nas</a></li>
        <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>

</body>
</html>
