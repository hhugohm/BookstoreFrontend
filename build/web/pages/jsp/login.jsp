<%@page import="java.util.Optional"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/pages/jspf/util/header_css.jsp"/>
<%!
    public static final String TITLE = "Welcome Login";
%>
<script type="text/javascript" src="<c:url  value='/resources/js/user_form.js'/>"></script>
<html>
    <body>
    <%@include file="/pages/jspf/header.jspf" %>
        <div>
            <h1  class="general_title_bar"><% out.print(TITLE);%></h1>
        </div>
          <jsp:include page="/pages/jspf/login/loginForm.jsp"/>
      
    </body>
</html>
