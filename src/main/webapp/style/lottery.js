$(function() {
	getLotteryTimes();
	getRecords();
});

function getRecords(){
	$.ajax({
		url : "records.json",
		dataType: "json",
		success : function(data) {
			var object =   eval(data);
			if(object.result==true){
				$('.roll-box ul').empty();
				$.each(object.value, function(index,item){ 
			        $('.roll-box ul').append("<li><span id=\"user-name\">"+item.userId+"</span>获得<span class=\"prize\">"+item.lottery_grade+"</span></li>");
			    });
			}
		},
		error : function() {
			alert("异常！");
		}
	});
}

function getLotteryTimes(){
	$.ajax({
		url : "clickTimes.json",
		dataType: "json",
		success : function(data) {
			var object =   eval(data);
			if(object.result==true){
				$('#count-time').html(object.value);
			}
		},
		error : function() {
			alert("异常！");
		}
	});
}