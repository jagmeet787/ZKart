<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.iiitb.ooadvoid.AccessProperties"%>
<!DOCTYPE html>
<html>
<!-- Use this page as a template to create new pages-->
<head>
	<%@include file="metaContent.jsp" %>
	<title>Welcome to Flopkart</title>
</head>
<body>
     
    <%@include file="header.jsp" %>
     
  	<div>Your Content</div>
  	
     
    <%@include file="footer.jsp" %>
     
</body>

<script>
$(document).ready(function(){
	swal("Here's the title!", "...and here's the text!");
    var ctxPath = "<%=request.getContextPath()%>";
	headerFunctions(ctxPath);
})
	
</script>
</html>