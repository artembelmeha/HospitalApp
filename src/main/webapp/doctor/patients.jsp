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
                <th>
                    <a href="/api/doctor/patients?page=${currentPage}&sortBy=firstName">
                        <fmt:message key="login.firstName"/>
                    </a>
                </th>
                <th>
                    <a href="/api/doctor/patients?page=${currentPage}&sortBy=lastName">
                        <fmt:message key="login.lastName"/>
                    </a>
                </th>
                <th>
                    <a href="/api/doctor/patients?page=${currentPage}&sortBy=birthDate">
                        <fmt:message key="patient.birthDate"/>
                    </a>
                </th>
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
                        <a href="/api/doctor/patient?id=${patient.id}">
                            <fmt:message key="patient.info"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br>

        <table border="1" cellpadding="2" cellspacing="2">
            <tr>
                <c:if test="${currentPage != 1}">
                    <td><a href="/api/doctor/patients?page=${currentPage - 1}&sortBy=${sortBy}">
                        <fmt:message key="form.previous"/>
                    </a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="/api/doctor/patients?page=${i}&sortBy=${sortBy}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <td><a href="/api/doctor/patients?page=${currentPage + 1}&sortBy=${sortBy}">
                        <fmt:message key="form.next"/>
                    </a></td>
                </c:if>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
