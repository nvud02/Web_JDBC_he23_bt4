<!DOCTYPE html>
<html>
<head>
    <title>Update Category</title>
</head>
<body>
    <h1>Update Category</h1>
    <form action="update" method="post">
        <input type="hidden" name="id" value="${category.id}"/>
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${category.name}" required><br><br>
        
        <label for="describe">Description:</label>
        <textarea id="describe" name="describe" rows="4" cols="50" required>${category.describe}</textarea><br><br>
        
        <button type="submit">Update Category</button>
    </form>
    <br>
    <a href="list">Back to Category List</a>
</body>
</html>
