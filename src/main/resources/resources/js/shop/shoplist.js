$(function(){
	getList();
	//获取店铺列表
	function getList(){
		$.get("/myo2o/shopadmin/getshoplist",
			{
				
			},
			function(data,status){
				handleUser(data.user);
				handleList(data.shopList);
			}
		);
	}
	
	//显示当前用户
	function handleUser(user){
		$("#user-name").html(user.userName);
	}
	
	//显示列表信息
	function handleList(shopList){
		var shopInfo="";
		$.each(shopList,function(index,item){
			shopInfo=shopInfo+"<div class='row row-shop'><div class='col-40'>"
							+item.shopName+"</div><div class='col-40'>"
							+shopStatus(item.enableStatus)
							+"</div><div class='col-20'>"
							+goShop(item.enableStatus,item.shopId)+"</div></div>";
		});
		$(".shop-wrap").html(shopInfo);
	}
	
	//显示店铺状态
	function shopStatus(enableStatus){
		if(enableStatus==0)
		{
			return "审核中";
		}
		else if(enableStatus==-1)
		{
			return "店铺非法";
		}
		else if(enableStatus==1)
		{
			return "审核通过";
		}
	}
	
	//判定是否可以修改店铺
	function goShop(enableStatus,shopId){
		if(enableStatus==1)
		{
			return "<a href='/myo2o/shopadmin/shopmanage?shopId="+shopId+"'>进入</a>";
		}
		else
		{
			return "";
		}
	}
})