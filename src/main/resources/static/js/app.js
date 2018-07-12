function deletePerson(index) {
	var a = document.getElementById("deletepersonUrl");
	a.href = "/deleteperson/" + index
}

function deleteHousework(index) {
	var a = document.getElementById("deletehouseworkUrl");
	a.href = "/deletehousework/" + index
}

function submitHouseworkPersonPre(index) {
	document.getElementById("personAddHouseworkId").value = index;
}

function submitHouseworkPerson() {

	var form = $("#idForm");
	form.on("submit", function(e) {
		//var values = $('#multipleSelect').val();
		//console.log(values)
		var url = ""; // the script where you handle the form input.
		$.ajax({
			type : "POST",
			url : "/personAddHousework",
			data : $("#idForm").serialize(), 
			success : function(data) {
				console.log(data)
			}

		});

		e.preventDefault(); // avoid to execute the actual submit of the form.

	})

};

function addPlayingHoursPre(index) {
	$('#addHoursAlert').hide()
	$('#personAddPlayinghoursSuccess').hide()
	$('#addHoursAlert').hide()
	$('#addHoursAlert3').hide()
	document.getElementById("personaddPlayinghours").value = index;
}

function addPlayingHours() {
	var input = document.getElementById("addPlayinghoursInput").value
	if (!isNaN(input) && input !== '' && input > -1) {
		var id = document.getElementById("personaddPlayinghours").value;
		var personPoints;
		$.ajax({
			type : "GET",
			url : "/getperson/" + id,
			data : JSON.stringify(input),
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				console.log("Info peab siin olema!")
				if (data === null) {
					console.log("Error 404!")
					$('#addHoursAlert2').show()
				}

				else {
					console.log(data)
					personPoints = data.points;
					if (input > personPoints) {
						console.log("Error too many points added!")
						$('#addHoursAlert').show()
					} else {
						$.ajax({
							type : "POST",
							url : "/reducepersonhousework/" + id,
							data : JSON.stringify(input),
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								$('#addPlayinghours').modal('hide')
								var cell = document.getElementById("person"
										+ id);
								cell.innerHTML = data.points;
								$('#personAddPlayinghoursSuccess').show();
							}

						});

					}

				}
			},

		});

	// empty input!	
	} else {
		$('#addHoursAlert3').show()

	}
}

function changeHouseworkPre(id) {
	$('#changeHouseworkSuccess').hide();
	$('#changeHouseworkError').hide();
	document.getElementById("houseworkpointsId").value = id;
}

function changeHousework() {
	var input = document.getElementById("houseworkpointsInput").value
	if (!isNaN(input) && input > 0) {
		var id = document.getElementById("houseworkpointsId").value;
		$.ajax({
			type : "POST",
			url : "/changehousework/" + id,
			data : JSON.stringify(input),
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				console.log(data);
				$('#changeHousework').modal('hide')
				var cell = document.getElementById("housework"
						+ id);
				cell.innerHTML = data.points;
				$('#changeHouseworkSuccess').show();
			}

		});
		
	}
	else {
		$('#changeHouseworkError').show();
	}
	
}

function personHistory(index) {
	$.ajax({
		type : "GET",
		url : "/history/" + index,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			var element = document.getElementById("historyBody");
			var innerElement ="";
			var tableBody =$("#historyTable").find('tbody')
			var tableText = $("#historyTableText")
			tableBody.empty();
			tableText.empty();
			if (!$.trim(data)) {
				
				tableText.append("<b>Antud kasutajal pole veel ühtegi tegevust!</b>");
				
			}
			else {
				tableText.append("<b>Kasutaja tegevused: </b>");
				
				$.each(data, function(k,v) {
		
					var dataPiece = data[k];
					
					
					tableBody.append("<tr><td>"+dataPiece.id+"</td><td>"+dataPiece.type+"</td><td>"+dataPiece.name+"</td><td>"+dataPiece.points+"</td><td>"+dataPiece.date+"</td></tr>");
					
					
				});
			}
			

		}

	});
}
