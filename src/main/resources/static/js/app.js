function deletePerson(index) {
	var a = document.getElementById("deletepersonUrl");
	a.href = "/deleteperson/"+index
}

function deleteHousework(index) {
	var a = document.getElementById("deletehouseworkUrl");
	a.href = "/deletehousework/"+index
}

function submitHouseworkPersonPre(index) {
	console.log("Siin id")
	console.log(index)
	document.getElementById("personAddHouseworkId").value = index;
}
//this is the id of the form
function submitHouseworkPerson() {

	var form = $("#idForm");
	form.on("submit", function (e) {
		var url = ""; // the script where you handle the form input.
	    console.log("Ma vähemalt sain aru, et siin")
	    $.ajax({
	           type: "POST",
	           url: $form.attr('action'),
	           data: $("#idForm").serialize(), // serializes the form's elements.
	           success: function(data)
	           {
	               alert(data); 
	           }
	    	
	         });

	    e.preventDefault(); // avoid to execute the actual submit of the form.
		
	})
    
};


function addPlayingHoursPre(index) {
	
	console.log("INFO")
	console.log(index)
	document.getElementById("personaddPlayinghours").value = index;
	//submitButtonUrl.href = "/reduce/"+index
	//addPlayingHours(index)
	/*
	submitButton.click(function(e) {
		console.log("Ma vähemalt sain aru, et siin")
	}) 
	*/
}

function addPlayingHours() {
	console.log("Jah õige")
	//document.getElementById("submitPlayingHours")
	var submitButton = document.getElementById("submitPlayingHours");
	
	console.log("NOH KUS??")
	var input = document.forms["playinghours"]["addPlayinghours"].value;
	if (!isNaN(input) && input !== '') {
		console.log("TORE OLEMAS")
		var id = document.getElementById("personaddPlayinghours").value;
		$.ajax({
	           type: "POST",
	           url: "/reducepersonhousework/"+id,
	           data: JSON.stringify(input),
		 		contentType: "application/json; charset=utf-8",
		 		dataType: "json"
	          
	    	
	         });
		$('#addPlayinghours').modal('hide')
		
	}
	else {
		// RETURN ERROR MESSAGE!
		console.log("POLE")
		return false;
	}
		
	
	//console.log(submitButtonUrl)
}

