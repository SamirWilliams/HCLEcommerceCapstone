<%@ include file="includes/needed-code.jsp"%>

<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <title>Welcome</title>
    <style>
.my-container {
	margin-top: 20px;
}

#my-image{
	border-radius: 5px;
}

.my-row {
	
}

.my-col {
	
}

#my-card {
	box-shadow: 0 0 20px 2px rgba(0, 0, 0, .1);
	transition:2s;
}

#my-card:hover{
	transform:scale(1.1);
	z-index:1;
}



.my-bod {
	height: 10%;
}
</style>
</head>
<body class="gradient-custom1">
<%@ include file="includes/navbar-principal.jsp" %>
<div class="container">
    <div class="card-header text-center my-3"><h1>Product Categories</h1></div>
    <div class="row">
        <div class="col-md-4 my-3" id="my-card">
            <div class="card w-100 h-100 text-center" style="width: 18rem;">
                <div class="card-body" >
                	<a href="product?cat=Phone"><img src = "https://i.pcmag.com/imagery/articles/00h6w5mO2CNC9tgOnu0u04N-5.fit_lim.size_1600x900.v1601499805.jpg" width = "300" height = "300"></a>
                    <h4 class="card-title my-2">Phones</h4>
                    <div>
                        <a href="product?cat=Phone" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 my-3" id="my-card">
            <div class="card w-100 h-100 text-center" style="width: 18rem;">
                <div class="card-body">
                	<a href="product?cat=Laptop"><img src = "https://cdn.shopify.com/s/files/1/0299/6495/9881/articles/best_laptop_2017_main_review_stuff-3-833x474_1170x.png?v=1591887931" width = "300" height = "300"></a>
                    <h4 class="card-title my-2">Laptops</h4>
                    <div>
                        <a href="product?cat=Laptop" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 my-3" id="my-card">
            <div class="card w-100 h-100 text-center" style="width: 18rem;">
                <div class="card-body">
                <a href="product?cat=Desktop"><img src = "https://static.turbosquid.com/Preview/2019/12/12__07_47_48/001.jpgFA7C74F9-9769-4697-977E-9798EC170A58Large.jpg" width = "300" height = "300"></a>
                    <h4 class="card-title my-2">Desktops</h4>
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
