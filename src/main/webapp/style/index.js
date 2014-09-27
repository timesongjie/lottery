
    // 单行滚动
	function AutoScroll(obj){
		$(obj).find("ul").animate({
		    marginTop:"-28px"
		},500,function(){    
		    $(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
		});
	}

	function center(obj){
		var windowWidth = document.documentElement.clientWidth;  
		var windowHeight = document.documentElement.clientHeight;  
		var popupHeight = $(obj).height();  
		var popupWidth = $(obj).width();   
		$(obj).css({  
	    	"position": "absolute",  
	    	"top": (windowHeight-popupHeight)/2+$(document).scrollTop(),  
	    	"left": (windowWidth-popupWidth)/2  
	 	}); 
	}
	
	function showDiv(obj){
	 	$(obj).show();
	 	center(obj);
	 	var scrolltop = $(document).height(); 
    	$(".black_overlay ").css("height",scrolltop);
	 	$(".black_overlay").show();
	 	$(window).scroll(function(){
	  		center(obj);
	 	});
	 	$(window).resize(function(){
	  	center(obj);
	 	});
	}
	
	var rotateFunc = function(awards,angle,text){  
		$("#lotteryBtn").rotate({
			angle:30, 
			duration: 3000, 
			animateTo: angle+1800, 
			callback:function(){ 
				showDiv(text);
			}
		});	 
	};

$(function(){
	getLotteryTimes();
	getRecords();
	setInterval('AutoScroll(".roll-box")',2000); 
	$("*").bind('touchstart', function(event){});
	
	//初始化变量
 var mov,running,login= false;
  var count=$('#count-time').text();
  var prize,sncode;

    // 页面加载向服务器发送ajax请求,检测是否登录 count（抽奖次数）
	// login=false;
	login=true;
	if (!login) {
		$(".draw").text("登录抽奖");
		$(".count,.examine").hide();
		return;
	}else{
		$(".draw").text("点击抽奖");
		$(".count,.examine").show();
	} ;

	if (count<=0) {
		$(".draw").css("background","#ccc");
		return;
	} else{
		mov=true;
		$(".draw").css("background","#FFF");
	}



	$(".draw").rotate({ 
	   	bind: 
		 	{ 
				click: function(){
				if (running) return;
		        if (count>3) {
		            showDiv($("#three-chance"))
		            $(".draw").css("background","#ccc")
		            return;
		        } else{
		            mov=true;
		        }
        		if(mov){
        			clickLottery();
				}
				setTimeout(function(){
					running=false;
				}, 3000);
			}
		 } 
	});
	
	//查看我的奖品 、兑奖规则、关闭
	$(".examine").bind('click', function(){
		 viewPrizes();
	});
	$(".check-rule,.see-rules").bind('click', function(){
		showDiv($("#ticket-rules"));
	});
	$(".close-bt,.ensure").bind('click', function(){
		$(".popup,.black_overlay").hide();
	});

	
// -----------------------------------------------------------------------
	
	// 星星评级
	$('.star-size').each(function(){   
	    var result=$(this).val();   
	    switch(result){
			case "1":
				$(this).siblings(".star-main").css({'background-position':'0 -13px'});
			  break;
			case "2":
				$(this).siblings(".star-main").css({'background-position':'0 -41px'});
			  break;
			case "3":
				$(this).siblings(".star-main").css({'background-position':'0 -69px'});
			  break;
			case "4":
				$(this).siblings(".star-main").css({'background-position':'0 -97px'});
			  break;
			case "5":
				$(this).siblings(".star-main").css({'background-position':'0 -125px'});
			  break;
		}
	}); 

	//下载样式
	$(".download-bt").click(function  () {
		$(this).css({background:"#009bfe",color:"#fff"});
	});

});

var arr_prize = ['vivo Xshot手机', '京东500元电子购物卡', '京东50元电子购物卡', '消灭星星游戏礼包', '迅雷看看VIP包月'];
/*获取轮转的奖品信息*/
function getRecords(){
	$.ajax({
		url : "records.json",
		cache:false,
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
			document.location.href= 'mvc/index'; 
		}
	});
}
/*获取用户的剩余抽奖次数*/
function getLotteryTimes(){
	$.ajax({
		url : "clickTimes.json",
		dataType: "json",
		cache:false,
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
		cache:false,
		url : "lottery.json",
		dataType: "json",
		success : function(data) {
			var object =   eval(data);
			if(null!=object.lastCount&&object.lastCount!=""){
				var prize = object.lottery_grade;
				if(prize==0){
					rotateFunc(1,180,$("#not-prize"));
				}else if(prize==1){
					$('#win-prize .prize-bg').empty().append('<div class="prize-word a2-word">'+arr_prize[prize]+'</div>');
					rotateFunc(1,0,$("#win-prize"));
				}else if(prize==2){
					$('#win-prize .prize-bg').empty().append('<div class="prize-word a1-word">'+arr_prize[prize]+'</div>');
					rotateFunc(2,123,$("#win-prize"));
				}else if(prize==3){
					$('#win-prize .prize-bg').empty().append('<div class="prize-word a1-word">'+arr_prize[prize]+'</div>');
					rotateFunc(3,243,$("#win-prize"));
				}else if(prize==4){
					$('#win-prize .prize-bg').empty().append('<div class="prize-word a2-word">'+arr_prize[prize]+'</div>');
					rotateFunc(4,300,$("#win-prize"));
				}else if(prize==5){
					$('#win-prize .prize-bg').empty().append('<div class="prize-word a2-word">'+arr_prize[prize]+'</div>');
					rotateFunc(5,63,$("#win-prize"));
				}
				$('#count-time').html(object.lastCount); //更新抽奖次数
			}
		},
		error : function() {
			alert("异常！");
		}
	});
}

/*判断是否登录*/
function isLogin(){
	var isLogin = fasle;
	$.ajax({
		async: false, 
		cache:false,
		url : ".json",
		dataType: "json",
		success : function(data) {
			var object =   eval(data);
			if(object.result==true){
				if(object.result==true){
					isLogin = true;
				}
			}
		},
		error : function() {
			alert("异常！");
		}
	});
	return isLogin;
}

/*查看我的奖品*/
function viewPrizes(){
	$.ajax({
		async: false, 
		cache:false,
		url : "owns.json",
		dataType: "json",
		success : function(data) {
			var object =   eval(data);
			if(object.result==true){
				//先判断是否中奖
				if(null==object.value||object.value==""){
				//没有中奖
					showDiv($("#not-prize"));
				}else{
				//中奖
					$('#prizes-li .line-out').remove();
					$.each(object.value, function(index,item){ 
				        $('<div class="line-out"><div class="date-main">2014-10-30</div><div class="prize-news"><span class="">20元购物券</span>兑换码<span>2188888888</span></div><div class="check-rule">查看兑换方式</div></div>').appendTo('#prizes-li .popup-main');
				    });
					showDiv($("#prizes-li"));
				}
			}
		},
		error : function() {
			alert("异常！");
		}
	});
}
