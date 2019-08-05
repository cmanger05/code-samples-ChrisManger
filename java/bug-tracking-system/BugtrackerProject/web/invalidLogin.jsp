<%-- 
    Document   : invalidLogin
    Created on : Mar 9, 2017, 5:11:18 PM
    Author     : sriganesh pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invalid Login</title>
        <link rel="stylesheet" type="text/css" href="css/master.css">
        <link rel="stylesheet" type="text/css" href="css/login_invalid.css">
    </head>
    <body>
        <div id="mainContainer">
            <h1>Login Attempt Failed</h1>
            <p>Your user name and/or password was incorrect. Please try again.</p>
            <form action="" method="get">
                <input type="submit" name="submit" value="Return to Login Page" />
            </form>
        </div>
</html>
