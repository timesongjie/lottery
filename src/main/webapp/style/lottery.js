function getRecords(){
	$.ajax({
		url : "records.json",
		dataType: "json",
		success : function(data) {
			var object =   eval(data);
			alert(object.result);
		},
		error : function() {
			alert("异常！");
		}
	});
}