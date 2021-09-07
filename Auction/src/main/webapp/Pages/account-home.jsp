<%@ page import="Models.User" %>
<%@ page import="Models.UserInfo" %>
<%@ page import="static Helper.GeneralConstants.*" %><%--
  Created by IntelliJ IDEA.
  User: samad
  Date: 12.08.2021
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);
//    UserInfo currentUserInfo = (UserInfo)session.getAttribute(CURRENT_USER_INFO_STRING);
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Account Home</title>
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
        <h1 class="h1">User: <%=currentUser.getUsername()%></h1>
        <a class="h4-link" href="profile">My Profile</a>
        <br>
        <a class="h4-link" href="leaderboard">Leaderboard</a>
        <br>
        <% if(currentUser.getIsAdmin()){ %>
            <a class="h4-link" href="auctions">Auctions</a>
            <br>
            <a class="h4-link" href="allusers">All Users</a>
            <br>
        <% } %>
        <% if (currentUser.getIsDealer()){ %>
        <a class="h4-link" href="user-reviews">My Reviews</a>
        <br>
        <% } %>
        <% if (currentUser.getIsDealer()){ %>
            <a class="h4-link" href="my-auctions">My Auctions</a> <br>
            <a class="h4-link" href="add-auction">Add auction</a> <br>
        <% } %>
        <a class="h4-link" href="active-auctions">Active auctions</a>
        <br>
        <a class="h4-link" href="interested-auctions">Active auctions you have bid on</a>
        <br>
        <a class="h4-link" href="user-auctions">Auctions you have won</a>
        <br>
        <a class="h4-link" href="logout">Logout</a>
        <br>
    </div>
</body>
</html>
