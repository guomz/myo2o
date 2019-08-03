$(function(){
	var listUrl="/myo2o/shopadmin/listshopauthmapsbyshop?pageIndex=1&pageSize=999"
	var modifyUrl="/myo2o/shopadmin/modifyshopauthmap"
	getList();
	
	function getList(){
		$.get(listUrl,
		{
			
		},
		function(data,status){
			if(data.success)
			{
				var tempHtml="";
				var shopAuthMapList=data.shopAuthMapList;
				$.each(shopAuthMapList,function(index,item){
					var textOp="删除";
					var status=0;
					if(item.enableStatus==0)
					{
						textOp="恢复";
						status=1;
					}
					tempHtml=tempHtml+"<div class='row row-shopauth'>"
									+"<div class='col-40'>"+item.employee.userName
									+"</div>";
					//非店家时加入编辑按钮
					if(item.titleFlag!=0)
					{
						tempHtml=tempHtml+"<div class='col-20'>"+item.title
										+"</div>"+"<div class='col-40'>"
										+"<a href='/myo2o/shopadmin/shopauthedit?shopAuthId="+item.shopAuthId+"' class='edit' data-employee-id='"+item.employee.userId+"' data-auth-id='"+item.shopAuthId+"'>编辑</a>"
										+"<a href='#' class='status' data-auth-id='"+item.shopAuthId+"' data-status='"+status+"'>"+textOp+"</a>"
										+"</div>";
					}
					else
					{
						tempHtml=tempHtml+"<div class='col-20'>"+item.title
										+"</div>"+"<div class='col-40'>"
										+"<span>不可操作</span>"+"</div>";
					}
					tempHtml=tempHtml+"</div>";
				});
				$(".shopauth-wrap").html(tempHtml);
			}
		}
		);
	}
	
	//点击恢复或删除时的事件
	$(".shopauth-wrap").on("click","a",function(e){
		//console.log('aaa')
		var target=$(e.currentTarget);
		if(target.hasClass('status'))
		{
			var shopAuthMap={};
			var enableStatus=e.currentTarget.dataset.status;
			var shopAuthId=e.currentTarget.dataset.authId;
			shopAuthMap.enableStatus=enableStatus;
			shopAuthMap.shopAuthId=shopAuthId;
			var choose=confirm("确认删除？");
			if(choose)
			{
				$.post(modifyUrl,
				{
					shopAuthMapStr:JSON.stringify(shopAuthMap),
					statusChange:true
				},
				function(data,status){
					if(data.success)
					{
						alert("操作成功");
						getList();
					}
					else
					{
						alert("操作失败");
						alert(data.errMsg);
					}
				}
				);
			}
		}
		
		
	});
});