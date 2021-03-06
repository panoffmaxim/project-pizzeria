<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="product" scope="request" type="kz.epam.pizzeria.entity.db.impl.Product"/>
<jsp:useBean id="groups" scope="request" type="java.util.List<kz.epam.pizzeria.entity.db.impl.ProductGroup>"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <title>Edit product</title>
    <tag:imphead footer="${true}" navbar="${true}" btns="${true}" err="${true}"/>

    <link rel="stylesheet" href="<c:url value='/static/css/admin/edit_product/main.css' />">

</head>
<body>

<fmt:setBundle basename="property.text" var="lang"/>

<c:import url="../fragments/navPanel.jsp"/>
<header>
    <h1>
        <fmt:message key="web.text.edit-product" bundle="${ lang }"/>
    </h1>
</header>

<main class="container">
    <c:if test="${product!=null}">
        <div class="create__data">
            <c:if test="${not empty redirect_unknown_error}">
                <div class="alert alert-danger alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <fmt:message key="web.errors.unknown.product" bundle="${ lang }"/>
                </div>
            </c:if>
            <form action="<c:url value="/page/admin/edit-product"/>" method="post">
                <div class="id">
                    <label for="id">
                        <fmt:message key="web.tab.identifier" bundle="${ lang }"/>
                    </label>
                    <input type="number" id="id" name="id"
                           placeholder="<fmt:message key="web.tab.identifier" bundle="${ lang }"/>"
                           class="form-control" value="${product.id}" readonly>
                </div>

                <div class="product_group">
                    <label for="product_group">
                        <fmt:message key="web.inputs.product-group" bundle="${ lang }"/>
                    </label>
                    <c:if test="${not empty redirect_product_group_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_product_group}"/>">
                        <fmt:message key="web.errors.field" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <select class="form-control" id="product_group" name="product_group">
                        <c:if test="${product.productGroup!=null}">
                            <option value="${product.productGroup.id}"><c:out
                                    value="${product.productGroup.name}"/></option>
                            <option value="">
                                <fmt:message key="web.text.empty" bundle="${ lang }"/>
                            </option>
                        </c:if>
                        <c:if test="${product.productGroup == null}">
                            <option value="">
                                <fmt:message key="web.text.empty" bundle="${ lang }"/>
                            </option>
                        </c:if>
                        <c:forEach var="g" items="${groups}">
                            <option value="${g.id}"><c:out value="${g.name}"/></option>
                        </c:forEach>
                    </select>
                </div>

                <div class="alert alert-warning alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <fmt:message key="web.warning.price-integer" bundle="${ lang }"/>
                </div>

                <div class="price">
                    <label for="price">
                        <fmt:message key="web.inputs.price" bundle="${ lang }"/>
                    </label>
                    <c:if test="${not empty redirect_price_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_price}"/>">
                        <fmt:message key="web.errors.field" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="number" id="price" name="price"
                           placeholder="<fmt:message key="web.inputs.price" bundle="${ lang }"/>"
                           class="form-control" value="${product.price}"
                           min="0" max="1000000000" required>
                </div>

                <div class="weight">
                    <label for="weight">
                        <fmt:message key="web.tab.weight" bundle="${ lang }"/>
                    </label>
                    <c:if test="${not empty redirect_weight_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ lang }"/>: <c:out value="${redirect_weight}"/>">
                        <fmt:message key="web.errors.field" bundle="${ lang }"/>
                    </span>
                    </c:if>
                    <input type="number" id="weight" name="weight"
                           placeholder="<fmt:message key="web.tab.weight" bundle="${ lang }"/>"
                           class="form-control" value="${product.weight}"
                           required min="1" max="1000000">
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