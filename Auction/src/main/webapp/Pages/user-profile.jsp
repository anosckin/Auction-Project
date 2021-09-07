<%@ page import="Models.User" %>
<%@ page import="Models.UserInfo" %>
<%@ page import="static Helper.GeneralConstants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);
    UserInfo currentUserInfo = (UserInfo)session.getAttribute(CURRENT_USER_INFO_STRING);
%>
<html>
    <head>
        <meta charset="utf-8">
        <title>Profile</title>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/a8ba60cbab.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="../Styles/reset.css">
        <link rel="stylesheet" type="text/css" href="../Styles/style.css">
    </head>
    <body>
        <div class="main-div">
            <h2 class="profile-name"><%=currentUser.getUsername()%>'s Profile</h2>
            <div>
                <ul class="list-a">
                    <li>
                        <span class="label-1">Username: </span> <span class="label-2"><%=currentUser.getUsername()%></span>
                    </li>
                    <li>
                        <span class="label-1">First Name: </span> <span class="label-2"><%=currentUserInfo.getFirstName()%></span>
                    </li>
                    <li>
                        <span class="label-1">Last Name: </span> <span class="label-2"><%=currentUserInfo.getLastName()%></span>
                    </li>
                    <li>
                        <span class="label-1">Email: </span> <span class="label-2"><%=currentUserInfo.getEmail()%></span>
                    </li>
                    <li>
                        <span class="label-1">Address: </span> <span class="label-2"><%=currentUserInfo.getAddress()%></span>
                    </li>
                    <li>
                        <span class="label-1">Phone Number: </span> <span class="label-2"><%=currentUserInfo.getPhoneNumber()%></span>
                    </li>
                    <li>
                        <span class="label-1">Note: </span> <span class="label-2"><%=currentUserInfo.getNote()%></span>
                    </li>
                    <% if (currentUser.getIsDealer()){%>
                        <li>
                            <span class="label-1">Score: <%=currentUser.getRating()%>/5</span>
                        </li>
                    <% } %>
                </ul>
            </div>

            <br>
            <a class="h4-link-2" href="account-home">Back</a>
        </div>
    </body>
</html>
