<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Confirmation - E-commerce</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>Order Confirmation</h1>
    <p>Thank you for your order, ${order.name}!</p>
    <p>Your order has been placed and will be shipped to:</p>
    <p>${order.address}</p>
    <p>Payment Information: ${order.paymentInfo}</p>
    <h2>Order Details:</h2>
    <ul>
        <c:forEach var="item" items="${order.cartItems}">
            <li>${item.product.name} - ${item.quantity} x ${item.product.price}</li>
        </c:forEach>
    </ul>
</body>
</html>
