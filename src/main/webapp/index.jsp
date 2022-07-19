<%@ include file="includes/needed-code.jsp"%>

<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Welcome</title>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar.jsp" %>
<div class="container">
    <div class="card-header text-center my-3"><h1>Product Categories</h1></div>
    <div class="row">
        <div class="col-md-4 my-3">
            <div class="card w-100 text-center" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="card-title">Phones</h4>
                    <div>
                        <a href="product?cat=Phone" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 my-3">
            <div class="card w-100 text-center" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="card-title">Laptops</h4>
                    <div>
                        <a href="product?cat=Laptop" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 my-3">
            <div class="card w-100 text-center" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="card-title">Desktops</h4>
                    <div>
                        <a href="product?cat=Desktop" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="includes/footer.jsp" %>
</body>
</html>
