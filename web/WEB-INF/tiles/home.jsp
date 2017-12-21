<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h2>Offers List</h2>
<table class="table table-bordered table-striped table-hover text-center">
    <thead>
    <tr class="bg-primary">
        <th>Name</th>
        <th>Email</th>
        <th>Text</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="offer" items="${offers}">
        <tr>
            <td>${offer.user.name}</td>
            <td><a href="<c:url value='/message?uid=${offer.username}' />">contact</a> </td>
            <td>${offer.text}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p/>
<c:choose>
    <c:when test="${hasOffer}">
        <p><a href="${pageContext.request.contextPath}/createOffer">Edit / Delete your current offer</a></p>
    </c:when>
    <c:otherwise>
        <p><a href="${pageContext.request.contextPath}/createOffer">Add New Offer</a></p>
    </c:otherwise>
</c:choose>


<sec:authorize access="hasRole('ROLE_ADMIN')">
    <p><a href="${pageContext.request.contextPath}/admin">Admin</a></p>
</sec:authorize>


