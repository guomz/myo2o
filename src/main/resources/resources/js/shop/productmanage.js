$(function(){
	var listUrl="/myo2o/shopadmin/getproductlistbyshop?pageIndex=0&pageSize=99";
	getList();
	//获取所有商品
	function getList(){
		$.get(listUrl,
			{
				
			},
			function(data,status){
				var productList=data.productList;
				var tempHtml="";
				$.each(productList,function(index,item){
					var textOp="下架";
					var opStatus=0;
					if(item.enableStatus==0)
					{
						opStatus=1
						textOp="上架";
					}
					else
					{
						opStatus=0;
					}
					tempHtml+="<div class='row row-product'>"
								+"<div class='col-33'>"
								+item.productName
								+"</div>"
								+"<div class='col-20'>"
								+item.point
								+"</div>"
								+"<div class='col-40'>"
								+"<a href='/myo2o/shopadmin/productoperation?productId="+item.productId+"' class='edit' id='"+item.productId+"' status='"+item.enableStatus+"'>编辑</a>"
								+"<a href='#' class='status' id='"+item.productId+"' status='"+opStatus+"'>"+textOp+"</a>"
								+"<a href='/myo2o/shopadmin/productdetail?productId="+item.productId+"' class='preview' id='"+item.productId+"' status='"+item.enableStatus+"'>预览</a>"
								+"</div>"
								+"</div>";
				});
				$(".product-wrap").html(tempHtml);
				$(".status").click(function(){
					var productId=$(this).attr("id");
					var enableStatus=$(this).attr("status");
					var product={};
					product.productId=productId;
					product.enableStatus=enableStatus;
					var choose=confirm("确认操作？");
					if(choose)
					{
						$.ajax({
							url:"/myo2o/shopadmin/modifyproduct",
							type:"POST",
							data:{
								productStr:JSON.stringify(product),
								statusChange:true
							},
							dataType:"json",
							success:function(data){
								if(data.success)
								{
									alert("操作成功");
									getList();
								}
								else{
									alert("操作失败");
									alert(data.errMsg)
								}
							}
							
						});
					}
					
				}); 
			}
		);
	}
	/* $(".product-wrap").on("click",".status",function(){
		var productId=$(this).attr("id");
		var enableStatus=$(this).attr("status");
		var product={};
		product.productId=productId;
		product.enableStatus=enableStatus;
		var choose=confirm("确认操作？");
		if(choose)
		{
			$.ajax({
				url:"/o2o/shopadmin/modifyproduct",
				type:"POST",
				data:{
					productStr:JSON.stringify(product),
					statusChange:true
				},
				dataType:"json",
				success:function(data){
					if(data.success)
					{
						alert("操作成功");
						getList();
					}
					else{
						alert("操作失败");
					}
				}
				
			});
		}
		
	}); */
	//更改上下架状态的按钮
	 
})