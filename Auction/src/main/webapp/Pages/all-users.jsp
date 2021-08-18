<%@ page import="Models.User" %>
<%@ page import="DAO.SqlUserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="utf-8">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/a8ba60cbab.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="../Styles/reset.css">
        <link rel="stylesheet" type="text/css" href="../Styles/style.css">
        <title>All Users</title>

    </head>
    <body>
        <div class="main-div">
            <h2 class="profile-name">All Users</h2>

            <form class="form-box" action="BanUserServlet" method="post">
                <h3 class="h3">Enter account to ban</h3>
                <br>
                <input class="input-2" type="text" name="userToBan" placeholder="Enter username to ban" style="margin-left: auto;">
                <input type="submit" value="Ban User" style="margin-left: 20px; width: 150px;" />
            </form>

            <form class="form-box" action="MakeDealerUserServlet" method="post" style="margin-top: 50px;">
                <h3 class="h3">Enter account to make Dealer</h3>
                <br>
                <input class="input-2" type="text" name="userToDealer" placeholder="Enter username to make dealer" style="margin-left: auto;">
                <input type="submit" value="Make User Dealer" style="margin-left: 20px; width: 150px;"/>
            </form>

            <form class="form-box" action="MakeAdminUserServlet" method="post" style="margin-top: 50px;">
                <h3 class="h3">Enter account to make Admin</h3>
                <br>
                <input class="input-2" type="text" name="userToAdmin" placeholder="Enter username to make admin" style="margin-left: auto;">
                <input type="submit" value="Make User Admin" style="margin-left: 20px; width: 150px;"/>
            </form>
            <span>
                <%  String errorMessage = (String)request.getAttribute("message");
                    if (errorMessage != null) { %>
                <span class="error-text"><%=errorMessage%></span>
                <%  } %>
            </span>

            <div>

                <table class="center" border="1" style = "margin-top: 35px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Admin Status</th>
                        <th>Dealer Status</th>
                        <th>Banned Status </th>
                        <th>Num of auctions won</th>
                        <th>Rating</th>
                    </tr>
                    </thead>
                    <tbody>

                    <%
                        SqlUserDAO userDao = (SqlUserDAO)application.getAttribute("SQL_USER_DAO");
                        for (User user : userDao.getAllUsers()){
                            out.println("<tr><td>"+user.getId() +
                                        "</td><td>"+user.getUsername() +
                                        "</td><td>"+user.getIsAdmin() +
                                        "</td><td>"+user.getIsDealer() +
                                        "</td><td>"+user.getIsBanned() +
                                        "</td><td>"+user.getNumAuctionsWon() +
                                        "</td><td>"+user.getRating() +
                                        "</td></tr>");
                            System.out.println(user.getRating());
                        }
                    %>
                    </tbody>
                </table>
                <br>
            </div>

            <a class="h4-link-2" href="account-home">Back</a>
        </div>
    </body>
</html>
