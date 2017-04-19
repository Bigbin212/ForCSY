/**
 * 快处快赔流程
 * @author wangqiang
 * @description
 */

var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope) {
	//事故描述字段及时提醒绑定
	$scope.getSgfsmsLenght = function() {
		if(!$scope.sgfsms){
			return 0;
		}else{
			return $scope.sgfsms.length;
		}
	};
	
	$scope.jsyxxIndex = function(number){
		return String.fromCharCode(number);
	};

	//驾驶员加载信息
	$scope.jsyxxs = [
	                 {name:"",hphm:"",phone:""},
	                 {name:"",hphm:"",phone:""}
	                 ];
	
	$scope.disableAddJsy = true;
	
	//新增驾驶员事件绑定
	$scope.addJsy = function(){
		$scope.jsyxxs.push({name:"",hphm:"",phone:""});
		
		if($scope.jsyxxs.length >= 3){
			$scope.disableAddJsy = false;
		}else{
			$scope.disableAddJsy = true;
		}
	};
	
	//驾驶员删除操作
	$scope.deleteJsy = function(index){
		$scope.jsyxxs.splice(index, 1);

		if($scope.jsyxxs.length >= 3){
			$scope.disableAddJsy = false;
		}else{
			$scope.disableAddJsy = true;
		}
	};
	
	//图片上传操作初始化数据
	$scope.xxtps = [];
	
	//图片预览
	$scope.previewImage = function(index){
		var urls = [];
		for(var i in $scope.xxtps){
			urls.push($scope.xxtps[i].uploadUrl);
		}
		wx.previewImage({
		    current: $scope.xxtps[index].uploadUrl, 
		    urls: urls // 需要预览的图片http链接列表
		});
	};
	
	//图片上传到本地
	$scope.uploadImgLocal = function(){
		wx.chooseImage({
		    count: 9, 
		    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
		    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
		    success: function (res) {
		        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		        for(var i=0; i<localIds.length; i++){
		        	if($scope.xxtps.length >= 10){
		        		alert("最多可上传十张图片!");
		        		return;
		        	}
		        	$scope.$apply(function(){
		        		$scope.xxtps.push({
			        		uploadUrl: localIds[i],
			        		uploadSuccess: false,
			        		uploadError: false,
			        		uploadPercent: 0
			        	});
		        	});
		        	
		        	alert(localIds[i]);
		        	wx.uploadImage({
		        	    localId: localIds[i], // 需要上传的图片的本地ID，由chooseImage接口获得
		        	    isShowProgressTips: 1, // 默认为1，显示进度提示
		        	    success: function (res) {
		        	        var serverId = res.serverId; // 返回图片的服务器端ID
		        	        alert(JSON.stringify(res));
		        	    }
		        	});
		        	
		        }
		    }
		});
	};
});


$(function(){
	$.getJSON(rootPath+"/wx/sign?url=" + encodeURIComponent(location.href.split('#')[0]), function (res) {
        wx.config({
            debug: false,
            appId: res.appid,
            timestamp: res.timestamp,
            nonceStr: res.nonceStr,
            signature: res.signature,
            jsApiList: [
				"getLocation",
				"openLocation",
				"chooseImage",
				"previewImage"
            ]
        });
        
        wx.ready(function(rst){
        	$("#position").click(function(){
        		getLocation();
        	});
        });
    });
	
	
	function getLocation(){
		wx.getLocation({
		    type: 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
		    success: function (res) {
		    	wx.openLocation({
		    	    latitude: res.latitude, // 纬度，浮点数，范围为90 ~ -90
		    	    longitude: res.longitude, // 经度，浮点数，范围为180 ~ -180。
		    	    name: '', // 位置名
		    	    address: '', // 地址详情说明
		    	    scale: 14, // 地图缩放级别,整形值,范围从1~28。默认为最大
		    	    infoUrl: 'http://weixin.qq.com' // 在查看位置界面底部显示的超链接,可点击跳转
		    	});
		    }
		});
	}
	
});