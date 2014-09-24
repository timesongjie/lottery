
    // 单行滚动
	function AutoScroll(obj){
	    $(obj).find("ul").animate({
	        marginTop:"-28px"
	    },500,function(){    
	        $(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
	    });
	}

	/*$(document).ready(function(){
	    setInterval('AutoScroll(".roll-box")',2000) 
	});
*/

$(function(){
	setInterval('AutoScroll(".roll-box")',2000) 
	$("*").bind('touchstart', function(event){});
	
	var rotateFunc = function(awards,angle,text){  
		$("#lotteryBtn").rotate({
			angle:30, 
			duration: 3000, 
			animateTo: angle+1440, 
			callback:function(){ 
				if (prize==0) {
					// $(".weizhongjiang").text(text);
				} else{
					// $(".zhongjiang").text(text);
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


/*    // 页面加载向服务器发送ajax请求,检测是否登录
	login=false;
		// login=true;
		if (!login) {
		// $(".roll-bg,.draw,.count").animate({show()},500);
			// $(".roll-bg,.count").hide();
			// $(".login").text("您必须登录才能参加抽奖！")
			// $(".login").slideToggle(500);
			return;
		} ;
*/

	$(".draw").rotate({ 
	   bind: 
		 { 
			// touchstart: function(){
				click: function(){
				// alert(555)
			// ------------------------------------------------------------------------------------------------------
			// 向服务器发送ajax请求,检测是否登录
				//login=false;
				login=true;
				if (login) {
					
					/*
					$(".roll-bg,.draw,.count").animate({show()},500);
					$(".roll-box,.examine,.count").show();*/

					/*$(".login").text("您必须登录才能参加抽奖！")
					$(".login").slideToggle(500);*/
					// return;
				}else{
					alert("您必须登录才能参加抽奖！");
					return;
				};


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
					$.ajax({
						url: "test.html",
						context: document.body
						}).done(function() {
						$( this ).addClass( "done" );
					});
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
	

	
	// 星星评级
	$('.star-size').each(function(){   
	    var result=$(this).val();   
	    // alert(result);
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
		$(this).css({background:"#009bfe",color:"#fff"})
	})
	

	//弹窗
/*	cur = 0;
    $(".small-img-box img").click(function  () {
    	
    	// 滚动条回到顶部 
    	$("html,body").animate({scrollTop:0},200);	
    	cur = $(this).index();
    	// alert(cur);
      $(".black_overlay,.white_content").show();
      $("body").css("overflow","hidden");
  
    })
  
    $(".screenshot-close,.black_overlay").click(function  () {
    	 $(".black_overlay,.white_content").hide();
    	 $("body").css("overflow","auto");
    })*/


    var scrolltop = $(document).height(); 
    $(".black_overlay ").css("height",scrolltop)


    showDiv($(".popup"));
	function showDiv(obj){
	 $(obj).show();
	 center(obj);
	 $(window).scroll(function(){
	  center(obj);
	 });
	 $(window).resize(function(){
	  center(obj);
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


})