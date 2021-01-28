<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/sketchy/bootstrap.min.css"
      integrity="sha384-RxqHG2ilm4r6aFRpGmBbGTjsqwfqHOKy1ArsMhHusnRO47jcGqpIQqlQK/kmGy9R" crossorigin="anonymous">
<title>Home</title>
<body >
    <style>
        body {background: url("static.images/111.jpg") no-repeat center center fixed;}
    </style>
    <jsp:include page="header.jsp"/>
    <div class="m-5 p-1 width: max-content ">
        <br>
        <br>
        <br>
        <br>
        <h1 style="margin: 0 auto; width: max-content"> <fmt:message key="home.message1" /></h1>
    </div>
</body>
</html>
