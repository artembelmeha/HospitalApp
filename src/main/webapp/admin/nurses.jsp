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
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/sketchy/bootstrap.min.css"
          integrity="sha384-RxqHG2ilm4r6aFRpGmBbGTjsqwfqHOKy1ArsMhHusnRO47jcGqpIQqlQK/kmGy9R" crossorigin="anonymous">
    <title><fmt:message key="nurse.listOfNurse"/></title>
</head>
<body style ="background: #eee;">
<jsp:include page="../header.jsp"/>
<div class="m-5 p-1">
    <div border="1" class = "table table-striped table-responsive-md">
        <h2><fmt:message key="nurse.listOfNurse"/></h2>
        <br>
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
