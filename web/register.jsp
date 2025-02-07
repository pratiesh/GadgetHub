<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
  </head>
  <body>
       <jsp:include page="header.jsp" />
       <%
           String message = (String)request.getAttribute("message") ;
        %>
    <div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <form action="./RegistrationServlet" method="post" class="col-md-6 myForm">
                
          <div class="text-center mt-3">
            <h2 class="text-primary">Registration Form</h2>
            <%
                if (message!=null) { %>
                <P style="color: crimson"><%=message%></P>
            <%
                }   
            %>
          </div>
          <div class="row mt-3">
            <div class="col-md-6 form-group">
              <label for="user-name">Name</label>
              <input type="text" name="username" class="form-control" id="user-name" required>
            </div>

            <div class="col-md-6 form-group">
              <label for="user-email">E-mail</label>
              <input type="email" name="useremail" class="form-control" id="user-email" required>
            </div>
          </div>
          <div class="form-group mt-3">
            <label for="address">Address</label>
            <textarea name="address" id="address" class="form-control" required></textarea>
          </div>
          <div class="row mt-3">
            <div class="col-md-6 form-group">
              <label for="mobile">Mobile No.</label>
              <input type="tel" name="mobile" class="form-control" id="mobile" required>
            </div>

            <div class="col-md-6 form-group">
              <label for="pincode">Pincode</label>
              <input type="number" name="pincode" class="form-control" id="pincode" required>
            </div>
          </div>
          <div class="row mt-3">
            <div class="col-md-6 form-group">
              <label for="password">Password</label>
              <input type="password" name="password" class="form-control" id="password" required>
            </div>

            <div class="col-md-6 form-group">
              <label for="cpassword">Confirm Password</label>
              <input type="password" name="cpassword" class="form-control" id="cpassword" required>
            </div>
          </div>
          <div class="row mt-3">
                <div class="col-md-12 form-group">
                    <label for="loginas">Register As</label>
                    <select name="usertype" id="loginas" class="form-control">
                        <option value="customer">Customer</option>
                    <option value="admin">Admin</option>
                    </select>            
                </div>
            </div>
          <div class="row mt-3 text-center">
            <div class="col-md-6 form-group">
              <button type="reset" class="btn btn-danger">Reset</button>  
            </div>
            <div class="col-md-6 form-group">
              <button type="submit" class="btn btn-primary">Register</button>  
            </div>
          </div>
          <br>
        </form>
    </div>
  </div>
    <jsp:include page="footer.jsp" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  </body>
</html>