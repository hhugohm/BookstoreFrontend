<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value ='/resources/css/bookstore.css'/>">
        <script src="../resources/js/bookstore.js"></script>
        <title>JSP Page</title>
    </head>
    <body>

        <div id="div_cart" class="header_div_cart">
            <div>
                <h1 class="general_title_bar">Your cart:</h1>
            </div>

            <table style="width: 100%;">
                <tr>
                    <th style="width: 60%;">Title</th>
                    <th style="width: 20%;">Price</th>
                    <th style="width: 20%;"></th>
                </tr>
                <c:forEach items="${sessionScope.books}" var="book">
                    <tr>
                        <td>${book.title}</td>
                        <td>${book.price}</td>
                        <td></td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
             <br/>
             <div  class="general_button_container" > 
                 <button  class="general_button_small"  
                          style="width: 30%"
                          onclick="hideCart()"
                          >
                         close  
                 </button>
            </div> 

    </body>
</html>
