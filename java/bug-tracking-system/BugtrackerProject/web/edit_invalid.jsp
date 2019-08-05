<%-- 
    Document   : edit_invalid
    Created on : Mar 11, 2017, 4:27:23 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Invalid</title>
        <link rel="stylesheet" type="text/css" href="css/master.css">
        <link rel="stylesheet" type="text/css" href="css/edit_invalid.css">
    </head>
    <body>
        <div id="mainContainer">
            <h1>Invalid Request</h1>
            <p>Your request was invalid, please try again.</p>
            <form action="/BugtrackerProject/List" method="post">
                <input type="submit" name="submit" value="Return to Ticket List Page" />
            </form>
        </div>
    </body>
</html>
