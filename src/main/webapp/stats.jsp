<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="javascript/themeChanger.js">
    </script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GoatCool</title>
    <link rel="stylesheet" type="text/css" href="resources/css/finalstyle.css" id="themer">
    <link href='https://fonts.googleapis.com/css?family=Roboto|Megrim' rel='stylesheet' type='text/css'>
</head>

<body class="two_div" onload="checkCookie()">

<nav>
    <ul>
        <a href="profile.jsp"><li>Profile</li></a>
        <a href="userlist"><li>UserList</li></a>
        <a href="curriculum"><li>Curriculum</li></a>
        <a href="assignments"><li>Assignments</li></a>
        <a href="stats"><li class="marked">Stats</li></a>
    </ul>
         <jsp:include page="snippets/themeChanger.jsp"/>
</nav>

<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href="">Assignment Statistics</a></div>
            </div>
                <table>
                    <tr>
                        <th>Assignment title</th>
                        <th>Submission Date</th>
                        <th>Max Score</th>
                        <th>Student Score</th>
                    </tr>
                    <c:forEach var="a" items="${assignmentMap}">
                        <tr>
                            <td><a href="handlepage?title=${a.key.getTitle()}"><c:out value="${a.key.getTitle()}"/></a></td>
                            <td><c:out value="${a.key.getSubmissionDate().toLocalDate()}"/></td>
                            <td><c:out value="${a.value}"/></td>
                            <c:if test="${a.key.getGrade() != 0}">
                            <td><c:out value="${a.key.getGrade()}"/> </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <jsp:include page="snippets/sidebar.jsp" />
    </div>

</div>
</body>
</html>
