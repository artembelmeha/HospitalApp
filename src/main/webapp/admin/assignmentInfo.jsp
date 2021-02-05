<%--
  Created by IntelliJ IDEA.
  User: artembelmeha
  Date: 31/01/2021
  Time: 5:01 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/sketchy/bootstrap.min.css"
          integrity="sha384-RxqHG2ilm4r6aFRpGmBbGTjsqwfqHOKy1ArsMhHusnRO47jcGqpIQqlQK/kmGy9R" crossorigin="anonymous">
    <title><fmt:message key="assignment.info"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="m-5 p-1">
    <h2><fmt:message key="assignment.info"/></h2>
    <br>
    <form method="GET" action="/api/doctor/addOneExecution?id=${assignmentDto.id}">
        <table>
            <tr>
                <td>
                    <label><fmt:message key="assignment.currentDiagnosis"/>: </label>
                </td>
                <td>
                    <input type="text" value="${assignment.currentDiagnosis}" disabled/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.date"/>: </label>
                </td>
                <td>
                    <fmt:message key="format.date" var="datePattern"/>
                    <fmt:parseDate value="${assignment.date}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
                    <fmt:formatDate var="date" value="${parsedDate}" pattern="${datePattern}"/>
                    <input type="text" value="${date}" disabled>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.name"/>: </label>
                </td>
                <td>
                    <input type="text" value="${assignment.name}" disabled/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.notes"/>: </label>
                </td>
                <td>
                    <input type="text" value="${assignment.notes}" disabled/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.type"/>: </label>
                </td>
                <td>
                    <c:set var="type" value="type.${fn:toLowerCase(assignment.type)}" />
                    <input type="text" value="<fmt:message key="${type}"/>" disabled/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.isComplete"/>: </label>
                </td>
                <td>
                    <input type="text" value="<fmt:message key="patient.${fn:toLowerCase(assignment.isComplete)}"/>" disabled/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.quantity"/>: </label>
                </td>
                <td>
                    <input type="text" value="${assignment.quantity}" disabled/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.doneTimes"/>: </label>
                </td>
                <td>
                    <input type="text" value="${assignment.doneTimes}" disabled/>
                </td>
            </tr>
        </table>
    </form>
    <div class="m-1 p-1">
        <br>
        <h3><fmt:message key="nurse.listOfAssignedNurse"/></h3>
        <table border="1">
            <tr>
                <th><fmt:message key="list.no"/></th>
                <th><fmt:message key="list.fullName"/></th>
                <th><fmt:message key="login.email"/></th>
            </tr>
            <c:forEach items="${nurses}" var="nurse" varStatus="theCount">
                <tr>
                    <td>
                        <c:out value="${theCount.count}"/>
                    </td>
                    <td>
                        <c:out value="${nurse.firstName}"/> <c:out value="${nurse.lastName}"/>
                    </td>
                    <td>
                        <a>${nurse.email}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>
</body>
</html>
