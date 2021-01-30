<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>


<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/sketchy/bootstrap.min.css"
          integrity="sha384-RxqHG2ilm4r6aFRpGmBbGTjsqwfqHOKy1ArsMhHusnRO47jcGqpIQqlQK/kmGy9R" crossorigin="anonymous">
    <title><fmt:message key="login.login" /></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="m-5 p-1" >
    <h1><fmt:message key="login.loginForm" /></h1>
<form class="form-group" method="POST" action="/api/login">
    <br>
    <c:set var = "status" scope = "page" value = "${login_fails.equals('error.wrongCredential') ? 'is-invalid' : ''}"/>
    <input type="hidden" name="command" value="login" />

    <c:if test="${status eq 'is-invalid'}" >
        <div class="is-invalid" style="color: #ff0000">
            <fmt:message  key="error.wrongCredential"/>
        </div>
    </c:if>
    <table >
        <tr>
            <td> <fmt:message key="login.email" /> :</td>
            <td>
                <input type="text" class="form-control form-control-sm ${status}" name="email" value="${email}">
            </td>
        </tr>
        <tr>
            <td> <fmt:message key="login.password" /> :</td>
            <td>
                <input type="password" class="form-control form-control-sm ${status}" name="password" value="${password}"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="<fmt:message key="login.login"/>"/>
            </td>
            <td>
                <input type="reset" value="<fmt:message key="login.clear"/>"/>
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>