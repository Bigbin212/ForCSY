/**
 * 设备故障上报
 */
$(function() {
	initResize();
	// 初始化尺寸
	$(window).resize(function() {
		initResize();
	});
	/*initComponent();*/
	
	doSearch(1);


	

});
function initResize() {
	var winWidth = $(window).width();// 窗口宽度
	var winHeight = $(window).height();// 窗口高度
	$(".whole-div").width(winWidth); // 整体的宽度
	$(".whole-div").height(winHeight - 30);// 整体的高度
	$(".main-top").height(winHeight / 8);// 整体的高度
	$('#dataGrid').height(($('.main-top').height() / 100) * 80);// 搜索条件的高度
	$('#dataGrid').width(($('.main-top').width() / 100) * 98);// 搜索条件的宽度
	$('.main-top1').height(
			$('.whole-div').height() - $('.main-top').height() - 18);
	$('.main-top1').width(($('.main-top').width() / 100) * 98);
	$('#sbgzTable').width($('.main-top1').width());
	$('#sbgzTable').height(($('.main-top1').height()/100)*10);
	$('#sbgzinfo').width($('.main-top1').width());
	$('#sbgzinfo').height(($('.main-top1').height()/100)*85);
	$('#pageContent').width(($('.main-top1').width()/100)*60);
	$('#pageContent').height(($('.main-top1').height()/100)*5);
}




