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
    List<Auction> wonAuctions = (List<Auction>) request.getAttribute("won-auctions");
    List<User> users = (List<User>) request.getAttribute("users");
    List<Review> reviews = (List<Review>) request.getAttribute("my-reviews");
    ;
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Your Auctions</title>
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
    <h1 class="h1">Auctions You Have Won:</h1>
    <span>
        <% String errorMessage = (String) request.getAttribute("message");
            if (errorMessage != null) { %>
        <span class="error-text"><%=errorMessage%></span><br>
        <% } %>
    </span>
    <br>

    <ol>
        <% for (Auction auction : wonAuctions) { %>
        <li class="auction">
            <span class="label-2-black"
                  style="font-size: 25px; font-weight: bold;"> <%=auction.getItem_name()%> </span> <br>
            <% for (User seller : users) {
                if (seller.getId() == auction.getSeller_id()) { %>
            <span class="label-2-black" style="font-size: 13px;">by <%=seller.getUsername()%> (<%= seller.getRating() %>/5.0) </span>
            <% break;
            } %>
            <% } %>
            <br>
            <span class="label-2-black">   Item Description: </span>
            <span class="label-2-black" style="font-size: 13px">   <%=auction.getItem_description()%></span> <br>
            <span class="label-2-black"> Sold for: <%=auction.getCurrent_price()%>$</span>
            <br>
            <%-- IF NOT REVIEWED YET --%>
            <% boolean foundReview = false;
                for (Review review : reviews) {
                if (review.getItemId() == auction.getId()) {
                    foundReview = true;
                }
            }
            if (foundReview) { %>
                <span class="label-2-black" style = "font-size: 25px; font-weight: bold; "> Already Reviewed </span>
            <%} else { %>
            <form class="form" action="WriteReviewServlet" method="post">
                <h3 style="font-size: 25px; font-weight: bold;"> Write a review: </h3>
                <div class="rate">
                    <input type="radio" id="star5" name="rate" value="5"/>
                    <label for="star5" title="text">5 stars</label>
                    <input type="radio" id="star4" name="rate" value="4"/>
                    <label for="star4" title="text">4 stars</label>
                    <input type="radio" id="star3" name="rate" value="3"/>
                    <label for="star3" title="text">3 stars</label>
                    <input type="radio" id="star2" name="rate" value="2"/>
                    <label for="star2" title="text">2 stars</label>
                    <input type="radio" id="star1" name="rate" value="1"/>
                    <label for="star1" title="text">1 star</label>
                </div>
                <input class="input-item-description" type="text" id="comment-input" name="comment">
                <br>
                <input type="hidden" id="auctionId" name="auctionId" value=<%= auction.getId() %>>
                <input type="submit" value="Create review"/>
            </form>
            <% } %>
        </li>
        <% } %>
    </ol>
</div>
</body>
</html>
