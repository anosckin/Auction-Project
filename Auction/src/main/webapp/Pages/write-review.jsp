<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Write Review</title>
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
        <h1 class="h1-wider">Write Review</h1>

        <form class="form-box" action="write-review" method="post">
            <div class="form-wrapper">
                <h3 class="h3">Review Details</h3>
                <br>
                <input class="input-2" type="text" id="item-id" name="item" placeholder="Item Code">
                <br>
                <input class="input-2" type="number" id="point-input" name="point" placeholder="Enter point 0-5...">
                <br>
                <input class="input-2" type="text" id="comment-input" name="comment" placeholder="Comment...">
                <br>
                <input type="submit" value="Create review" style="height: 40px;"/>
                <span>
                    <%  String errorMessage = (String)request.getAttribute("message");
                        if (errorMessage != null) { %>
                    <span class="error-text"><%=errorMessage%></span>
                    <%  } %>
                </span>
            </div>
        </form>
        <a class="h4-link-2" style="margin-top: 100px;" href="account-home">Back</a>
    </div>
</body>
</html>
