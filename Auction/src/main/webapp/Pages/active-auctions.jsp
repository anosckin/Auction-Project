<%@ page import="Models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Auction" %><%--
  Created by IntelliJ IDEA.
  User: samad
  Date: 14.08.2021
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Auction> auctions = (List<Auction>)request.getAttribute("auctions");
    List<User> users = (List<User>)request.getAttribute("users");
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
    <h1 class="h1">Active Auctions:</h1>
    <span>
        <%  String errorMessage = (String)request.getAttribute("message");
            if (errorMessage != null) { %>
        <span class="error-text"><%=errorMessage%></span>
        <%  } %>
    </span>
    <br>

    <ol>
        <% for (Auction auction : auctions) {
            if (auction.isActive()){ %>
                <li>
                    <span class="label-2-blue"> Item Code: <%=auction.getId()%> </span> <br>
                    <span class="label-2-blue"> Item Name: <%=auction.getItem_name()%> </span> <br>
                    <span class="label-2-blue"> End Date: <%=auction.getEnd_date().toString()%> </span> <br>
                    <span class="label-2-blue">   Item Description: <%=auction.getItem_description()%></span> <br>
                    <span class="label-2-blue"> Current Price: <%=auction.getCurrent_price()%>$ </span> <br>
                    <% if (auction.getSeller_id()==auction.getCurrent_bidder_id()){ %>
                       <span class="label-2-blue"> Minimal Next Bid:  <%=auction.getCurrent_price()%>$ </span>
                    <% } else{ %>
                        <span class="label-2-blue"> Minimal Next Bid:  <%=auction.getCurrent_price()+auction.getMin_increment()%>$ </span>
                    <% } %>
                    <br>
                    <% for (User seller : users) {
                        if (seller.getId()==auction.getSeller_id()){ %>
                            <span class="label-2-blue">Seller : <%=seller.getUsername()%> </span> <br>
                            <span class="label-2-blue">Seller Rating: <%= seller.getRating() %> </span>
                        <%  break;
                        } %>
                    <% } %>
                    <br>
                    <% for (User bidder : users) {
                        if (bidder.getId()==auction.getCurrent_bidder_id() && bidder.getId() == auction.getSeller_id()){ %>
                            <span class="label-2-blue">Current Bidder : N/A </span> <br>
                        <% } %>
                        <% if (bidder.getId()==auction.getCurrent_bidder_id() && bidder.getId() != auction.getSeller_id()){ %>
                           <span class="label-2-blue">Current Bidder : <%=bidder.getUsername()%> </span> <br>
                        <% } %>
                    <% } %>
                    <form class="form" action="MakeBidServlet" method="post">
                        <input class="input-2" type="number" id="price" name="price" placeholder="Enter Bet amount">
                        <br>
                        <input type="hidden" id="auctionId" name="auctionId" value = <%= auction.getId() %> >
                        <input type="submit" value="Place Bid" />
                    </form>
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
