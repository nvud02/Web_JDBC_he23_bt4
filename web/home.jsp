<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Category List</title>
</head>
<body>
    <h1>Category List</h1>
    <a href="addNewCategory.jsp">Add New Category</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="category" items="${data}">
                <tr>
                    <td><c:out value="${category.id}"/></td>
                    <td><c:out value="${category.name}"/></td>
                    <td><c:out value="${category.describe}"/></td>
                    <td>
                        <a href="update?id=${category.id}">Update</a>
                        <a href="#" onclick="doDelete('${category.id}')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script type="text/javascript">
        function doDelete(id) {
            if (confirm("Are you sure you want to delete category with ID='" + id + "'?")) {
                window.location.href = "delete?id=" + id;
            }
        }
    </script>
</body>
</html>
