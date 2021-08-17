<%@ page import="Models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Auction" %>
<%@ page import="static Helper.GeneralConstants.*" %>
<%@ page import="Models.Review" %><%--
  Created by IntelliJ IDEA.
  User: samad
  Date: 14.08.2021
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Review> reviews = (List<Review>)request.getAttribute("reviews");
    List<User> users = (List<User>)request.getAttribute("users");
    User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Your Reviews</title>
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
    <h1 class="h1">Reviews of You:</h1>
    <br>

    <ol>
        <% for (Review review : reviews) {
            if (review.getRecipientId()==currentUser.getId()){ %>
        <li>
            <% for (User reviwer : users) {
                if (reviwer.getId()==review.getReviewerId()){ %>
                    <span class="label-1">Reviewer : <%=reviwer.getUsername()%> </span> <br>
                <% } %>
            <% } %>
            <span class="label-2-blue"> Score (out of 5): <%=review.getScore()%> </span> <br>
            <span class="label-2-blue"> Review: <%=review.getReview()%> </span> <br>
            --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            <br>
        </li>
        <% } %>
        <% } %>
    </ol>

    <br>
    <a class="h4-link" href="account-home">Back</a>
</div>

</body>
</html>
