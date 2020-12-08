<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bookstore.css">
        <title>Manage Book</title>
    </head>
    <body>
        
        <%@include file="../jspf/header.jspf" %>
        
        <div>
            <h1 class="general_title_bar">${(param.pAction eq 'ADD')? 'Add New Book' : 'Edit Book'}</h1>
        </div>
        
        <div class="manage_div_main_container">
            <div class="manage_div_fields_container">
                <div class="manage_div_fields">
                    <form method="POST" action="manage_action?pId=${(param.pAction eq 'EDIT')? requestScope.book.id : null}">
                        
                        <label>Title:</label>
                        <br/>
                        <input type="text"
                               name="pTitle"
                               value="${(param.pAction eq 'EDIT')? requestScope.book.title : null}"
                               class="manage_input_text"/>
                        <br/>
                        
                        <label>Description:</label>
                        <br/>
                        <textarea name="pDescription"
                                  class="manage_textarea"
                                  rows="15">${(param.pAction eq 'EDIT')? requestScope.book.description : null}</textarea>
                        <br/>
                        
                        <label>Author:</label>
                        <br/>
                        <input type="text"
                               name="pAuthor"
                               value="${(param.pAction eq 'EDIT')? requestScope.book.author : null}"
                               class="manage_input_text"/>
                        <br/>
                        
                        <label>Publisher:</label>
                        <br/>
                        <input type="text"
                               name="pPublisher"
                               value="${(param.pAction eq 'EDIT')? requestScope.book.publisher : null}"
                               class="manage_input_text"/>
                        <br/>
                        
                        <label>Published Date:</label>
                        <br/>
                        <input type="text"
                               name="pPublishedDate"
                               value="${(param.pAction eq 'EDIT')? requestScope.book.publishedDate : null}"
                               class="manage_input_text"/>
                        <br/>
                        
                        <label>Language:</label>
                        <br/>
                        <input type="text"
                               name="pLanguage"
                               value="${(param.pAction eq 'EDIT')? requestScope.book.language : null}"
                               class="manage_input_text"/>
                        <br/>
                        
                        <label>Number of pages:</label>
                        <br/>
                        <input type="text"
                               name="pNumberOfPages"
                               value="${(param.pAction eq 'EDIT')? requestScope.book.numberOfPages : null}"
                               class="manage_input_text"/>
                        <br/>
                        
                        <label>Format:</label>
                        <br/>
                        <select name="pFormat"
                                class="manage_input_text">
                            <option value="PAPERBACK" ${(requestScope.book.format.value eq 'PAPERBACK')? 'selected' : ''}>Paperback</option>
                            <option value="HARDCOVER" ${(requestScope.book.format.value eq 'HARDCOVER')? 'selected' : ''}>Hardcover</option>
                            <option value="EBOOK"     ${(requestScope.book.format.value eq 'EBOOK')? 'selected' : ''}>eBook</option>
                            <option value="AUDIOBOOK" ${(requestScope.book.format.value eq 'AUDIOBOOK')? 'selected' : ''}>Audio Book</option>
                        </select>
                        <br/>
                        
                        <label>Price:</label>
                        <br/>
                        <input type="text"
                               name="pPrice"
                               value="${(param.pAction eq 'EDIT')? requestScope.book.price : null}"
                               class="manage_input_text"/>
                        <br/>
                        
                        <label>Availability:</label>
                        <br/>
                        <select name="pAvailability"
                                class="manage_input_text">
                            <option value="IN_STOCK"      ${(requestScope.book.availability.value eq 'IN_STOCK')? 'selected' : ''}>In stock</option>
                            <option value="PRESALE"       ${(requestScope.book.availability.value eq 'PRESALE')? 'selected' : ''}>Presale</option>
                            <option value="NOT_AVAILABLE" ${(requestScope.book.availability.value eq 'NOT_AVAILABLE')? 'selected' : ''}>Not available</option>
                        </select>
                        <br/>
                        
                        <label>Stock quantity:</label>
                        <br/>
                        <input type="text"
                               name="pStockQuantity"
                               value="${(param.pAction eq 'EDIT')? requestScope.book.stockQuantity : null}"
                               class="manage_input_text"/>
                        <br/>
                        
                        <div class="manage_div_buttons">
                            <c:if test="${param.pAction eq 'ADD'}">
                                <button type="submit"
                                        name="pAction"
                                        value="ADD"
                                        class="general_button">
                                    Add
                                </button>
                            </c:if>
                            <c:if test="${param.pAction eq 'EDIT'}">
                                <button type="submit"
                                        name="pAction"
                                        value="DELETE"
                                        class="general_button">
                                    Delete
                                </button>
                                <button type="submit"
                                        name="pAction"
                                        value="EDIT"
                                        class="general_button">
                                    Save
                                </button>
                            </c:if>
                        </div>
                        
                    </form>
                    
                </div>
            </div>
        </div>

        <c:if test="${param.pAction eq 'EDIT'}">
        <div class="manage_div_image">
            <div style="text-align: center;">
                <br/>
                <img id="outputImage"
                     src="${pageContext.request.contextPath}/images?pAction=R&pId=${(param.pAction eq 'EDIT')? requestScope.book.id : null}"
                     width="276px"
                     height="363px"/>
                <br/>
                <form method="POST"
                      enctype="multipart/form-data"
                      action="../images?pAction=U&pId=${(param.pAction eq 'EDIT')? requestScope.book.id : null}">
                    <input id="inputImage" type="file" name="pImage"/>
                    <br/>
                    <br/>
                    <input type="submit"
                           value="Save Image"
                           class="general_button"
                           style="width: 100%"/>
                </form>
            </div>
        </div>
        </c:if>
                               
    </body>
</html>
