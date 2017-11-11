<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <h3>Create New Account</h3>
    <hr>
    <form:form id="addNewAccount" method="post" action="${pageContext.request.contextPath}/createAccount"
               modelAttribute="user">
        <div class="form-horizontal">
            <div class="form-group">
                <label class="col-md-2 control-label">Username</label>
                <div class="col-md-5">
                    <form:input path="username" cssClass="form-control"/>
                    <form:errors path="username" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">Name</label>
                <div class="col-md-5">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">Email</label>
                <div class="col-md-5">
                    <form:input path="email" cssClass="form-control"/>
                    <form:errors path="email" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label for="pass" class="col-md-2 control-label">Password</label>
                <div class="col-md-5">
                    <form:password path="password" id="pass" cssClass="form-control"></form:password>
                    <form:errors path="password" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label for="confirmPass" class="col-md-2 control-label">Confirm Password</label>
                <div class="col-md-5">
                    <input type="password" name="confirmPass" id="confirmPass" class="form-control"/>
                    <div id="passwordMatch"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-5 col-md-offset-2">
                    <button type="submit" class="btn btn-info btn-block">Create Account</button>
                </div>
            </div>
        </div>
    </form:form>
</div>


