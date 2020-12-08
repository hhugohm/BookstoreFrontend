<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bookstore.css">
        <title>Login</title>
    </head>
    <body>
        <%@include file="../jspf/header.jspf" %>
        <div>
            <h1 class="general_title_bar">Login</h1>
        </div>

       <div class="login_div_main_container">
            <div class="login_div_login_fields">
                <form method="POST" action="start_session">
                    Username: <input type="text" name="pUsername"/>     
                    <br/><br/>
                    Password: <input type="password" name="pPassword"/> 
                    <br/><br/>
                    <input type="submit" value="Login"/>
                </form>
            </div>
        </div>
        
        <%--
         <div class="login_div_main_container">
            <div class="login_div_login_fields">
                <form method="POST" action="j_security_check">
                    Username: <input type="text" name="j_username"/>     
                    <br/><br/>
                    Password: <input type="password" name="j_password"/> 
                    <br/><br/>
                    <input type="submit" value="Login"/>
                </form>
            </div>
        </div>
        
        
         <div class="login_div_main_container">
            <div class="login_div_login_fields">
                <form method="POST" action="start_session">
                    Username: <input type="text" name="pUsername"/>     
                    <br/><br/>
                    Password: <input type="password" name="pPassword"/> 
                    <br/><br/>
                    <input type="submit" value="Login"/>
                </form>
            </div>
        </div>
                   --%>
    </body>
</html>
