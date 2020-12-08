<%@page import="java.util.List"%>
<%@page import="bookstore.backend.datamodel.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bookstore.css">
        <title>Catalog</title>
    </head>
    <body>
        <%@include file="../jspf/header.jspf" %>
        <div>
            <h1 class="general_title_bar">Catalog</h1>
        </div>
        
        <table style="width: 100%;">
            <tr style="vertical-align: top;">
                <td style="width: 50%;">
                    <table class="catalog_table_data">
                        <tr>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Price</th>
                            <c:if test="${sessionScope.sessionActive eq 'Y' and sessionScope.sessionType eq 'ADMIN'}">
                                <th>Manage</th>
                            </c:if>
                            <c:if test="${sessionScope.sessionActive eq 'Y' and sessionScope.sessionType eq 'CUSTOMER'}">
                                <th>Add to Cart</th>
                            </c:if>
                        </tr>
                    <c:if test="${sessionScope.sessionActive eq 'Y' and sessionScope.sessionType eq 'ADMIN'}">
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td style="text-align: center;">
                                <a href="manage?pAction=ADD">Add new book</a>
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach items="${requestScope.books}" var="book">
                        <tr>
                            <td> ${book.title} </td>
                            <td> ${book.author} </td>
                            <td> ${book.price} </td>
                            <c:if test="${sessionScope.sessionActive eq 'Y' and sessionScope.sessionType eq 'ADMIN'}">
                                <td style="text-align: center;">
                                    <a href="manage?pAction=EDIT&pId=${book.id}">Manage</a>
                                </td>
                            </c:if>
                            <c:if test="${sessionScope.sessionActive eq 'Y' and sessionScope.sessionType eq 'CUSTOMER'}">
                                <td style="text-align: center;">
                                    <a   href="javascript:void(0)"  onclick="addToCart(${book.id})"  >
                                        <img border="0"
                                             src="${pageContext.request.contextPath}/resources/images/add.png"
                                             width="20"
                                             height="20"
                                             />
                                    </a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </table>
                </td>
                <td>
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
                </td>
            </tr>
        </table>
        
    </body>
</html>
