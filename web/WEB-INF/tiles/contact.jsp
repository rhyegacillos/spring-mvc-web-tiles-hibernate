<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<div class="container">
    <h3>Send Message</h3>
    <hr>
    <form:form method="post" commandName="message">
        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
        <input type="hidden" name="_eventId" value="send" />
        <div class="form-horizontal">
            <div class="form-group">
                <label class="col-md-2 control-label">Your name:</label>
                <div class="col-md-5">
                    <form:input path="name" value="${fromName}" cssClass="form-control"/>
                    <form:errors path="name" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">Your email:</label>
                <div class="col-md-5">
                    <form:input path="email" value="${fromEmail}" cssClass="form-control"/>
                    <form:errors path="email" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">Subject:</label>
                <div class="col-md-5">
                    <form:input path="subject" cssClass="form-control"/>
                    <form:errors path="subject" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">Your Message:</label>
                <div class="col-md-5">
                    <form:textarea path="content" cssClass="form-control"/>
                    <form:errors path="content" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-5 col-md-offset-2">
                    <button type="submit" class="btn btn-info btn-block">Send Message</button>
                </div>
            </div>
        </div>
    </form:form>
</div>

