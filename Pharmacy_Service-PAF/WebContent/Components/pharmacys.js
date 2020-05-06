$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event)
		{
		// Clear alerts---------------------
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();
		// Form validation-------------------
		var status = validateItemForm();
		if (status != true)
		 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
		 }
		// If valid------------------------
		var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
		
		$.ajax(
				{
				 url : "PharmacyAPI",
				 type : type,
				 data : $("#formPharmacy").serialize(),
				 dataType : "text",
				 complete : function(response, status)
				 {
				 onHospitalSaveComplete(response.responseText, status);
				 }
				});
}); 

function onHospitalSaveComplete(response, status)
{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
	 $("#alertSuccess").text("Successfully saved.");
	 $("#alertSuccess").show();
	$("#divItemsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
	 $("#alertError").text(resultSet.data);
	 $("#alertError").show();
	}
	else if (status == "error")
	{
	 $("#alertError").text("Error while saving.");
	 $("#alertError").show();
	} else
	{
	 $("#alertError").text("Unknown error while saving..");
	 $("#alertError").show();
	}
	
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();

}

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "PharmacyAPI",
		 type : "DELETE",
		 data : "ID=" + $(this).data("id"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onItemDeleteComplete(response.responseText, status);
		 }
		 });
});

function onItemDeleteComplete(response, status)
{
	if (status == "success")
	 {
		 var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
		 {
		 $("#alertSuccess").text("Successfully deleted.");
		 $("#alertSuccess").show();
		 $("#divItemsGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error"){
		 $("#alertError").text(resultSet.data);
		 $("#alertError").show();
		 }
	 } 
	else if (status == "error"){
		 $("#alertError").text("Error while deleting.");
		 $("#alertError").show();
	 } 
	else{
		 $("#alertError").text("Unknown error while deleting..");
		 $("#alertError").show();
	 }
}

function validateItemForm()
{
if ($("#name").val().trim() == "")
 {
 return "Insert the pharmacy name.";
 }
if ($("#owner").val().trim() == "")
 {
 return "Insert the owner's name.";
 }
if ($("#address").val().trim() == "")
 {
 return "Insert the address.";
 }
if ($("#email").val().trim() == "")
{
return "Insert the email.";
}
var tmpPhne = $("#phone").val().trim();
if (!$.isNumeric(tmpPhne))
 {
 return "Insert a numerical value for Phone Number.";
 }

return true;
}

$(document).on("click", ".btnUpdate", function(event)
{
			 $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDSave').val());
			 $("#name").val($(this).closest("tr").find('td:eq(1)').text());
			 $("#owner").val($(this).closest("tr").find('td:eq(2)').text());
			 $("#address").val($(this).closest("tr").find('td:eq(3)').text());
			 $("#email").val($(this).closest("tr").find('td:eq(4)').text());
			 $("#phone").val($(this).closest("tr").find('td:eq(5)').text());
});


