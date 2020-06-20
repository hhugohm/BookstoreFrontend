<%@page import="java.util.Optional"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="../resources/js/bookstore.js"/>

<jsp:include page="/pages/jspf/util/header_css.jsp"/>
    <link rel="stylesheet" href="<c:url value ='/resources/css/bookstore.css'/>">
<%!
    public static final String TITLE = "Catalog Book";
%>
<html>
    <body onload="hideCart()"  >
    <%@include file="/pages/jspf/header.jspf" %>
        <div>
            <h1  class="general_title_bar"><% out.print(TITLE);%> </h1>
        </div>
        <jsp:include page="/pages/jspf/catalog/booksTable.jsp"/>
      
    </body>
</html>