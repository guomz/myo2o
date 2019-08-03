$(function(){
	var pageSize=999;
	var pageIndex=1;
	var listUrl="/myo2o/fronted/listproductsbyshop";
	var shopId=getQueryString("shopId");
	var productCategoryId="";
	var productName="";
	var searchDivUrl="/myo2o/fronted/listshopdetailpageinfo?shopId="+shopId;
	
	getSearchDivData();
	addItems(pageSize,pageIndex);
	
	function getSearchDivData(){
		$.get(searchDivUrl,
			{
				
			},
			function(data,status){
				if(data.success)
				{
					var shop=data.shop;
					var productCategoryList=data.productCategoryList;
					$("#shop-update-time").html(new Date(shop.lastEditTime).Format("yyyy-MM-dd"));
					$("#shop-cover-pic").attr("src","/myo2o"+shop.shopImg);
					$("#shop-name").html(shop.shopName);
					$("#shop-desc").html(shop.shopDesc);
					$("#shop-phone").html(shop.phone);
					$("#shop-addr").html(shop.shopAddr);
					var tempHtml="";
					$.each(productCategoryList,function(index,item){
						
						tempHtml=tempHtml+"<a href='#' class='button' data-category-id='"+item.productCategoryId+"'>"+
										item.productCategoryName+
										"</a>";
					});
					$("#shopdetail-button-div").html(tempHtml);
				}
				else
				{
					$.toast(data.errMsg);
				}
			}
		);
	}
	
	function addItems(pageSize,pageIndex){
		var url=listUrl+"?pageIndex="+pageIndex+"&pageSize="+pageSize+"&shopId="+shopId
						+"&productCategoryId="+productCategoryId+"&productName="+productName;
		
		$.get(url,
			{
				
			},
			function(data,status){
				if(data.success)
				{
					var productList=data.productList;
					var count=data.count;
					var tempHtml="";
					$.each(productList,function(index,item){
						
						tempHtml=tempHtml+"<div class='card' data-product-id='"+item.productId+"'>"
										+"<div class='card-header'>"+item.productName
										+"</div>"
										+"<div class='card-content'>"
										+"<div class='list-block media-list'>"
										+"<ul>"
										+"<li class='item-content'>"
										+"<div class='item-media'>"
										+"<img src='/myo2o"+item.imgAddr+"' width='44'>"
										+"</div>"
										+"<div class='item-inner'>"
										+"<div class='item-subtitle'>"+item.productDesc
										+"</div>"
										+"</div>"
										+"</li>"
										+"</ul>"
										+"</div>"
										+"</div>"
										+"<div class='card-footer'>"
										+"<p class='color-gray'>"
										+new Date(item.lastEditTime).Format("yyyy-MM-dd")
										+"更新</p>"
										+"<span>点击查看</span>"
										+"</div>"
										+"</div>";
					});
					
					$(".list-div").append(tempHtml);
				}
				else
				{
					$.toast(data.errMsg);
				}
			}
		);
	}
	
	//点击商品卡片显示详情
	$(".product-list").on("click",".card",function(){
		var productId=$(this).attr("data-product-id");
		window.location.href="/myo2o/fronted/productdetail?productId="+productId;
	});
	
	//名称搜索栏发生变化
	$("#search").on("input",function(){
			productName=$(this).val();
			$(".list-div").empty();
			pageIndex=1;
			addItems(pageSize,pageIndex);
	});
	
	//点击类别后触发的事件
	$("#shopdetail-button-div").on("click",".button",function(){
		
			productCategoryId=$(this).attr("data-category-id");
			$("#shopdetail-button-div .button").removeClass("button-fill");
			$(this).addClass("button-fill");
			$(".list-div").empty();
			pageIndex=1;
			addItems(pageSize,pageIndex);
		
		
	});
	
	$("#me").click(function(){
		$.openPanel("#panel-right-demo");
	});
})