<%-- 
    Document   : edit_invalid_failed_load
    Created on : Mar 13, 2017, 5:57:08 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket Not Exist</title>
        <link rel="stylesheet" type="text/css" href="css/master.css">
        <link rel="stylesheet" type="text/css" href="css/edit_invalid_failed_load.css">
    </head>
    <body>
        <div id="mainContainer">
            <h1>Invalid Request</h1>
            <p>The ticket you requested does not exist.</p>
            <form action="/BugtrackerProject/List" method="post">
                <input type="submit" name="submit" value="Return to Ticket List Page" />
            </form>
        </div>
    </body>
</html>
