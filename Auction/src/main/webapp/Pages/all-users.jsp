<%@ page import="Models.User" %>
<%@ page import="Models.UserInfo" %>
<%@ page import="DAO.SqlUserDAO" %>
<%@ page import="static Helper.GeneralConstants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);
    UserInfo currentUserInfo = (UserInfo)session.getAttribute(CURRENT_USER_INFO_STRING);
%>
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
            <style>
                  table,
                  th,
                  td {
                    padding: 10px;
                    border: 1px solid black;
                    border-collapse: collapse;
                  }

                  form {
                    margin-top: 100px;
                  }
            </style>
    </head>
    <body>
        <div class="main-div">
            <h2 class="profile-name">All Users</h2>

            <div>

                <table border="1">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Username</th>
                      <th>isDealer</th>
                      <th>isAdmin</th>
                      <th>isBanned</th>
                      <th>Num of auctions won</th>
                      <th>Rating</th>
                    </tr>
                   </thead>
                   <tbody>

                        <%
                                SqlUserDAO userDao = (SqlUserDAO)application.getAttribute("SQL_USER_DAO");
                                for (User user : userDao.getAllUsers()){
                                    out.println("<tr><td>"+user.getId()+"</td><td>"+user.getUsername()+"</td><td>"+user.getIsDealer()+"</td><td>"+user.getIsAdmin()+"</td><td>"+user.getIsBanned()+"</td><td>"+user.getNumAuctionsWon()+"</td><td>"+user.getRating()+"</td></tr>");
                                }
                        %>
                    </tbody>
                </table>
            </div>

            <form class="form-box" action="BanUserServlet" method="post">
                <h3 class="h3">Enter account to ban</h3>
                <br>
                <input class="input-2" type="text" name="userToBan" placeholder="Enter username to ban">
                <input type="submit" value="Ban User" style="margin-left: -135px"/>
            </form>

            <form class="form-box" action="MakeDealerUserServlet" method="post" style="margin-top: 50px;">
                <h3 class="h3">Enter account to make Dealer</h3>
                <br>
                <input class="input-2" type="text" name="userToDealer" placeholder="Enter username to make dealer">
                <input type="submit" value="Make User Dealer" style="margin-left: -135px"/>
            </form>

            <form class="form-box" action="MakeAdminUserServlet" method="post" style="margin-top: 50px;">
                <h3 class="h3">Enter account to make Admin</h3>
                <br>
                <input class="input-2" type="text" name="userToAdmin" placeholder="Enter username to make admin">
                <input type="submit" value="Make User Admin" style="margin-left: -135px"/>
            </form>
            <span>
                <%  String errorMessage = (String)request.getAttribute("message");
                    if (errorMessage != null) { %>
                <span class="error-text"><%=errorMessage%></span>
                <%  } %>
            </span>
            <br>
            <a class="h4-link-2" href="account-home">Back</a>
        </div>
    </body>
</html>
