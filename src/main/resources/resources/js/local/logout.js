$(function(){
	
	var logoutUrl="/myo2o/local/logout";
	$("#log-out").click(function(){
		
		$.get(logoutUrl,
			{
				
			},
			function(data,status){
				if(data.success)
				{
					alert("登出成功");
					var userType=$("#log-out").attr("userType");
					window.location.href="/myo2o/local/login?userType="+userType;
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