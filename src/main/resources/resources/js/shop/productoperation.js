$(function(){
	
	var productId=getQueryString("productId");
	var isEdit=productId?true:false;
	//获取商品信息
	var infoUrl="/myo2o/shopadmin/getproductbyid?productId="+productId;
	//获取商品类别地址
	var categoryUrl="/myo2o/shopadmin/getproductcategorylist";
	//编辑商品地址与增加商品地址
	var productPostUrl="/myo2o/shopadmin/modifyproduct";
	if(!isEdit)
	{
		productPostUrl="/myo2o/shopadmin/addproduct";
		getCategory();
	}
	else
	{
		getInfo(productId);
	}
	
	//获得商品初始化信息，初始化类别下拉栏
	function getInfo(productId){
		$.get(infoUrl,
			{
				
			},
			function(data,status){
				if(data.success)
				{
					var product=data.product;
					$("#product-name").val(product.productName);
					$("#product-desc").val(product.productDesc);
					$("#priority").val(product.priority);
					$("#normal-price").val(product.normalPrice);
					$("#promotion-price").val(product.promotionPrice);
					var tempHtml="";
					$.each(data.productCategoryList,function(index,item){
						tempHtml=tempHtml+"<option id=" + item.productCategoryId + ">" + item.productCategoryName + "</option>";
					});
					$("#product-category").html(tempHtml);
					if(product.productCategory&&product.productCategory.productCategoryId)
					{
						$("#product-category option[id=" + product.productCategory.productCategoryId + "]").attr("selected", "selected");
					}
					
				}
				
			}
		);
	}
	
	//初始化商品类别下拉菜单
	function getCategory(){
		$.get(categoryUrl,
			{
				
			},
			function(data,status){
				if(data.success)
				{
					var productCategoryList=data.productCategoryList;
					var tempHtml="";
					$.each(productCategoryList,function(index,item){
						tempHtml=tempHtml+"<option id=" + item.productCategoryId + ">" + item.productCategoryName + "</option>";
					});
					$("#product-category").html(tempHtml);
				}
			}
		);
	}
	
	//批量上传详情图（最多6个）
	$(".detail-img-div").on("change",".detail-img:last-child",function(){
		if($(".detail-img").length<6)
		{
			$("#detail-img").append("<input type='file' class='detail-img'>");
		}
	});
	
	$("#submit").click(function(){
		
		var product={};
		product.productName=$("#product-name").val();
		product.productDesc=$("#product-desc").val();
		product.priority=$("#priority").val();
		product.normalPrice=$("#normal-price").val();
		product.promotionPrice=$("#promotion-price").val();
		product.point=$("#point").val();
		product.productCategory={
			productCategoryId:$("#product-category").find("option").filter(function() {
				return this.selected;
			}).attr("id")
		};
		product.productId=productId;
		//添加缩略图与详情图
		var formData=new FormData();
		formData.append("productStr",JSON.stringify(product));
		var productImg=$("#small-img")[0].files[0];
		formData.append("productImg",productImg);
		$.each($(".detail-img"),function(index,item){
			if($(".detail-img")[index].files.length>0)
			{
				formData.append("productImgList",$(".detail-img")[index].files[0]);
			}
		});
		var verifyCodeActual = $("#j_captcha").val();
		if (!verifyCodeActual) {
			alert("请输入验证码");
			return;
		}
		formData.append("verifyCodeActual",verifyCodeActual);
		formData.append("statusChange",false);
		
		$.ajax({
			url:productPostUrl,
			type:"POST",
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success)
				{
					alert("提交成功");
				}
				else
				{
					alert("提交失败");
					alert(data.errMsg);
					window.location.reload();
				}
				
			}
		});
		
	});
})