/**
 * 快处快赔流程
 * @author wangqiang
 * @description
 */
var app = angular.module("myApp", ["ui.router"]);

app.config(function ($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('');
	
	$stateProvider.state('detail', {
		url: '/detail',
    	params: {data: undefined},
		templateUrl: 'detail.html',
		controller: 'detail'
    }).state('/',{
    	url: '/',
    	template: ''
    });
});

app.controller("detail", function($scope, $stateParams, $state, $http, $sce){
	if(!$stateParams.data){
		$state.go("/");
		return;
	}
	
	var info = $stateParams.data;
	var resoure_prev = $("#wxResource").val() + "/" + new Date(info.occurrenceTime.time)
			.format("yyyy-MM-dd")+"/"+ info.fkWxOpenid + "/";
	//无法直接绑定audio元素问题
	$scope.sce = $sce.trustAsResourceUrl;
	
	$scope.info = info;
	
	$http({
		method: "post",
		url: rootPath + "/getDriverByEventID",
		params: {
			id: info.id
		}
	}).success(function(data,status,config,headers){
		$scope.info.jsyxxs = data;
	});
	
	//日期格式化
	$scope.formatterTime = function(date){
		return new Date(date.time).format("yyyy-MM-dd hh:mm:ss");
	};
	
	//驾驶员信息索引预定
	$scope.jsyxxIndex = function(number){
		return String.fromCharCode(number+65);
	};
	
	//图片资源转换
	$scope.imgUrls = !info.liveImage?[]:info.liveImage.split(",");
	$.each($scope.imgUrls, function(index,item){
		var imgUrl = resoure_prev + item;
		$scope.imgUrls[index] = {
			src: imgUrl,
			cacheSuccess: false
		};
	});
	
	$.each($scope.imgUrls, function(index){
		var img = new Image();
		img.src = this.src;
		img.onload = function(){
			$scope.$apply(function(){
				$scope.imgUrls[index].cacheSuccess = true;
			});
		};
	});
	
	
	$scope.imgClick = function(src){
		var srcArray = [];
		for(var i=0;i<$scope.imgUrls.length;i++){
			srcArray.push($scope.imgUrls[i].src);
		}
		WeixinJSBridge.invoke('imagePreview', {    
			'current': src,    
            'urls': srcArray
		});
	};
	
	//语音资源转换
	$scope.voiceUrls = !info.liveVoice?[]:info.liveVoice.split(",");
	$.each($scope.voiceUrls, function(index,item){
		var voiceUrl = resoure_prev + item;
		$scope.voiceUrls[index] = {
			voiceUrl: voiceUrl,
			playing: false,
			duration: 0,
			cache: false
		};
	});
	
	$scope.voiceClick = function(domId){
		var dom = $("#"+domId)[0];
		if(dom.paused){
			dom.play();
		}else{
			dom.pause();
		}
	};
	
	$scope.voiceStyle = function(length){
		var width = 0;
		if(length>=60){
			width = 2*10+8*5+50*0.5;
		}else if(length>=10){
			width = 2*10+8*5+(length-10)*1;
		}else if(length>=2){
			width = 2*10+(length-2)*3;
		}else if(length>0){
			width = (length-2)*5;
		}else{
			width = 10;
		}
		return {width:width+"%"};
	};
	
	$scope.loadingVoice = function(voice, domId){
		//微信内置浏览器
		$("#"+domId).unbind("canplay").bind("canplay",function(){
			var $this = $(this);
			$scope.$apply(function(){
				voice.duration = Math.ceil($this[0].duration);
				voice.cache = true;
			});
		});

		$("#"+domId).unbind("play").bind("play",function(){
			$scope.$apply(function(){
				voice.playing = true;
			});
			
			$(this).parent(".voice").siblings(".voice").children("audio").each(function(){
				this.pause();
			});
		});
		
		$("#"+domId).unbind("ended").bind("ended",function(){
			$scope.$apply(function(){
				voice.playing = false;
			});
		});
		
		$("#"+domId).unbind("pause").bind("pause",function(){
			$(this)[0].load();
			$scope.$apply(function(){
				voice.playing = false;
			});
		});
	};
});

app.controller("myCtrl", function($scope, $http, $state){
	
	$scope.hidden = false;
	$scope.pages = [];
	$scope.pageNum = 1;
	$scope.noMoreData = false;
	
	/*图片加载类*/
	$scope.loadPage = function(pageIndex){
		$scope.infosLoading = true;
		$http({
			method: "post",
			url: rootPath + "/wx/getKckpPageInfo",
			params: {
				pageNum: pageIndex
			}
		}).success(function(data,status,config,headers){
			$scope.infosLoading = false;
			//有数据的时候才进行填充
			if(data.list.length>0){
				$scope.pages[data.pageNum-1] = data;
				if(data.endRow<data.total){
					$scope.pageNum++;
				}else{
					$scope.noMoreData = true;
				}
			}
		}).error(function(data,status,hedaers,config){
			$scope.infosLoading = false;
			console.log(data);
		});
	};
	//默认加载一页
	$scope.loadPage(1);
	
	$scope.formatterTime = function(date){
		return new Date(date.time).format("yyyy年MM月dd日");
	};

	$scope.formatterPosition = function(info){
		var geocoder = new qq.maps.Geocoder();
		var latLng = new qq.maps.LatLng(info.latitude, info.longitude);
		geocoder.getAddress(latLng);//对指定经纬度进行解析
		geocoder.setComplete(function(result) {
			$scope.$apply(function(){
				if(!!result.detail.address){
					var address = result.detail.addressComponents;
					info.position = address.city+address.district+address.streetNumber;
				}else{
					info.position = "经度:"+info.longitude+",纬度:"+info.latitude;
				}
				console.log(result);
			});
		});
		//若服务请求失败，则运行以下函数
		geocoder.setError(function() {
			$scope.$apply(function(){
				info.position = "经度:"+info.longitude+",纬度:"+info.latitude;
			});
		});
	};
	
	$scope.detail = function(info){
		$state.go("detail", {data:info});
	};
});

//浏览器返回事件
window.addEventListener("hashchange", function(e) {
	if(location.hash.indexOf("detail")>0){
		$("div.myCtrl").css("display", "none");
		$("div.detailDiv").removeClass("hidden");
	}else{
		$("div.myCtrl").css("display", "block");
		$("div.detailDiv").addClass("hidden");
	}
}, false);

