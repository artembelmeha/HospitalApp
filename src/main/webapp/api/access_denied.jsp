<%--
  Created by IntelliJ IDEA.
  User: artembelmeha
  Date: 30/01/2021
  Time: 10:50 am
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/sketchy/bootstrap.min.css"
          integrity="sha384-RxqHG2ilm4r6aFRpGmBbGTjsqwfqHOKy1ArsMhHusnRO47jcGqpIQqlQK/kmGy9R" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<br>
<h2 class="text-center"> <fmt:message key="error.accessDenied"/> </h2>

</body>
</html>
