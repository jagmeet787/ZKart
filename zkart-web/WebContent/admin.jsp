<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="zkart.jsp.AccessProperties"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
<link rel="stylesheet" href="./bootstrapFiles/css/bootstrap.min.css">
<link rel="stylesheet" href="./bootstrapFiles/css/SellerRegStyle.css">
</head>
<body style="background: url(&quot;./images/sellerHub/Signup-screen-BG.jpg&quot;);
  background-size: 100%;">

  	<!-- fields to store variables for counting and other information -->
	<input type="text" id="count" name="count" hidden="hidden"/>
	<input type="text" id="itemid" name="itemid" hidden="hidden"/>
	<input type="text" id="img1" name="img1" hidden="hidden"/>

	<header>
		<nav class="nav navbar-default" style="background-color:#027cd5">
			<a href="index.jsp"><img class="logo" src="./images/flopkartLogo.jpg"></a>
			<div style="float:right; padding-top:30px; padding-right: 5px">
				<a style="text-decoration:underline; color:white;font-size: 20px; font-weight:500;" href="adminDeals.jsp">Click here to add new deal category</a><br/>
				<%-- <button onclick="seeAdminBalance()">Click here to see account balance</button><br/> --%>
				<span style="color:white; display=none" id="rupees">Account Balance: &#8377; </span>
        <span id="adminBalance" style="color:white"></span>
        <button onclick="seeAdminBalance()">Refresh</button>
			</div>
		</nav>
	</header>
	<div class="row" style="margin-top:20px">
				<div class="col-md-1"></div>
				<div class="col-md-5">
					<div class="panel-group checkout-steps" id="accordion">
						<!-- categories  -->
						<div  id="categories_panel" class="panel panel-default checkout-step-01" >

						    <!-- panel-heading -->
							<div class="panel-heading">
					    	<h4 class="unicase-checkout-title">
						        <a data-toggle="collapse" class="" data-parent="#accordion"
						        href="#collapseCategories">Categories</a>
						     </h4>
					    	</div>
					    	<!-- panel-heading -->

							<!-- panel-body  -->
							<div id="#collapseCategories" class="panel-collapse collapse in">
							    <div class="panel-body">
							    	<ul class="list-group" id="categoryList">
									</ul>
							       <div class="container">
									<h4>Enter new category</h4><br/>
									<form id="form1">
										<input class="form-control" type="text" id="catName"
											placeholder="Enter category name">
										<br/>
										<input type="submit" class="btn btn-primary"
											onclick="insertCategory();" />
									</form>
								</div>
								</div>
							</div>
							<!-- panel-body  --><!-- row -->


						</div>
						<!-- End checkout-step-01  -->

					</div><!-- /.checkout-steps -->



				</div>
				<div class="col-md-5">
					<div class="panel-group checkout-steps" id="accordion">
						<!-- categories  -->
						<div  id="subcategories_panel" class="panel panel-default checkout-step-01" >

						    <!-- panel-heading -->
							<div class="panel-heading">
					    	<h4 class="unicase-checkout-title">
						        <a data-toggle="collapse" class="" data-parent="#accordion"
						        href="#collapseSubcategories">Sub-Categories</a>
						     </h4>
					    	</div>
					    	<!-- panel-heading -->

							<!-- panel-body  -->
							<div id="#collapseSubcategories" class="panel-collapse collapse in">
							    <div class="panel-body">
							       <div class="container">
									<form id="form2">
										<div id="content"></div><br/>
										<input type="button" class="btn btn-warning" onclick="DispSubCat()" value="Subcategories">
										<br/>
							    	<ul class="list-group" id="SubCat">
									</ul>
									<h4>Enter new subcategory</h4>
										<br /> <input class="form-control" type="text" id="subcatName"
											placeholder="Enter subcategory name">
										<br/>
										<input type="submit" class="btn btn-primary"
											onclick="return insertSubcategory();" />
									</form>
								</div>
								</div>
							</div>
							<!-- panel-body  --><!-- row -->


						</div>
						<!-- End checkout-step-01  -->

					</div><!-- /.checkout-steps -->



				</div>
	</div>
	<div class="row" style="margin-top:20px">
			<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="panel-group checkout-steps" id="accordion">
						<!-- checkout-step-01  -->
						<div  id="user_panel" class="panel panel-default checkout-step-01" >

						    <!-- panel-heading -->
							<div class="panel-heading">
					    	<h4 class="unicase-checkout-title">
						        <a data-toggle="collapse" class="" data-parent="#accordion"
						        href="#collapseUser">Users</a>
						     </h4>
					    	</div>
					    	<!-- panel-heading -->

							<!-- panel-body  -->
							<div id="collapseUser" class="panel-collapse collapse in">
							    <div class="panel-body">
									<div class="table-responsive">
									  <table id="user_table" class="table">
									  <thead>
								    	<tr>
								    		<th>User Name</th>
								    		<th>UserId (Email)</th>
								    		<th>Password</th>
										</tr>
								    	</thead>
								    	<tbody id="userTable_body">
								    	</tbody>
								    </table>
							       </div>
								</div>
							</div>
							<!-- panel-body  --><!-- row -->


						</div>
						<!-- End checkout-step-01  -->

					</div><!-- /.checkout-steps -->



				</div>
				</div>
			<div class="row" style="margin-top:20px">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="panel-group checkout-steps" id="accordion">
						<!-- checkout-step-02  -->
						<div  id="seller_panel" class="panel panel-default checkout-step-01" >

						    <!-- panel-heading -->
							<div class="panel-heading">
					    	<h4 class="unicase-checkout-title">
						        <a data-toggle="collapse" class="" data-parent="#accordion"
						        href="#collapseSeller">Sellers</a>
						     </h4>
					    	</div>
					    	<!-- panel-heading -->

							<!-- panel-body  -->
							<div id="collapseSeller" class="panel-collapse collapse in">
							    <div class="panel-body">
									<div class="table-responsive">
									  <table id="user_table" class="table">
									  <thead>
								    	<tr>
								    		<th>Seller Name</th>
								    		<th>SellerId (Email)</th>
								    		<th>Password</th>
										</tr>
								    	</thead>
								    	<tbody id="sellerTable_body">
								    	</tbody>
								    </table>
							       </div>
								</div>
							</div>
							<!-- panel-body  --><!-- row -->


						</div>
						<!-- End checkout-step-01  -->

					</div><!-- /.checkout-steps -->



				</div>

			</div>

	<!-- ravindra  item list-->

		<div class="row" style="margin-top:20px">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="panel-group checkout-steps" id="accordion">
						<!-- checkout-step-02  -->
						<div  id="seller_panel" class="panel panel-default checkout-step-01" >

						    <!-- panel-heading -->
							<div class="panel-heading">
					    	<h4 class="unicase-checkout-title">
						        <a data-toggle="collapse" class="" data-parent="#accordion"
						        href="#collapseSeller">Purchased Items</a>
						     </h4>
					    	</div>
					    	<!-- panel-heading -->

							<!-- panel-body  -->
							<div id="collapseSeller" class="panel-collapse collapse in">
							    <div class="panel-body">
									<div class="table-responsive">
									  <table id="purchase_table" class="table">
									  <thead>
								    	<tr>
								    		<th>Order Id</th>
								    		<th>Item Id</th>
								    		<th>Order Date</th>
								    		<th>Status</th>
										</tr>
								    	</thead>
								    	<tbody id="purchaseTable_body">
								    	</tbody>
								    </table>
							       </div>
								</div>
							</div>
							<!-- panel-body  --><!-- row -->


						</div>
						<!-- End checkout-step-01  -->

					</div><!-- /.checkout-steps -->



				</div>

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
$(document).ready(function(){

<%--     <% FlopkartCategoryClient client = new FlopkartCategoryClient(); %> --%>
<%-- 	String test1 = test.getImageURL();%> --%>
<%-- 	var test = "<%=test1%>"; --%>
// 	alert(test);
	$('#count').val(0);
	fetch();
	fetchUsers() ;
	fetchItems();
  seeAdminBalance();
});


