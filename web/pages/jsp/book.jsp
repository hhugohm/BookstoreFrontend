<%@page import="java.util.Optional"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/pages/jspf/util/header_css.jsp"/>
<%!
    public static final String TITLE = "Book Details";
%>

<html>
    <%@include file="/pages/jspf/header.jspf" %>
    <div>
        <h1  class="general_title_bar"><% out.print(TITLE);%> </h1>
    </div>
    <jsp:useBean class="bookstore.backend.datamodel.Book" scope="request" id="book"/>
    <jsp:include page="/pages/jspf/book/bookTable.jsp"/>
    <%@include file="/pages/jspf/footer.jspf" %>
</html>


