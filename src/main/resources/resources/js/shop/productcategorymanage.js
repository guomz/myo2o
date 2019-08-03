$(function(){
	var listUrl="/myo2o/shopadmin/getproductcategorylist";
	getList();
	//初始化获取所有类别
	function getList(){
		$.get(listUrl,
		
			{
				
			},
			
			function(data,status){
				if(data.success==true)
				{
					var tempHtml="";
					$.each(data.productCategoryList,function(index,item){
						tempHtml=tempHtml+"<div class='row row-product-category now'>"
										+"<div class='col-33 product-category-name'>"
										+item.productCategoryName
										+"</div>"
										+"<div class='col-33'>"
										+item.priority
										+"</div>"
										+"<div class='col-33'><a href='#' class='button delete' id='"
										+item.productCategoryId
										+"'>删除</a></div>"
										+"</div>";
					});
					
					$(".category-wrap").html(tempHtml);
				}
			}
		);
	}
	
	//新增按钮的编辑
	$("#new").click(function(){
		var tempHtml="<div class='row row-product-category temp'>"+
					"<div class='col-33'><input class='category-input category' type='text' placeholder='分类名'></div>"+
					"<div class='col-33'><input class='category-input priority' type='number' placeholder='优先级'></div>"+
					"<div class='col-33'><a href='#' class='button delete'>删除</a></div>"+
					"</div>";
		$(".category-wrap").append(tempHtml);
	});
	
	//点击提交时会把类别拼接为数组，通过json格式传递到后端
	$("#submit").click(function(){
		var tempArr=$(".temp");
		var productCategoryList=[];
		$.each(tempArr,function(index,item){
			var tempObj={};
			tempObj.productCategoryName=$(item).find(".category").val();
			tempObj.priority=$(item).find(".priority").val();
			if(tempObj.productCategoryName&&tempObj.priority)
			{
				productCategoryList.push(tempObj);
			}
		});
		$.ajax({
			url:"/myo2o/shopadmin/addproductcategorys",
			type:"POST",
			data:JSON.stringify(productCategoryList),
			contentType:"application/json",
			success:function(data){
				if(data.success)
				{
					alert("提交成功");
					//成功后重新渲染
					getList();
				}
				else
				{
					alert(data.errMsg);
				}
			}
			
		});
	});
	//编辑时点击删除按钮
	$(".category-wrap").on("click",".row-product-category.temp .delete",function(){
		$(this).parent().parent().remove();
	});
	//编辑保存后点击删除按钮
	$(".category-wrap").on("click",".row-product-category.now .delete",function(){
		var choose=confirm("确定删除？");
		if(choose)
		{
			var productCategoryId=$(this).attr("id");
			$.post("/myo2o/shopadmin/removeproductcategory",{
				productCategoryId:productCategoryId
			},
			function(data){
				if(data.success)
				{
					alert("删除成功");
					getList();
				}
				else
				{
					alert(data.errMsg);
				}
			}
			);
		}
		
	});
})