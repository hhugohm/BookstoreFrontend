<%@page import="java.util.Optional"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="util/./header.jsp"/>
<link rel="stylesheet" href="../resources/css/bookstore.css">

<%!
    public static final String TITLE = "Welcome Login";
%>
<html>
    <body>
        <%@include file="../jspf/header.jspf" %>
        <div>
            <h1  class="general_title_bar"><% out.print(TITLE);%></h1>
        </div>

        <div class="login .login_div_main_container" >
            <div class="login_div_login_fields" >
                <form method="POST" action="start_session">
                    <table id="t01">
                        <tr>
                            <th>User</th>
                            <td><input type="text" name="pUsername"/></td>
                        </tr>
                        <tr>
                            <th>Password</th>
                            <td><input type="password" name="pPassword"/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Login"/></td>
                        </tr>
                    </table>

                </form>
            </div>
        </div>

    </body>
</html>
