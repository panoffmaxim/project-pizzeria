<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="groups" scope="request" type="java.util.List<kz.epam.pizzeria.entity.db.impl.ProductGroup>"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <title>Product group list</title>

    <tag:imphead footer="${true}" navbar="${true}" btns="${true}"/>


    <link rel="stylesheet" href="<c:url value='/static/css/admin/product_group_list/main.css' />">

</head>
<body>

<fmt:setBundle basename="property.text" var="lang"/>


<c:import url="../fragments/navPanel.jsp"/>
<header>
    <h1>
        <fmt:message key="web.links.product-groups" bundle="${ lang }"/>
    </h1>
</header>

<main class="container">

    <div class="create__new mb-5">
        <a href="<c:url value="/page/admin/create-product-group"/>">
            <button class="btn white__bg__orange">
                <fmt:message key="web.btn.create-new-product-group" bundle="${ lang }"/>
            </button>
        </a>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th>
                <fmt:message key="web.tab.photo" bundle="${ lang }"/>
            </th>
            <th>
                <fmt:message key="web.tab.identifier" bundle="${ lang }"/>
            </th>
            <th>
                <fmt:message key="web.tab.name" bundle="${ lang }"/>
            </th>
            <th>
                <fmt:message key="web.tab.description" bundle="${ lang }"/>
            </th>
            <th>
                <fmt:message key="web.tab.type" bundle="${ lang }"/>
            </th>
            <th>
                <fmt:message key="web.tab.disabled" bundle="${ lang }"/>
            </th>
            <th>
                <fmt:message key="web.tab.disable" bundle="${ lang }"/>
            </th>
            <th>
                <fmt:message key="web.tab.enable" bundle="${ lang }"/>
            </th>
            <th>
                <fmt:message key="web.tab.edit" bundle="${ lang }"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${groups}" var="p">
            <tr>
                <td><img src="<c:url value="/static/img/${p.photoName}"/>" alt="Photo"></td>
                <td>${p.id}</td>
                <td><c:out value="${p.name}"/></td>
                <td><c:out value="${p.description}"/></td>
                <td>${p.type}</td>
                <td>${p.disabled}</td>
                <td>
                    <form action="<c:url value="/page/admin/disable-product-group"/>" method="post">
                        <button class="btn orange__bg" type="submit">
                            <fmt:message key="web.tab.disable" bundle="${ lang }"/>
                        </button>
                        <label>
                            <input type="number" hidden name="id" value="${p.id}">
                        </label>
                    </form>
                </td>

                <td>
                    <form action="<c:url value="/page/admin/enable-product-group"/>" method="post">
                        <button class="btn orange__bg" type="submit">
                            <fmt:message key="web.tab.enable" bundle="${ lang }"/>
                        </button>
                        <label>
                            <input type="number" hidden name="id" value="${p.id}">
                        </label>
                    </form>
                </td>
                <td>
                    <a href="<c:url value="/page/admin/edit-product-group/${p.id}"/>">
                        <button class="btn orange__bg">
                            <fmt:message key="web.tab.edit" bundle="${ lang }"/>
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <tag:pagination paginationMap="${paginationMap}" url="/page/admin/product-group-list" current="${param.pagination}"/>
</main>

<c:import url="../fragments/footer.jsp"/>

<tag:impfoot/>

</body>
</html>