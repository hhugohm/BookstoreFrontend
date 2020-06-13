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


 <textarea id="pDescription"  name="pDescription" rows="15"  class="manage_textarea"/>


<!--
        <jsp:include page="catalog/./booksTable.jsp"/>
        
        -->
        
        
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

        
        
        
        
        
        
        
        <table class="catalog_table_image">
                        <tr>
                            <th style="text-align: center;">
                                <span id="outputTitle">Title</span>
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <div style="text-align: center;">
                                    <img id="outputImage"
                                         src="${pageContext.request.contextPath}/resources/images/not_available.png"
                                         width="230px"
                                         height="300px"/>
                                    <h3 id="outputDescription" style="text-align: justify;">Description</h3>
                                </div>
                            </td>
                        </tr>
                    </table>
                                         
                                         
                                         
 <%@page import="java.util.Optional"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Book Details</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/book.css" />"/>
    <link rel="stylesheet" href="<c:url value ='/resources/css/bookstore.css'/>">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
</head>