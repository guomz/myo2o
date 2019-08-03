$(function(){
	var shopAuthId=getQueryString('shopAuthId');
	var infoUrl="/myo2o/shopadmin/getshopauthmapbyid?shopAuthId="+shopAuthId;
	var modifyUrl="/myo2o/shopadmin/modifyshopauthmap";
	if(shopAuthId)
	{
		getShopAuthInfo(shopAuthId);
	}
	
	function getShopAuthInfo(shopAuthId){
		$.get(infoUrl,
		{
			shopAuthId:shopAuthId
		},
		function(data,status){
			if(data.success)
			{
				var shopAuthMap=data.shopAuthMap;
				$("#shopauth-name").val(shopAuthMap.employee.userName);
				$("#title").val(shopAuthMap.title);
			}
		}
		);
	}
	
	$("#submit").click(function(){
		var shopAuthMap={};
		var employee={};
		shopAuthMap.shopAuthId=shopAuthId;
		shopAuthMap.employee=employee;
		shopAuthMap.employee.userName=$("#shopauth-name").val();
		shopAuthMap.title=$("#title").val();
		var verifyCodeActual=$("#j_captcha").val();
		if(!verifyCodeActual)
		{
			alert("请输入验证码");
			return;
		}
		$.post(modifyUrl,
		{
			shopAuthMapStr:JSON.stringify(shopAuthMap),
			statusChange:false,
			verifyCodeActual:verifyCodeActual
		},
		function(data,status){
			if(data.success)
			{
				alert("操作成功");
			}
			else
			{
				alert("操作失败");
				alert(data.errMsg);
				window.location.reload();
			}
		}
		);
	})
})