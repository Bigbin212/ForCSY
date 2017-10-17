/**
 * 操作日志
 */
$(document).ready(function(){ 
	initResize();
	//初始化尺寸
	$(window).resize(function(){
		initResize();
	});
	initTable();

	
});
function initResize(){
	var  winWidth = $(window).width();//窗口宽度
	var  winHeight = $(window).height();//窗口高度
	$(".whole-div").width(winWidth); //整体的宽度
	$(".whole-div").height(winHeight - 20);//整体的高度
	$(".main-top").height((winHeight/100)*19);//整体的高度
	$('#dataGrid').height($('.main-top').height() - $('.titleDiv').height());
	$('.znjt-search-table').height($('#dataGrid').height());
	$('.main-top1').height($('.whole-div').height() - $('.main-top').height()-35);
	$('.main-top1').width($('.main-top').width());
	$('#divList').width($('.main-top1').width());
	$('#divList').height($('.main-top1').height() - $('.titleDiv').height());
	
}
function initTable()
{
	
	//加载日志表格
	$("#CzTable").datagrid({
		singleSelect : true,
		remoteSort : true,
		border:false,
		fitColumns : true,
		queryParams: getQueryParams(),
		url:rootPath + "/loadTable.do",
		columns:[[
		    {
		    	field : 'fAccount',
		    	title : '操作账户',
				width : '15%',
				fixed : true,
				align : 'center',
		    	
		    } ,{
		    	field : 'ip',
		    	title : 'ip地址',
		    	width : '16%',
				fixed : true,
				align : 'center',
		    	
		    } ,{
		    	field : 'createtime',
		    	title : '审核时间',
		    	width : '18%',
		    	fixed : true,
		    	align : 'center',
		    	formatter:createTimeFormat
		    } ,{
		    	field : 'operType',
		    	title : '日志类型',
		    	width : '10%',
		    	fixed : true,
		    	align : 'center',
		  	  formatter:function(value,rowData,rowIndex){
		        	var ret ='';
					if(value == 1){
						ret="审核状态更新";
					}else if(value == 2){
						ret="其他";
					}
					return ret;
      	  }
		    	
		    	
		    } ,{
		    	field : 'operRst',
		    	title : '日志操作结果',
		    	width : '10%',
		    	fixed : true,
		    	align : 'center',
		    	formatter:function(value,rowData,rowIndex){
		        	var ret ='';
					if(value == 1){
						ret="已确认";
					}else if(value == 2){
						ret="已忽略";
					}
					return ret;
      	  }
		    	
		    	
		    }, {
		    	field : 'description',
		    	title : '操作描述',
		    	width : '30%',
		    	fixed : true,
		    	align : 'center',
		    	
		    	
		    }
        ]],
        fit : true,
		rownumbers : true,
		loadMsg : '正在查询数据，请稍候...',
		pagination : true,
		pageSize : 15,
		pageList:[15,30,45,60],
	    onLoadSuccess:function(data){
	    	if(reData.error != ""){
				jAlert('提示', reData.error);
				return ;
			}
	    	
	    }
		
	});
	   
}

//审核状态格式化
function stateFormat(value){
	
		return value;
	
}
function getQueryParams(){
	var params = {};
	params.kssj = $('#kssj').val();
	params.jssj = $('#jssj').val();
	params.operrst = $('#statusSelect').val();
	params.faccount = $('#faccount').val();
	return params;
}

//按钮事件
function doSearch()
{
	$("#CzTable").datagrid('load',getQueryParams());
		
}

//重置
function doReset()
{
	
	$("#statusSelect").val('');
	$('#faccount').val('');	
	$('#kssj').val($('#hidden_kssj').val());
	$('#jssj').val($('#hidden_jssj').val());
}
//时间转换
function createTimeFormat(value){
	 if(value==null || value==""){
		  return "";
	  }else{
		  return new Date(value.time).format("yyyy-MM-dd hh:mm:ss");
	  }
}
/**      
 * 对Date的扩展，将 Date 转化为指定格式的String      
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符      
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)      
 * eg:      
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423      
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
 */        
Date.prototype.format = function(format){
	var o = {
	"M+" : this.getMonth()+1, //month
	"d+" : this.getDate(), //day
	"h+" : this.getHours(), //hour
	"E"  : this.getDay(), //周几
	"m+" : this.getMinutes(), //minute
	"s+" : this.getSeconds(), //second
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter
	"S" : this.getMilliseconds() //millisecond
	}

	if(/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}

	for(var k in o) {
		if(new RegExp("("+ k +")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}
	}
	return format;
}

