<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<title>Order Placed</title>
	<%@ page import="zkart.jsp.AccessProperties" %>
	<%@ page import="zkart.jsp.CreateProperties" %>
	<%@include file="metaContent.jsp" %>
	<%@include file="header.jsp" %>
</head>

<body>
	<!-- fields to store variables for counting and other information -->
	<input type="text" id="count" name="count" hidden="hidden"/>
	<input type="text" id="itemid" name="itemid" hidden="hidden"/>
	<input type="text" id="img1" name="img1" hidden="hidden"/>

	<!-- order display cards -->
	<div class=col-sm-12 id="orderDisplay" style="margin: 0px 0px 0px 0px; padding:50px;background-color:white">
		<div class=row style="text-align:center">
			<div class="col-sm-2"></div>
			<div class="col-sm-8" id="orders"></div>
			<div class="col-sm-2"></div>
		</div>
	</div>

<%@include file="footer.jsp" %>

</body>


<link href="rateit/rateit.css" rel="stylesheet" type="text/css">
<script src="rateit/jquery.rateit.min.js" type="text/javascript"></script>
	<script>

		$(document).ready(function()
		{
			var ctxPath = "<%=request.getContextPath()%>";
			$('#count').val(0);
			headerFunctions(ctxPath);
			myorder(ctxPath);
		});

		function myorder(ctxPath)
		{
			var user = getCookie("user_details");
			var userid = JSON.parse(user).id;
			var j =0;
			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				url : ctxPath + "/webapi/orders/user/" + userid,
				dataType : "json", // data type of response
				success : function(orders_json) {
					for(var i in orders_json) {
						displayListings(orders_json[i], userid);
					}
				},
				error:function() {
					swal("error occurred");
				}
			});
		}

		function displayListings(result, userid) {
			var ctxPath = "<%=request.getContextPath()%>";
			<% AccessProperties ap = new AccessProperties(); %>
			var imgServerURL = "<%=ap.getImageServerURL() %>";
			var data = "";
			var j = $("#count").val();
			j++;
			$('#count').val(j);
			var xxx = document.getElementById("img1").value;
			var itid = result.itemId;

					data += "" +
					"<div id='divi" + j + "' class = 'row' style = 'margin:5px 25px;border-style: groove;border-width: 7px;font-size:15px; text-align:left; padding-left:70px;' >" +
					"<div class='col-sm-4' style = 'font-size:15px; text-align:left; padding-top: 40px ; padding-left:0px;'>" +
					"<div style='height: 250px;'>" +
					"<img class='listingImage' id =" +  result.itemId + "" + j  + " style='max-height:100%; max-width:100%;' src='" + xxx + "' alt=''>" +
					"</div>" +
					"</div>" +

					"<div class='col-sm-8' style ='font-size:15px; text-align:left; padding:20px;' >" +
					// Ordered Item number
					"<h2 style='padding-bottom:20px;'>Ordered Item No: " + j + "</h2>" +

					"<div style = 'padding-bottom:10px;font-size:15px;'>Order ID :  <b>" + result.orderId + "</b></div>" +

					"<div style = 'padding-bottom:10px;font-size:15px;'>Item  ID :  <b><span id ='itid" + j + "'>" + result.itemId + "</span></b></div>" +
					// Item name
					"<div id ='name" + j + "'style = 'padding-bottom:10px;font-size:15px;'> </div>" +

					"<div id ='seller_name" + j + "'style = 'padding-bottom:10px;font-size:15px;'> </div>" +

					"<div style = 'padding-bottom:10px;font-size:15px;'>Shipping Address : <b> " + result.shippingAddress + "</b></div>" +

					"<div style = 'padding-bottom:10px;font-size:15px;'>Total Amount : <b> " + result.totalAmount + "</b></div>" +

					"<div  style = 'padding-bottom:10px;font-size:15px;'>Quantity : <b> " + result.quantity + "</b></div>" +
					// Item status
					"<div  id='status" + j + "' style = 'padding-bottom:10px;font-size:18px;color:green;'>Item Status :  <b>" + result.status + "</b></div>";






					// ORDER_FAILED 0 // ORDER_SHIPPED 2 // ORDER_RECIEVED 1 // ORDER_CANCELLED 0 // ORDER_RETURNED 0 // ORDER_COMPLETED 0 // PAYMENT_RECIEVED 1
					var orderCancelled = "\"ORDER_CANCELLED\"";
					var orderReceived = "\"ORDER_RECIEVED\"";
					var orderReturned = "\"ORDER_RETURNED\"";
					var res = JSON.stringify(result);


					if (result.status == "PAYMENT_RECIEVED") {
		    			data += "<div style = 'padding-bottom:10px;font-size:15px;'>" +
		    				"<button id='cancel" + j + "'  name='cancel' onclick='updateStatus(this, " + res + ", " + j + ", " + orderCancelled + ");'>Cancel Order</button>" +
		    			"</div>";
					} else if (result.status == "ORDER_SHIPPED") {
						data += "<div style = 'padding-bottom:10px;font-size:15px;'>" +
							"<button id='orderReceived" + j + "'  name='orderReceived' onclick='updateStatus(this, " + res + ", " + j + ", " + orderReceived + ");'>Order Recieved</button>" +
						"</div>";
		    			data += "<div style = 'padding-bottom:10px;font-size:15px;'>" +
		    				"<button id='cancel" + j + "'  name='cancel' onclick='updateStatus(this, " + res + ", " + j + ", " + orderCancelled + ");'>Cancel Order</button>" +
		    			"</div>";

					} else if (result.status == "ORDER_RECIEVED")
					{
						var curDate=new Date();
						var returnDate=new Date(result.returnDate);
						if(returnDate >= curDate)
						{
						data += "<div style = 'padding-bottom:10px;font-size:15px;'>" +
	    					"<button id='return" + j + "'  name='return' onclick='updateStatus(this, " + res + ", " + j + ", " + orderReturned + ");'>Return</button>" +
	    				"</div>";
	    				}
						else
						{
							data+="<div>Your return period is over.</div>";
						}
					}
					else if (result.status == "ORDER_RETURNED")
					{

						{
							data+="<div>Your Money Will Be Reverted Back To Your Account.</div>";
						}
					}
					data+="<span id='hidden_message'></span>";
					data += "<div  style ='font-size:20px; text-align:left; padding:0px;' id=rating_" +  j  + " >"  +
							"<div>Leave A Rating:</div>" +
							"<span style='cursor:pointer' class='fa fa-star' onclick=highlight(rating_" + j + ",1,'" + result.itemId + "'," + userid + ")></span>" +
							"<span style='cursor:pointer' class='fa fa-star' onclick=highlight(rating_" + j + ",2,'" + result.itemId + "'," + userid + ")></span>" +
							"<span style='cursor:pointer' class='fa fa-star' onclick=highlight(rating_" + j + ",3,'" + result.itemId + "'," + userid + ")></span>" +
							"<span style='cursor:pointer' class='fa fa-star' onclick=highlight(rating_" + j + ",4,'" + result.itemId + "'," + userid + ")></span>" +
							"<span style='cursor:pointer' class='fa fa-star' onclick=highlight(rating_" + j + ",5,'" + result.itemId + "'," + userid + ")></span>" +
							"<div style='color:red;font-size:15px' hidden> Rating updated </div>" +
							"</div>" +
							"</div>" +
							"</div>";


			$('#orders').append(data);
			var element = $("#rating_"+j);
			listing(ctxPath, result.itemId, userid, element);
			document.getElementById("rating_"+ j ).style.visibility = "hidden";
			// if(result.status != "Goods Shipped"){
			// 	document.getElementById("button"+j).style.visibility = "hidden";
			// }

			if(result.status == "ORDER_RECIEVED" || result.status == "ORDER_COMPLETED"){
				document.getElementById("rating_"+ j ).style.visibility = "visible";
			}
			// if(result.status == "Failed"){
			// 	document.getElementById("divi"+j).remove();
			// }
			// if(result.status == "Ordered"){
			// 	document.getElementById("divi"+j).remove();
			// }

			var itemid = result.itemId;

			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				url :  ctxPath +"/webapi/listings/item/" + itemid,
				success : function(result1) {
					var imgs = imgServerURL + result1.imgUrl;
					$('#img1').val(imgs);
					document.getElementById(itemid + "" + j).src = imgs;
					document.getElementById("name" + j).innerHTML= "Item Name :  <b>"+result1.listingName+"</b>";
					var sellerid = result1.sellerid;
					$.ajax({
						type : 'GET',
						contentType : 'application/json',
						url :  ctxPath +"/webapi/users/"+sellerid,
						success : function(result2){
							document.getElementById("seller_name"+j).innerHTML= "Seller Name :  <b>"+result2.firstName+" "+result2.lastName+"</b>";
						},
						error:function(){
							swal("error occurred");
						}
					});
				},
				error:function(){
					swal("error occurred");
				}
			});
		}


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
				url : ctxPath + "/webapi/orders/update/orderid",
				data : result,
				statusCode : {
					200 : function() {
						swal("Status Updated to " + newStatus);
						if (newStatus == "ORDER_RECIEVED")
						{
							document.getElementById("rating_"+ j ).style.visibility = "visible";
						}/*
						else if (newStatus == "ORDER_RETURNED")
						{
							document.getElementById("hidden_message").value="Your money will be reverted back to your account.";
						}*/
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
			window.location = "/flopkartPrototype/myOrder.jsp";
		}


		function reduceFromFlopkart(amt){
			var ctxPath = "<%=request.getContextPath()%>";
			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				url : ctxPath + "/webapi/accounts/accountno/1",
				success : function(res) {
					var amount =  res.balance - amt;
					$.ajax({
						type : 'PUT',
						contentType : 'application/json',
						url : ctxPath + "/webapi/accounts/update/1",
						data : JSON.stringify({
							"balance": amount
						}),
						success : function(data){
						},
						error: function(err) {
							swal(JSON.stringify(err));
						}
					});
				},
				error: function(err) {
					swal(JSON.stringify(err));
				}
			});
		}

		function addToSeller(amt, itemidd) {
		var ctxPath = "<%=request.getContextPath()%>";
		$.ajax( {
			type : 'GET',
			contentType : 'application/json',
			url :  ctxPath +"/webapi/listings/item/"+itemidd,
			success : function(result){
				var sellerid =result.sellerid;
				$.ajax({
					type : 'GET',
					contentType : 'application/json',
					url : ctxPath + "/webapi/accounts/user/"+sellerid,
					success : function(res){
					var amount = amt + res.balance ;
					var id = res.id
					$.ajax({
						type : 'PUT',
						contentType : 'application/json',
						url : ctxPath + "/webapi/accounts/update/"+id,
						data : JSON.stringify({
							"balance": amount
						}),
						success : function(data){
						},
						error: function(err) {
						swal(JSON.stringify(err));
						}
					});
					},
					error: function(err) {
						swal(JSON.stringify(err));
					}
				});
				},
				error:function(){
					swal("error occurred");
				}
			});
		}

		function highlight(elementid, itemId, userId) {
			var ctxPath = "<%=request.getContextPath()%>";
			var val = $('#' + elementid).rateit('value');
			$('#' + elementid).rateit('value', val);
			updateRatingListing(ctxPath, val, userId, itemId);
		}

		function highlight(el, val, itemId, userId) {
			var ctxPath = "<%=request.getContextPath()%>";
			var parent = $(el).attr("id");
			var stars = $("#"+parent).find("span");

			var color;
			if(val <= 2)
				color = "red";
			else if(val <= 4)
				color = "orange";
			else
				color = "green";

			var i;
			for(i=0; i<val; i++) {
				$(stars[i]).css('color',color);
			}

			for(; i<5; i++) {
				$(stars[i]).css('color','black');
			}

			var divisions = $("#"+parent).find("div");
			$(divisions[1]).hide();
			$(divisions[1]).show(100);
			updateRatingListing(ctxPath,val,userId,itemId);
		}

		function updateRatingListing(ctxPath, val, userId, itemId) {
			var sellerid;
			var listingid;
			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				url : ctxPath + "/webapi/listings/item/"+itemId,
				dataType : "json", // data type of response
				success : function(result){
					listingid=result.id;
					sellerid=result.sellerid;
					updateRatingCheck(ctxPath,val,userId,sellerid,listingid);
				},
				error:function() {
					swal("error occurred");
				}
			});
		}

		//
		function updateRatingCheck(ctxPath, val, userId, sellerId, listingId) {
			var parameter;
			$.ajax(
			{
			type : 'GET',
			contentType : 'application/json',
			url : ctxPath + "/webapi/reviews/user/" + userId,
			dataType : "json", // data type of response
			success : function(results){
				var check = 0;
				for(var i in results) {
					if(results[i].listingId == listingId) {
						check++;
						parameter = results[i].id;
					}
				}

				if(check > 0)
					updateRatingUpdate(ctxPath, val, userId, sellerId, listingId, parameter);
				if(check == 0)
					updateRatingAdd(ctxPath, val, userId, sellerId, listingId);

			},
			error:function() {
				swal("error occurred");
			}
			});
		}

		// update rating put
		function updateRatingUpdate(ctxPath,val,userId,sellerId,listingId,parameter) {
			//swal(""+ctxPath+" "+ val + " " + userId + " " + sellerId + " " + listingId + " " + parameter);
			var flopkartReview = JSON.stringify({
				"listingId" : listingId,
				"sellerId" : sellerId,
				"stars" : val,
				"userId" : userId
			});

			$.ajax({
				type : 'PUT',
				contentType : 'application/json',
				url : ctxPath + "/webapi/reviews/update/" + parameter,
				data : flopkartReview,
				success : function(){
				},
				error: function() {
					swal(JSON.stringify(err));
				}
			});
		}

		// add new rating post
		function updateRatingAdd(ctxPath,val,userId,sellerId,listingId) {
			//swal(""+ctxPath+" "+ val + " " + userId + " " + sellerId + " " + listingId);
			var flopkartReview = JSON.stringify({
				"listingId" : listingId,
				"sellerId" : sellerId,
				"stars" : val,
				"userId" : userId
			});
			$.ajax({
				type : 'POST',
				contentType : 'application/json',
				url : ctxPath + "/webapi/reviews/create",
				data : flopkartReview,
				success : function(){
				},
				error: function() {
					swal(JSON.stringify(err));
				}
			});
		}

		// get item by itemid and then call check()
		function listing(ctxPath, itemId, userId, element) {
			var listingid;
			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				url : ctxPath + "/webapi/listings/item/" + itemId,
				dataType : "json", // data type of response
				success : function(result){
					listingid = result.id;
					check(ctxPath, listingid, userId, element);
				},
				error:function() {
					swal("error occurred");
				}
			});
		}

		// get reviews by userid and then call renderstars()
		function check(ctxPath, listingid, userId, element) {
			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				url : ctxPath + "/webapi/reviews/user/" + userId,
				dataType : "json", // data type of response
				success : function(results){
					for(var i in results) {
						if(results[i].listingId == listingid)
							renderstars(element, results[i].stars);
					}
				},
				error:function() {
					swal("error occurred");
				}
			});
		}

		// renderstars
		function renderstars(element, val) {
			var color;
			if(val <= 2)
				color = "red";
			else if(val<=4)
				color = "orange";
			else
				color = "green";

			var parent = $(element).attr("id");
			var stars = $("#" + parent).find("span");
			var i;
			for(i = 0; i<val; i++) {
				$(stars[i]).css('color', color);
			}

			for(; i<5; i++) {
				$(stars[i]).css('color', 'black');
			}
			var divisions = $("#"+parent).find("div");
		}

	</script>

</html>
