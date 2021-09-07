<%@ page import="Models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Auction" %>
<%@ page import="static Helper.GeneralConstants.*" %><%--
  Created by IntelliJ IDEA.
  User: samad
  Date: 14.08.2021
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Auction> auctions = (List<Auction>) request.getAttribute("auctions");
    List<User> users = (List<User>) request.getAttribute("users");
    User currentUser = (User) session.getAttribute(CURRENT_USER_STRING);
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Active Auctions</title>
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
    <h1 class="h1">My Auctions:</h1>

    <ol>
        <% for (Auction auction : auctions) {
            if (auction.getSeller_id() == currentUser.getId()) { %>
        <li class="auction">
            <span class="label-2-black" style="font-size: 23px; font-weight: bold;"> <%=auction.getItem_name()%> </span>
            <br>
            <span class="label-2-black"
                  style="font-size: 13px;">by <%=currentUser.getUsername()%> (<%= currentUser.getRating() %>/5.0) </span><br>
            <span class="label-2-black">   Item Description: </span>
            <span class="label-2-black" style="font-size: 13px">   <%=auction.getItem_description()%></span> <br>
            <% if (auction.isActive()) { %>
                <span class="label-2-black"> <b> End Date: </b> <%=auction.getEnd_date().toString()%> </span> <br>
                <span class="label-2-black"> Current Bid: <%=auction.getCurrent_price()%>$</span>
                <% for (User bidder : users) {
                    if (bidder.getId() == auction.getCurrent_bidder_id() && bidder.getId() == auction.getSeller_id()) { %>
                        <span class="label-2-black " style="font-size: 13px;">by N/A </span> <br>
                    <% } %>
                    <% if (bidder.getId() == auction.getCurrent_bidder_id() && bidder.getId() != auction.getSeller_id()) { %>
                        <span class="label-2-black" style="font-size: 13px;">by <%=bidder.getUsername()%> </span> <br>
                    <% } %>
                <% } %>
                <% if (auction.getSeller_id() == auction.getCurrent_bidder_id()) { %>
                <span class="label-2-black"> Minimal Next Bid:  <%=auction.getCurrent_price()%>$ </span>
                <% } else { %>
                <span class="label-2-black"> Minimal Next Bid:  <%=auction.getCurrent_price() + auction.getMin_increment()%>$ </span>
                <% } %>
            <% } else { %>
                <span class="label-2-black"> <b> Already Ended </b> </span> <br>
                <% if (auction.getSeller_id() != auction.getCurrent_bidder_id()) { %>
                    <span class="label-2-black"> Bought For: <%=auction.getCurrent_price()%>$</span>
                    <% for (User bidder : users) {
                        if (bidder.getId() == auction.getCurrent_bidder_id()) { %>
                        <span class="label-2-black" style="font-size: 13px;">by <%=bidder.getUsername()%> </span> <br>
                        <% } %>
                    <% } %>
                <% }else{%>
                    <span class="label-2-black"> Starting Price was: <%=auction.getCurrent_price()%>$</span>
                <% }%>
            <% }%>
        </li>
        <% } %>
        <% } %>
    </ol>

    <br>
</div>
</body>
</html>
