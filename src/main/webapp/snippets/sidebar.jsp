<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v3.2"></script>
<div class="sbcontainer">
    <div class="containerhead">
        <div class="title">Current User</div>
    </div>
    <div class="desc">
        <p>Welcome ${user.getName()}! You can see and edit your basic data and track your progress here.</p>
        <p>Name: ${user.getName()}</p>
        <c:choose>
            <c:when test="${user.getClass().simpleName == 'Student'}">
                <p>Role: Student</p>
            </c:when>
            <c:otherwise>
                <p>Role: Mentor</p>
            </c:otherwise>
        </c:choose>
        <form action="logout" method="post">
            <input class="button" type="submit" value="Log out">
        </form>
    </div>

</div>
<div class="sbcontainer">
    <div class="containerhead">
        <div class="title">Facebook Timeline</div>
    </div>
    <div class="fb-page" data-href="https://www.facebook.com/CodecoolOfficial" data-tabs="timeline" data-width="250" data-height="600" data-small-header="true" data-adapt-container-width="true" data-hide-cover="true" data-show-facepile="false"><blockquote cite="https://www.facebook.com/CodecoolOfficial" class="fb-xfbml-parse-ignore"><a href="https://www.facebook.com/CodecoolOfficial">Codecool</a></blockquote></div>
</div>
