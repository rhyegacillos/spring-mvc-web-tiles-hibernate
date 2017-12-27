<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <title><tiles:insertAttribute name="title" /> </title>
    <link type="text/css" rel="stylesheet" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/custom.css">
</head>
<body>
    <div class="header">
        <tiles:insertAttribute name="header" />
    </div>
    <div class="toolbar">
        <tiles:insertAttribute name="toolbar" />
    </div>
    <div class="content">
        <tiles:insertAttribute name="content" />
    </div>
    <div class="footer">
        <tiles:insertAttribute name="footer" />
    </div>


    <script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
    <script src="/static/js/custom.js"></script>
</body>
</html>
