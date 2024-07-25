
<!DOCTYPE html>
<html>
<head>
    <title>Add New Category</title>
</head>
<body>
    <h1>Add New Category</h1>
    <form action="add" method="post">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required><br><br>
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        
        <label for="describe">Description:</label>
        <textarea id="describe" name="describe" rows="4" cols="50" required></textarea><br><br>
        
        <button type="submit">Add Category</button>
    </form>
    <br>
    <a href="list">Back to Category List</a>
</body>
</html>
