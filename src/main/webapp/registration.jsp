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
<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/sketchy/bootstrap.min.css"
          integrity="sha384-RxqHG2ilm4r6aFRpGmBbGTjsqwfqHOKy1ArsMhHusnRO47jcGqpIQqlQK/kmGy9R" crossorigin="anonymous">
    <title><fmt:message key="login.registration" /></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="m-5 p-1">
    <h1><fmt:message key="login.newUserRegistration"/></h1>
    <br>
    <form method="POST" method="POST" action="/api/registration">
        <table>
            <tr>
                <td> <fmt:message key="login.firstName" /> :</td>
                <td>
                    <input type="text" class="form-control form-control-sm ${status}" name="firstName" value="${firstName}"
                           pattern="[A-Z][a-z]+" title="<fmt:message key="message.forName"/>" />
                </td>
            </tr>
            <tr>
                <td> <fmt:message key="login.lastName" /> :</td>
                <td>
                    <input type="text" class="form-control form-control-sm ${status}" name="lastName" value="${lastName}"
                           pattern="[A-Z][a-z]+" title="<fmt:message key="message.forName"/>" />
                </td>
            </tr>

            <tr>
                <td> <fmt:message key="login.email" /> :</td>
                <td>
                    <input type="text" class="form-control form-control-sm ${status}" name="email" value="${email}" />
                </td>
            </tr>

            <tr>
                <td> <fmt:message key="login.password" /> :</td>
                <td>
                    <input type="password" class="form-control form-control-sm ${status}" name="password" value="${password}"
                    pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}" title="<fmt:message key="message.Password"/>"  />
                </td>
            </tr>

            <tr>
                <td>
                    <input type="submit" value="<fmt:message key="login.register"/>"/>
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
