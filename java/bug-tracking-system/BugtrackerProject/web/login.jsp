<%-- 
    Document   : login
    Created on : Mar 7, 2017, 12:02:46 AM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/master.css">
        <link rel="stylesheet" type="text/css" href="css/login.css">
    </head>
    <body>
        <div id="mainContainer"> 
            <div id="innerContainer">
                <form action = "LoginServlet" method = "post">
                    <h1>Login</h1>
                    <table id="loginTable">
                        <tr>
                            <td><p class="tableRow">Username</p></td>
                            <td><p class="tableRow"><input type="text" name="username" /></p></td>
                        </tr>
                        <tr>
                            <td><p class="tableRow">Password</p></td>
                            <td><p class="tableRow"><input type="password" name="password" /></p></td>                    
                        </tr>
                    </table>
                    <p class="loginButton"><input type="submit" value="Submit" name="submit"/></p>
                </form> 
            </div>
        </div>      
    </body>
</html>
