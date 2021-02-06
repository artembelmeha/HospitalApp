<%@ page import="model.entity.Qualification" %>
<%@ page import="model.entity.Sex" %><%--
  Created by IntelliJ IDEA.
  User: artembelmeha
  Date: 01/02/2021
  Time: 7:58 am
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
    <title><fmt:message key="patient.patientRegistration"/></title>
</head>
<script>
    function clearError()
    {
        document.getElementById("test").remove();
    }
</script>
<body>
<jsp:include page="../header.jsp"/>
    <div class="m-5 p-1">

        <h2><fmt:message key="patient.patientRegistration"/></h2>
        <br>
        <form method="POST" action="/api/admin/assignAsPatient">
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
                        <label><fmt:message key="login.firstName"/>:</label>
                    </td>
                    <td>
                        <input type="text" value="${patient.firstName}" disabled/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label><fmt:message key="login.lastName"/>:</label>
                    </td>
                    <td>
                        <input type="text" value="${patient.lastName}" disabled/>
                    </td>
                </tr>
                <tr>
                <tr>
                    <td>
                        <label><fmt:message key="login.email"/>:</label>
                    </td>
                    <td>
                        <input type="text" value="${patient.email}" disabled/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label><fmt:message key="patient.birthDate"/>:</label>
                    </td>
                    <td>
                        <input type="date" value="${sessionScope.patient.birthDate}" name="birthDate"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label><fmt:message key="patient.telephoneNumber"/>:</label>
                    </td>
                    <td>
                        <input type="text" value="${patient.telephoneNumber}" name="telephoneNumber"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label><fmt:message key="patient.sex"/></label>
                    </td>
                    <td>
                        <c:set var="enumValues" value="<%=Sex.values()%>"/>
                        <select name="sex">
                            <c:forEach items="${enumValues}" var="enumValue">
                                <option value="${enumValue}" }>
                                    <fmt:message key="patient.${fn:toLowerCase(enumValue.name())}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label><fmt:message key="patient.doctor"/></label>
                    </td>
                    <td>
                        <select name="doctor">
                            <c:forEach items="${doctors}" var="doctor">
                                <option value="${doctor.id}" }>
<%--                                    <c:out value="qualification.${fn:toLowerCase(doctor.qualification)}"/>--%>
                                    <fmt:message key="qualification.${fn:toLowerCase(doctor.qualification)}"/>,
                                    <c:out value="${doctor.firstName}"/>  <c:out value="${doctor.lastName}"/>,
                                    <c:out value="${doctor.patientsNumber}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

<%--                <tr>--%>
<%--                    <td>--%>
<%--                        <label th:text="#{patient.doctor}">Doctor</label>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <select th:field="*{doctorId}">--%>
<%--                            <option th:each="doctor : ${doctors}"--%>
<%--                                    th:value="${doctor.id}"--%>
<%--                                    th:text="#{'qualification.'+${doctor.qualification.name().toLowerCase()}}--%>
<%--                                +': '+${doctor.firstName} +' '+${doctor.lastName} +',  '+ ${doctor.patientsNumber}"/>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                </tr>--%>

                <tr>
                    <td>
                        <input type="submit" value="<fmt:message key="doctor.add"/>" value="patient"/>
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
