$(function(){
	var shopId=getQueryString("shopId");
	var shopInfoUrl="/myo2o/shopadmin/getshopmanagementinfo?shopId="+shopId;
	$.get(shopInfoUrl,
		{
			
		},
		
		function(data,status){
			if(data.redirect==true)
			{
				window.location.href=data.url;
			}
			else
			{
				$("#shop-info").attr("href","/myo2o/shopadmin/shopoperation?shopId="+data.shopId);
				$("#product-category").attr("href","/myo2o/shopadmin/productcategorymanage");
				$("#product").attr("href","/myo2o/shopadmin/productmanage");
				$("#shop-auth").attr("href","/myo2o/shopadmin/shopauthmanage");
			}
		}
	
	);
})