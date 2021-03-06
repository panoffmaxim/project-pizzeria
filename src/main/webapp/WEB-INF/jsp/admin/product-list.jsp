<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="products" scope="request" type="java.util.List<kz.epam.pizzeria.entity.db.impl.Product>"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <title>Users</title>

    <tag:imphead footer="${true}" navbar="${true}" btns="${true}"/>

    <link rel="stylesheet" href="<c:url value='/static/css/admin/product_list/main.css' />">

</head>
<body>

<fmt:setBundle basename="property.text" var="lang"/>

<c:import url="../fragments/navPanel.jsp"/>

<header>
    <h1>
        <fmt:message key="web.links.products" bundle="${ lang }"/>
    </h1>
</header>

<main class="text-center container">
    <div class="create__new mb-5">
        <a href="<c:url value="/page/admin/create-product"/>">
            <button class="btn white__bg__orange">
                <fmt:message key="web.text.create-product" bundle="${ lang }"/>
            </button>
        </a>
    </div>

    <div>
        <table class="table">
            <thead>
            <tr>
                <th>
                    <fmt:message key="web.tab.identifier" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.price" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.weight" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.product-group" bundle="${ lang }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.edit" bundle="${ lang }"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td><tag:money input="${p.price}"/></td>
                    <td>${p.weight}</td>
                    <td><c:out value="${p.productGroup.name}"/></td>
                    <td>
                        <a href="<c:url value="/page/admin/edit-product/${p.id}"/>">
                            <button class="btn orange__bg">
                                <fmt:message key="web.tab.edit" bundle="${ lang }"/>
                            </button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <tag:pagination paginationMap="${paginationMap}" url="/page/admin/product-list" current="${param.pagination}"/>
</main>
<c:import url="../fragments/footer.jsp"/>

<tag:impfoot/>

</body>
</html>
