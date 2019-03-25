<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.iiitb.ooadvoid.AccessProperties" %>
<%@ page import="com.iiitb.ooadvoid.CreateProperties" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="metaContent.jsp" %>
	<title>My Wishlist</title>
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

<table >
			<thead>
				<tr>
					<th colspan="4" style="font-size: 22px; font-weight: 500;
    					font-family: Roboto, Arial, sans-serif">My Wishlist</th>
				</tr>
			</thead>
			<tbody>
          <div id="itemsPopulate"></div>
			</tbody>
		</table>
<%@include file="footer.jsp" %>


<script>
$(document).ready(function() {
	$("#unregistered").hide();
  var ctxPath = "<%=request.getContextPath()%>"
	headerFunctions(ctxPath);
  //console.log("ready");
  console.log("calling checkCookie");
	checkCookie1();
	$('#myModal').modal('hide');
});

function checkCookie1()
{
	getWishList(1);

    console.log("checkCookie");
    var user = getCookie("user_details");
    //console.log("user:"+user);
    if (user != "")
    {
		setCookie("user_details", user, 30);
		diplayUser(JSON.parse(user));
    user=JSON.parse(user);
    console.log("calling getWishList");
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
  //console.log("user display:"user.firstName);
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
		dataType : "json", // My Wishlistdata type of response
		data : formToJSON(),
		success : renderDetails
});
}

function getWishList(userId){
	userId = 1;
  console.log("getWishList " + userId);
  $.ajax(
    {
      type:'GET',
      contentType : 'application/json',
      url : "http://localhost:8051/flopkartPrototype/webapi/wishlist/user/"+userId,
      dataType : "json",
      success:function(items){
        getWishListItems(items);
      },
      error:function(){
        console.log(items);
      }
    }
  )
}

function getWishListItems(wishlistItems){
  console.log("length:"+wishlistItems.length);
  var ctxPath = "<%=request.getContextPath()%>";
  var allItems="";
  for(var i in wishlistItems){
    $.ajax(
      {
        type:'GET',
        contentType : 'application/json',
        url : "http://localhost:8051/flopkartPrototype/webapi/listings/"+wishlistItems[i].listingId,
        dataType : "json", // data type of response
        success:function(item){
          console.log(item);
          var img='http://127.0.0.1:8887/'+item.imgUrl;
          var itemUrl = 'http://localhost:8051/flopkartPrototype/item.jsp?id=' + item.id;
          console.log("img: " + img);
            var data=
              "<tr>"+
    				 	"<td class='col-md-2'><img src="+
              img+" alt='imga'>"+
               "</td>"+
    				 	 "<td class='col-md-7'>"+
    						"<div class='product-name'><a href='"+
                 itemUrl+
                 "'>"+
                 item.listingName
                 +"</a></div>"+

    				 		"<div class='price'>"+
    				 		"Price"+
    				 			"<span style='text-decoration: line-through;'>"+
                   item.price
                   +"</span>"+
    				 		"</div>"+
    				 	"</td>"+
    				 	"<td class='col-md-3 close-btn' style='float: right;'>"+
    				 		"<div id='removeItem'><i class='fa fa-trash'></i></div>"+
    				 	"</td>"+
    				 "</tr>";
             console.log(data);
             $("#itemsPopulate").append(data);
        },
        error:function(){
          console.log("Error in retrieving wishlist items");
        }
      }
    );
  }

}

function goToItem(itemId){
  var url="http://localhost:8051/flopkartPrototype/item.jsp?id="+itemId;
  window.location.assign(url);
}
</script>
</body></html>