// 查询
function doSearch(pageNum){

	$('#sbgzinfo').showLoading();
	$.ajax({
		type: "post",
		url: rootPath + "/sbgzcx.do",
		data: {
			kssj:$('#kssj').val(),
			jssj:$('#jssj').val(),
			offset:pageNum,
			auditstatus:$('#auditstatus').val(),
			processstatus:$('#processtatus').val(),
			nickname:$('#wxh').val(),
		},
		dataType: "json",
		success: function(data){
			if(data.error != ""){
				jAlert(data.error);
				return ;
			}else{
				$('#sbgzinfo').hideLoading();
				$('#sbgzinfo').empty();
				
				if(data.rows.length == 0){
					jAlert("没有查询到数据");
					return ;
				}
				
				makesbgzinfo(data,pageNum);
				 var total = data.total;  //总数目
		         var pageNum = Math.ceil(total/10); //分页的总页数
		         doPage(total,pageNum);
			}
		}
	});
}
function makesbgzinfo(data,count){

	$("#sbgzinfo").empty();
	
	if(data.rows != null && data.rows.length > 0){
		jsonData = data;
		
		$.each(data.rows, function (i, item) {

			$("#sbgzinfo").append("<div id='tablediv"+i+"'  style='font-size: 15px;font-weight: bold;background-color: white;text-align: center;'></div>");
			$("#tablediv"+i).width($('#sbgzTable').width());
			$("#tablediv"+i).append("<table id='table"+i+"' style='margin-top:5px'></table>");
			$("#table"+i).width($('#sbgzTable').width());
		
			$("#table"+i).append("<tr id='tr"+i+"' class='change'></tr>");
		
			
			$("#tr"+i).append("<td style='width : 12%;fixed : true;height: 47px'><img src='"+item.wxheadimage+"' style='width:30px;height:30px;border-radius:50px;border: 1px solid #EFEFEF;'></td>");
			$("#tr"+i).append("<td style='width : 16%;fixed : true;'>"+item.nickname+"</td>");
			$("#tr"+i).append("<td style='width : 14%;fixed : true;'>"+item.phone+"</td>");
			$("#tr"+i).append("<td style='width : 14%;fixed : true;'>"+item.uploadtime+"</td>");
			$("#tr"+i).append("<td style='width : 20%;fixed : true;'>"+item.uploadposition+"</td>");
			var auditstatus='';
							if (item.auditstatus == '1') {
								auditstatus = '已确认';
							} else if (item.auditstatus == '2') {
								auditstatus = '已忽略';
							} else {
								auditstatus = '未审核';
							}

							var processstate = '';
							if (item.auditstatus == '2') {
								processstate = '';
							} else {
								if (item.processstatus == '1') {
									processstate = '已处理';
								} else if (item.processstatus == '2') {
									processstate = '已忽略';
								} else {
									processstate = '未处理';
								}
							}
			$("#tr"+i).append("<td style='width : 8%;fixed : true;' ><span id='audit"+i+"'>"+auditstatus+"<span></td>");
			$("#tr"+i).append("<td style='width : 8%;fixed : true;' ><span id='process"+i+"'>"+processstate+"<span></td>");
			$("#tr"+i).append("<td style='width : 8%;fixed : true;text-align: left'><div style='margin-left:40px'><img id='down"+i+"' onClick='dotoggle("+i+",1)' style='display:block' src='"+rootPath+"/static/img/sbgzsb/down.png' ><img id='up"+i+"' style='display:none' onClick='dotoggle("+i+",2)' src='"+rootPath+"/static/img/sbgzsb/up.png' ></div></td>");
			$("#tablediv"+i).append("<div id='panel"+i+"' style='display:none;' align='center'></div>");
			$("#panel"+i).width($('#sbgzTable').width());
			$("#panel"+i).height(($('.main-top1').height()/100)*38);
			$("#panel"+i).append("<div id='detail"+i+"' style='background-color:#FCFCFC;margin-top:10px;border-radius:10px;border:1px solid #DFDFDF;' align='center'></div>");
			$("#detail"+i).width(($('#sbgzTable').width()/100)*97);
			$("#detail"+i).height(($('.main-top1').height()/100)*28);
			 var str = item.faultImages.split(",");
			 var imagePath = item.imagepath+"/"+item.uploadtime.substring(0,10)+"/"+item.openid+"/";
			 $("#detail"+i).append("<div style='width:50%;float:left;margin-left:2%' align='left'><font color='#676767' style='font-weight:normal'>现场图片</font></div>");
			 $("#detail"+i).append("<div style='width:40%;float:right;' align='left'><font color='#676767' style='font-weight:normal'>故障描述</font></div><br>");
			$("#detail"+i).append("<div id='imgslideDiv"+i+"' style='width:45%;height:80%;float:left; align='center'></div>");
			//图片
			
			$("#imgslideDiv"+i).append("<div class='apply' id='focus"+i+"'></div>");
			$("#focus"+i).append("<div class='img_l' id='prev_btn"+i+"'><img onclick='doPrev("+i+")' src='"+rootPath+"/static/img/sbgzsb/prevbtn.png'/></div>");
			
			$("#focus"+i).append("<div class='apply_nav' id='apply_nav"+i+"' align='center'></div>");
			$("#apply_nav"+i).append("<div class='apply_w' id='apply_w"+i+"'></div>");
	
			for(var j=0;j<str.length;j++)
				{
				$("#apply_w"+i).append("<div class='apply_array' id='apply_array"+i+""+j+"'></div>");
				$("#apply_array"+i+j).append("<div class='apply_img'><img  src='"+(imagePath+str[j])+"' /></div>");
				
				}
			$("#focus"+i).append("<div class='img_r' id='next_btn"+i+"'><img onclick='doNext("+i+")' src='"+rootPath+"/static/img/sbgzsb/nextbtn.png'/></div>");
	
		
			
				
			//描述
			$("#detail"+i).append("<div id='description"+i+"' style='width:40%;float:right;'></div>");
            $("#description"+i).append("<div style='margin-right:40%;margin-top:5px;' align='left'><textarea rows='6' cols='50' style='font-weight:normal ;color: #919191;border:0px;background-color:#FCFCFC'  readonly='true'>"+item.faultDescription+"</textarea></div>");
        	var confirmgz = 1;
			var ignoregz = 2;
			var confirmcl = 1;
			var ignorecl = 2;
            if(item.auditstatus == '1'&& item.processstatus=='' )//审核已确认 处理没进行
			{
            	  $("#panel"+i).append("<div id='clbtndiv"+i+"' style='margin-top:10px;display:block;' align='center'></div>");
            	  $("#clbtndiv"+i).width(($('#sbgzTable').width()/100)*97);
      			  $("#clbtndiv"+i).height(($('.main-top1').height()/100)*7);
      			$("#clbtndiv"+i).append("<button id='confirmclbtn"+i+"'   type='button' onclick='doclCheck("+JSON.stringify(item)+","+confirmcl+","+i+")' class='btn btn-sm btn-primary'>处理确认</button>&nbsp;&nbsp;&nbsp;");
    			$("#clbtndiv"+i).append("<button id='ignoreclbtn"+i+"'  type='button' onclick='doclCheck("+JSON.stringify(item)+","+ignorecl+","+i+")' class='btn btn-sm btn-primary'>处理忽略</button>");
            	  
			}else if(item.auditstatus == '1'&& item.processstatus !='')//审核处理都进行 审核为已确认 
				{
				  $("#panel"+i).append("<div id='gzbtndiv"+i+"' style='margin-top:10px;display:none;background-color:#ffffff;' align='center'></div>");
				  $("#panel"+i).append("<div id='clbtndiv"+i+"' style='margin-top:10px;display:none;' align='center'></div>");
				}else if(item.auditstatus == '2')//审核为忽略
					{
					$("#panel"+i).append("<div id='gzbtndiv"+i+"' style='margin-top:10px;display:none;background-color:#ffffff;' align='center'></div>");
				    $("#panel"+i).append("<div id='clbtndiv"+i+"' style='margin-top:10px;display:none;' align='center'></div>");
					}else if(item.auditstatus == '')//未审核
						{
						    $("#panel"+i).append("<div id='gzbtndiv"+i+"' style='margin-top:10px;display:block;background-color:#ffffff;' align='center'></div>");
						    $("#panel"+i).append("<div id='clbtndiv"+i+"' style='margin-top:10px;display:none;' align='center'></div>");
							$("#gzbtndiv"+i).width(($('#sbgzTable').width()/100)*97);
							$("#gzbtndiv"+i).height(($('.main-top1').height()/100)*8);
							$("#clbtndiv"+i).width(($('#sbgzTable').width()/100)*97);
							$("#gzbtndiv"+i).height(($('.main-top1').height()/100)*8);
						
							$("#gzbtndiv"+i).append("<button id='confirmgzbtn"+i+"'  type='button' onclick='dogzCheck("+JSON.stringify(item)+","+confirmgz+","+i+")' class='btn btn-sm btn-primary'>故障确认</button>&nbsp;&nbsp;&nbsp;");
							$("#gzbtndiv"+i).append("<button id='ignoregzbtn"+i+"'  type='button' onclick='dogzCheck("+JSON.stringify(item)+","+ignoregz+","+i+")' class='btn btn-sm'>故障忽略</button>");
							$("#clbtndiv"+i).append("<button id='confirmclbtn"+i+"'   type='button' onclick='doclCheck("+JSON.stringify(item)+","+confirmcl+","+i+")' class='btn btn-sm btn-primary'>处理确认</button>&nbsp;&nbsp;&nbsp;");
			    			$("#clbtndiv"+i).append("<button id='ignoreclbtn"+i+"'  type='button' onclick='doclCheck("+JSON.stringify(item)+","+ignorecl+","+i+")' class='btn btn-sm'>处理忽略</button>");
						}
     
			
		}); 


        

	

	}
}
var _i=-1;
var lc1 = 0;
var rc1 =0;
function doPrev(i)
{
	if(i != _i)
		{
	$li1 = $("#apply_nav"+i+" .apply_array");
	$window1 = $("#focus"+i+" #apply_w"+i+"");
	$left1 = $("#focus"+i+" #prev_btn"+i+"");
	$right1 = $("#focus"+i+" #next_btn"+i+"");
	
	$window1.css("width", $li1.length*151);
    lc1 = 0;
	rc1 = $li1.length-3;
	if (lc1 < 1) {
		jAlert("已经是第一张图片");
		return;
	}
	lc1--;
	rc1++;
	_i=i;
	
	$window1.animate({left:'+=151px'}, 200);
		}
	else
		{
		if (lc1 < 1) {
			jAlert("已经是第一张图片");
			return;
		}
		lc1--;
		rc1++;
		$window1.animate({left:'+=151px'}, 200);
		_i=i;
		}
}

