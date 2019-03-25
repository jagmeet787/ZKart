<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<!-- Use this page as a template to create new pages-->
<head>
	<%@include file="metaContent.jsp" %>
	<title>My Flopkart Wallet</title>
</head>
<body>
     
    <%@include file="header.jsp" %>
     
  	<div class="body-content outer-top-xs">
  <div class="container">
		
        <div class="search-result-container ">
            <div class="sidebar-widget wow fadeInUp" style="visibility: hidden; animation-name: none;">
                <div class="widget-header">
                		<h4 class="widget-title"><B>MY WALLET</B></h4>
                </div>
	              
			    <div class="row">
			      <div class="col-md-5 sidebar"> 
			        <div class="sidebar-module-container">
			          <div class="sidebar-filter"> <div class="side-menu animate-dropdown outer-bottom-xs">
			          			<div class="head">
			          			<a href="#" style="color:black" onmouseleave="style='color:black'" onmouseenter="style='color:blue'"> 
			          			Balance&nbsp;&nbsp;&nbsp;:<span class="pull-right"><i class='fa fa-rupee-sign'></i>
			          			<span id="wallet"> </span>
			          			</span>
			          			</a></div>
			          	  </div>
			          </div>
			          <!-- /.sidebar-filter --> 
			        </div>
			        <!-- /.sidebar-module-container --> 
			      </div>
			      <div class="col-md-2 sidebar"> </div>
					<div class="col-md-3 sidebar"> 
					<input type="button" value="Add Money" onclick="addMoney()" class="btn btn-primary">
					</div>
		        </div>
        
              <!-- /.sidebar-widget-body --> 
      			<div class="row" id="bankAccount" hidden="hidden">
		        <div class="col-md-12">        
		        <div class="widget-header">
                		<h4 class="widget-title"><B>FLOPKART ACCOUNT</B></h4>
                </div>
	              
      			<div class="row">
			      <div class="col-md-4 sidebar"> 
			      		<label>Account Number</label>
		              <input type="number" class="form-control input-lg"  name="accountno" id="accountno" required>
		          </div>
			      <div class="col-md-4 sidebar"> 
			      		<label>Pin</label>
		              <input type="password" maxlength="4" class="form-control input-lg"  name="pin"  id="pin" required>
		          	</div>
			      <div class="col-md-4 sidebar"> 
			      		<label>Amount</label>
		              <input type="number" class="form-control input-lg"  name="amount"  id="amount" required>
		          	</div>
		         </div>
      			<div class="row">
		               <div style="margin-top:5px;margin-left:15px;color:red" hidden="hidden"
		                              id="accWarning">Please enter account no. and pin</div>	
		         </div><br/>
		         <div class="col-md-3 sidebar"> </div>
			      <div class="col-md-6 sidebar"> 
  				<input type="submit" class="btn btn-primary" value="Proceed to pay" id="proceed"
  									onclick="proceedToPay()">
		          </div>
		         </div>
		         </div>
            </div>
        </div>   
  </div>
 </div>
  	
     
    <%@include file="footer.jsp" %>
     
</body>

<script>
$(document).ready(function()
{
	
    var ctxPath = "<%=request.getContextPath()%>";
	headerFunctions(ctxPath);
    var user = getCookie("user_details");
    if (user != "") 
    {
		setCookie("user_details", user, 30);
		var user_json = JSON.parse(user);
		$('#wallet').html(user_json.wallet);
    } 
})

function addMoney()
{
	$("#bankAccount").show();
}

function proceedToPay()
{

	var accountno = $("#accountno").val();
	var pin = $("#pin").val();
	if(accountno=="" || pin=="" || isNaN(pin))
	{
		$("#accWarning").show();
	}
	else
	{
		var ctxPath = "<%=request.getContextPath()%>";
		$.ajax(
			{
				type : 'POST',
				contentType : 'application/json',
				url : ctxPath + "/webapi/accounts/accountno",
				data : JSON.stringify({
					"accountno": accountno,
				    "pin":pin 
				}),
				success : function(data)
				{
					if(!data)
					{
						$("#accWarning").text("No such account");
						$("#accWarning").show();
					}
					else if(!data.id)
					{
						$("#accWarning").text("Wrong pin");
						$("#accWarning").show();
					}
					else
					{
						var TotalAmount =$("#amount").val();
						if(parseInt(data.balance)>=parseInt(TotalAmount))
						{
								$("#accWarning").hide();
								deductBalance(data.balance-TotalAmount,data.id);
								addBalanceFlopkart(TotalAmount);
						}
						else
						{
								$("#accWarning").text("Balance Insufficient");
								$("#accWarning").show();
// 								$("#paymentStatus").show();
						}
					}
				},
				error: function(err) 
				{
					swal(JSON.stringify(err));
				}
		});
	}
}



function deductBalance(amt,id)
{
	var ctxPath = "<%=request.getContextPath()%>";
	$.ajax(
		{
			type : 'PUT',
			contentType : 'application/json',
			url : ctxPath + "/webapi/accounts/update/"+id,
			data : JSON.stringify({
				"balance": amt
			}),
			success : function(data)
			{
				swal("Amount deducted from bank account");
			},
			error: function(err) 
			{
				swal(JSON.stringify(err));
			}
	});
}

function addBalanceFlopkart(amt)
{
	var ctxPath = "<%=request.getContextPath()%>";

    var user = getCookie("user_details");
    if (user != "") 
    {
		
		var user_json = JSON.parse(user);
		var amount = parseInt(amt) + parseInt(user_json.wallet);
		$.ajax(
			{
				type : 'PUT',
				contentType : 'application/json',
				url : ctxPath + "/webapi/users/update/"+user_json.id,
				data : JSON.stringify({
					"wallet": amount
				}),
				success : function(data)
				{
					user_json.wallet = amount;
					user = JSON.stringify(user_json);
					$('#wallet').html(user_json.wallet);
					setCookie("user_details", user, 30);
				},
				error: function(err) 
				{
					swal(JSON.stringify(err));
				}
		});
    }
}


</script>
</html>