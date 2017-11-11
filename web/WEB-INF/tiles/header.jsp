<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="<c:url value="/"/>" class="title">Offers</a>

<sec:authorize access="isAuthenticated()">
    <a href="<c:url value="/logout"/>" class="login">Logout</a>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <a href="<c:url value="/login"/>" class="login">Login</a>
</sec:authorize>