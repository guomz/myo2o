/**
 * 
 */
$(function() {
	//得到请求地址
	var initurl = "/myo2o/shopadmin/getshopinitinfo";
	var registerurl = "/myo2o/shopadmin/registershop";
	var shopId = getQueryString("shopId");
	//根据是否由shopId来决定时添加店铺还是修改店铺
	var isEdit = shopId ? true : false;
	var shopinfourl = "/myo2o/shopadmin/getshopbyid?shopId=" + shopId;
	var editshopurl = "/myo2o/shopadmin/modifyshop";
	if (isEdit) {
		getShopInfo(shopId);
	} else {
		getShopInitInfo();
	}

	//修改店铺信息之前获取店铺信息
	function getShopInfo(shopId) {
		$.get(shopinfourl, {

			},
			function(data, status) {
				if (data.success) {
					var shop = data.shop
					$("#shop-name").val(shop.shopName);
					$("#shop-addr").val(shop.shopAddr);
					$("#shop-phone").val(shop.phone);
					$("#shop-desc").val(shop.shopDesc);
					var categoryStr = "<option id=" + shop.shopCategory.shopCategoryId + ">" + shop.shopCategory.shopCategoryName +
						"</option>";
					var areaStr = "";
					$.each(data.areaList, function(index, item) {
						areaStr = areaStr + "<option id=" + item.areaId + ">" + item.areaName + "</option>";
					});
					$("#shop-category").html(categoryStr);
					$("#shop-category").attr("disabled", "disabled");
					$("#shop-area").html(areaStr);
					$("#shop-area option[id=" + shop.area.areaId + "]").attr("selected", "selected");
				}
			}
		);
	}

	//注册店铺之前获取区域和类别信息
	function getShopInitInfo() {
		$.get(initurl, {

			},
			function(data, status) {
				if (data.success) {
					var categoryStr = "";
					var areaStr = "";
					$.each(data.shopCategoryList, function(index, item) {
						categoryStr = categoryStr + "<option id=" + item.shopCategoryId + ">" + item.shopCategoryName + "</option>";
					});
					$.each(data.areaList, function(index, item) {
						areaStr = areaStr + "<option id=" + item.areaId + ">" + item.areaName + "</option>";
					});
					$("#shop-category").html(categoryStr);
					$("#shop-area").html(areaStr);
				}
			}
		);
	}

	//编辑提交按钮的方法
	$("#submit").click(function() {
		var shop = new Object();
		if (isEdit) {
			//若是修改店铺信息则需要传递店铺id
			shop.shopId = shopId;
		}
		shop.shopName = $("#shop-name").val();
		if (!shop.shopName) {
			alert("请输入店铺名称");
			return;
		}
		shop.shopDesc = $("#shop-desc").val();
		shop.phone = $("#shop-phone").val();
		shop.shopAddr = $("#shop-addr").val();
		shop.shopCategory = {
			shopCategoryId: $("#shop-category").find("option").filter(function() {
				return this.selected;
			}).attr("id")
		};
		shop.area = {
			areaId: $("#shop-area").find("option").filter(function() {
				return this.selected;
			}).attr("id")
		};
		//alert(shop.area);
		var shopImg = $("#shop-img")[0].files[0];
		if (!isEdit) {
			if (!shopImg) {
				alert("请上传图片");
				return;
			}
		}
		var verifyCodeActual = $("#j_captcha").val();
		if (!verifyCodeActual) {
			alert("请输入验证码");
			return;
		} 
		var formData = new FormData();
		formData.append("shopImg", shopImg);
		formData.append("shopStr", JSON.stringify(shop));
		formData.append("verifyCodeActual", verifyCodeActual);
		$.ajax({
			url: (isEdit ? editshopurl : registerurl),
			data: formData,
			type: "POST",
			contentType: false,
			processData: false,
			cache: false,
			success: function(data) {
				if (data.success) {
					alert("提交成功")
				} else {
					alert("提交失败");
					alert(data.errMsg);
					window.location.reload();
				}
				//$("#captcha_img").click();
			}
		});
		/* $.post(registerurl, {
				shopStr: JSON.stringify(shop),
				shopImg: shopImg
			},
			function(data, success) {
				alert(data.success);
				alert(data.errMsg);
			}
		); */

	})

})
