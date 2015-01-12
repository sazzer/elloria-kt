<%@ page contentType="text/javascript;charset=UTF-8" language="java" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
require.config({
    "paths": {
        <c:forEach items="${modules}" var="entry" varStatus="status">
            <c:out value="${entry.key}" />: "<c:out value="${entry.value}" />"<c:if test="${not status.last}">,</c:if>
        </c:forEach>
    }
});