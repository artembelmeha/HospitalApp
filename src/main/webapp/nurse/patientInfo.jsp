<%--
  Created by IntelliJ IDEA.
  User: artembelmeha
  Date: 31/01/2021
  Time: 5:01 pm
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
    <title><fmt:message key="patient.info"/></title>
</head>
<body style ="background: #eee;">
<jsp:include page="../header.jsp"/>
    <div class="m-5 p-1">
        <h2><fmt:message key="patient.info"/></h2>
        <br>
        <p><fmt:message key="login.firstName"/>  :   ${patient.firstName}</p>
        <p><fmt:message key="login.lastName"/>  :   ${patient.lastName}</p>
        <p><fmt:message key="login.email"/>  :   ${patient.email}</p>
        <p><fmt:message key="patient.telephoneNumber"/>  :   ${patient.telephoneNumber}</p>
        <p><fmt:message key="patient.isOnTreatment"/>  :   <fmt:message key="patient.${patient.isOnTreatment}"/> </p>

        <fmt:message key="format.date" var="datePattern"/>
        <fmt:parseDate value="${patient.birthDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
        <fmt:formatDate var="date" value="${parsedDate}" pattern="${datePattern}"/>
        <p><fmt:message key="patient.birthDate"/>  :   ${date}</p>

        <c:set var="sex" value="patient.${fn:toLowerCase(patient.sex)}" />
        <p><fmt:message key="patient.sex"/>  :   <fmt:message key="${sex}"/> </p>

        <p><fmt:message key="patient.doctor"/>  :   ${doctorFullName},  <fmt:message key="qualification.${fn:toLowerCase(qualification)}"/> </p>
        <a href="/api/nurse/medicalCard?id=${patient.cardId}"><fmt:message key="patient.medicalCard"/></a>
    </div>
</body>
</html>
