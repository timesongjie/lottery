
    // 单行滚动
	function AutoScroll(obj){
	    $(obj).find("ul").animate({
	        marginTop:"-60px"
	    },500,function(){    
	        $(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
	    });
	}

	$(document).ready(function(){
	    setInterval('AutoScroll("#box")',2000) 
	});


$(function(){
	$("*").bind('touchstart', function(event){});
	var rotateFunc = function(awards,angle,text){  
		$(".rotate-bg").rotate({
			angle:30, 
			duration: 3000, 
			animateTo: angle+1440, 
			callback:function(){ 
				if (prize==0) {
					$(".weizhongjiang").text(text);
				} else{
					$(".zhongjiang").text(text);
				};
			
			}
		});
		 
	};
	



	 //初始化变量
	var mov= false;
    var running= false;
    //抽奖次数
    var count=1;
    //中几等奖
    var prize;
    // 兑奖SN码
    var sncode;
    // 检测是否已登录
    var login=false;

	$("#lotteryBtn").rotate({ 
	   bind: 
		 { 
			touchstart: function(){

			// ------------------------------------------------------------------------------------------------------
			// 向服务器发送ajax请求,检测是否登录
				// login=false;
				login=true;
				if (!login) {
					$(".login").text("您必须登录才能参加抽奖！")
					$(".login").slideToggle(500);
					return;
				} ;


			// ------------------------------------------------------------------------------------------------------
				// 抽奖时向服务器发送ajax请求，返回之前中奖结果：count（抽奖次数）、prize（几等奖）、sncode（兑奖SN码）
				
			    // count=4;
			    // sncode="hdsj55dg56dsad57ed";
			    // sncode="";


				if (running) return;
		        if (count>3) {
		            $(".cishu").slideToggle(500);
		            $(".cishu").text("您今天的机会已经用完了啦！明天再来！")
		            return;
		        } else{
		            mov=true;
		        }
		        if (!sncode) {
		            mov=true; 
		        } else{
		            alert('本次活动您已经中过奖了!兑奖SN码为:' + sncode);
		            return;
		        };

      
        		if(mov){
        			// 抽奖时向服务器发送ajax请求，返回中奖结果：prize（几等奖）、sncode（兑奖SN码）
					prize=3;
					sncode="1245668885";



        			running=true;
        			count++;

					if(prize==1){
						rotateFunc(1,5,'恭喜您抽中的一等奖!兑奖SN码为:' + sncode)
					}
					if(prize==2){
						rotateFunc(2,126,'恭喜您抽中的二等奖!兑奖SN码为'+ sncode)
					}
					if(prize==3){
						rotateFunc(3,246,'恭喜您抽中的三等奖!兑奖SN码为'+ sncode)
					}
					if(prize==0){
						var angle = [36, 66, 96, 152, 180, 210, 270, 300, 330];
							angle = angle[Math.floor(Math.random()*angle.length)]
						rotateFunc(0,angle,'手气不行，洗洗手再来！')
					}
					
				}
				setTimeout(function(){
					running=false;
				}, 3000);
			}
		 } 
	   
	});


	
})