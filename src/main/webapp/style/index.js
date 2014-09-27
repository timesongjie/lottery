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
	
	function showTicketRules(check_grade){
		if(check_grade==1){
			$('#ticket-rules .detail-main').empty().append(arr_info[0]);
		}else if(check_grade==2||check_grade==3){
			$('#ticket-rules .detail-main').empty().append(arr_info[1]);
		}else if(check_grade==4){
			$('#ticket-rules .detail-main').empty().append(arr_info[2]);
		}else if(check_grade==5||check_grade==6||check_grade==7||check_grade==8||check_grade==9||check_grade==10){
			$('#ticket-rules .detail-main').empty().append(arr_info[3]);
		}
	}

	function initHomePage(){
		$(".draw").bind('click', function(){
			location.href = "./index";
		});
		getRecords();
		setInterval('getRecords()',300000);  //每隔5分钟获取中奖信息
		setInterval('AutoScroll(".roll-box")',2000);  
	}
	
function initIndexPage(){
	//查看我的奖品 、兑奖规则、关闭
	$(".examine").bind('click', function(){
		 viewPrizes();
	});
	$(".see-rules").bind('click', function(){
		var check_grade=$("#check_grade").val();
		showTicketRules(check_grade);
		showDiv($("#ticket-rules"));
		$('#win-prize').hide();
	});
	
	$(".close-bt,.ensure").bind('click', function(){
		$(".popup,.black_overlay").hide();
	});
	
	//下载样式
	$(".download-bt").click(function  () {
		$(this).css({background:"#009bfe",color:"#fff"});
	});

	getRecords();
	setInterval('getRecords()',300000);  //每隔5分钟获取中奖信息
	
	setInterval('AutoScroll(".roll-box")',2000);  //滚播中奖信息
	
	var times = getLotteryTimes(); //获取剩余抽奖次数
	
	var running= false;
	$(".draw").rotate({ 
	   	bind: 
		 	{ 
				click: function(){
				if(running==false){ //只有转盘停止转动点击按钮才有效
					if(times>0){
						running=true;
						clickLottery();	
						times=$('#count-time').text();
						if(times<=0){
							 $(".draw").css("background","#ccc");
					         return;
						}
					}else if(times<=0){
						 showDiv($("#three-chance"));
						 $(".draw").css("background","#ccc");
				         return;
					}
				}
				setTimeout(function(){
					running=false;
				}, 3000);
			}
		 } 
	});
}

/*获取轮转的奖品信息*/
function getRecords(){
	$.ajax({
		url : "records.json",
		cache:false,
		success : function(data) {
			var object =   eval(data);
			if(object.result==true){
				$('.roll-box ul').empty();
				$.each(object.value, function(index,item){ 
			        $('.roll-box ul').append("<li><span id=\"user-name\">"+item.userId+"</span>获得<span class=\"prize\">"+item.award_name+"</span></li>");
			    });
			}
		},
		error : function() {
			
		}
	});
}
/*获取用户的剩余抽奖次数*/
function getLotteryTimes(){
	var times = 0;
	$.ajax({
		url : "clickTimes.json",
		cache:false,
		async: false, 
		success : function(data) {
			var object =   eval(data);
			if(object.result==true){
				times = object.value;
				$('#count-time').html(times);
				if (times<=0) {
					$(".draw").css("background","#ccc");
				}else{
					$(".draw").css("background","#FFF");
				}
			}
		},
		error : function() {
			location.href = "./index";
		}
	});
	return times;
}

/*点击抽奖*/
function clickLottery(){
	$.ajax({
		async: false, 
		cache:false,
		url : "lottery.json",
		success : function(data) {
			var object =   eval(data);
			var lastCount = object.lastCount;
			var prize = object.lottery_grade;
			var award_name = object.award_name;
			if(lastCount>=0&&prize!=null){
				var prize = object.lottery_grade;
				$('#check_grade').val(prize);
				if(prize==0){
					rotateFunc(0,180,$("#not-prize"));
				}else if(prize==1){
					$('#win-prize .prize-bg').empty().append('<div class="prize-word a2-word">'+award_name+'</div>');
					rotateFunc(1,0,$("#win-prize"));
				}else if(prize==2){
					$('#win-prize .prize-bg').empty().append('<div class="prize-word a1-word">'+award_name+'</div>');
					rotateFunc(2,123,$("#win-prize"));
				}else if(prize==3){
					$('#win-prize .prize-bg').empty().append('<div class="prize-word a1-word">'+award_name+'</div>');
					rotateFunc(3,243,$("#win-prize"));
				}else if(prize==4){
					$('#win-prize .prize-bg').empty().append('<div class="prize-word a2-word">'+award_name+'</div>');
					rotateFunc(4,300,$("#win-prize"));
				}else if(prize==5){
					$('#win-prize .prize-bg').empty().append('<div class="prize-word a2-word">'+award_name+'</div>');
					rotateFunc(5,63,$("#win-prize"));
				}
				$('#count-time').html(object.lastCount); //更新抽奖次数
			}else if(lastCount==0){
				showDiv($("#three-chance"));
				$('#count-time').html(object.lastCount); //更新抽奖次数
			}
		},
		error : function() {
			location.href = "./index";
		}
	});
}

/*查看我的奖品*/
function viewPrizes(){
	$.ajax({
		async: false, 
		cache:false,
		url : "owns.json",
		success : function(data) {
			var object =   eval(data);
			if(object.result==true){
				//先判断是否中奖
				if(null==object.value||object.value==""){
				//没有中奖
					showDiv($("#no_prize_info"));
				}else{
				//中奖
					$('#prizes-li .line-out').remove();
					$.each(object.value, function(index,item){ 
				        $('<div class="line-out"><div class="date-main">'+item.lottery_date+'</div><div class="prize-news"><span class="">'+item.award_name+'</span>&nbsp;兑换码<span>'+item.sn+'</span></div><div class="check-rule">查看兑换方式<input type="hidden" value="'+item.lottery_grade+'" /></div></div>').appendTo('#prizes-li .popup-main');
				    });
					$(".check-rule").bind('click', function(){
     					showTicketRules($(this).children('input').val());
						showDiv($("#ticket-rules"));
						$('#prizes-li').hide();
					});
					showDiv($("#prizes-li"));
				}
			}
		},
		error : function() {
			location.href = "./index";
		}
	});
}
var arr_info=['<p>联系客服：appstore@vivo.com.cn，邮件中写明您的中奖兑换码、vivo账户名称、手机号码，即可，收到邮件后客服会电话联系以便寄送奖品</p>',
                          '<p>京东卡使用办法：</p><p>1，登录京东网上商城www.360buy.com，新用户需要注册。</p><p>2，选择任意商品，点击加入购物车。</p><p> 3，确认购物车商品后，点击去结算。</p><p>4，填写收货人信息、支付及配送方式、发票信息。</p><p>5，在提交订单之前选择“使用京东礼品卡”，输入礼品卡密码，点击添加。</p>',
              			  '<p>注册或者登陆迅雷会员增值服务中心：</p><p>http://pay.vip.xunlei.com/选择“激活码支付”进行激活使用；</p>',
                          '<p>在活动页面、vivo应用商店、vivo游戏中心下载游戏：消灭星星（最新版）、去吧皮卡丘、刀塔传奇、时空猎人、神魔、攻城掠地、剑魂之刃，安装后登录游戏内进行兑奖。</p>'
                         ];