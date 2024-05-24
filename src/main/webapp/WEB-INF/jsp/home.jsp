<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home - E-commerce</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>Featured Products</h1>
    <div>
        <c:forEach var="product" items="${products}">
            <div>
                <img src="${product.imageUrl}" alt="${product.name}" />
                <h2><a href="<c:url value='/products/${product.id}' />">${product.name}</a></h2>
                <p>${product.description}</p>
                <p>${product.price}</p>
                <form action="<c:url value='/cart' />" method="post">
                    <input type="hidden" name="productId" value="${product.id}" />
                    <button type="submit">Add to Cart</button>
                </form>
            </div>
        </c:forEach>
    </div>
</body>
</html>
