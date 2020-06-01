<table id="t01">
    <tr>
        <th>Description</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>ID</td>
        <td><jsp:getProperty name="book" property="id"/></td>
    </tr>
    <tr>
        <td>Title</td>
        <td><jsp:getProperty name="book" property="title"/></td>
    </tr>
    <tr>
        <td>Author</td>
        <td>${requestScope.book.author}</td>
    </tr>
</table>
