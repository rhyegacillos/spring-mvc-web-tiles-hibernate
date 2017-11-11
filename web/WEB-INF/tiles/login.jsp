<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body onload='document.f.username.focus();'>
<div class="container">
    <h3>Login with Username and Password</h3>
    <hr>
    <c:if test="${param.error != null}" >
        <p class="text-danger loginError">Invalid Credentials</p>
    </c:if>
    <form name='f' action='${pageContext.request.contextPath}/login' method='POST'>
        <div class="form-horizontal">
            <div class="form-group">
                <label class="col-md-1">Username:</label>
                <div class="col-md-4">
                    <input name="username" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-1">Password:</label>
                <div class="col-md-4">
                    <input type="password" name="password" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-4 col-md-offset-1">
                    <button class="btn btn-info">LOGIN</button>
                    <a href="${pageContext.request.contextPath}/newAccount" class="btn btn-info">Create New Account</a>
                    <input name="remember-me" id="remember" type="checkbox" checked>
                    <label for="remember">Remember Me</label>
                </div>

            </div>
        </div>
    </form>
</div>

</body>

