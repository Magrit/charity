<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>" />
  </head>
  <body>
  <header>
    <%@include file="../home/header.jsp"%>
  </header>

    <section class="login-page">
      <h2>Zmień hasło</h2>
      <form method="post" action="/profile/password">
        <div class="form-group">
          <input type="password" name="password" placeholder="Nowe hasło" />
        </div>
        <div class="form-group">
          <input type="password" name="confirmPassword" placeholder="Powtórz nowe hasło" />
        </div>
        <div class="form-group form-group--buttons">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <button class="btn" type="submit">Zapisz</button>
        </div>
      </form>
    </section>

    <%@include file="../home/footer.jsp"%>>
  </body>
</html>
