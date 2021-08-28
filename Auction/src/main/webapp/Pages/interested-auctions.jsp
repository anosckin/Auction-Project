<%@ page import="Models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Auction" %>
<%@ page import="static Helper.GeneralConstants.*" %>
<%@ page import="Models.BidderAuction" %>
<%@ page import="DAO.SqlAuctionDAO" %><%--
  Created by IntelliJ IDEA.
  User: samad
  Date: 14.08.2021
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<BidderAuction> bidderAuctions = (List<BidderAuction>)request.getAttribute("interested-auctions");
    SqlAuctionDAO auctionDao = (SqlAuctionDAO) request.getAttribute("auctionDAO");
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Interesting Auctions</title>
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
    <h1 class="h1">Active auctions you have bid on at least once:</h1>
    <br>

    <ol>
        <% for (BidderAuction bidderAuction : bidderAuctions) {
            Auction auction = auctionDao.getAuction(bidderAuction.getAuctionId());
            if (auction.getCurrent_bidder_id()!=auction.getSeller_id()
                    && auction.isActive()){ %>
        <li>
            <% if (auction.getCurrent_bidder_id() == bidderAuction.getBidderId()){%>
                <span class="label-2-blue"> You Are Currently Winning: </span> <br>
            <%}else{%>
                <span class="label-2-blue"> Someone is Outbidding You! </span> <br>
            <%}%>
            <span class="label-2-blue"> Item Code: <%=auction.getId()%> </span> <br>
            <span class="label-2-blue"> Item Name: <%=auction.getItem_name()%> </span> <br>
            <span class="score-text">   Item Description: <%=auction.getItem_description()%></span> <br>
            <span class="label-2-blue"> Current Price: <%=auction.getCurrent_price()%>$ </span> <br>
            <% if (auction.getSeller_id()==auction.getCurrent_bidder_id()){ %>
            <span class="score-text"> Minimal Next Bid:  <%=auction.getCurrent_price()%>$ </span> <br>
            <% } else{ %>
            <span class="score-text"> Minimal Next Bid:  <%=auction.getCurrent_price()+auction.getMin_increment()%>$ </span> <br>
            <% } %>
            <br>
            -----------------------------------------------------------------------------------------------------
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
