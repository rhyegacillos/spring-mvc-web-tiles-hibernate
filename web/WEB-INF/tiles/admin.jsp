<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <h3>List Of Users</h3>

    <table class="table table-bordered table-striped table-hover text-center">
        <thead class="bg-primary">
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>Enabled</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.authority}</td>
                <td>${user.enabled}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

