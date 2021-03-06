<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="group" scope="request" type="kz.epam.pizzeria.entity.db.impl.ProductGroup"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="products" scope="request" type="java.util.List<kz.epam.pizzeria.entity.db.impl.Product>"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <tag:imphead footer="${true}" navbar="${true}" btns="${true}" err="${true}"/>

    <title>Edit product group</title>

    <link rel="stylesheet" href="<c:url value='/static/css/admin/create_product_group/main.css' />">

</head>
<body>

<fmt:setBundle basename="property.text" var="lang"/>

<c:import url="../fragments/navPanel.jsp"/>
<header>
    <h1>
        <fmt:message key="web.text.edit-product-group" bundle="${ lang }"/>
    </h1>
</header>

<main class="container">
    <div class="create__data">
        <c:if test="${not empty redirect_unknown_error}">
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <fmt:message key="web.errors.unknown-error" bundle="${ lang }"/>
            </div>
        </c:if>
        <form action="<c:url value="/page/admin/edit-product-group"/>" method="post" enctype="multipart/form-data">

            <div class="id_row">
                <label for="id">
                    <fmt:message key="web.tab.identifier" bundle="${ lang }"/>
                </label>
                <input class="form-control" type="number" id="id" name="id" value="${group.id}" readonly>
            </div>

            <div class="name">
                <label for="name">
                    <fmt:message key="web.inputs.name" bundle="${ lang }"/>
                </label>
                <c:if test="${not empty redirect_name_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_name}"/>">
                        <fmt:message key="web.errors.field" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="name" name="name"
                       placeholder="<fmt:message key="web.inputs.name" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${group.name}"/>"
                       pattern="[A-Za-z??-????-??????\s\d-]{1,30}"
                       required>
            </div>

            <div class="description">
                <label for="description">
                    <fmt:message key="web.inputs.description" bundle="${ lang }"/>
                </label>
                <c:if test="${not empty redirect_description_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_description}"/>">
                        <fmt:message key="web.errors.field" bundle="${ lang }"/>
                    </span>
                </c:if>
                <input type="text" id="description" name="description"
                       placeholder="<fmt:message key="web.inputs.description" bundle="${ lang }"/>"
                       class="form-control" value="<c:out value="${group.description}"/>"
                       required pattern="[^\n]{1,200}">
            </div>

            <div class="custom-file">
                <label class="custom-file-label">
                    <c:if test="${not empty redirect_file_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_file}"/>">
                        <fmt:message key="web.errors.field" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input id="file" type="file" name="file">
                </label>
            </div>

            <div class="type">
                <label for="type">
                    <fmt:message key="web.inputs.type" bundle="${ lang }"/>
                </label>
                <c:if test="${not empty redirect_type_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_type}"/>">
                        <fmt:message key="web.errors.field" bundle="${ lang }"/>
                    </span>
                </c:if>
                <select class="form-control" id="type" name="type">
                    <option><c:out value="${group.type}"/></option>
                    <c:forEach var="t" items="${types}">
                        <option><c:out value="${t}"/></option>
                    </c:forEach>
                </select>
            </div>

            <div class="items">
                <c:if test="${not empty redirect_products_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_products}"/>">
                        <fmt:message key="web.errors.field" bundle="${ lang }"/>
                    </span>
                </c:if>
                <%--                <c:if test="${group.products!=null}">--%>
                <c:forEach items="${group.products}" var="p">
                    <div>
                        <label>
                            <span>
                                ${p.weight}
                                <fmt:message key="web.gram" bundle="${ lang }"/>
                                ,
                            </span>
                            <span>
                                    <tag:money input="${p.price}"/>
                            </span>
                            <input type="checkbox" value="${p.id}" checked name="products">
                        </label>
                    </div>
                </c:forEach>
                <c:forEach items="${products}" var="p">
                    <div>
                        <label>
                            <span>${p.weight} <fmt:message key="web.gram" bundle="${ lang }"/>,</span>
                            <span><tag:money input="${p.price}"/></span>
                            <input type="checkbox" value="${p.id}" name="products">
                        </label>
                    </div>
                </c:forEach>
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

<c:import url="../fragments/footer.jsp"/>

<tag:impfoot/>

</body>
</html>