<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bookstore.backend.datamodel.Book"%>
<%@page import="java.util.List"%>

<table id="t01">
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
    </tr>
    <c:forEach items="${requestScope.books}" var="book"  >
    <tr>
        <td>${book.title}</td>
        <td>${book.author}</td>
        <td>${book.price}</td>
    </tr>
     </c:forEach>   
</table>
