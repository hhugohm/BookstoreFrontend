<%@page import="java.util.Optional"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!  
            public static final String TITLE="Book Details";
%>
 <jsp:include page="util/./header.jsp"/>
   <link rel="stylesheet" href="../resources/css/bookstore.css">
<html>
    <body>
          <%@include file="../jspf/header.jspf" %>
       <div>
            <h1  class="general_title_bar"><% out.print(TITLE);%> </h1>
        </div>
        <jsp:useBean class="bookstore.backend.datamodel.Book" scope="request" id="book"  />
        <jsp:include page="book/./bookTable.jsp"/>
        
    </body>
</html>