function updateStatus(element, result, j, newStatus) {
	result = JSON.stringify(result);
	console.log("newStatus: " + newStatus);
	result = JSON.parse(result);
	result.status = newStatus;
	console.log(result);
	result = JSON.stringify(result);
	var ctxPath = "<%=request.getContextPath()%>";
	$.ajax({
		type: 'PUT',
		contentType : 'application/json',
		url : ctxPath + "/webapi/orders/update/"+result.orderId,
		data : result,
		statusCode : {
			200 : function() {
				swal("Status Updated to " + newStatus);
//				if (newStatus == "ORDER_RECIEVED") document.getElementById("rating_"+ j ).style.visibility = "visible";
				//$("#status" + j).text("<b>" + newStatus + "</b>");
				swal({
					  title: "Status Updated.",
					  //text: "Submit to run ajax request",
					  type: "info",
					  showCancelButton: false,
					  closeOnConfirm: false,
					  showLoaderOnConfirm: true
					}, function () {

					});

			},
			204 : function() {
				// ok with no response
				swal("Status Updated to " + newStatus);
			}
		}
	});
	window.location = "/flopkartPrototype/admin.jsp";
}


function displayListings(order_json)
{
	orderObj=JSON.stringify(order_json);
	console.log("display listings:"+JSON.stringify(order_json));
	var ctxPath = "<%=request.getContextPath()%>";
    <% AccessProperties ap = new AccessProperties(); %>
	console.log("status ="+ order_json.status);
	var data="";
	var j = $("#count").val();
	j++;
	$('#count').val(j);



	data+= "<tr><td>"+order_json.orderId+"</td>"+
	"<td>"+order_json.itemId+"</td>"+
	"<td>"+order_json.orderDate+"</td>"+
	"<td>"+order_json.status+"</td>";

	var orderTerminated = "\"ORDER_TERMINATED\"";
	var orderCompletedWithReturn = "\"ORDER_COMPLETED_AND_RETURNED\"";
	var orderCompleted = "\"ORDER_COMPLETED\"";

	var res = JSON.stringify(order_json);

	if(order_json.status == "ORDER_RETURNED")
	{//cancel transaction button
		data+="<td>"+
			  "<button id='return" + j + "'  name='cancel' onclick='updateStatus(this, " + res + ", " + j + ", " + orderCompletedWithReturn + ");'>Cancel Order and Send Money To Buyer</button>"+
		      "</td>";
	}
	else if(order_json.status == "ORDER_CANCELLED")
	{//send money to seller button
		data+="<td>"+
		  "<button id='cancel" + j + "'  name='cancel' onclick='updateStatus(this, " + res + ", " + j + ", " + orderTerminated + ");'>Complete Order and Send Money To Buyer</button>"+
	      "</td>";
	}
	// else if(order_json.status == "ORDER_RECIEVED")
	// {//send money to seller button
 //    console.log(order_json);
 //    var retDate=new Date(order_json.returnDate);
 //    console.log(retDate);
 //    var curDate=new Date();
 //    console.log(curDate);
 //    console.log(curDate-retDate);
 //    if(curDate-retDate>0){
 //      data+="<td>"+
 //  		  "<button id='complete" + j + "'  name='cancel' onclick='updateStatus(this, " + res + ", " + j + ", " + orderCompleted + ");'>Complete Order and Send Money To Seller</button>"+
 //  	      "</td>";
 //    }

	// }
	else
		{
		data+="<td></td>"
		}




	data+="</tr>";











	$("#purchaseTable_body").append(data);



}

