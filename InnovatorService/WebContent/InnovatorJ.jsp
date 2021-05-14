<%@page import="com.Innovator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Innovator Management</title>

<!-- link to page -->>

<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/innovator.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>INNOVATOR DETAILS</h1>
				<form id="formInnovator" name="formInnovator" method="post" action="InnovatorJ.jsp">  
					Innovator Name:  
 	 				<input id="innovatorName" name="innovatorName" type="text"  class="form-control form-control-sm">
					<br> Project Name:   
  					<input id="projName" name="projName" type="text" class="form-control form-control-sm">   
  					<br> Price:   
  					<input id="price" name="price" type="text"  class="form-control form-control-sm">
					<br>  
					<br> Project Discription:   
  					<input id="project" name="project" type="text"  class="form-control form-control-sm">
					<br> 
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidInnovatorIDSave" name="hidInnovatorIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divInnovatorGrid">
					<%
						Innovator innoObj = new Innovator();
						out.print(innoObj.readInnovator());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>