function doNext(i)
{
	if(i != _i)
	{
	$li1 = $("#apply_nav"+i+" .apply_array");
	$window1 = $("#focus"+i+" #apply_w"+i+"");
	$left1 = $("#focus"+i+" #prev_btn"+i+"");
	$right1 = $("#focus"+i+" #next_btn"+i+"");
	
	$window1.css("width", $li1.length*151);

	 lc1 = 0;
	 rc1 = $li1.length-3;
	if (rc1 < 1){
		jAlert("已经是最后一张图片");
		return;
	}
	lc1++;
	rc1--;
	$window1.animate({left:'-=151px'}, 200);
	_i=i;
	}else
		{
		if (rc1 < 1){
			jAlert("已经是最后一张图片");
			return;
		}
		lc1++;
		rc1--;
		$window1.animate({left:'-=151px'}, 200);
		_i=i;
		}
}

function doPage(total,pageNum)
{
	 $("#pageContent").paging({
  		totalPage: pageNum,
  		totalSize: total,
  		callback: function(num) {

  			getdata(num);
  	    
  		}
      });
}
function getdata(num)
{
	$('#sbgzinfo').showLoading();
	$.ajax({
		type: "post",
		url: rootPath + "/sbgzcx.do",
		data: {
			kssj:$('#kssj').val(),
			jssj:$('#jssj').val(),
			offset:num,
			auditstatus:$('#auditstatus').val(),
			processstatus:$('#processtatus').val(),
			nickname:$('#wxh').val(),
		},
		dataType: "json",
		success: function(data){
			if(data.error != ""){
				jAlert(data.error);
				return ;
			}else{
				$('#sbgzinfo').hideLoading();
				$('#sbgzinfo').empty();
				
				if(data.rows.length == 0){
					jAlert("没有查询到数据");
					return ;
				}
				
				makesbgzinfo(data,num);
				 _i=-1;
				 lc1 = 0;
				 rc1 =0;
				 
			}
		}
	});
	
}

