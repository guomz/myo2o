$(function(){
	var listUrl="/myo2o/shopadmin/listuserproductmapbyshop?pageIndex=1&pageSize=999&productName="+productName;
	var productName="";
	getList();
	
	function getList(){
		$.get(listUrl,
		{
			
		},
		function(data,status)
		{
			if(data.success)
			{
				var userProductMapList=data.userProductMapList;
				var tempHtml="";
				$.each(userProductMapList,function(index,item){
					tempHtml=tempHtml+"<div class='row row-productbuycheck'>"
									+"<div class='col-20'>"+item.product.productName
									+"</div>"
									+"<div class='col-40 productbuycheck-time'>"
									+new Date(item.createTime).Format("yyyy-MM-dd")
									+"</div>"+"<div class='col-25'>"
									+item.user.userName+"</div>"
									+"<div class='col-15'>"+item.point+"</div>"
									+"</div>";
				});
				$(".productbuycheck-wrap").html(tempHtml);
			}
		}
		);
	}
	
	function getProductSellDailyList(){
		var listProductSellDailyUrl="/myo2o/shopadmin/listproductselldailybyshop";
		$.get(listProductSellDailyUrl,
		{
			
		},
		function(data,status){
			if(data.success)
			{
				var myChart = echarts.init(document.getElementById('chart'));
				var option=generateStaticEchartPart();
				option.legend.data=data.legengData;
				option.xAxis=data.xAxis;
				option.series=data.series;
				myChart.setOption(option);
			}
		}
		);
	}
	
	function generateStaticEchartPart()
	{
		var option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:['避孕套','麻古','伟哥']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : ['周一','周二','周三','周四','周五','周六','周日']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'避孕套',
		            type:'bar',
		            data:[120, 132, 101, 134, 290, 230, 220]
		        },
		        {
		            name:'麻古',
		            type:'bar',
		            data:[60, 72, 71, 74, 190, 130, 110]
		        },
		        {
		            name:'伟哥',
		            type:'bar',
		            data:[62, 82, 91, 84, 109, 110, 120]
		        }
		    ]
		};
		
		//myChart.setOption(option);
	}
	
	$("#search").on("change",function(e){
		productName=e.target.value;
		$(".productbuycheck-wrap").empty();
		getList();
	})
	
	

    
})