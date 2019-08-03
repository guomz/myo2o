$(function(){
	
	var userType=getQueryString("userType");
	var bindUrl="/myo2o/local/bindlocalauth";
	
	$("#submit").click(function(){
		var userName=$("#username").val();
		var password=$("#psw").val();
		var verifyCode=$("#j_captcha").val();
		
		if(!userName)
		{
			alert("请输入用户名");
			return;
		}
		if(!password)
		{
			alert("请输入密码");
			return;
		}
		if(!verifyCode)
		{
			alert("请输入验证码");
			return;
		}
		
		$.post(bindUrl,
			{
				userName:userName,
				password:password,
				verifyCode:verifyCode
			},
			
			function(data,status){
				if(data.success)
				{
					alert("绑定成功");
					if(userType==1)
					{
						window.location.href="/myo2o/fronted/index";
					}
					else
					{
						window.location.href="/myo2o/shopadmin/shoplist";
					}
				}
				else{
					alert(data.errMsg);
					window.location.reload();
				}
			}
			
			);
	});
})