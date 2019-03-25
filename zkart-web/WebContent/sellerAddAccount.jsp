<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.iiitb.ooadvoid.AccessProperties" %>
    <%@ page import="com.iiitb.ooadvoid.CreateProperties" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Seller Hub</title>
<%@include file="metaContent.jsp" %>
</head>
<body>
<%@include file="headerSeller.jsp" %>
  <%if (request.getParameter("id") != null) {
		String x = request.getParameter("id");
		out.println("Successful "+ x );
 	 } %>

<div class="body-content outer-top-xs">
	<div class="container">
		<div class="row">
			<%@include file="sidebarSeller.jsp" %>
			<div class="col-md-9">

				<!-- add account section -->
        <div id="message"></div>
				<form id="insert_data" action="SellerUploadServlet" method="post"  enctype="multipart/form-data">
					<div class="search-result-container ">
						<div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
							<div class="row">
								<div class="widget-header" style="margin-bottom:10px; margin-left:15px;">
									<h3 class="widget-title"  style="color:#FF0000;"><B>FLIPKART ACCOUNT</B></h3>
								</div>

								<div class="search-result-container ">
            						<div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
	            						<div class="widget-header">
	                						<h4 class="widget-title"><B>Account Number<label style="color:red; padding-left:3px;">  *</label></B></h4>
	            						</div>
			    						<div class="row">
			      							<div class="col-md-5 sidebar">
		              							<input type="number" class="form-control input-lg"  name="accountNo" id="accountNo" autocomplete="name" >
		              							<input type="text" id="cnt_accno" name="cnt_accno" hidden="hidden"/>
		          							</div>
		         						</div>
        							</div>
      							</div>

      							<div class="search-result-container hide-for-existing">
            						<div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
	            						<div class="widget-header">
	                						<h4 class="widget-title"><B>Pin<label style="color:red; padding-left:3px;">  *</label></B></h4>
	            						</div>
			    						<div class="row">
			      							<div class="col-md-5 sidebar">
		              							<input type="password" class="form-control input-lg"  maxlength="4"  name="pin" id="pin" autocomplete="name" required/>
		              							<input type="text" id="cnt_pin" name="cnt_pin" maxlength="4" hidden="hidden" required/>
		          							</div>
		         						</div>
        							</div>
      							</div>

      							<div class="search-result-container ">
            						<div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
	            						<div class="widget-header">
	                						<h4 class="widget-title"><B>Balance</B></h4>
	            						</div>
			    						<div class="row">
			      							<div class="col-md-5 sidebar ">
		              							<input type="number" class="form-control input-lg"  name="bal" id="bal" autocomplete="name" >
		              							<input type="password" id="cnt_bal" name="cnt_bal" hidden="hidden" />
		          							</div>
		         						</div>
        							</div>
      							</div>

      						</div>
      					</div>
      				</div>

      				<div class="search-result-container hide-for-existing"  style="text-align:center;">
            			<div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
	            			<div class="widget-header">
	           					<input type="button" style="font-size:18px;color: white; padding: 8px 20px; background-color: #4CAF50;" value="DONE" onClick="AddAcc()"/>
								<input type="text" id="sellerid" name="sellerid" hidden="hidden"/>
	        					<input type="text" id="selleremail" name="selleremail" hidden="hidden"/>
							</div>
						</div>
      				</div>

      			</form>
      		</div>
      	</div>
      </div>
	</div>

<!-- JavaScripts placed at the end of the document so the pages load faster -->
<script src="./bootstrapFiles/js/jquery-1.11.1.min.js"></script>
<script src="./bootstrapFiles/js/bootstrap.js"></script>
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
<script src="./customJavascripts/cookies.js"></script>
<script src="./bootstrapFiles/js/sweetalert.min.js"></script>

<script>
	$(document).ready(function(){
		checkCookie();
		/* $('#accountNo').prop('disabled', false);
		$('#pin').prop('disabled', false); */
	})

