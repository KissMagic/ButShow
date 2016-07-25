function getMsgCode() {

	post_gt_validate_data = gt_captcha_obj.getValidate();
	post_gt_validate_data.phone = $("input[name='phone']").val();

	$.ajax({
		url : "VerifyGeetestServlet",
				type : "post",
				data : post_gt_validate_data,
				success : function(sdk_result) {

					if (sdk_result == 1) {
						console
								.log("the picture capthca is success,and wait for message");
					}

					console.log(sdk_result);
					if (sdk_result == -6) {
						console.log("the picture capthca is fail");
					}

					if (sdk_result == -10) {
						console.log("the form is wrong");
					}

					// TODO set the notice before message captcha
				}
		});
}