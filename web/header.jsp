<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="in.gadgethub.utility.*,in.gadgethub.dao.*,in.gadgethub.dao.impl.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Logout Header</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />

		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
		/>

		<link rel="stylesheet" href="mycss.css" />
		<link
			rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
		/>
	</head>
	<body style="background-color: #e6f9e6">
		<%
                    String userType = (String)session.getAttribute("usertype") ;
                    if (userType == null) { //guest      
                %>
		<!-- Starting Navigation Bar -->
		<nav class="navbar navbar-expand-lg navbar-custom">
			<div class="container-fluid">
				<a class="navbar-brand" href="LandingServlet">
					<%=AppInfo.appName%>
				</a>
				<button
					class="navbar-toggler"
					type="button"
					data-bs-toggle="collapse"
					data-bs-target="#navbarNav"
					aria-controls="navbarNav"
					aria-expanded="false"
					aria-label="Toggle navigation"
				>
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav ms-auto">
						<!--Category JSP file-->
						<jsp:include page="category.jsp" />
						<li class="nav-item">
							<a class="nav-link" href="LandingServlet">Products</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="login.jsp">Login</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="register.jsp">Register</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
                <%
                    } /* Customer Header */
                else if (userType.equalsIgnoreCase("customer")) {
                    String userName = (String)session.getAttribute("username") ;
                    CartDaoImpl cartDao = new CartDaoImpl() ;
                    int notf = cartDao.getAllCartItems(userName).size() ;
                    %>
                    <nav class="navbar navbar-expand-lg navbar-custom"> 
                    <div class="container-fluid"> 
                     <a class="navbar-brand" href="UserHomeServlet"> 
                    <%=AppInfo.appName%> 
                    </a> 
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" 
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation"> 
                    <span class="navbar-toggler-icon"></span> 
                </button> 
                <div class="collapse navbar-collapse" id="navbarNav"> 
                    <ul class="navbar-nav ms-auto"> 
                        <!--Category JSP file--> 
                        <jsp:include page="category.jsp" /> 
                        <li class="nav-item"> 
                            <a href="./UserHomeServlet" class="nav-link">Products</a> 
                        </li> 
                        <% 
                            if (notf == 0) { 
                        %> 
                        <li class="nav-item"><a class="nav-link" href="./CartDetailsServlet"><span 
                                    class="glyphiconglyphicon-shopping-cart"></span>Cart 
                            </a></li>
                            <%
                                } else { 
                        %> 
                        <li class="nav-item"><a class="nav-link" href="./CartDetailsServlet" 
                                                style="margin: 0px; padding: 0px;" id="mycart"><i 
                                    data-count="<%=notf%>" 
                                    class="fa fa-shopping-cart fa-3x icon-white badge" 
                                    style="background-color: #333; margin: 0px; padding: 0px; padding-bottom: 0px; padding-top: 5px;"> 
                                </i></a></li> 
                                <% 
                                    } 
                                %> 
                        <li class="nav-item"><a class="nav-link" href="./OrderServlet">Orders</a></li> 
                        <li class="nav-item"><a class="nav-link" href="./UserProfileServlet">Profile</a></li> 
                        <li class="nav-item">
                            <form action="./LogoutServlet" method="post" onsubmit="return confirm('Are you sure you want to logout?');">
                                <button type="submit" class="nav-link btn btn-link" style="border: none; background: none; padding:1 ; cursor: pointer;">
                                    Logout
                                </button>
                            </form>
                        </li> 
                    </ul> 
                </div> 
            </div> 
        </nav>
                <% }else{ %>
 <!--admin navbar starts here-->
<nav class="navbar navbar-expand-lg navbar-custom"> 
      <div class="container-fluid"> 
        <a href="./LandingServlet" class="navbar-brand"> 
          <%=AppInfo.appName%> 
        </a> 
 
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#mynav"> 
          <span class="navbar-toggler-icon"></span> 
        </button>
        <div class="collapse navbar-collapse" id="mynav"> 
          <ul class="navbar-nav ms-auto">
            <li class="nav-item ">
              <a href="./AdminViewServlet" class="nav-link">Products</a>
            </li> 
 
            <li class="nav-item dropdown">
                
                <jsp:include page="category.jsp" /> 
            </li>          
 
            <li class="nav-item"> 
              <a href="./AdminStockServlet" class="nav-link">Stocks</a> 
            </li> 
            <li class="nav-item"> 
              <a href="./ShippedItemServlet" class="nav-link">Shipped</a> 
            </li> 
            <li class="nav-item"> 
              <a href="./UnshippedItemServlet" class="nav-link">Orders</a> 
            </li>
            <li class="nav-item dropdown"> 
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" 
                               aria-expanded="false"> 
                                Update Item 
                            </a> 
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown"> 
                                <li><a href="addProduct.jsp" class="dropdown-item">Add Product</a></li> 
                                <li><a href="removeProduct.jsp" class="dropdown-item">Remove Product</a></li> 
                                <li><a href="updateProductByID.jsp" class="dropdown-item">Update Product</a></li> 
                            </ul> 
                        </li> 
                        <li class="nav-item"> 
                            <form action="./LogoutServlet" method="post" onsubmit="return confirm('Are you sure you want to logout?');">
                                <button type="submit" class="nav-link btn btn-link" style="border: none; background: none; padding:1 ; cursor: pointer;">
                                    Logout
                                </button>
                            </form> 
                        </li> 
 
                    </ul> 
                </div> 
            </div> 
        </nav> 
 
 
        <%} 
        %>     
        <!-- End of Navigation Bar --> 
 
        <!--Company Header Starting  --> 
        <div 
            class="container-fluid text-center bg-warning mt-0 p-3" 
            style="--bs-bg-opacity: 0.5" 
            > 
            <h2><%=AppInfo.appName%></h2> 
            <h6>We specialize in Electronics</h6> 
            <form class="form-inline" action="LandingServlet" method="get"> 
                <div class="input-group"> 
                    <input 
                        type="text" 
                        class="form-control" 
                        name="search" 
                        placeholder="Search Items" 
                        required 
                        /> 
 
                    <input type="submit" class="btn btn-primary" value="Search" /> 
                </div> 
            </form> 
            <p 
                align="center" 
                style=" 
                color: blue; 
                font-weight: bold; 
                margin-top: 5px; 
                margin-bottom: 5px; 
                " 
                id="message" 
                > 
 
            </p> 
        </div> 
        <!-- Company Header Ending --> 
 
    </body> 
</html>