<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Form</title>
    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <%@include file="../home/header.jsp" %>
    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form id="form" action="/donation/form" method="post" modelAttribute="donation">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>

                <c:forEach items="${categoriesList}" var="category">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input id="categories" type="checkbox" name="categories" value="${category.id}">
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>
                </c:forEach>
                <div class="form-group form-group--inline">
                    <label>
                        <form:errors path="categories" cssClass="checkbox"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button id="first" type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <form:input path="quantity" id="quantity"/> <br/>
                    </label>
                    <label>
                        <form:errors path="quantity"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button id="second" type="button" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 3 -->
            <div data-step="3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>
                <c:forEach items="${institutionsList}" var="institution">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="radio" name="institution" id="institutions" value="${institution.id}"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                                <div class="title">${institution.name}</div>
                                <div class="subtitle">Cel i misja: ${institution.description}</div>
                            </span>
                        </label>
                    </div>
                </c:forEach>
                <div class="form-group form-group--inline">
                    <label>
                        <form:errors path="institution" cssClass="checkbox"/>
                    </label>
                </div>


                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button id="third" type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 4 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Ulica <form:input path="street" id="street"/>
                            </label>
                            <label>
                                <form:errors path="street"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Miasto <form:input path="city" id="city"/>
                            </label>
                            <label>
                                <form:errors path="city"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Kod pocztowy <form:input path="zipCode" id="zipCode"/>
                            </label>
                            <label>
                                <form:errors path="zipCode"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Numer telefonu <input type="text" id="phone" name="phone" value="${phone}">
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label>
                                Data <form:input path="pickUpDate" id="date"/>
                            </label>
                            <label>
                                <span>Data w formacie YYYY-MM-DD</span>
                            </label>
                            <label>
                                <form:errors path="pickUpDate"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Godzina <form:input path="pickUpTime" id="time"/>
                            </label>
                            <label>
                                <span>Czas w formacie hh-mm</span>
                            </label>
                            <label>
                                <form:errors path="pickUpTime"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <form:textarea path="pickUpComment" rows="5" id="comment"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button id="last" type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 6 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul id="firstUl">
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul id="secondUl">
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul id="thirdUl">
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button id="prev" type="button" class="btn prev-step">Wstecz</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>
        </form:form>
    </div>
</section>

<%@include file="../home/footer.jsp" %>

<script src="../../resources/js/app.js"></script>
<script src="../../resources/js/donationForm.js"></script>
</body>
</html>
