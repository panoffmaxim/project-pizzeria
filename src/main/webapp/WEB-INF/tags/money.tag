<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="input" required="true"
              type="java.lang.Integer"
              description="Integer to parse to money value" %>
<fmt:setBundle basename="property.text" var="lang"/>
<fmt:formatNumber type="number" value="${input}"/> <fmt:message key="web.text.kzt" bundle="${ lang }"/>