<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <title>Registration</title>
    <tag:imphead footer="${true}" navbar="${true}" btns="${true}" err="${true}"/>

    <link rel="stylesheet" href="<c:url value='/static/css/registration/main.css' />">

</head>
<body>

<fmt:setBundle basename="property.text" var="lang"/>

<c:import url="fragments/navPanel.jsp"/>

<header>
    <h1>
        <fmt:message key="web.text.registration" bundle="${ lang }"/>
    </h1>
</header>

<main class="container">
    <div class="create__data">
        <c:if test="${not empty redirect_check_email}">
            <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <fmt:message key="web.check.email" bundle="${ lang }"/>
            </div>
        </c:if>
        <c:if test="${not empty redirect_email_send_error}">
            <div class="alert alert-info alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <fmt:message key="web.check.email-error" bundle="${ lang }"/>
            </div>
        </c:if>
        <form action="<c:url value="/page/registration-begin"/>" method="post">
            <div class="email">
                <label for="email">
                    <fmt:message key="web.inputs.email" bundle="${ lang }"/>
                    :
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="<fmt:message key="web.inputs.obligatory" bundle="${ lang }"/>"> *
                        </a>
                    </span>
                </label>
                <c:if test="${not empty redirect_email_error}">
                    <span class="error__message">
                        <fmt:message key="web.errors.email" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="email" id="email" name="email"
                       placeholder="<fmt:message key="web.inputs.email" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${redirect_email}"/>">
            </div>

            <div class="phone">
                <label for="phone">
                    <fmt:message key="web.inputs.phone" bundle="${ lang }"/>
                    :
                </label>
                <c:if test="${not empty redirect_phone_error}">
                    <span class="error__message">
                        <fmt:message key="web.errors.phone" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="phone" name="phone"
                       placeholder="<fmt:message key="web.inputs.phone" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${redirect_phone}"/>"
                       pattern="\d{9}" required>
            </div>

            <div class="username">
                <label for="username">
                    <fmt:message key="web.inputs.username" bundle="${ lang }"/>
                    :
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top">
                           <fmt:message key="web.inputs.obligatory" bundle="${ lang }"/>
                        </a>
                    </span>
                </label>
                <c:if test="${not empty redirect_username_error}">
                    <span class="error__message">
                        <fmt:message key="web.errors.username" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="username" name="username"
                       placeholder="<fmt:message key="web.inputs.username" bundle="${ lang }"/>"
                       class="form-control" value="${redirect_username}"
                       required pattern="[A-Za-z??-????-??????\d]{1,20}">
            </div>

            <div class="password">
                <label for="password">
                    <fmt:message key="web.inputs.password" bundle="${ lang }"/>:
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="<fmt:message key="web.inputs.obligatory" bundle="${ lang }"/>"> *
                        </a>
                    </span>
                </label>
                <c:if test="${not empty redirect_password_error}">
                    <span class="error__message">
                        <fmt:message key="web.errors.password" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="password" name="password"
                       placeholder="<fmt:message key="web.inputs.password" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${redirect_password}"/>"
                       required>
            </div>

            <div class="password__repeat">
                <label for="password__repeat"><fmt:message key="web.inputs.repeat-password" bundle="${ lang }"/>
                    :
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="<fmt:message key="web.inputs.obligatory" bundle="${ lang }"/>"> *
                        </a>
                    </span>
                </label>
                <input type="text" id="password__repeat" name="password_repeat"
                       placeholder="<fmt:message key="web.inputs.password" bundle="${ lang }"/>"
                       class="form-control"
                       required>
            </div>
            <br>
            <br>
            <br>
            <div class="name__row">
                <label for="name"><fmt:message key="web.inputs.name" bundle="${ lang }"/>:
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="<fmt:message key="web.inputs.obligatory" bundle="${ lang }"/>"> *
                        </a>
                    </span>
                </label>
                <c:if test="${not empty redirect_name_error}">
                    <span class="error__message">
                        <fmt:message key="web.errors.name" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="name" name="name"
                       placeholder="<fmt:message key="web.inputs.name" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${redirect_name}"/>"
                       required
                       pattern="^[A-Za-z??-????-??????]+(([',. \\-][A-Za-z??-????-?????? ])?[A-Za-z??-????-??????]*)*$">
            </div>

            <div class="surname__row">
                <label for="surname">
                    <fmt:message key="web.inputs.surname" bundle="${ lang }"/>:
                </label>
                <c:if test="${not empty redirect_surname_error}">
                    <span class="error__message">
                        <fmt:message key="web.errors.surname" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="surname" name="surname"
                       placeholder="<fmt:message key="web.inputs.surname" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${redirect_surname}"/>"
                       pattern="[A-Za-z??-????-??????-]{1,20}">
            </div>

            <div class="street">
                <label for="street">
                    <fmt:message key="web.inputs.street" bundle="${ lang }"/>:
                </label>
                <c:if test="${not empty redirect_street_error}">
                    <span class="error__message">
                        <fmt:message key="web.errors.street" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="street" name="street"
                       placeholder="<fmt:message key="web.inputs.street" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${redirect_street}"/>">
            </div>
            <div class="house">
                <label for="house">
                    <fmt:message key="web.inputs.house" bundle="${ lang }"/>
                    :</label>
                <c:if test="${not empty redirect_house_error}">
                    <span class="error__message">
                        <fmt:message key="web.errors.house" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="house" name="house"
                       placeholder="<fmt:message key="web.inputs.house" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${redirect_house}"/>">
            </div>
            <div class="room">
                <label for="room">
                    <fmt:message key="web.inputs.room" bundle="${ lang }"/>:
                </label>
                <c:if test="${not empty redirect_room_error}">
                    <span class="error__message">
                        <fmt:message key="web.errors.room" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="room" name="room"
                       placeholder="<fmt:message key="web.inputs.room" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${redirect_room}"/>">
            </div>

            <div class="porch">
                <label for="porch">
                    <fmt:message key="web.inputs.porch" bundle="${ lang }"/> :
                </label>
                <c:if test="${not empty redirect_porch_error}">
                    <span class="error__message">
                        <fmt:message key="web.errors.porch" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="porch" name="porch"
                       placeholder="<fmt:message key="web.inputs.porch" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${redirect_porch}"/>"
                       min="1" max="49">
            </div>

            <div class="floor">
                <label for="floor">
                    <fmt:message key="web.inputs.floor" bundle="${ lang }"/>:
                </label>
                <c:if test="${not empty redirect_floor_error}">
                    <span class="error__message">
                        <fmt:message key="web.errors.floor" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="floor" name="floor"
                       placeholder="<fmt:message key="web.inputs.floor" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${redirect_floor}"/>"
                       min="-99" max="99">
            </div>

            <div class="submit">
                <label for="submit"></label>
                <button type="submit" id="submit" class="btn orange__bg">
                    <fmt:message key="web.inputs.submit" bundle="${ lang }"/>
                </button>
            </div>

        </form>
    </div>
</main>

<c:import url="fragments/footer.jsp"/>

<tag:impfoot/>

<script src="<c:url value="/static/js/registration/main.js"/>"></script>

</body>
</html>