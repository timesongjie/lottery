var arr_prize = ['vivo Xshot手机', '京东500元电子购物卡', '京东50元电子购物卡', '消灭星星游戏礼包', '迅雷看看VIP包月'];
/*获取轮转的奖品信息*/
function getRecords(){
	$.ajax({
		url : "records.json",
		dataType: "json",
		success : function(data) {
			var object =   eval(data);
			if(object.result==true){
				$('.roll-box ul').empty();
				$.each(object.value, function(index,item){ 
			        $('.roll-box ul').append("<li><span id=\"user-name\">"+item.userId+"</span>获得<span class=\"prize\">"+arr_prize[item.lottery_grade-1]+"</span></li>");
			    });
			}
		},
		error : function() {
			alert("异常！");
		}
	});
}
/*获取用户的剩余抽奖次数*/
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

/*点击抽奖*/
function clickLottery(){
	$.ajax({
		async: false, 
		url : "lottery.json",
		dataType: "json",
		success : function(data) {
			var object =   eval(data);
			if(object.result==true){
				
				
				//$('#count-time').html(object.value); //更新抽奖次数
			}
		},
		error : function() {
			alert("异常！");
		}
	});
}

/*查看我的奖品*/
function viewPrizes(){
	$.ajax({
		async: false, 
		url : "owns.json",
		dataType: "json",
		success : function(data) {
			var object =   eval(data);
			if(object.result==true){
				//先判断是否中奖
				
				//没有中奖
				$("#not-prize").show();
				//中奖
				$('#prizes-li').empty();
				$.each(object.value, function(index,item){ 
			        $('#prizes-li').append('<div class="line-out"><div class="date-main">2014-10-30</div><div class="prize-news"><span class="">20元购物券</span>兑换码<span>2188888888</span></div><div class="check-rule">查看兑换方式</div></div>');
			    });
				$("#prizes-li").show();
				
			}
		},
		error : function() {
			alert("异常！");
		}
	});
}