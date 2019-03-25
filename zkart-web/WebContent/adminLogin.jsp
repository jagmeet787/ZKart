<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" href="./bootstrapFiles/css/bootstrap.min.css">
<link rel="stylesheet" href="./bootstrapFiles/css/SellerRegStyle.css">
</head>
<body style="background: url(&quot;./images/sellerHub/Signup-screen-BG.jpg&quot;) no-repeat;
  background-size: 100%;">
  <header>
		<nav class="nav navbar-default" style="background-color:#027cd5">
			<a href="index.jsp"><img class="logo" src="./images/flopkartLogo.jpg"></a>
		</nav>
	</header>
	<br/><br/>
<div class="well" style="width:300px; margin: 0 auto;">
	<h2>Admin Login</h2>
	<form action="admin.jsp" method="post">
		<div class="floating-div" style="padding-left: 0px; padding-top: 15px; padding-bottom: 15px; text-align: left">
			<span class="blocking-span"> <input type="email" id="email" name="email" class="inputText" required /> 
				<span class="floating-label pull-left"><b>Enter Email</b></span>
			</span>
		</div>
		<div class="floating-div" style="padding-left: 0px; padding-top: 15px; padding-bottom: 15px; text-align: left">
			<span class="blocking-span"> <input type="password" id="password" name="password" class="inputText" required /> 
				<span class="floating-label pull-left"><b>Enter Password</b></span>
			</span>
		</div>
		<input onclick="return adminLogin();" type="submit" class="submitButton"/>
	</form>
</div>
</body>
<script src="./bootstrapFiles/js/jquery-1.11.1.min.js"></script>
<script src="./bootstrapFiles/js/bootstrap.min.js"></script>
<script src="./bootstrapFiles/js/bootstrap-hover-dropdown.min.js"></script>
<script src="./bootstrapFiles/js/owl.carousel.min.js"></script>
<script src="./bootstrapFiles/js/echo.min.js"></script>
<script src="./bootstrapFiles/js/jquery.easing-1.3.min.js"></script>
<script src="./bootstrapFiles/js/bootstrap-slider.min.js"></script>
<script src="./bootstrapFiles/js/jquery.rateit.min.js"></script>
<script src="./bootstrapFiles/js/bootstrap-select.min.js"></script>
<script src="./bootstrapFiles/js/wow.min.js"></script>
<script src="./bootstrapFiles/js/scripts.js"></script>
<script src="./bootstrapFiles/js/sweetalert.min.js"></script>
<script>
function adminLogin(){
	var email = $("#email").val();
	var password = $("#password").val();
	if(email=="admin@admin.com" && password=="password"){
		return true;
	}
	else
		swal("Wrong credentials. Try again");
		return false;
}
</script>
</html>