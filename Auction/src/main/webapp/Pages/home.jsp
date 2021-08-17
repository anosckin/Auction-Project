<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Empty Barrels</title>
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
        <h1 class="big-title">Empty Barrels Auction</h1>

        <form class="form-box" action="account-home" method="post">
            <div class="form-wrapper">
                <h2 class="h2">Please log in.</h2> <br>
                <input class="input-1" type="text" id="username-input" name="username" placeholder="username">
                <br>
                <input class="input-1" type="password" id="password-input" name="password" placeholder="•••••••">
                <br>
                <button class="button-login">Login</button>
                <br>
                <a class="h4-link" href="create-account">Create New Account</a>
                <br>
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
