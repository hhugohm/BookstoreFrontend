<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bookstore.backend.datamodel.Book"%>
<%@page import="java.util.List"%>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
%> 
<table id="t01">
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
    </tr>
    <%
        for (Book book : books) {
    %>
    <c:forEach items="${requestScope.books}" var="book"  >
    <tr>
        <td><%= book.getTitle()%></td>
        <td><%= book.getAuthor()%></td>
        <td><%= book.getPrice()%></td>
    </tr>
     </c:forEach>   
    <%
        }
    %>  
</table>


<!--
        <jsp:include page="catalog/./booksTable.jsp"/>
        
        -->