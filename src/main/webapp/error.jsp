<%--
  Created by IntelliJ IDEA.
  User: artembelmeha
  Date: 06/02/2021
  Time: 3:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>   <fmt:message key="error.general" /></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="m-5 p-1" >
    <br>
    <fmt:message key="error.general" />
</div>
</body>
</html>
