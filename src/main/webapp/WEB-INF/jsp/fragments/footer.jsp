<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Footer fragment</title>
</head>
<body>
<footer class="bg-dark">

    <fmt:setBundle basename="property.text" var="lang"/>

    <div class="container">
        <a href="?">
            <fmt:message key="web.links.about-us" bundle="${ lang }"/>
        </a>
        <a href="?">
            <fmt:message key="web.links.all-love" bundle="${ lang }"/>
        </a>
        <a href="?">
            <fmt:message key="web.links.our-blog" bundle="${ lang }"/>
        </a>
        <a href="?">
            <fmt:message key="web.links.sponsors" bundle="${ lang }"/>
        </a>

        <form method="post" class="footer__element" action="<c:url value="/page/change-language"/>">
            <input type="hidden" value="ru" name="lang">
            <input type="submit" value="<fmt:message key="web.lang.rus" bundle="${ lang }"/>">
        </form>
        <form method="post" class="footer__element" action="<c:url value="/page/change-language"/>">
            <input type="hidden" value="en" name="lang">
            <input type="submit" value="<fmt:message key="web.lang.en" bundle="${ lang }"/>">
        </form>

    </div>

</footer>
</body>
</html>
