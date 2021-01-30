
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>


<html lang="${sessionScope.lang}"  user="${user}">

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/sketchy/bootstrap.min.css"
          integrity="sha384-RxqHG2ilm4r6aFRpGmBbGTjsqwfqHOKy1ArsMhHusnRO47jcGqpIQqlQK/kmGy9R" crossorigin="anonymous">

    <title>Title</title>

</head>
<body>
    <jsp:include page="../header.jsp"/>
    Success
    <br>
    <label><fmt:message key="login.lastName"/></label>: <label>${user.lastName}</label><br>
    <label><fmt:message key="login.firstName"/></label>: <label>${user.firstName}</label><br>
</body>
</html>
