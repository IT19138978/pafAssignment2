$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateInnovatorForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidInnovatorIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "InnovatorService",  
			type : type,  
			data : $("#formInnovator").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onInnovatorSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onInnovatorSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divInnovatorGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidInnovatorIDSave").val("");  
	$("#formInnovator")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidInnovatorIDSave").val($(this).closest("tr").find('#hidInnovatorIDUpdate').val());     
	$("#innovatorName").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#projName").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#price").val($(this).closest("tr").find('td:eq(2)').text());  
	$("#project").val($(this).closest("tr").find('td:eq(3)').text());      
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "InnovatorService",   
		type : "DELETE",   
		data : "innovatorID=" + $(this).data("iid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onInnovatorDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onInnovatorDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divInnovatorGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateInnovatorForm() 
{  
	// NAME  
	if ($("#innovatorName").val().trim() == "")  
	{   
		return "Insert Innovator Name.";  
	} 

	// DESCRIPTION------------------------  
	if ($("#projName").val().trim() == "")  
	{   
		return "Insert Project Name.";  
	} 
	
	
	//PRICE-------------------------------
	 var tmpAmount = $("#price").val().trim();
	if (!$.isNumeric(tmpAmount)) 
	 {
	 return "Insert Price.";
	 }
	
	//DISCRIPTION-------------------------------
	if ($("#projName").val().trim() == "")  
	{   
		return "Insert Discription.";  
	} 

	return true; 
}