//ravindra purchase item
function fetchItems()
{
    var ctxPath = "<%=request.getContextPath()%>";

    $.ajax(
    		{
    			type : 'GET',
    			contentType : 'application/json',
    			url : ctxPath + "/webapi/orders/",
    			dataType : "json", // data type of response
    			success : function(orders_json)
    			{
    				console.log(orders_json);
    	            for(var i in orders_json)
    	            {
    	            	displayListings(orders_json[i]);
    	            }
    	    	},
    	    	error:function()
    	    	{
    	        	swal("error occurred: Purchase table");
    	    	}
    		});

}

function fetchUsers()
{
    var ctxPath = "<%=request.getContextPath()%>";
	$.ajax(
	{
		type : 'GET',
		contentType : 'application/json',
		url : ctxPath + "/webapi/users",
		dataType : "json", // data type of response
		success : function(users_json)
		{
            for(var i in users_json)
            {
            	var data =  "<tr><td>"+users_json[i].firstName+" "+users_json[i].lastName+"</td>"+
				"<td>"+users_json[i].email+"</td>"+
				"<td>"+users_json[i].secret+"</td>"+
            				"</tr>";
            	if(users_json[i].userType==="seller")
            		$("#sellerTable_body").append(data);
            	else
            		$("#userTable_body").append(data);
            }
    	},
    	error:function()
    	{
        	swal("error occurred");
    	}
	});
}
function fetch()
{
    var ctxPath = "<%=request.getContextPath()%>";
	$.ajax(
	{
		type : 'GET',
		contentType : 'application/json',
		url : ctxPath + "/webapi/categories",
		dataType : "json", // data type of response
		success : function(result){
			var data="<select  class='form-control' id='catId'>"+"<option value=' "+ 0 +" '>Select a category</option>";
            for(var i in result){
               data+="<option value='"+result[i].id+"'>"+result[i].categoryName+"</option>";
           		var data1 =  "<li class='list-group-item'>"+result[i].categoryName+"</li>";
        		$("#categoryList").append(data1);
            }
            data += "</select>";
            $('#content').html(data);
    	},
    	error:function() {
        	//alert("error occurred");
    	}
	});
}

