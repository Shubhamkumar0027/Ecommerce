<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${product.name} - E-commerce</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>${product.name}</h1>
    <img src="${product.imageUrl}" alt="${product.name}" />
    <p>${product.description}</p>
    <p>${product.price}</p>
    <form action="<c:url value='/cart' />" method="post">
        <input type="hidden" name="productId" value="${product.id}" />
        <button type="submit">Add to Cart</button>
    </form>
</body>
</html>
