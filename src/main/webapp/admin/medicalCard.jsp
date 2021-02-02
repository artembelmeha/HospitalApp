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
                    <form action="@{|/medicalCard/finish/${id}|}" method="post">
                        <input type="submit" value="<fmt:message key="medicalCard.finishTreatment"/>"/>
                        <input type="text" name="finalDiagnosis"/>
                    </form>
                </td>
            </tr>
        </table>


<%--        <br>--%>
<%--        <form class="button" th:action="@{|/assignment/${medicalCard.id}|}" method="GET"--%>
<%--              sec:authorize="hasAuthority('DOCTOR')">--%>
<%--            <input type="submit" th:value="#{assignment.createNew}"/>--%>
<%--        </form>--%>


<%--        <br>--%>
<%--        <table border="1">--%>
<%--            <tr>--%>
<%--                <th th:text="#{list.no}">No.</th>--%>
<%--                <th th:text="#{medicalCard.currentDiagnosis}">Current Diagnosis</th>--%>
<%--                <th th:text="#{medicalCard.isComplete}">Is Complete</th>--%>
<%--                <th th:text="#{assignment.action}">Action</th>--%>

<%--            </tr>--%>
<%--            <tr th:each="assignment, iStat: ${medicalCard.assignmentList}">--%>
<%--                <td th:text="${iStat.index + 1}"/>--%>
<%--                <td th:text="${assignment.currentDiagnosis}">--%>
<%--                <td th:text="#{'patient.'+${assignment.isComplete}}">--%>
<%--                <td>--%>
<%--                    <a th:href="@{|/assignment/view/${assignment.id}|}" th:text="#{assignment.open}">Open Assignment</a>--%>
<%--                </td>--%>
<%--            </tr>--%>
        </table>

    </div>
</body>
</html>
