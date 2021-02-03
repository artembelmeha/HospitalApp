<%--
  Created by IntelliJ IDEA.
  User: artembelmeha
  Date: 31/01/2021
  Time: 10:55 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/sketchy/bootstrap.min.css"
          integrity="sha384-RxqHG2ilm4r6aFRpGmBbGTjsqwfqHOKy1ArsMhHusnRO47jcGqpIQqlQK/kmGy9R" crossorigin="anonymous">
    <title><fmt:message key="patient.listOfPatients"/></title>
</head>
<body style ="background: #eee;">
<jsp:include page="../header.jsp"/>
<div class="m-5 p-1">
    <div border="1" class = "table table-striped table-responsive-md">
        <h2><fmt:message key="patient.listOfPatients"/></h2>
        <br>
        <table border="1">
            <tr>
                <th><fmt:message key="login.firstName"/></th>
                <th><fmt:message key="login.lastName"/></th>
                <th><fmt:message key="patient.birthDate"/></th>
                <th><fmt:message key="login.email"/></th>
                <th><fmt:message key="list.operations"/></th>
            </tr>
            <c:forEach items="${patients}" var="patient">
                <tr>
                    <td>
                        <c:out value="${patient.firstName}"/>
                    </td>
                    <td>
                        <c:out value="${patient.lastName}"/>
                    </td>
                    <td>
                        <fmt:message key="format.date" var="datePattern"/>
                        <fmt:parseDate value="${patient.birthDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                        <fmt:formatDate var="date" value="${parsedDate}" pattern="${datePattern}"/>
                            ${date}
                    </td>
                    <td>
                        <c:out value="${patient.email}"/>
                    </td>
                    <td>
                        <a href="/api/admin/patient?id=${patient.id}">
                            <fmt:message key="patient.info"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
