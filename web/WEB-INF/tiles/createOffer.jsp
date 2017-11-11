<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <h3>Create New Offer</h3>
    <hr>
    <form:form method="post" action="${pageContext.request.contextPath}/doCreate" modelAttribute="offer">
        <div class="form-horizontal">
            <div class="form-group">
                <input:hidden path="id" />
                <label for="textOffer" class="col-md-2 control-label">Your Offer</label>
                <div class="col-md-5">
                    <form:textarea path="text" id="textOffer" cols="30" rows="10"
                                   cssClass="form-control"></form:textarea>
                    <form:errors path="text" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-5 col-md-offset-2">
                    <button type="submit" class="btn btn-info btn-block">Save Offer</button>
                    <c:if test="${offer.id != 0}">
                        <button type="submit" name="delete" class="btn btn-warning btn-block" id="delete">Delete Offer</button>
                    </c:if>
                </div>
            </div>

        </div>

    </form:form>
</div>

