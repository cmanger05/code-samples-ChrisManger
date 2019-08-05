<%-- 
    Document   : list
    Created on : Mar 7, 2017, 12:02:05 AM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
        <link rel="stylesheet" type="text/css" href="css/master.css">
        <link rel="stylesheet" type="text/css" href="css/edit_success_existing.css">
    </head>
    <body>
        <div id="mainContainer">
            <h1>Success</h1>
            <p>You have successfully edited the ticket.<p>
            <form action="/BugtrackerProject/List" method="post">
                <input type="submit" name="submit" value="Return to Ticket List Page" />
            </form>
        </div>
    </body>
</html>
