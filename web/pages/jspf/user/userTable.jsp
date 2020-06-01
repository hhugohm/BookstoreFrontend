<table id="t01">
    <tr>
        <th>Description</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Name</td>
        <td><jsp:getProperty name="user" property="name"/></td>
    </tr>
    <tr>
        <td>Email</td>
        <td><jsp:getProperty name="user" property="email"/></td>
    </tr>
    <tr>
        <td>Username</td>
        <td>${requestScope.user.username}</td>
    </tr>
    <tr>
        <td>Password</td>
        <td>${requestScope.user.password}</td>
    </tr>
</table>