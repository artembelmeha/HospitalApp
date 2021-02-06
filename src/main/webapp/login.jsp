<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}" onclick="clearError()">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/sketchy/bootstrap.min.css"
          integrity="sha384-RxqHG2ilm4r6aFRpGmBbGTjsqwfqHOKy1ArsMhHusnRO47jcGqpIQqlQK/kmGy9R" crossorigin="anonymous">
    <title><fmt:message key="login.login" /></title>
</head>
<script>
    function clearError()
    {
        document.getElementById("test").remove();
        document.getElementById("password").className = "form-control form-control-sm";
        document.getElementById("email").className = "form-control form-control-sm";
    }
</script>
<body>
<jsp:include page="header.jsp"/>
<div class="m-5 p-1" >
    <h1><fmt:message key="login.loginForm" /></h1>
<form class="form-group" method="POST" action="/api/login">
    <br>
    <c:set var = "status" scope = "page" value = "${error.length()>5 ? 'is-invalid' : ''}"/>
    <input type="hidden" name="command" value="login" />

    <c:if test="${status eq 'is-invalid'}" >
        <div class="is-invalid" id="test" style="color: #ff0000">
            <fmt:message  key="error.wrongCredential"/>
        </div>
        <c:remove var="error" scope="session" />
    </c:if>

    <table >
        <tr>
            <td> <fmt:message key="login.email" /> :</td>
            <td>
                <input type="text"  id="email" class="form-control form-control-sm ${status}" name="email" value="${email}"
                       onclick="clearError()"
                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="<fmt:message key="message.Email"/>" />
            </td>
        </tr>
        <tr>
            <td> <fmt:message key="login.password" /> :</td>
            <td>
                <input type="password" id="password" class="form-control form-control-sm ${status}" name="password" value="${password}"
                       onclick="clearError()" />
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="<fmt:message key="login.login"/>"/>
            </td>
            <td>
                <input type="reset" class="clear" value="<fmt:message key="login.clear"/>" onclick="clearError()"/>
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html >