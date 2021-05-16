window.onload=function(){
	downTime();
}
function downTime(){
	var time=5*60*60;
	var timer=null;
	var skTime=document.querySelector('.killstore_seckill');
	var spans=skTime.querySelectorAll('span');
	timer=setInterval(function(){
		if(time<=0){
			clearInterval(timer);
			return false;
		}
		time--;
		var h=Math.floor(time/3600);
		var m=Math.floor(time%3600/60);
		var s=time%60;
		console.log(h);
		console.log(m);
		console.log(s);
		spans[0].innerHTML=Math.floor(h/10);
		spans[1].innerHTML=h%10;
		spans[3].innerHTML=Math.floor(m/10);
		spans[4].innerHTML=m%10;
		spans[6].innerHTML=Math.floor(s/10);
		spans[7].innerHTML=s%10;
	},1000);
}