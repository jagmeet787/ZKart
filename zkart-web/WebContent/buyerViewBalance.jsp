<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="zkart.jsp.AccessProperties" %>
<%@ page import="zkart.jsp.CreateProperties" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="metaContent.jsp" %>
	<title>View Balance</title>
	<style>
	.custom-file-upload 
	{
		border: 1px solid #ccc;
		display: inline-block;
		padding: 6px 12px;
		cursor: pointer;
	}
	.dp .tooltiptext 
	{
		visibility: hidden;
		background-color: black;
		color: #fff;
		text-align: center;
		border-radius: 6px;
		/* Position the tooltip */
		position: absolute;
		z-index: 1;
		overflow:visible;
	}
	.dp:hover .tooltiptext 
	{
	    visibility: visible;
	}
	</style>
	</head>

<body>
<!-- ============================================== HEADER ============================================== -->

<%@include file="header.jsp" %>

<div class="body-content outer-top-xs">
  <div class="container">
    <div class="row">
      <div class="col-md-3 sidebar"> 
        <div class="sidebar-module-container">
          <div class="sidebar-filter"> 
            <!-- ============================================== IMAGE============================================== -->
            <div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
            	
              <h5 class="section-title">Hello,</h5>
              <div class="widget-header">
                <h4 class="widget-title" id="userID"></h4>
                <input type="text" id="userEmail" hidden="hidden">
              </div>
              <div class="sidebar-widget-body">
                <ul class="list">
                  <li>
						<div id="dp" class="dp"><img id="dp1" height="100px"  src="#" >
						<span id="tooltiptext" class="tooltiptext"></span></div>
                  	
					<div id="no_dp">		
					<%if (request.getParameter("imgName") != null) { %>
						<p style="color:red;"><b>Login again to view uploaded dp</b></p>
					 <% } else { %>		
						<form id="update_img" method="post" action="UploadServlet" enctype="multipart/form-data">
						<label for="dpImg" class="custom-file-upload">
						    <img height="50px" width="50px" src="./images/profile-pic-male_icon.svg">   Choose File
						</label>
							<input type="file"  style="display:none"
								id="dpImg" name="dpImg" accept=".jpg, .jpeg, .png, .JPG, .PNG, .JPEG" required/>
							<input type="submit" class="btn btn-warning btn-block" value="Set profile picture"/>
							<input type="text" id="ID" name="ID" hidden="hidden"/>
						</form>
					<% } %>
					</div>
				 </li>
                </ul>
              </div>
              <!-- /.sidebar-widget-body --> 
            </div>
            <!-- /.sidebar-widget --> 
            <!-- ============================================== IMAGE: END ============================================== --> 
            
            
            <!-- ============================================== Other TAGS ============================================== -->
          <!----------- Account------------->
          <br/>
              <div class="side-menu animate-dropdown outer-bottom-xs">
          			<div class="head"><i class="fas fa-list"></i><a href="myOrder.jsp" style="color:black" onmouseleave="style='color:black'" onmouseenter="style='color:blue'"> My Orders </a></div>
          	  </div>
       <div class="side-menu animate-dropdown outer-bottom-xs">
          <div class="head"><i class="icon fa fa-user"></i>Account Settings</div>
			<div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
            	
              <div class="sidebar-widget-body">
                <ul class="list">
                  <li><h5><a href="myProfile.jsp">Profile Information</a></h5></li>
                  <li><h5><a href="underConstruct.html">Notification Preferences</a></h5></li>
                </ul>
              </div>
              <!-- /.sidebar-widget-body --> 
            </div>
            <!-- /.sidebar-widget --> 
          <!-- /.megamenu-horizontal --> 
        </div>
        <div class="side-menu animate-dropdown outer-bottom-xs">
          <div class="head"><i class="icon fa fa-credit-card"></i>Payments</div>
			<div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
            	
              <div class="sidebar-widget-body">
                <ul class="list">
                  <li><h5><a href="flopkartAccount.jsp">Flopkart Bank Account</a></h5></li>
                </ul>
              </div>
              <!-- /.sidebar-widget-body --> 
            </div>
            <!-- /.sidebar-widget --> 
          <!-- /.megamenu-horizontal --> 
        </div>
        <!-- /.side-menu -->  
        <div class="side-menu animate-dropdown outer-bottom-xs">
          <div class="head"><i class="icon fa fa-folder-open"></i>My Stuff</div>
			<div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
            	
              <div class="sidebar-widget-body">
                <ul class="list">
                  <li><h5><a href="wishlist.jsp">My Wishlist</a></h5></li>
                </ul>
              </div>
              <!-- /.sidebar-widget-body --> 
            </div>
            <!-- /.sidebar-widget --> 
          <!-- /.megamenu-horizontal --> 
        </div>
        <!-- /.side-menu --> 
        
            <div class="sidebar-widget  wow fadeInUp outer-top-vs " style="visibility: hidden; animation-name: none;">
            <form id="logout_form" action="./index.jsp">
              <input type="submit" class="head btn-link" style="color:black;font-weight:bold" value="LOGOUT" onclick="logout();"></input>
            </form>
            </div>
            
            <!-- ============================================== Other: END ============================================== -->

          </div>
          <!-- /.sidebar-filter --> 
        </div>
        <!-- /.sidebar-module-container --> 
      </div>
      <!-- /.sidebar -->
      <div class="col-md-9"> 
      	 <div class="search-result-container ">
           <div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
              <div class="row">
	              <div class="widget-header" style="margin-bottom:10px; margin-left:15px;">
	                <h3 class="widget-title"  style="color:#FF0000;"><B>View Balance of Your Accounts</B></h3>
	                <!--  <h2>Balance: <span id="balanceNow"></span></h2>-->
	              </div>
              </div>
            </div>
           </div>
           <div class="search-result-container ">
            <div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
            	
            	<div class="row">
				      <div class="col-md-5 sidebar"> 
			              <input type="number" class="form-control input-lg"  name="accountno" id="accountno" placeholder="Account Number" required>
			          </div>
			          <div class="col-md-5 sidebar"> 
			          	<input type="password" class="form-control input-lg" maxlength="4" name="password" id="password" placeholder="Password" required>
			          </div>
		          </div>
		          <br>
	            <div class="widget-header">
	            </div>   
			    <div class="row">
			      <div class="col-md-5 sidebar"> 
		              <input type="number" class="form-control input-lg"  name="amount" id="amount" placeholder="Balance" disabled>
		          </div>
		          <div class="col-md-5 sidebar"> 
		              <input type="button" style="font-size:18px;color: white;
   							padding: 8px 20px; background-color: #4CAF50;" value="View Balance" onclick="viewBalance()"/>
		          </div>
		         </div> 
        		</div>
      	</div>
	  </div>
    </div>
  </div>
