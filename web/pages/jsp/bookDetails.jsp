<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bookstore.css">
        <title>Book Details</title>
    </head>
    <body>
        
        <%@include file="../jspf/header.jspf" %>
        
        <div>
            <h1 class="general_title_bar">${requestScope.book.title}</h1>
        </div>
        
        Title:          ${requestScope.book.title}       <br/>
        Description:    ${requestScope.book.description} <br/>
        Author:         ${requestScope.book.author}      <br/>
        
    </body>
</html>
