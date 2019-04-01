<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Aladin|Merienda+One|Walter+Turncoat" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="resources/css/text.css">
    <title>GoatCool</title>
    <script>
        document.getElementById('datefield').valueAsDate = new Date();
    </script>
</head>
<body>

<div class="header">
    <a href="index.html"><h1>GoatCool</h1></a>
</div>

<nav>
    <ul>
        <a href="profile.jsp"><li>Profile</li></a>
        <a href="userlist"><li>UserList</li></a>
        <a href="curriculum"><li class="marked">Curriculum</li></a>
        <a href="assignments"><li>Assignments</li></a>
        <a href="stats"><li>Stats</li></a>
    </ul>
</nav>

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title">Attendance</div>
            </div>
            <form method="post" action="setDate">
                <input id="setNewDate" type="submit" value="Set date">
                <input id="datefield2" type='date' min='1899-01-01' max='2000-13-13'/>
                <script>
                    var today = new Date();
                    var dd = today.getDate();
                    var mm = today.getMonth()+1;
                    var yyyy = today.getFullYear();
                    if(dd<10){
                        dd='0'+dd
                    }
                    if(mm<10){
                        mm='0'+mm
                    }
                    var currentDate = yyyy+'-'+mm+'-'+dd;
                    document.getElementById("datefield2").setAttribute("max", currentDate);
                </script>
            </form>
            <form method="post" action="attendance">
                <input id="datefield" type='date' min='1899-01-01' max='2000-13-13'/>
                <script>
                    document.getElementById("datefield").setAttribute("max", currentDate);
                    document.getElementById("datefield").value = currentDate;
                </script>
                <table>
                <tr>
                    <td>Name</td>
                    <td>E-mail</td>
                    <td>Attendance rate</td>
                </tr>
                <c:forEach var="u" items="${attendance}">
                    <c:if test="${u.getClass().simpleName == 'Student'}">
                        <tr>
                            <td><c:out value="${u.name}"/></td>
                            <td><c:out value="${u.email}"/></td>
                            <td><c:out value="${u.getAttendanceRate()}"/>%</td>
                            <c:set var="date" value="${param.id1}"/>
                            <td><input type="checkbox" name="attending" value="${u.name}"></td>
                        </tr>
                    </c:if>
                </c:forEach>
                </table>
                <input class="attendance-button" type="submit" value="Save!">
            </form>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <div class="sbcontainer">
            <div class="containerhead">
                <div class="title">Logged in user</div>
            </div>

            <div class="desc">
                <p>Name: ${user.getName()}</p>
                    <c:choose>
                          <c:when test="${user.getClass().simpleName == 'Student'}">
                                <p>Role: Student</p>
                          </c:when>
                          <c:otherwise>
                                <p>Role: Mentor</p>
                          </c:otherwise>
                    </c:choose>
                <br>
            </div>

            <ul class="links">
                <div class="linktitle">Favourites</div>
                <li><a href="">Sidebar link 1</a></li>
                <li><a href="">Sidebar link 2</a></li>
                <li><a href="">Sidebar link 3</a></li>
                <li><a href="">Sidebar link 4</a></li>
            </ul>

        </div>
    </div>

</div>
</body>
</html>
