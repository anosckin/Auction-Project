<%--
  Created by IntelliJ IDEA.
  User: samad
  Date: 10.08.2021
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Create Account</title>
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
        <h1 class="h1-wider">Create new account</h1>

        <form class="form-box" action="create-account" method="post">
            <div class="form-wrapper">
                <h3 class="h3">Account Details</h3>
                <br>
                <input class="input-2" type="text" id="username-input" name="username" placeholder="username">
                <br>
                <input class="input-2" type="password" id="password-input" name="password" placeholder="•••••••">
                <br>
                <input class="input-2" type="password" id="password-repeat-input" name="password-repeat" placeholder="repeat password">
                <br>
                <input class="input-2" type="text" id="firstname-input" name="firstname" placeholder="first name">
                <br>
                <input class="input-2" type="text" id="lastname-input" name="lastname" placeholder="last name">
                <br>
                <input class="input-2" type="text" id="email-input" name="email" placeholder="email">
                <br>
                <input class="input-2" type="text" id="address-input" name="address" placeholder="address">
                <br>
                <input class="input-2" type="text" id="phonenumber-input" name="phonenumber" placeholder="phone number">
                <br>
                <input class="input-2" type="text" id="note-input" name="note" placeholder="note">
                <br>
                <button class="button-create-account">Create Account</button>
                <br>
                <a class="h4-link" href="logout">Back to login</a>
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