function formToJSON()
{
    var categoryId = $("#catId").val();
	var subcategoryName = $("#subcatName").val();
	var flopkartSubCat = JSON.stringify({
	    "categoryId" : categoryId,
	    "subcategoryName" : subcategoryName
	});
	//alert(flopkartSubCat);
	return flopkartSubCat;
}

function formToJSON1()
{
	var categoryName = $("#catName").val();
	var flopkartCat = JSON.stringify({
	    "categoryName" : categoryName
	});
	//alert(flopkartCat);
	return flopkartCat;
}

// function formToJSON2()
// {
// 	var categoryId = $("#catId").val();
// 	var subcategoryName = "";
// 	var flopkartSubCat = JSON.stringify({
// 	    "categoryId" : categoryId,
// 	    "subcategoryName" : subcategoryName
// 	});
// 	return flopkartSubCat;
// }

function insertSubcategory()
{
	if(document.getElementById("subcatName").value=="")
		{
			alert("field cannot be empty");
			return;
		}
	var ctxPath = "<%=request.getContextPath()%>";
	$.ajax(
		{
			type : 'POST',
			contentType : 'application/json',
			url : ctxPath + "/webapi/subcategories/create",
			data : formToJSON(),
			success : render(),
			error: function() {
				swal(JSON.stringify(err));
			}
	});
}

function render(){
	swal("Succesful entry into the database");
	window.location.reload(true);
}
var adminId=1;
function seeAdminBalance()
{
	var ctxPath = "<%=request.getContextPath()%>";
  $("rupees").show();
	$.ajax(
			{
				type : 'GET',
				url : ctxPath + "/webapi/accounts/user/"+adminId,
				dataType : "json", // data type of response
				success : function(result){
					console.log(result);
					document.getElementById("adminBalance").innerText = result['balance'];
/*					var data="";
		            for(var i in result){
		            	data+="<li class='list-group-item'>"+result[i].subcategoryName+"</li>";
		            }
		            $('#SubCat').html(data);*/
		    	},
		    	error:function(data,status) {
		    		swal("Data: " + data + "\nStatus: " + status);
		        	//alert("error occurred");
		    	}
			});
}

function insertCategory()
{
	if(document.getElementById("catName").value=="")
	{
		alert("field cannot be empty");
		return;
	}
	var ctxPath = "<%=request.getContextPath()%>";
	$.ajax(
		{
			type : 'POST',
			contentType : 'application/json',
			url : ctxPath + "/webapi/categories/create",
			data : formToJSON1(),
			success : render(),
			error: function() {
				swal(JSON.stringify(err));
			}
	});
}

function DispSubCat()
{
	var ctxPath = "<%=request.getContextPath()%>";
	if($("#catId").val()==0)
		{
		    swal("Select A Category");
		}
	else
	{
		var categoryId = $("#catId").val();
	$.ajax(
	{
		type : 'GET',
		contentType : 'application/json',
		url : ctxPath + "/webapi/subcategories/category/"+categoryId,
		dataType : "json", // data type of response
		success : function(result){
			var data="";
            for(var i in result){
            	data+="<li class='list-group-item'>"+result[i].subcategoryName+"</li>";
            }
            $('#SubCat').html(data);
    	},
    	error:function(data,status) {
    		swal("Data: " + data + "\nStatus: " + status);
        	//alert("error occurred");
    	}
	});
	}
}
</script>
</html>
