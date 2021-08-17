<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Add Auction</title>
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
  <h1 class="h1-wider">Add Auction</h1>

  <form class="form-box" action="add-auction" method="post">
    <div class="form-wrapper">
      <h3 class="h3">Auction Details</h3>
      <br>
      <input class="input-2" type="text" id="item-name-input" name="item-name" placeholder="item name">
      <br>
      <input class="input-2" type="date" id="start" name="end-date" mmax="2022-12-31">
      <br>
      <input class="input-2" type="number" id="price-input" name="price" placeholder="Enter starting price">
      <br>
      <input class="input-2" type="number" id="increment-input" name="min-increment" placeholder="Enter minimum increment">
      <br>
      <input class="input-2" type="text" id="comment-input" name="comment" style = "height: 150px;" placeholder="Item description" >
      <br>
      <input type="submit" value="Add Auction" style="height: 40px;"/>
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