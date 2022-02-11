<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cabinet</title>
    <tag:imphead err="${true}"/>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <link rel="stylesheet" href="<c:url value='/static/css/client_cabinet/main.css' />">
</head>
<body>

<fmt:setBundle basename="property.text" var="lang"/>

<div class="main-container">
    <c:import url="fragments/navPanel.jsp"/>

    <main class="container">
        <div class="title-container">
            <fmt:message key="web.text.self-cabinet" bundle="${ lang }"/>
        </div>
        <form action="<c:url value="/page/change-self-data"/>" method="post">
            <div class="data__input">
                <div class="data__input__label">
                    <fmt:message key="web.inputs.username" bundle="${ lang }"/>
                </div>
                <div class="data__input__place">
                    <c:out value="${user.username}"/>
                </div>
                <div class="data__input__label">
                    <fmt:message key="web.inputs.email" bundle="${ lang }"/>
                </div>
                <div class="data__input__place">
                    <c:out value="${user.email}"/>
                </div>
                <div class="data__input__label">
                    <label for="name">
                        <fmt:message key="web.inputs.name" bundle="${ lang }"/>
                    </label>
                </div>
                <div class="data__input__place">
                    <c:if test="${not empty redirect_name_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_name}"/>">
                        <fmt:message key="web.errors.name" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="text" id="name" name="name"
                           placeholder="<fmt:message key="web.inputs.name" bundle="${ lang }"/>"
                           class="form-control" value="<c:out value="${user.name}"/>"
                           required
                           pattern="^[A-Za-zА-Яа-яЁё]+(([',. \\-][A-Za-zА-Яа-яЁё ])?[A-Za-zА-Яа-яЁё]*)*$">
                </div>
                <div class="data__input__label">
                    <label for="surname">
                        <fmt:message key="web.inputs.surname" bundle="${ lang }"/>
                    </label>
                </div>
                <div class="data__input__place">
                    <c:if test="${not empty redirect_surname_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_surname}"/>">
                        <fmt:message key="web.errors.surname" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="text" id="surname" name="surname"
                           placeholder="<fmt:message key="web.inputs.surname" bundle="${ lang }"/>"
                           class="form-control" value="<c:out value="${user.surname}"/>">
                </div>
                <div class="data__input__label">
                    <label for="street">
                        <fmt:message key="web.inputs.street" bundle="${ lang }"/>
                    </label>
                </div>
                <div class="data__input__place">
                    <c:if test="${not empty redirect_street_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_street}"/>">
                        <fmt:message key="web.errors.street" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="text" id="street" name="street"
                           placeholder="<fmt:message key="web.inputs.street" bundle="${ lang }"/>"
                           class="form-control" value="<c:out value="${user.street}"/>">
                </div>
                <div class="data__input__label">
                    <label for="house">
                        <fmt:message key="web.inputs.house" bundle="${ lang }"/>
                    </label>
                </div>
                <div class="data__input__place">
                    <c:if test="${not empty redirect_house_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_house}"/>">
                        <fmt:message key="web.errors.house" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="text" id="house" name="house"
                           placeholder="<fmt:message key="web.inputs.house" bundle="${ lang }"/>"
                           class="form-control" value="<c:out value="${user.house}"/>">
                </div>
                <div class="data__input__label">
                    <label for="room">
                        <fmt:message key="web.inputs.room" bundle="${ lang }"/>
                    </label>
                </div>
                <div class="data__input__place">
                    <c:if test="${not empty redirect_room_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_room}"/>">
                        <fmt:message key="web.errors.room" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="text" id="room" name="room"
                           placeholder="<fmt:message key="web.inputs.room" bundle="${ lang }"/>"
                           class="form-control" value="<c:out value="${user.room}"/>">
                </div>
                <div class="data__input__label">
                    <label for="porch">
                        <fmt:message key="web.inputs.porch" bundle="${ lang }"/>
                    </label>
                </div>
                <div class="data__input__place">
                    <c:if test="${not empty redirect_porch_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_porch}"/>">
                        <fmt:message key="web.errors.porch" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="text" id="porch" name="porch"
                           placeholder="<fmt:message key="web.inputs.porch" bundle="${ lang }"/>"
                           class="form-control" value="<c:out value="${user.porch}"/>"
                           min="1" max="49">
                </div>
                <div class="data__input__label">
                    <label for="floor">
                        <fmt:message key="web.inputs.floor" bundle="${ lang }"/>
                    </label>
                </div>
                <div class="data__input__place">
                    <c:if test="${not empty redirect_floor_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_floor}"/>">
                        <fmt:message key="web.errors.floor" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="text" id="floor" name="floor"
                           placeholder="<fmt:message key="web.inputs.floor" bundle="${ lang }"/>"
                           class="form-control" value="<c:out value="${user.floor}"/>"
                           min="-99" max="99">
                </div>
                <div class="data__input__label">
                    <label for="phone">
                        <fmt:message key="web.inputs.phone" bundle="${ lang }"/>
                    </label>
                </div>
                <div class="data__input__place">
                    <c:if test="${not empty redirect_phone_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_phone}"/>">
                        <fmt:message key="web.errors.phone" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="text" id="phone" name="phone"
                           placeholder="<fmt:message key="web.inputs.phone" bundle="${ lang }"/>"
                           class="form-control" value="<c:out value="${user.phone}"/>">
                </div>
                <div class="data__input__label">
                    <label for="old_password">
                        <fmt:message key="web.inputs.old-password" bundle="${ lang }"/>
                    </label>
                </div>
                <div class="data__input__place">
                    <c:if test="${not empty redirect_old_password_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_password}"/>">
                        <fmt:message key="web.errors.password" bundle="${ lang }"/>
                    </span>
                    </c:if>

                    <input type="text" id="old_password" name="old_password" disabled
                           placeholder="<fmt:message key="web.inputs.password" bundle="${ lang }"/>"
                           class="form-control" required>
                    <button type="button" id="change_password" class="btn orange__bg">Change</button>
                </div>
                <div class="data__input__label">
                    <label for="new_password">
                        <fmt:message key="web.inputs.new-password" bundle="${ lang }"/>
                    </label>
                </div>
                <div class="data__input__place">
                    <c:if test="${not empty redirect_new_password_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_new_password}"/>">
                        <fmt:message key="web.errors.password" bundle="${ lang }"/>
                    </span>
                    </c:if>

                    <input type="text" id="new_password" name="new_password" disabled
                           placeholder="<fmt:message key="web.inputs.new-password" bundle="${ lang }"/>"
                           class="form-control"
                           pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" required>
                </div>
            </div>
            <div>
                <button type="submit" class="btn orange__bg mt-5">
                    <fmt:message key="web.inputs.submit" bundle="${ lang }"/>
                </button>
            </div>
        </form>
        <br><br><br>
        <div class="log__out">
            <a href="<c:url value="/page/logout"/>">
                <button class="btn orange__hover">
                    <fmt:message key="web.links.logout" bundle="${ lang }"/>
                </button>
            </a>
        </div>
    </main>

    <c:import url="fragments/footer.jsp"/>

</div>

<script src="<c:url value="/static/js/cabinet/main.js"/>"></script>

<tag:impfoot/>

</body>
</html>