<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Offers List</h2>
<table class="table table-bordered table-striped table-hover text-center">
    <thead>
    <tr class="bg-primary">
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Text</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="offer" items="${offers}">
        <tr>
            <td>${offer.id}</td>
            <td>${offer.user.name}</td>
            <td>${offer.user.email}</td>
            <td>${offer.text}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