function dotoggle(e,clickid)
{
	
	$("#panel"+e).slideToggle("slow");
	
	if(clickid==1)
		{
		$("#down"+e)[0].style.display = 'none';
		$("#up"+e)[0].style.display = 'block';
		}else
			{
			$("#down"+e)[0].style.display = 'block';
			$("#up"+e)[0].style.display = 'none';
			}

	
}



function dogzCheck(dataSingle,value,e)
{
	
	var ignore="已忽略";
	var confirm="已确认";
	if (value == 1) {
		jConfirm('是否确认故障?', '提示', function(r) {
			if (r == true) {
				changegzStatus(dataSingle,value,e,confirm);

			} else {
				return;
			}
		});

	} else if (value == 2) {
		jConfirm('是否忽略该故障?', '提示', function(r) {
			if (r == true) {
				changegzStatus(dataSingle,value,e,ignore);

			} else {
				return;
			}
		});

	} 
}

function doclCheck(dataSingle,value,e)
{
	var ignore="已忽略";
	var confirm="已确认";
	if (value == 1) {
		jConfirm('是否确认处理?', '提示', function(r) {
			if (r == true) {
				changeclStatus(dataSingle,value,e,confirm);

			} else {
				return;
			}
		});

	} else if (value == 2) {
		jConfirm('是否忽略处理?', '提示', function(r) {
			if (r == true) {
				changeclStatus(dataSingle,value,e,ignore);

			} else {
				return;
			}
		});

	} 
}


function changegzStatus(dataSingle,value,e,status)
{
	
	   $.ajax({
			type: "post",
			url: rootPath + "/auditcheck.do",
			data: {
				faultid:dataSingle.faultid,
				auditstatus:value,
		
			},
			dataType: "json",
			complete: function(){

					
					$("#audit"+e).empty();
					$("#audit"+e).html(status);
					if(status == '已忽略')
						{
						$("#gzbtndiv"+e)[0].style.display = 'none';
						$("#process"+e).empty();
						$("#process"+e).html('');
						
						}else if(status == '已确认')
							{
							$("#gzbtndiv"+e)[0].style.display = 'none';
							$("#clbtndiv"+e)[0].style.display = 'block';
							}
													
			}
		});

}

function changeclStatus(dataSingle,value,e,status)
{
	   $.ajax({
			type: "post",
			url: rootPath + "/processcheck.do",
			data: {
				faultid:dataSingle.faultid,
				processstatus:value,
		
			},
			dataType: "json",
			complete: function(){

					
					$("#process"+e).empty();
					$("#process"+e).html(status);
					$("#clbtndiv"+e)[0].style.display = 'none';
													
			}
		});
	
}

function doSetProcess()
{
	
	var auditstatus =$("#auditstatus").val();
	if(auditstatus == 2)
		{
		$("#processtatus").disabled=true;
		}else
			{
			$("#processtatus").disabled=false;
			}
	
}


