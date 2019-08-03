$(function() {
	$
			.get(
					"/myo2o/fronted/listmainpageinfo",
					{

					},
					function(data, status) {

						if (data.success) {
							var shopCategoryList = data.shopCategoryList;
							var headLineList = data.headLineList;
							var tempHtml = "";
							// 加载头条
							$.each(headLineList, function(index, item) {

								tempHtml = tempHtml
										+ "<div class='swiper-slide img-wrap'>"
										+ "<a href='" + item.lineLink + "'>"
										+ "<img class='banner-img' src='/myo2o"
										+ item.lineImg + "' alt='"
										+ item.lineName + "'>" + "</a></div>";

							});

							$(".swiper-wrapper").html(tempHtml);
							// 设置轮播图轮播时间间隔
							$(".swiper-container").swiper({
								autoplay : 3000,
								autoplayDisableOnInteraction : false
							});

							tempHtml = "";
							// 加载一级类别
							$
									.each(
											shopCategoryList,
											function(index, item) {
												tempHtml += "<div class='col-50 shop-classify' data-category='"
														+ item.shopCategoryId
														+ "'>"
														+ "<div class='word'>"
														+ "<p class='shop-title'>"
														+ item.shopCategoryName
														+ "</p>"
														+ "<p class='shop-desc'>"
														+ item.shopCategoryDesc
														+ "</p>"
														+ "</div>"
														+ "<div class='shop-classify-img-wrap'>"
														+ "<img class='shop-img' src='/myo2o"
														+ item.shopCategoryImg
														+ "'>"
														+ "</div>"
														+ "</div>";

											});
							$(".row").html(tempHtml);
							// 点击后前往类别列表
							$(".row")
									.on(
											"click",
											".shop-classify",
											function() {
												var shopCategoryId = $(this)
														.attr("data-category");
												window.location.href = "/myo2o/fronted/shoplist?parentId="
														+ shopCategoryId;
											});

							
						} else {
							alert(data.errMsg);
						}
					});
					// 侧边栏从右边打开
					$("#me").click(function() {
						$.openPanel("#panel-right-demo");
					});
})
