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
<body style ="background: #eee;" >
<jsp:include page="header.jsp"/>
<div class="m-5 p-1" >
<form name="loginForm" method="POST" action="controller">
    <h1><fmt:message key="login.loginForm" /></h1>
    <br>
    <table >
        <tr>
            <td> <fmt:message key="login.email" /> :</td>
            <td>
                <input type="text" name="email" size="24"/>
            </td>
        </tr>
        <tr>
            <td> <fmt:message key="login.password" /> :</td>
            <td>
                <input type="password" name="password" size="24"/>
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
    <input type="hidden" name="command" value="login" />
</form>
</div>
</body>
</html>