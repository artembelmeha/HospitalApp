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
    <title><fmt:message key="doctor.listOfDoctors"/></title>
</head>
<body style ="background: #eee;">
<jsp:include page="../header.jsp"/>
<div class="m-5 p-1">
    <div border="1" class = "table table-striped table-responsive-md">
        <h2><fmt:message key="doctor.listOfDoctors"/></h2>
        <br>
        <table border="1">
            <tr>
                <th>
                    <a href="/api/admin/doctors?page=${currentPage}&sortBy=firstName">
                    <fmt:message key="login.firstName"/>
                    </a>
                </th>
                <th>
                    <a href="/api/admin/doctors?page=${currentPage}&sortBy=lastName">
                        <fmt:message key="login.lastName"/>
                    </a>
                </th>
                <th><fmt:message key="login.email"/></th>
                <th>
                    <a href="/api/admin/doctors?page=${currentPage}&sortBy=qualification">
                        <fmt:message key="doctor.qualification"/>
                    </a>
                </th>
                <th>
                    <a href="/api/admin/doctors?page=${currentPage}&sortBy=numberOfPatient">
                    <fmt:message key="doctor.numberOfPatient"/>
                    </a>
                </th>
            </tr>
            <c:forEach items="${doctors}" var="doctor" varStatus="theCount">
                <tr>
                    <td>
                        <c:out value="${doctor.firstName}"/>
                    </td>
                    <td>
                        <c:out value="${doctor.lastName}"/>
                    </td>
                    <td>
                        <a>${doctor.email}</a>
                    </td>
                    <td>
                        <c:set var="qualification" value="qualification.${fn:toLowerCase(doctor.qualification)}" />
                        <fmt:message key="${qualification}"/>
                    </td>
                    <td>
                        <c:out value="${doctor.patientsNumber}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>

        <table border="1" cellpadding="2" cellspacing="2">
            <tr>
            <c:if test="${currentPage != 1}">
                <td><a href="/api/admin/doctors?page=${currentPage - 1}&sortBy=${sortBy}">
                <fmt:message key="form.previous"/>
                </a></td>
            </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="/api/admin/doctors?page=${i}&sortBy=${sortBy}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <td><a href="/api/admin/doctors?page=${currentPage + 1}&sortBy=${sortBy}">
                        <fmt:message key="form.next"/>
                    </a></td>
                </c:if>
            </tr>
        </table>







    </div>
</div>
</body>
</html>
