<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="users" scope="request" type="java.util.List<kz.epam.pizzeria.entity.db.impl.User>"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <title>Users</title>

    <tag:imphead footer="${true}" navbar="${true}" btns="${true}"/>

    <link rel="stylesheet" href="<c:url value='/static/css/admin/user_list/main.css' />">

</head>
<body>

<fmt:setBundle basename="property.text" var="lang"/>

<c:import url="../fragments/navPanel.jsp"/>

<header>
    <h1>
        <fmt:message key="web.links.user-list" bundle="${ lang }"/>
    </h1>
</header>

<main class="text-center container">
    <div class="create__new mb-5">
        <a href="<c:url value="/page/admin/create-user"/>">
            <button class="btn white__bg__orange">
                <fmt:message key="web.btn.create-user" bundle="${ lang }"/>
            </button>
        </a>
    </div>

    <div class="text-center">
        <table class="table">
            <thead>
            <tr>
                <th>
                    <fmt:message key="web.tab.identifier" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.is-blocked" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.role" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.username" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.name" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.inputs.surname" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.address" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.details" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.block" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.unblock" bundle="${ lang }"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="u">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.blocked}</td>
                    <td>${u.role}</td>
                    <td><c:out value="${u.username}"/></td>
                    <td><c:out value="${u.name}"/></td>
                    <td><c:out value="${u.surname}"/></td>
                    <td>
                        <span><c:out value="${u.street}"/></span>
                        &nbsp;
                        <span><c:out value="${u.house}"/></span>
                        &nbsp;
                        <span><c:out value="${u.room}"/></span>
                        &nbsp;
                        <span><c:out value="${u.porch}"/></span>
                        &nbsp;
                        <span><c:out value="${u.floor}"/></span>
                        &nbsp;
                    </td>
                    <td>
                        <a href="<c:url value="/page/admin/edit-user/${u.id}"/>">
                            <button class="btn orange__bg">
                                <fmt:message key="web.tab.details" bundle="${ lang }"/>
                            </button>
                        </a>
                    </td>
                    <td>
                        <form action="<c:url value="/page/admin/block/${u.id}"/>" method="post">
                            <button class="btn orange__bg" type="submit">
                                <fmt:message key="web.tab.block" bundle="${ lang }"/>
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="<c:url value="/page/admin/unblock/${u.id}"/>" method="post">
                            <button class="btn orange__bg" type="submit">
                                <fmt:message key="web.tab.unblock" bundle="${ lang }"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <tag:pagination paginationMap="${paginationMap}" url="/page/admin/user-list" current="${param.pagination}"/>
</main>
<c:import url="../fragments/footer.jsp"/>

<tag:impfoot/>

</body>
</html>
