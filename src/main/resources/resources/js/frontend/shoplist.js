$(function(){
	var loading=false;
	//定义一次返回最大总数
	var maxItem=999;
	//每页数目
	var pageSize=999;
	//店铺列表获取地址
	var listUrl="/myo2o/fronted/listshops";
	//类别获取地址
	var searchDivUrl="/myo2o/fronted/listshopspageinfo";
	//初始化查询信息
	var pageIndex=1;
	var shopName="";
	var areaId="";
	var shopCategoryId="";
	var parentId=getQueryString("parentId");
	getSearchDivDate();
	addItems(pageSize,pageIndex);
	//获得并渲染类别
	function getSearchDivDate(){
		$.get(searchDivUrl+"?parentId="+parentId,
			{
				
			},
			function(data,status){
				if(data.success)
				{
					//加载类别
					var tempHtml="";
					var shopCategoryList=data.shopCategoryList;
					$.each(shopCategoryList,function(index,item){
						
						tempHtml=tempHtml+"<a href='#' class='button' data-category-id='"+item.shopCategoryId+"'>"+
										item.shopCategoryName+
										"</a>";
					});
					$("#shoplist-search-div").html(tempHtml);
					
					//加载地区下拉栏
					var areaList=data.areaList;
					tempHtml="<option value=''>全部街道</option>";
					$.each(areaList,function(index,item){
						tempHtml=tempHtml+"<option value='"+item.areaId+"'>"+
										item.areaName+
										"</option>"
						
					});
					$("#area-search").html(tempHtml);
				}
				else
				{
					alert(data.errMsg);
				}
			}
		
		);
	}
	
	//商铺信息的显示，在无极滚动、重新选择地区、类别、名称后都会触发
	function addItems(pageSize,pageIndex){
		var url=listUrl+"?pageIndex="+pageIndex+"&pageSize="+pageSize+"&parentId="+parentId
						+"&areaId="+areaId+"&shopCategoryId="+shopCategoryId+"&shopName="+shopName;
		loading=true;
		$.get(url,
			{
				
			},
			function(data,status){
				if(data.success){
					var shopList=data.shopList;
					maxItem=data.count;
					var tempHtml="";
					$.each(shopList,function(index,item){
						
						tempHtml=tempHtml+"<div class='card' data-shop-id='"+item.shopId+"'>"
										+"<div class='card-header'>"
										+item.shopName+"</div>"
										+"<div class='card-content'>"
										+"<div class='list-block media-list'>"
										+"<ul>"
										+"<li class='item-content'>"
										+"<div class='item-media'>"
										+"<img src='/myo2o"+item.shopImg+"' width='44'>"
										+"</div>"
										+"<div class='item-inner'>"
										+"<div class='item-subtitle'>"
										+item.shopDesc
										+"</div>"+"</div>"+"</li>"+"</ul>"
										+"</div>"+"</div>"+"<div class='card-footer'>"
										+"<p class='color-gray'>"
										+new Date(item.lastEditTime).Format("yyyy-MM-dd")
										+"更新</p>"+"<span>点击查看</span>"
										+"</div>"+"</div>";
					});
					$(".list-div").append(tempHtml);
					/* var total=$(".list-div .card").length;
					if(total>=maxItem)
					{
						//若加载超过总数则取消加载
						$.detachInfiniteScroll($('.infinite-scroll'));
						$('.infinite-scroll-preloader').remove();
					}
					pageIndex++;
					loading=false;
					$.refreshScroller(); */
				}
				else
				{
					alert(data.errMsg);
				}
			}
		);
	}
	
	//注册滚动事件
	/* $(document).on('infinite', '.infinite-scroll-bottom',function() {

          // 如果正在加载，则退出
          if (loading)
		   {
			   return; 
		   }
			
			addItems(pageSize,pageIndex);
          
    }); */
	
	//点击店铺卡片显示详情
	$(".shop-list").on("click",".card",function(){
		var shopId=$(this).attr("data-shop-id");
		window.location.href="/myo2o/fronted/shopdetail?shopId="+shopId;
	});
	
	//名称搜索栏发生变化
	$("#search").on("input",function(){
			shopName=$(this).val();
			$(".list-div").empty();
			pageIndex=1;
			addItems(pageSize,pageIndex);
	});
	
	//点击类别后触发的事件
	$("#shoplist-search-div").on("click",".button",function(){
		if(parentId)
		{
			shopCategoryId=$(this).attr("data-category-id");
			$("#shoplist-search-div .button").removeClass("button-fill");
			$(this).addClass("button-fill");
			$(".list-div").empty();
			pageIndex=1;
			addItems(pageSize,pageIndex);
		}
		else
		{
			parentId=$(this).attr("data-category-id");
			$("#shoplist-search-div .button").removeClass("button-fill");
			$(this).addClass("button-fill");
			$(".list-div").empty();
			pageIndex=1;
			addItems(pageSize,pageIndex);
			parentId="";
		}
		
	});
	
	//点击地区搜索栏事件
	$("#area-search").on("change","",function(){
		areaId=$(this).val();
		$(".list-div").empty();
		pageIndex=1;
		addItems(pageSize,pageIndex);
	});
	
	$("#me").click(function(){
		$.openPanel("#panel-right-demo");
	});
	
})