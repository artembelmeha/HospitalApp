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
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<div>
    <nav class="navbar navbar-inverse"  lang="${sessionScope.lang}">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Modern Hospital LLC</a>
            </div>
            <ul>
                <li>
                    <a href="@{/users/undefine}"> <fmt:message key="login.users"/> </a>
                </li>

                <li>
                    <a href="@{'/users/patients/page/1?sortField=firstName&sortDir=asc'}">
                        <fmt:message key="login.patients"/></a>
                </li>

            </ul>
            <ul>
                <li>
                    <a href="@{/users/nurses}">
                        <fmt:message key="login.nurses"/>
                    </a>
                </li>
                <li>
                    <a href="'/users/"
                       >
                        <fmt:message key="doctor.doctors"/>
                    </a>
                </li>
            </ul>
            <ul>
                <a href="?sessionLocale=en">English</a>
                <a>/</a>
                <a href="?sessionLocale=ua">Українська</a>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/registration">
                    <span class="glyphicon glyphicon-user"></span>
                    <fmt:message key="login.registration"/></a>
                </li>
                <li>
                    <a href="/login">
                        <span class="glyphicon glyphicon-log-in"></span>
                    <fmt:message key="login.login"/></a>
                </li>
            </ul>
            <ul>
                <input type="button" >
            </ul>
        </div>
    </nav>
</div>