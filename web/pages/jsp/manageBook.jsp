<%@page import="java.util.Optional"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="../resources/css/bookstore.css">
<%!
    public static final String TITLE = "Manage Book";
%>
<html>
    <body>
    <%@include file="/pages/jspf/header.jspf" %>
        <div>
            <h1  class="general_title_bar">
                ${(param.pAction eq 'ADD')?'Add New Book':'Edit Book'}
            </h1>
        </div>
            <div  class="manage_div_main_container">
                <div class="manage_div_fields_container">
                    <div  class="manage_div_fields">
                        <form  method="POST"  action="manage_action?pId=${(param.pAction eq 'EDIT')? requestScope.book.id : null}">
                            <label>Title:</label>
                            </br>
                            <input id="pTitle" type="text" name="pTitle" value="${(param.pAction eq 'EDIT')? requestScope.book.title : null}" class="manage_input_text"/>
                            </br>
                             <label>Description:</label>
                        <br/>
                        <textarea name="pDescription"
                                  class="manage_textarea"
                                  rows="15">${(param.pAction eq 'EDIT')? requestScope.book.description: null}</textarea>
                        <br/>
                            </br>
                            <label>Author:</label>
                            </br>
                            <input id="pAuthor:" type="text" name="pAuthor" value="${(param.pAction eq 'EDIT')? requestScope.book.author : null}" class="manage_input_text"/>
                            </br>
                            <label>Publisher:</label>
                            </br>
                            <input id="pPublisher:" type="text" name="pPublisher" value="${(param.pAction eq 'EDIT')? requestScope.book.publisher : null}" class="manage_input_text"/>
                            </br>
                            <label>Publisher Date:</label>
                            </br>
                            
                            
                             <input id="pPublisherDate" type="text" name="pPublisherDate" value="${(param.pAction eq 'EDIT')?  requestScope.book.publishedDate : null}" class="manage_input_text"/>
                            
                            </br>
                            <label>Language:</label>
                            </br>
                            <input id="pLanguage" type="text" name="pLanguage" value="${(param.pAction eq 'EDIT')? requestScope.book.language : null}" class="manage_input_text"/>
                            </br>
                            <label>Numbre Of Pages:</label>
                            </br>
                            <input id="pNumberOfPages" type="text" name="pNumberOfPages" value="${(param.pAction eq 'EDIT')? requestScope.book.numberOfPages : null}" class="manage_input_text"/>
                            </br>
                            
                           <label>Format:</label>
                        <br/>
                        <select name="pFormat"
                                class="manage_input_text">
                            <option value="PAPERBACK"  ${(requestScope.book.format.value eq 'PAPERBACK')? 'selected' : ''}  >Paperback</option>
                            <option value="HARDCOVER"  ${(requestScope.book.format.value eq 'HARDCOVER')? 'selected' : ''}>Hardcover</option>
                            <option value="EBOOK"      ${(requestScope.book.format.value eq 'EBOOK')? 'selected' : ''} >ebook</option>
                            <option value="AUDIOBOOK"  ${(requestScope.book.format.value eq 'AUDIOBOOK')? 'selected' : ''} >AudioB ook</option>
                        </select>
                        <br/>
                            <label>Price:</label>
                            </br>
                            <input id="pPrice" type="text" name="pPrice" value="${(param.pAction eq 'EDIT')? requestScope.book.price : null}" class="manage_input_text"/>
                            </br>
                            <label>Availability:</label>
                        <br/>
                        <select name="pAvailability"
                                
                                class="manage_input_text">
                            <option   value="IN_STOCK"    ${(requestScope.book.availability.value eq 'IN_STOCK')? 'selected' : ''}     >In stock</option>
                            <option   value="PRESALE"       ${(requestScope.book.availability.value eq 'PRESALE')? 'selected' : ''}>Presale</option>
                            <option   value="NOT_AVAILABLE" ${(requestScope.book.availability.value eq 'NOT_AVAILABLE')? 'selected' : ''} >Not available</option>
                        </select>
                        <br/>
                            <label>Stock Quantity:</label>
                            </br>
                            <input id="pStockQuantity" type="text" name="pStockQuantity" value="${(param.pAction eq 'EDIT')? requestScope.book.stockQuantity : null}" class="manage_input_text"/>
                            </br>
                            
                            <div  class="manage_div_buttons" >
                                <c:if  test="${param.pAction eq 'ADD'}">
                                    <button  type="submit" name="pAction" value="ADD" class="general_button">Add</button>
                                </c:if>
                                <c:if  test="${param.pAction eq 'EDIT'}">
                                    <button  type="submit" name="pAction" value="DELETE" class="general_button">Delete</button>
                                    <button  type="submit" name="pAction" value="EDIT" class="general_button">Save</button>
                                </c:if>
                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>
    </body>
</html>