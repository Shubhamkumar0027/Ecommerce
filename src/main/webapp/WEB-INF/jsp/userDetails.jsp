<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Details - E-commerce</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>User Details</h1>
    <p>Username: ${user.username}</p>
    <p>Email: ${user.email}</p>
</body>
</html>
