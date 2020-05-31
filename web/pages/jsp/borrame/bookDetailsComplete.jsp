<%@page import="java.util.Optional"%>
<%@page import="bookstore.backend.datamodel.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Details</title>
        <style>
            table {
                margin: auto;
                width:50%;
            }
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
            th, td {
                padding: 15px;
                text-align: left;
            }
            table#t01 tr:nth-child(even) {
                background-color: #eee;
            }
            table#t01 tr:nth-child(odd) {
                background-color: #fff;
            }
            table#t01 th {
                background-color: black;
                color: white;
            }
        </style>
    </head>
    <body>
        <%!  
            public static final String TITLE="Book Details";
         %>
        
        <%
            Book book = (Book) request.getAttribute("book");
        %>
        <h1> <center><% out.print(TITLE);%> </center></h1>
        <table id="t01">
            <tr>
                <th>Description</th>
                <th>Value</th>
            </tr>
            <tr>
                <td>ID</td>
                <td><jsp:scriptlet>out.print(book.getId());</jsp:scriptlet></td>
            </tr>
           <tr>
                <td>Title</td>
                <td><jsp:expression>book.getTitle()</jsp:expression></td>
            </tr>
            <tr>
                <td>Author</td>
                <td><%=book.getAuthor()%></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><%=book.getDescription()  %></td>
            </tr>
            <tr>
                <td>Publisher</td>
                <td><%=book.getPublisher()  %></td>
            </tr>
            <tr>
                <td>Language</td>
                <td><%=book.getLanguage()  %></td>
            </tr>
            <tr>
                <td>Format</td>
                <td><%=book.getFormat()  %></td>
            </tr>
            <%
                if(book.getImage()!=null){
                   %>
                 <tr>
                    <td>Image</td>
                    <td><%=book.getImage()  %></td>
                </tr>
              <%       
                } 
              %>
            <tr>
                <td>Pages</td>
                <td><%=book.getNumberOfPages()  %></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><%=book.getPrice()  %></td>
            </tr>
            <tr>
                <td>Date</td>
                <td><%=book.getPublishedDate()  %></td>
            </tr>
            <tr>
                <td>Stock</td>
                <td><%=book.getStockQuantity()  %></td>
            </tr>
        </table>
    </body>
</html>