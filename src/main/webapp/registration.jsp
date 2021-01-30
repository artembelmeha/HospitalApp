<%--
  Created by IntelliJ IDEA.
  User: artembelmeha
  Date: 28/01/2021
  Time: 2:18 pm
  To change this template use File | Settings | File Templates.
--%>
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
    <title><fmt:message key="login.registration" /></title>
</head>
<script>
    function clearError()
    {
        document.getElementById("test").remove();
        document.getElementById("password").className = "form-control form-control-sm";
        document.getElementById("email").className = "form-control form-control-sm";
        document.getElementById("firstName").className = "form-control form-control-sm";
        document.getElementById("lastName").className = "form-control form-control-sm";
    }
</script>
<body>
<jsp:include page="header.jsp"/>
<div class="m-5 p-1">
    <h1><fmt:message key="login.newUserRegistration"/></h1>
    <br>
    <c:set var = "status" scope = "page" value = "${error.length()>5 ? 'is-invalid' : ''}"/>
    <form method="POST" method="POST" action="/api/registration">
        <c:if test="${status eq 'is-invalid'}" >
            <div class="is-invalid" id="test" style="color: #ff0000">
                    <fmt:message  key="error.alreadyExist"/>
            </div>
            <c:remove var="error" scope="session" />
        </c:if>
        <table>
            <tr>
                <td> <fmt:message key="login.firstName" /> :</td>
                <td>
                    <input type="text" id="firstName" class="form-control form-control-sm ${status}"
                           name="firstName" value="${firstName}"
                           pattern="[A-Z][a-z]+" title="<fmt:message key="message.forName"/>" />
                </td>
            </tr>
            <tr>
                <td> <fmt:message key="login.lastName" /> :</td>
                <td>
                    <input type="text" id="lastName" class="form-control form-control-sm ${status}"
                           name="lastName" value="${lastName}"
                           pattern="[A-Z][a-z]+" title="<fmt:message key="message.forName"/>" />
                </td>
            </tr>

            <tr>
                <td> <fmt:message key="login.email" /> :</td>
                <td>
                    <input type="text" id="email" class="form-control form-control-sm ${status}"
                           name="email" value="${email}"
                           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="<fmt:message key="message.Email"/>"/>
                </td>
            </tr>

            <tr>
                <td> <fmt:message key="login.password" /> :</td>
                <td>
                    <input type="password" id="password" class="form-control form-control-sm ${status}"
                           name="password" value="${password}"
                    pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}" title="<fmt:message key="message.Password"/>"  />
                </td>
            </tr>

            <tr>
                <td>
                    <input type="submit" value="<fmt:message key="login.register"/>" />
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
