var rootPath = function(){
	return '/ForCsy/mp';
}();

$(function(){
	//初始化加载事件
	init();
	
	function init(){
		//用户菜单
		$(".usermenu").hover(function(e){
			$(this).children('.xs-popover').removeClass("hidden");
		},function(e){
			$(this).children('.xs-popover').addClass("hidden");
		});
	}
});