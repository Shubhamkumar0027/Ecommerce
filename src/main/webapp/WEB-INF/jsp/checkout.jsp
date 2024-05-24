<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout - E-commerce</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>Checkout</h1>
    <form action="<c:url value='/checkout' />" method="post">
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required />
        <label for="address">Address:</label>
        <input type="text" name="address" id="address" required />
        <label for="paymentInfo">Payment Information:</label>
        <input type="text" name="paymentInfo" id="paymentInfo" required />
        <button type="submit">Place Order</button>
    </form>
</body>
</html>
