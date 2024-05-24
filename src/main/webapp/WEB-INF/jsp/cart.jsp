<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart - E-commerce</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>Shopping Cart</h1>
    <div>
        <c:forEach var="item" items="${cartItems}">
            <div>
                <img src="${item.product.imageUrl}" alt="${item.product.name}" />
                <h2>${item.product.name}</h2>
                <p>${item.product.price}</p>
                <p>Quantity: <input type="number" name="quantity" value="${item.quantity}" onchange="updateCart(${item.id}, this.value)" /></p>
                <button onclick="removeFromCart(${item.id})">Remove</button>
            </div>
        </c:forEach>
    </div>
    <form action="<c:url value='/checkout' />" method="get">
        <button type="submit">Checkout</button>
    </form>

    <script>
        function updateCart(itemId, quantity) {
            fetch('<c:url value="/cart" />/' + itemId, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ quantity: quantity })
            }).then(response => response.json()).then(data => location.reload());
        }

        function removeFromCart(itemId) {
            fetch('<c:url value="/cart" />/' + itemId, {
                method: 'DELETE'
            }).then(response => response.json()).then(data => location.reload());
        }
    </script>
</body>
</html>