</div>	  
<%@include file="footer.jsp" %>


<script>
$(document).ready(function() { 
	$("#unregistered").hide();
    var ctxPath = "<%=request.getContextPath()%>"
	headerFunctions(ctxPath);
	checkCookie();	
	$('#myModal').modal('hide');
});

function checkCookie() 
{
    var user = getCookie("user_details");
    if (user != "") 
    {
		setCookie("user_details", user, 30);
		diplayUser(JSON.parse(user));
    } 
    else 
    {
    	logout();
    }
}

function resetCookie() 
{
    var user = getCookie("user_details");
    if (user != "") 
    {
		setCookie("user_details", user, 30);
		diplayUser(JSON.parse(user));
    } 
    else 
    {
    	logout();
    }
}

$("#dp1").hover(function(){
	$("#tooltiptext").show();
    $("#tooltiptext").text($('#userEmail').val()+"\n"+$("#userID").text());
});

function diplayUser(user)
{	
	$('#userID').html(user.firstName+" "+user.lastName);
	$('#userEmail').val(user.email);
<%--     <% CreateProperties cp = new CreateProperties(); %> --%>
    <% AccessProperties ap = new AccessProperties(); %>
    var imgServerURL = "<%=ap.getImageServerURL() %>"; 

	if(!("pic_URL" in user) || user.pic_URL=="")

	{	
		$("#no_dp").show();
		$("#dp").hide();
	}
	else
	{	
        $('#dp1').attr('src',imgServerURL+user.pic_URL);
		$("#dp").show();
		$("#no_dp").hide();
	}
}

function logout()
{
	deleteCookie("user_details");
}


function viewBalance()
{
  console.log("viewBalance(): ");
  var pass=document.getElementById('password').value;
  var accountno = document.getElementById("accountno").value;
  if(pass.length!=4 || isNaN(pass)){
	  swal({
          title: "Incorrect PIN.",
          text: "Please Enter 4 digits PIN.",
          type: "warning"
      });
    console.log("password length incorrect !");
    return false;
  } else if (accountno == '' || accountno <= 999) {
		swal({
            title: "Wrong Account Number.",
            text: "Please Add account number with atleast 4 digits.",
            type: "warning"
        });
        return;
	}
  var ctxPath = "<%=request.getContextPath()%>";
  var user = JSON.parse(getCookie("user_details"));
  
  console.log(user);
  var userID = user.id;
  $.ajax(
  {
	  
    type : 'GET',
    contentType : 'application/json',
    url : ctxPath + "/webapi/accounts/accountno/" + accountno,
    success : function(result)
    {
    	console.log("hello");
    	console.log(result);
    	document.getElementById("amount").value=result.balance;
  
    },
      error:function() {
          //swal("error occurred");
      }
  });
}

$('#dpImg').change(function()
{
	var i = $(this).prev('label').clone();
	var file = $('#dpImg')[0].files[0].name;
	$(this).prev('label').text(file);
});

function findUser(ctxPath) 
{
	$.ajax(
	{
		type : 'POST',
		contentType : 'application/json',
		url : ctxPath + "/webapi/users/email",
		dataType : "json", // data type of response
		data : formToJSON(),
		success : renderDetails
});
}
	

</script>
</body></html>