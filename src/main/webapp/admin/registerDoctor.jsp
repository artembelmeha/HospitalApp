<%@ page import="model.entity.Qualification" %><%--
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
    <title><fmt:message key="doctor.doctorRegistration"/></title>
</head>
<body style ="background: #eee;">
<jsp:include page="../header.jsp"/>
    <div class="m-5 p-1">

        <h2><fmt:message key="doctor.doctorRegistration"/></h2>
        <br>
        <form method="POST" action="@{|/users/doctor/define/qualification/${user.id}|}">
            <table>
                <tr>
                    <td>
                        <label><fmt:message key="login.firstName"/>:</label>
                    </td>
                    <td>
                        <input type="text" value="${doctor.firstName}" disabled/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label ><fmt:message key="login.lastName"/>:</label>
                    </td>
                    <td>
                        <input type="text" value="${doctor.lastName}" disabled/>
                    </td>
                </tr>
                <tr>
                <tr>
                    <td>
                        <label><fmt:message key="login.email"/>:</label>
                    </td>
                    <td>
                        <input type="text" value="${doctor.email}" disabled/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label><fmt:message key="doctor.qualification"/></label>
                    </td>
                    <td>
                        <c:set var="enumValues" value="<%=Qualification.values()%>"/>
                            <select>
                                <c:forEach items="${enumValues}" var="enumValue">
                                     <option><fmt:message key="qualification.${fn:toLowerCase(enumValue.name())}"/></option>
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
