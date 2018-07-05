function deletePerson(index) {
	var a = document.getElementById("deletepersonUrl");
	a.href = "/deleteperson/"+index
}

function deleteHousework(index) {
	var a = document.getElementById("deletehouseworkUrl");
	a.href = "/deletehousework/"+index
}
