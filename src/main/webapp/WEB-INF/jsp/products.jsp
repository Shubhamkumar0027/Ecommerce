<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products - E-commerce</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>Product Listing</h1>
    <div>
        <form method="get" action="<c:url value='/products' />">
            <label for="category">Category:</label>
            <select name="category" id="category">
                <option value="">All</option>
                <option value="electronics">Electronics</option>
                <option value="clothing">Clothing</option>
                <!-- Add more categories as needed -->
            </select>
            <label for="minPrice">Min Price:</label>
            <input type="number" name="minPrice" id="minPrice" min="0" />
            <label for="maxPrice">Max Price:</label>
            <input type="number" name="maxPrice" id="maxPrice" min="0" />
            <button type="submit">Filter</button>
        </form>
    </div>
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
