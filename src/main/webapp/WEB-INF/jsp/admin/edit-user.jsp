<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" scope="request" type="kz.epam.pizzeria.entity.db.impl.User"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <title>Edit user</title>
    <tag:imphead footer="${true}" navbar="${true}" btns="${true}" err="${true}"/>

    <link rel="stylesheet" href="<c:url value='/static/css/admin/create_user.css' />">

</head>
<body>

<fmt:setBundle basename="property.text" var="lang"/>

<c:import url="../fragments/navPanel.jsp"/>
<header>
    <h1>
        <fmt:message key="web.text.edit-user" bundle="${ lang }"/>
    </h1>
</header>

<main class="container">
    <c:if test="${user!=null}">
        <div class="create__data">
            <c:if test="${not empty redirect_unknown_error}">
                <div class="alert alert-danger alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <fmt:message key="web.errors.unknown-error" bundle="${ lang }"/>
                    <fmt:message key="web.errors.user-error" bundle="${ lang }"/>
                </div>
            </c:if>
            <form action="<c:url value="/page/admin/edit-user"/>" method="post">
                <div class="id">
                    <label for="id">
                        <fmt:message key="web.tab.identifier" bundle="${ lang }"/>
                    </label>
                    <input type="number" id="id" name="id"
                           placeholder="<fmt:message key="web.tab.identifier" bundle="${ lang }"/>"
                           class="form-control" value="${user.id}" readonly>
                </div>

                <div class="username">
                    <label for="username">
                        <fmt:message key="web.inputs.username" bundle="${ lang }"/>
                    </label>
                    <c:if test="${not empty redirect_username_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_username}"/>">
                        <fmt:message key="web.errors.username" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="text" id="username" name="username"
                           placeholder="<fmt:message key="web.inputs.username" bundle="${ lang }"/>"
                           class="form-control" value="<c:out value="${user.username}"/>"
                           required pattern="[A-Za-z??-????-??????\d]{1,20}">
                </div>

                <div class="role__row">
                    <label for="role">
                        <fmt:message key="web.tab.role" bundle="${ lang }"/>
                    </label>
                    <c:if test="${not empty redirect_role_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <%--@elvariable id="redirect_role" type="kz"--%>
                          <c:out value="${redirect_role}"/>">
                        <fmt:message key="web.errors.field" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <select class="form-control" id="role" name="role">
                        <option>${user.role}</option>
                        <%--@elvariable id="roles" type="java.util.List"--%>
                        <c:forEach var="role" items="${roles}">
                            <option>${role}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="name__row">
                    <label for="name">
                        <fmt:message key="web.inputs.name" bundle="${ lang }"/>
                    </label>

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
                           pattern="^[A-Za-z??-????-??????]+(([',. \\-][A-Za-z??-????-?????? ])?[A-Za-z??-????-??????]*)*$">
                </div>

                <div class="surname__row">
                    <label for="surname">
                        <fmt:message key="web.inputs.surname" bundle="${ lang }"/>
                    </label>
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

                <div class="street">
                    <label for="street">
                        <fmt:message key="web.inputs.street" bundle="${ lang }"/>
                    </label>
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
                <div class="house">
                    <label for="house">
                        <fmt:message key="web.inputs.house" bundle="${ lang }"/>
                    </label>
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
                <div class="room">
                    <label for="room">
                        <fmt:message key="web.inputs.room" bundle="${ lang }"/>
                    </label>
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

                <div class="porch">
                    <label for="porch">
                        <fmt:message key="web.inputs.porch" bundle="${ lang }"/>
                    </label>
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

                <div class="floor">
                    <label for="floor">
                        <fmt:message key="web.inputs.floor" bundle="${ lang }"/>
                    </label>
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

                <div class="phone">
                    <label for="phone">
                        <fmt:message key="web.inputs.phone" bundle="${ lang }"/>
                    </label>
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

                <div class="email">
                    <label for="email">
                        <fmt:message key="web.inputs.email" bundle="${ lang }"/>
                    </label>
                    <c:if test="${not empty redirect_email_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_email}"/>">
                        <fmt:message key="web.errors.email" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="email" id="email" name="email"
                           placeholder="<fmt:message key="web.inputs.email" bundle="${ lang }"/>"
                           class="form-control" value="<c:out value="${user.email}"/>">
                </div>

                <div class="isBlocked">
                    <label for="isBlocked">
                        <fmt:message key="web.inputs.is-blocked" bundle="${ lang }"/>
                    </label>
                    <c:if test="${user.blocked}">
                        <input type="checkbox" checked name="isBlocked" id="isBlocked" value="1">
                    </c:if>
                    <c:if test="${!user.blocked}">
                        <input type="checkbox" name="isBlocked" id="isBlocked" value="1">
                    </c:if>
                </div>
                <div class="submit">
                    <label for="submit"></label>
                    <button type="submit" id="submit" class="btn orange__bg">
                        <fmt:message key="web.inputs.submit" bundle="${ lang }"/>
                    </button>
                </div>

            </form>
        </div>
    </c:if>
</main>

<c:import url="../fragments/footer.jsp"/>


<tag:impfoot/>

</body>
</html>