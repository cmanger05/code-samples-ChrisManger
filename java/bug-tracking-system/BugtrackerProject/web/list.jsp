<%-- 
    Document   : list
    Created on : Mar 7, 2017, 12:02:05 AM
    Author     : chris
--%>
<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="Issue.controller.List"        
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Issue List</title>
        <link rel="stylesheet" type="text/css" href="css/master.css">
        <link rel="stylesheet" type="text/css" href="css/list.css">   
    </head>
    <body>
        <div id="buttonContainer">
            <p>Welcome, ${userFullName}!</p>
            <p><a href="/BugtrackerProject/LogoutServlet">Logout</a></p>
            <button onclick="location.href = '/BugtrackerProject/Edit';">Create a New Ticket</button>
        </div>
        <div id="mainContainer">
            <form method="post" action="">
                <input type="hidden" name="assignee" value="${userId}" />
                <input type="submit" name="search" value="View My Open Tickets" />
            </form>
            <form method="post" action="">
                <p class="searchTitle"> <label> Search Tickets By Assignee and/or Project</label></p>
                <select name="assignee" >
                    <option value="">--Select--</option>
                    <c:forEach items="${userDropDownList}" var="userDropDownItem">
                            <option value="${userDropDownItem.get("user_id")}" ${userDropDownItem.get("user_id") == assignee ? 'selected="selected"' : ''}>
                                ${userDropDownItem.get("fullname")}
                            </option>                                                             
                    </c:forEach>
                </select>
                <select name="project">
                    <option value="">--Select--</option>
                    <c:forEach items="${projectDropDownList}" var="projectDropDownItem">
                        <option value="${projectDropDownItem.get("project_id")}" ${projectDropDownItem.get("project_id") == project ? 'selected="selected"' : ''}>
                            ${projectDropDownItem.get("project_name")}
                        </option>                                                             
                    </c:forEach>
                </select>
                <p>
                    <input type="submit" name="search" value="Search" />
                    <input type="submit" name="search" value="Search Only Open Tickets" />
                </p>
                <p><i>Note: Only tickets that are &#8220;In Progress&#8221; are considered open</i></p>
            </form>
            <form action="" method="get">
                <input type="submit" name="search" value="Reset Filters" />
            </form>

            <div align="center">
                <table border="1" cellpadding="3" CELLSPACING="1" style="text-align: center;">
                    <caption> <h2> Tickets </h2> </caption>
                <tr>
                  <th>Issue ID</th>
                  <th>Project Name</th>
                  <th>Owner Name </th>
                  <th>Assignee Name </th>      
                  <th>Status Name </th>
                  <th>Priority Name </th>
                  <th>Title </th> 
                  <th>Resolution Date </th>
                </tr>   

                <c:forEach items="${issuesList}" var="issueItem">
                <tr>
                   <td><a href = "/BugtrackerProject/Edit?issue_id=${issueItem.get("issue_id")}">${issueItem.get("issue_id")}</a> </td>    
                   <td>${issueItem.get("project_name")}</td>
                   <td>${issueItem.get("owner_name")}</td>
                   <td>${issueItem.get("assignee_name")}</td>
                   <td>${issueItem.get("status_name")}</td>
                   <td>${issueItem.get("priority_name")}</td>
                   <td><a href = "/BugtrackerProject/Edit?issue_id=${issueItem.get("issue_id")}">${issueItem.get("title")}</a></td>  
                   <td>${issueItem.get("resolution_date")}</td>  
                </tr>
                </c:forEach>
               </table>
            </div>
        </div>
    </body>
</html>
