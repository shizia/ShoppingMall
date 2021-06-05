/*    
    var second = 0;
    window.setInterval(function () {
        second ++;
    }, 1000);
    var tjArr = localStorage.getItem("jsArr") ? localStorage.getItem("jsArr") : '[{}]';
    $.cookie('tjRefer', getReferrer() ,{expires:1,path:'/'});
    window.onbeforeunload = function() {
        if($.cookie('tjRefer') == ''){
            var tjT = eval('(' + localStorage.getItem("jsArr") + ')');
            if(tjT){
                tjT[tjT.length-1].time += second;
                var jsArr= JSON.stringify(tjT);
                localStorage.setItem("jsArr", jsArr);
            }
        } else {
            var tjArr = localStorage.getItem("jsArr") ? localStorage.getItem("jsArr") : '[{}]';
            var dataArr = {
                'url' : location.href,
                'time' : second,
                'refer' : getReferrer(),
                'timeIn' : Date.parse(new Date()),
                'timeOut' : Date.parse(new Date()) + (second * 1000)
            };
            tjArr = eval('(' + tjArr + ')');
            tjArr.push(dataArr);
            tjArr= JSON.stringify(tjArr);
            localStorage.setItem("jsArr", tjArr);
        }
        localStorage.content = encodeURIComponent(localStorage.content)
        console.log("canyouseme???",localStorage);
    };
    function getReferrer() {
        var referrer = '';
        try {
            referrer = window.top.document.referrer;
        } catch(e) {
            if(window.parent) {
                try {
                    referrer = window.parent.document.referrer;
                } catch(e2) {
                    referrer = '';
                }
            }
        }
        if(referrer === '') {
            referrer = document.referrer;
        }
        return referrer;
    } */

var ap_name = navigator.appName;  
var ap_vinfo = navigator.appVersion;  
var ap_ver = parseFloat(ap_vinfo.substring(0,ap_vinfo.indexOf('(')));// 获取版本号
var time_start = new Date();  
var clock_start = time_start.getTime();  
var dl_ok=false;  
function init (){  
if(ap_name=="netscape" && ap_ver>=3.0)  
    dl_ok=true;   
    return true;  
}  
//已经打开页面多少秒
function get_time_spent (){   
    var time_now = new Date();  
    return((time_now.getTime() - clock_start)/1000);   
}  
function show_secs (){  // show the time user spent on the side           
    var i_total_secs = Math.round(get_time_spent());   
    var i_secs_spent = i_total_secs % 60;  
    var i_mins_spent = Math.round((i_total_secs-30)/60);//四舍五入，超60s，大于0.5，四舍五入就是1min   
    var s_secs_spent = "" + ((i_secs_spent>9) ? i_secs_spent : "0" + i_secs_spent);//改显示格式：个位数-> 0+个位数，如7->07
    var s_mins_spent ="" + ((i_mins_spent>9) ? i_mins_spent : "0" + i_mins_spent);  
    document.fm0.time_spent.value = s_mins_spent + ":" + s_secs_spent;  //把值放入form中name为time_spent的input中
	document.fm1.visitsecond.value=i_total_secs;
    window.setTimeout('show_secs()',1000);   //每隔1s刷新一次input里的值
}  
 window.onbeforeunload = function() {
	var form = document.getElementById('fm1');
	form.submit();
            }
   
