<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Place a Bid</title>
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
    <h1 class="h1-wider">Place a Bid</h1>

    <form class="form-box" action="auction-bid" method="post">
        <div class="form-wrapper">
            <h3 class="h3">Auction Details</h3>
            <br>
            <input class="input-2" type="number" id="item-code" name="item-code" placeholder="Item Code">
            <br>
            <input class="input-2" type="number" id="price" name="price" placeholder="Enter Bet amount">
            <br>
            <input type="submit" value="Place Bid" style="height: 40px;"/>
            <span>
                    <%  String errorMessage = (String)request.getAttribute("message");
                        if (errorMessage != null) { %>
                    <span class="error-text"><%=errorMessage%></span>
                    <%  } %>
                </span>
        </div>
    </form>
</div>
</body>
</html>