/*	function SetAccInfo(){
		var result={'accountno': 100, 'pin': '1234', 'bal': '100'};
		$('#accountNo').val(result['accountno']);
		$('#pin').val(result['pin']);
		$('#bal').val(result['balance']);
	}*/

	function GetAccInfo(user){
	    var ctxPath = "<%=request.getContextPath()%>";
	    data={};
	    var id = user.id;
	    console.log(id);
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : ctxPath + "/webapi/accounts/user/"+id,
			dataType : "json", // data type of response
      statusCode: {
          404: function() {
            console.log("404");
          //window.location.href = ctxPath+"/sellerhome.jsp";
          },

          400: function() {
             console.log("400");
          //window.location.href = ctxPath+"/sellerhome.jsp";
         },
         200: function() {
           console.log("200");
           $('#message').html("<h2 style='color:red'>You already have a Flipkart Account.</h2>");
           $('#insert_data').hide();

          //window.location.href = ctxPath+"/sellerhome.jsp";
        },
        204: function() {
          console.log("no response");
         //window.location.href = ctxPath+"/sellerhome.jsp";
        }
        }
		});
	}

	function checkCookie() {
    	var result = getCookie("seller_details");
    	if (result != "") {
    		var user = JSON.parse(result);
			setCookie("seller_details", result, 30);
			document.getElementById("sellername").innerText = user.firstName;
			$('#selleremail').val(user.email);
			GetAccInfo(user); //sh - uncomment
			/* SetAccInfo() */ //sh - comment
    	}
    	else {
    		swal("Login failed. Try again.");
      		window.location = "sellerHub.jsp";
    		logout();
    	}
	}

	function logout()
	{
		deleteCookie("seller_details");
	}

	function AddAcc(){
		var ctxPath = "<%=request.getContextPath()%>";
    	var result = getCookie("seller_details");
		data={};
		data['userid'] = JSON.parse(result).id;
		data['accountno'] = $('#accountNo').val();
		data['balance'] = $('#bal').val();
		if (data['balance'] == "") data['balance'] = "0";
		data['pin'] = $('#pin').val();
		if (data['accountno'] == '' || parseInt(data['accountno']) <= 999) {
			swal({
	            title: "Wrong Account Number.",
	            text: "Please Add account number with atleast 4 digits.",
	            type: "warning"
	        });
	        return;
		} else if (data['pin'] == "" || isNaN(data['pin'])) {
			swal({
	            title: "Enter Correct PIN.",
	            text: "Please Enter PIN with 4 digits.",
	            type: "warning"
	        });
	        return;
		} else if (parseInt(data['balance']) < 0) {
			swal({
	            title: "Balance Incorrect.",
	            text: "Balance can't be less than 0.",
	            type: "warning"
	        });
	        return;
		}
		console.log(data);
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : ctxPath + "/webapi/accounts/create",
			dataType : "json", // data type of response
			data: JSON.stringify(data),
			/* success:function(result){
				if(result['accountno']!=''){
					$('#accountNo').prop('disabled', false);
					$('#pin').prop('disabled', false);
				}
				window.location.href = ctxPath+"/sellerhome.jsp";
			},
			error:function(result){
				alert("Something went wrong!!");
				window.location.href = ctxPath+"/sellerhome.jsp";
			} */
			statusCode: {
			    404: function() {
			      alert('page not found');
					window.location.href = ctxPath+"/sellerhome.jsp";
			    },

			    400: function() {
			       alert('bad request');
					window.location.href = ctxPath+"/sellerhome.jsp";
			   },
			   200: function() {
				    setTimeout(function() {
				        swal({
				            title: "Success",
				            text: "Seller Account Successfully Created.",
				            type: "success"
				        }, function() {
				        	window.location.href = ctxPath+"/sellerhome.jsp";
				        })
				    }, 1000);
			   }
			  }
		});
	}

</script>
</body>
</html>
