<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="links">
    <c:choose>
        <c:when test="${hasOffer}">
            <a href="${pageContext.request.contextPath}/createOffer">Edit / Delete your current offer</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/createOffer">Add New Offer</a>
        </c:otherwise>
    </c:choose>


    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="${pageContext.request.contextPath}/admin">Admin</a>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <span> <a href="${pageContext.request.contextPath}/messages">Messages(<span id="numberMessages">0</span>)</a> </span>
    </sec:authorize>
</div>
