<%@ page import="model.entity.AssignmentType" %><%--
  Created by IntelliJ IDEA.
  User: artembelmeha
  Date: 04/02/2021
  Time: 7:59 am
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
    <title><fmt:message key="assignment.createNew"/></title>
</head>
<script>
    function clearError()
    {
        document.getElementById("test").remove();
    }
</script>
<body  onclick="clearError()" >
<jsp:include page="../header.jsp"/>
<div class="m-5 p-1">
    <h2><fmt:message key="assignment.createNew"/></h2>
    <br>

    <form method="POST" action="/api/doctor/addAssignment" >
        <c:set var = "status" scope = "page" value = "${error.length()>5 ? 'is-invalid' : ''}"/>

        <c:if test="${status eq 'is-invalid'}" >
            <div class="is-invalid" id="test" style="color: #ff0000">
                <fmt:message  key="error.wrongData"/>
            </div>
            <c:remove var="error" scope="session" />
        </c:if>
        <table>
            <tr>
                <td>
                    <label><fmt:message key="assignment.currentDiagnosis"/>:</label>
                </td>
                <td>
                    <input type="text" name="currentDiagnosis" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.date"/>:</label>
                </td>
                <td>
                    <input type="date" name="date"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.name"/>:</label>
                </td>
                <td>
                    <input type="text" name="name"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.notes"/>:</label>
                </td>
                <td>
                    <input type="text" name="notes"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.quantity"/>:</label>
                </td>
                <td>
                    <input type="number" name="quantity" min ="1" max="100" value="1"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><fmt:message key="assignment.type"/></label>
                </td>
                <td>
                    <c:set var="enumValues" value="<%=AssignmentType.values()%>"/>
                    <select name="type">
                        <c:forEach items="${enumValues}" var="enumValue">
                            <option value="${enumValue}" }>
                                <fmt:message key="type.${fn:toLowerCase(enumValue.name())}"/>
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="<fmt:message key="doctor.add"/>"/>
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
