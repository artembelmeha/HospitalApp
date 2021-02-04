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
    <title><fmt:message key="patient.medicalCard"/></title>
</head>
<body style ="background: #eee;">
<jsp:include page="../header.jsp"/>
    <div class="m-5 p-1">
        <h2><fmt:message key="patient.medicalCard"/></h2>
        <br>

        <table>
            <tr>
                <td>
                    <label><fmt:message key="medicalCard.finalDiagnosis"/>:</label>
                </td>
                <td>
                    <input type="text" value="${medicalCard.finalDiagnosis}" disabled/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <form action="/api/doctor/dischargePatient" method="post">
                        <input type="submit" value="<fmt:message key="medicalCard.finishTreatment"/>"/>
                        <input type="text" name="finalDiagnosis"/>
                    </form>
                </td>
            </tr>
        </table>

        <br>
        <form class="button" action="/api/doctor/addAssignment.jsp" method="GET">
            <input type="submit" value="<fmt:message key="assignment.createNew"/>"/>
        </form>

        <br>
        <table border="1">
            <tr>
                <th><fmt:message key="list.no"/></th>
                <th><fmt:message key="medicalCard.currentDiagnosis"/></th>
                <th><fmt:message key="medicalCard.isComplete"/></th>
                <th><fmt:message key="assignment.action"/></th>
            </tr>
            <c:forEach items="${assignments}" var="assignment" varStatus="theCount">
                <tr>
                    <td>
                        <c:out value="${theCount.count}"/>
                    </td>
                    <td>
                        <c:out value="${assignment.currentDiagnosis}"/>
                    </td>
                    <td>
                        <fmt:message key="patient.${assignment.isComplete}"/>
                    </td>
                    <td>
                        <a href="/api/doctor/assignmentInfo?id=${assignment.id}" ><fmt:message key="assignment.open"/></a>
                    </td>

                </tr>
            </c:forEach>
        </table>

    </div>
</body>
</html>
