<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
      <h2>Załóż konto</h2>
      <form:form method="post" action="/register" modelAttribute="appUser">
        <div class="form-group">
          <form:input path="firstName" placeholder="imię"/>
        </div>
        <div class="form-group">
          <form:input path="lastName" placeholder="nazwisko"/>
        </div>
        <div class="form-group form-group--inline">
          <form:input path="email" placeholder="email"/><br/>
          <label>
          <form:errors path="email" cssClass="checkbox" cssStyle="color: darkred"/>
          </label>
        </div>
        <div class="form-group form-group--inline">
          <form:input path="phoneNumber" placeholder="numer telefonu"/><br/>
          <label>
          <form:errors path="phoneNumber" cssClass="checkbox" cssStyle="color: darkred" />
          </label>
        </div>
        <div class="form-group form-group--inline">
          <form:password path="password" placeholder="hasło" /><br/>
          <label>
          <form:errors path="password" cssClass="checkbox" cssStyle="color: darkred"/>
          </label>
        </div>
        <div class="form-group">
          <input type="password" name="confirmPassword" placeholder="powtórz hasło" />
        </div>

        <div class="form-group form-group--buttons">
          <a href="/login" class="btn btn--without-border">Zaloguj się</a>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <input class="btn" type="submit" value="Załóż konto"/>
        </div>
      </form:form>
    </section>

    <%@include file="../home/footer.jsp"%>
  </body>
</html>
