<%@page import="java.util.Optional"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
     <jsp:include page="util/./header.jsp"/>
     
      <link rel="stylesheet" href="../resources/css/bookstore.css">
     
<%!
    public static final String TITLE = "Catalog Book";
%>
<html>
    <body>
        <%@include file="../jspf/header.jspf" %>
        <div>
            <h1  class="general_title_bar"><% out.print(TITLE);%> </h1>
        </div>
        <jsp:include page="catalog/./booksTable.jsp"/>
    </body>
</html>