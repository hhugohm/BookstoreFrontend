<%@page import="java.util.Optional"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/pages/jspf/util/header_css.jsp"/>
    <link rel="stylesheet" href="<c:url value ='/resources/css/bookstore.css'/>">
<%!
    public static final String TITLE = "Catalog Book";
%>
<html>
    <body>
    <%@include file="/pages/jspf/header.jspf" %>
        <div>
            <h1  class="general_title_bar"><% out.print(TITLE);%> </h1>
        </div>
        <jsp:include page="/pages/jspf/catalog/booksTable.jsp"/>
       <%@include file="/pages/jspf/footer.jspf" %>
    </body>
</html>