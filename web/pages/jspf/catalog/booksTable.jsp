<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bookstore.backend.datamodel.Book"%>
<%@page import="java.util.List"%>


<table style="width: 100%" >
    <tr style="vertical-align: top;" >
        <td style="width: 50%" >
            <table class="catalog_table_data"  >
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Price - <c:out value="${sessionScope.sessionActive}" /><c:out value="${sessionScope.sessionType}" /></th>
                    <c:if  test="${sessionScope.sessionActive eq 'Y' and sessionScope.sessionType eq 'ADMIN' }" >
                            <th>Manage</th>
                    </c:if>
                     <c:if  test="${sessionScope.sessionActive eq 'Y' and sessionScope.sessionType eq 'CUSTOMER' }" >
                     <th>Add to Cart</th>
                    </c:if>      
                </tr>
                
                 <c:if  test="${sessionScope.sessionActive eq 'Y' and sessionScope.sessionType eq 'ADMIN' }" >
                     <tr>
                         <td></td>
                          <td></td>
                           <td></td>
                          <td style="text-align: center;"  >
                                 <a href="manage?pAction=ADD">Add new Book</a>
                          </td>
                     </tr>    
                </c:if>  
                
                <c:forEach items="${requestScope.books}" var="book">
                    <tr>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.price}</td>
                         <c:if  test="${sessionScope.sessionActive eq 'Y' and sessionScope.sessionType eq 'ADMIN' }" >
                             <td style="text-align: center;"  >
                                 <a href="manage?pAction=EDIT&pId=${book.id}">Manage</a>
                             </td>
                        </c:if>
                        <c:if  test="${sessionScope.sessionActive eq 'Y' and sessionScope.sessionType eq 'CUSTOMER' }" >
                            <td style="text-align: center" >
                                <a href="">
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
            <table class="catalog_table_image" >
                <tr>
                    <th style="text-align: center" > 
                        <span id="outputTitle">Title</span>
                    </th>
                </tr>
                <tr>
                    <td>
                        <div style="text-align: center" > 
                            <img id="outputImage" src="${pageContext.request.contextPath}/resources/images/not_available.png"
                                 width="230px" height="300px"/>
                            <h3 id="outputDescription"  style="text-align: justify;" >Description</h3>
                        </div>
                    </td>
                </tr>
            </table>

        </td>
    </tr>

</table>



