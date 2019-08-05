<%-- 
    Document   : edit
    Created on : Mar 7, 2017, 12:01:11 AM
    Author     : chris
--%>
<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="Issue.controller.Edit"        
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
    <head>
        <title>Bug List</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/master.css">
        <link rel="stylesheet" type="text/css" href="css/edit.css">
    </head>
    <body>
        <div id="buttonContainer">
            <p>Welcome, ${userFullName}!</p>
            <p><a href="/BugtrackerProject/LogoutServlet">Logout</a></p>
            <form action="/BugtrackerProject/List" method="get">
                <input type="submit" name="submit" value="Return to Ticket List Page"  />
            </form>
        </div>
        <div id="mainContainer">
            <div id="innerContainer">
                <c:set var="issueIdAsString">${issueId}</c:set>
                <c:choose>
                    <c:when test="${issueIdAsString.matches('[0-9]+')}">
                        <h1>Edit Ticket #${issueId}: ${title}</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Create a New Ticket</h1>
                    </c:otherwise>
                </c:choose>
                <c:if test="${hasErrors == true}">
                    <div class="errorContainer">
                        <c:forEach items="${errorsList}" var="error">
                            <p class="error">${error}</p>
                        </c:forEach>
                    </div>
                </c:if>
                <form  method ="post" action = "">
                    <p><input type ="submit" name ="Submit" value ="Save Ticket"></p>
                    <input name="issueId" type="hidden" value="${issueId}" />
                    <p><span class="required">*</span> <label>Date Identified (format: YYYY-MM-DD HH:MM:SS)</label>
                        <input type="text" name="dateidentified" value="${dateidentified}">
                    </p>                              
                    <p><span class="required">*</span> <label>${statusLabel}</label>
                        <select name ="status" size ="1">
                        <c:forEach items="${statusDropDownList}" var="statusDropDownItem">
                                <option value="${statusDropDownItem.get("status_id")}" ${statusDropDownItem.get("status_id") == status ? 'selected="selected"' : ''}>
                                    ${statusDropDownItem.get("status_name")}
                                </option>                                                             
                        </c:forEach>
                        </select>

                        <span class="required">*</span> <label>${priorityLabel}</label>
                        <select name ="priority" size ="1">
                        <c:forEach items="${priorityDropDownList}" var="priorityDropDownItem">
                                <option value="${priorityDropDownItem.get("priority_id")}" ${priorityDropDownItem.get("priority_id") == priority ? 'selected="selected"' : ''}>
                                    ${priorityDropDownItem.get("priority_name")}
                                </option>                                                             
                        </c:forEach>
                        </select>

                        <span class="required">*</span> <label>${ownerLabel}</label>
                        <select name ="owner" size="1">
                        <c:forEach items="${userDropDownList}" var="userDropDownItem">
                                <option value="${userDropDownItem.get("user_id")}" ${userDropDownItem.get("user_id") == owner ? 'selected="selected"' : ''}>
                                    ${userDropDownItem.get("fullname")}
                                </option>                                                             
                        </c:forEach>
                        </select>
                    </p>      
                    <p><span class="required">*</span> ${titleLabel}</p>
                    <p><textarea name="title" rows = "1" cols="56">${title}</textarea></p>

                    <p><span class="required">*</span> <label>${projectLabel}</label>
                        <select name ="project" size ="1">
                        <c:forEach items="${projectDropDownList}" var="projectDropDownItem">
                                <option value="${projectDropDownItem.get("project_id")}" ${projectDropDownItem.get("project_id") == project ? 'selected="selected"' : ''}>
                                    ${projectDropDownItem.get("project_name")}
                                </option>                                                             
                        </c:forEach>
                        </select>  


                    <p><label><span class="required">*</span> ${identifiedbyLabel}</label>
                        <select name ="identifiedby" size="1">
                        <c:forEach items="${userDropDownList}" var="userDropDownItem">
                                <option value="${userDropDownItem.get("user_id")}" ${userDropDownItem.get("user_id") == identifiedby ? 'selected="selected"' : ''}>
                                    ${userDropDownItem.get("fullname")}
                                </option>                                                             
                        </c:forEach>
                        </select>

                <label><span class="required">*</span> ${assigneeLabel}</label>
                <select name ="assignee" size ="1">
                <c:forEach items="${userDropDownList}" var="userDropDownItem">
                        <option value="${userDropDownItem.get("user_id")}" ${userDropDownItem.get("user_id") == assignee ? 'selected="selected"' : ''}>
                            ${userDropDownItem.get("fullname")}
                        </option>                                                             
                </c:forEach>
                </select>  
            </p> 


            <p><label>${targetResolutionDateLabel} (format: YYYY-MM-DD HH:MM:SS)</label>
                <input type="text" name="targetResolutionDate" value="${targetResolutionDate}">
            </p>
            <p><label>${actualResolutionDateLabel} (format: YYYY-MM-DD HH:MM:SS)</label>
                <input type="text" name="actualResolutionDate" value="${actualResolutionDate}">
            </p>
            <div id="issueInformation">
                <p class="subHeading">Please describe the defect and the impact on the use case you are trying to perform.</p>            
                <p><span class="required">*</span> ${summaryLabel}</p>
                <p><textarea name="summary" rows = "5" cols="56">${summary}</textarea></p> 
                <p> <span class="required">*</span>${descriptionLabel}</p>
                <p><textarea name="description" rows = "10" cols="56">${description}</textarea></p> 
            </div>
            
            <c:if test="${hasComments == true}">
              <div class="commentsSection" align="center">
                <table border="1" cellpadding="3" CELLSPACING="1" style="text-align: center;" width="100%">
                    <caption> <h2> Comments </h2> </caption>

                <c:forEach items="${commentsList}" var="commentItem">
                <tr>   
                   <td>${commentItem.get("full_name")}</td>
                   <td>${commentItem.get("comment")}</td>
                </tr>
                </c:forEach>
               </table>
            </div>
            </c:if>
               
            <p>Add a Comment: </p>
            <p><textarea name="commentText" rows = "10" cols="56"></textarea></p> 
            
            <p><input type ="submit" name="Submit" value ="Save Ticket"></p>    
        </form>
            </div>
        </div>
</body>
</html>

