<%--
  Created by IntelliJ IDEA.
  User: artembelmeha
  Date: 28/01/2021
  Time: 10:09 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<div>
    <nav class="navbar navbar-inverse"  lang="${sessionScope.lang}">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">Modern Hospital LLC</a>
            </div>
            <ul>
                <c:if test="${fn:contains(pageContext.request.requestURI, 'admin')}">
                    <li>
                        <a href="@{/users/undefine}"> <fmt:message key="login.users"/> </a>
                    </li>
                </c:if>

                <c:if test="${fn:contains(pageContext.request.requestURI, 'admin')
                    or fn:contains(pageContext.request.requestURI, 'doctor')
                        or fn:contains(pageContext.request.requestURI, 'nurse')}">
                    <li>
                         <a href="@{'/users/patients/page/1?sortField=firstName&sortDir=asc'}">
                            <fmt:message key="login.patients"/></a>
                    </li>
                </c:if>
            </ul>
            <ul>
                <c:if test="${fn:contains(pageContext.request.requestURI, 'admin')}">
                    <li>
                        <a href="@{/users/nurses}">
                            <fmt:message key="login.nurses"/>
                         </a>
                    </li>
                </c:if>
                <c:if test="${fn:contains(pageContext.request.requestURI, 'admin')}">
                    <li>
                        <a href="'/users/">
                            <fmt:message key="doctor.doctors"/>
                        </a>
                    </li>
                </c:if>
            </ul>
            <ul>
                <a href="?sessionLocale=en">English</a>
                <a>/</a>
                <a href="?sessionLocale=ua">Українська</a>
            </ul>

            <ul class="nav navbar-nav navbar-right">

                <c:if test="${empty user}">
                    <li>
                        <a href="/api/registration.jsp">
                            <fmt:message key="login.registration"/>
                        </a>
                    </li>
                </c:if>

                <c:if test="${empty user}">
                    <li>
                         <a href="/api/login.jsp">
                            <fmt:message key="login.login"/>
                         </a>
                    </li>
                </c:if>

            </ul>
            <ul>

                <c:if test="${not empty user}">
                    <form action="${pageContext.request.contextPath}/logout" method="post" style="display: inline-block">
                        <input type="submit" value=<fmt:message key="login.logout"/>>
                    </form>
                </c:if>

            </ul>
        </div>

    </nav>
</div>
