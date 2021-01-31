<%--
  Created by IntelliJ IDEA.
  User: artembelmeha
  Date: 31/01/2021
  Time: 8:25 am
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
    <title><fmt:message key="login.listOfUsers"/></title>
</head>
    <body style ="background: #eee;">
        <jsp:include page="../header.jsp"/>
        <div class="m-5 p-1">
            <div border="1" class = "table table-striped table-responsive-md">
                <h2><fmt:message key="login.listOfUsers"/></h2>
                <br>
                <table border="1">
                    <tr>
                        <th><fmt:message key="list.no"/></th>
                        <th><fmt:message key="list.fullName"/></th>
                        <th><fmt:message key="login.email"/></th>
                        <th colspan="3"><fmt:message key="list.operations"/></th>
                    </tr>
                    <c:forEach items="${undefined}" var="user" varStatus="theCount">
                        <tr>
                            <td>
                                <c:out value="${theCount.count}"/>
                            </td>
                            <td>
                                <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>
                            </td>
                            <td>
                                <a>${user.email}</a>
                            </td>
                        <td>
                            <a href="@{|/users/appoint/nurse/${user.id}|}"> <fmt:message key="list.signAsNurse"/></a>
                        </td>
                        <td>
                            <a href="@{|/users/appoint/doctor/${user.id}|}"><fmt:message key="list.signAsDoctor"/></a>
                        </td>
                        <td>
                            <a href="@{|/users/appoint/patient/${user.id}|}" ><fmt:message key="list.signAsPatient"/></a>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>