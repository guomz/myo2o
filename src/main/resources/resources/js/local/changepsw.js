$(function(){
	var userType=getQueryString("userType");
	var changeUrl="/myo2o/local/changelocalpassword"
	$("#submit").click(function(){
		var userName=$("#userName").val();
		var password=$("#password").val();
		var newPassword=$("#newPassword").val();
		var ensurePassword=$("#ensurePassword").val();
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
		if(!newPassword)
		{
			alert("请输入新密码");
			return;
		}
		if(newPassword!=ensurePassword)
		{
			alert("两次新密码不一致");
			return;
		}
		if(!verifyCode)
		{
			alert("请输入验证码");
			return;
		}
		
		$.post(changeUrl,
			{
				userName:userName,
				password:password,
				newPassword:newPassword,
				verifyCode:verifyCode
			},
			function(data,status){
				if(data.success)
				{
					alert("修改密码成功");
					if(userType==1)
					{
						window.location.href="/myo2o/fronted/index";
					}
					else
					{
						window.location.href="/myo2o/shopadmin/shoplist";
					}
				}
				else
				{
					alert(data.errMsg);
					window.location.reload();
				}
			}
		);
		
	});
});