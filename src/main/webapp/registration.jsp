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
<body style ="background: #eee;" >
<jsp:include page="header.jsp"/>
<div class="m-5 p-1">
    <h1><fmt:message key="login.newUserRegistration"/></h1>
    <br>
    <form method="POST" action="@{/users}" object="${registrationInfo}">
        <table>
            <tr>
                <td>
                    <label for="firstName">
                        <fmt:message key="login.firstName" /> :
                    </label>
                </td>
                <td>
                    <input type="text" name="firstName" size="24" id="firstName"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="lastName">
                        <fmt:message key="login.lastName" /> :
                    </label>
                </td>
                <td>
                    <input type="text" name="lastName" size="24" id="lastName"/>
                </td>
            </tr>

            <tr>
                <td>
                    <label for="email">
                        <fmt:message key="login.email" /> :
                    </label>
                </td>
                <td>
                    <input type="email" name="email" size="24" id="email"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="password">
                        <fmt:message key="login.password" /> :
                    </label>
                </td>
                <td>
                    <input type="password" name="password" size="24" id="password"/>
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
