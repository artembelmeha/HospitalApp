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
                <th><fmt:message key="login.firstName"/></th>
                <th><fmt:message key="login.lastName"/></th>
                <th><fmt:message key="login.email"/></th>
                <th><fmt:message key="doctor.qualification"/></th>
                <th><fmt:message key="doctor.numberOfPatient"/></th>
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
    </div>
</div>
</body>
</html>
