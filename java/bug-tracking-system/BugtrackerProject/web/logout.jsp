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
        <link rel="stylesheet" type="text/css" href="css/logout.css">
    </head>
    <body>
        <div id="mainContainer">
            <h1>You are now Logged Out</h1>
            <p>If you wish to login again, please click the link Below</p>
            <form action="/BugtrackerProject/LoginServlet" method="get">
                <input type="submit" name="submit" value="Go to Login Page" />
            </form>
        </div>
</html>
