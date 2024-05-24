<!DOCTYPE html>
<html>
<head>
    <title>Register - E-commerce</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>Register</h1>
    <form action="<c:url value='/register' />" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required />
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required />
        <button type="submit">Register</button>
    </form>
</body>
</html>
