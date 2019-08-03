$(function(){
	
	var productId=getQueryString("productId");
	var productUrl="/myo2o/fronted/listproductdetailpageinfo?productId="+productId;
	getProductInfo();
	
	function getProductInfo(){
		$.get(productUrl,
			{
				
			},
			function(data,status){
				if(data.success){
					var product=data.product;
					$("#product-img").attr("src","/myo2o"+product.imgAddr);
					$("#product-time").text(new Date(product.lastEditTime).Format("yyyy-MM-dd"));
					if(product.point)
					{
						$("#product-point").text("购买可得"+product.point+"积分");
					}
					$("#product-name").text(product.productName);
					$("#prodct-desc").text(product.productDesc);
					if(product.normalPrice&&product.promotionPrice)
					{
						$("#price").show();
						$("#normalPrice").html("<del>"+"￥"+product.normalPrice+"</del>");
						$("#promotion-price").text("￥"+product.promotionPrice);
					}
					else if(product.normalPrice&&!product.promotionPrice)
					{
						$("#price").show();
						$("#promotion-price").text("￥"+product.normalPrice);
					}
					else if(!product.normalPrice&&product.promotionPrice)
					{
						$("#promotion-price").text("￥"+product.promotionPrice);
					}
					
					var imgListHtml="";
					$.each(product.productImgList,function(index,item){
						imgListHtml+='<div><img src="/myo2o'+item.imgAddr+'" width="100%" /></div>';
					});
					$("#imgList").html(imgListHtml);
				}
				else{
					$.toast(data.errMsg);
				}
			}
		
		)
	}
	
	$("#me").click(function(){
		$.openPanel("#panel-right-demo");
	})
})