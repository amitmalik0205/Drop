function cleanErrors() {
	$(".formFieldError").each(function() {
		$(this).remove();
	});
}

function forgotPassword() {
	cleanErrors();
	var $form = $("#forgotPassForm");
	jQuery.ajax({
		url : $form.attr("action"),
		context : document.body,
		type : 'post',
		data : $form.serialize()
	}).done(
			function(res) {
				alert(res);
				if (res === "ERROR") {
					var errorMsg = "Some Error Occured";
					var err = "<div class=\"formFieldError\" id=\"" + i
							+ "errorSpan\">" + errorMsg + "</div>";
					$form.before(err);
				} else if (res === "SUCCESS") {
					$.magnificPopup.instance.close();
				} else {
					var items = res.split(',');
					for ( var i = 0; i < items.length; i++) {
						var id;
						if (i == 0) {
							var err = "<div class=\"formFieldError\" id=\"" + i
									+ "errorSpan\">" + items[i] + "</div>";
							$form.before(err);
							id = i + "errorSpan";
						} else {
							var err = "<div class=\"formFieldError\" id=\"" + i
									+ "errorSpan\">" + items[i] + "</div>";
							jQuery("#" + id).after(err);
							id = i + "errorSpan";
						}
					}
				}
			}).fail(
			function(data) {
				var errorMsg = "Some Error Occured";
				var err = "<div class=\"formFieldError\" id=\"" + i
						+ "errorSpan\">" + errorMsg + "</div>";
				$form.before(err);
			});
	return false;
}

function login() {
	cleanErrors();
	var $form = $("#loginForm");
	jQuery.ajax({
		url : $form.attr("action"),
		context : document.body,
		type : 'post',
		data : $form.serialize()
	}).done(
			function(res) {
				alert(res);
				if (res === "ERROR") {
					var errorMsg = "Some Error Occured";
					var err = "<div class=\"formFieldError\" id=\"" + i
							+ "errorSpan\">" + errorMsg + "</div>";
					$form.before(err);
				} else if (res === "SUCCESS") {
					$.magnificPopup.instance.close();
				} else {
					var items = res.split(',');
					for ( var i = 0; i < items.length; i++) {
						var id;
						if (i == 0) {
							var err = "<div class=\"formFieldError\" id=\"" + i
									+ "errorSpan\">" + items[i] + "</div>";
							$form.before(err);
							id = i + "errorSpan";
						} else {
							var err = "<div class=\"formFieldError\" id=\"" + i
									+ "errorSpan\">" + items[i] + "</div>";
							jQuery("#" + id).after(err);
							id = i + "errorSpan";
						}
					}
				}
			}).fail(
			function(data) {
				var errorMsg = "Some Error Occured";
				var err = "<div class=\"formFieldError\" id=\"" + i
						+ "errorSpan\">" + errorMsg + "</div>";
				$form.before(err);
			});
	return false;
}

function registerUser() {
	cleanErrors();
	var $form = $("#registerationForm");
	jQuery.ajax({
		url : $form.attr("action"),
		context : document.body,
		type : 'post',
		data : $form.serialize()
	}).done(
			function(res) {
				alert(res);
				if (res === "ERROR") {
					var errorMsg = "Some Error Occured";
					var err = "<div class=\"formFieldError\" id=\"" + i
							+ "errorSpan\">" + errorMsg + "</div>";
					$form.before(err);
				} else if (res === "SUCCESS") {
					$.magnificPopup.instance.close();
				} else {
					var items = res.split(',');
					for ( var i = 0; i < items.length; i++) {
						var id;
						if (i == 0) {
							var err = "<div class=\"formFieldError\" id=\"" + i
									+ "errorSpan\">" + items[i] + "</div>";
							$form.before(err);
							id = i + "errorSpan";
						} else {
							var err = "<div class=\"formFieldError\" id=\"" + i
									+ "errorSpan\">" + items[i] + "</div>";
							jQuery("#" + id).after(err);
							id = i + "errorSpan";
						}
					}
				}
			}).fail(
			function(data) {
				var errorMsg = "Some Error Occured";
				var err = "<div class=\"formFieldError\" id=\"" + i
						+ "errorSpan\">" + errorMsg + "</div>";
				$form.before(err);
			});
	return false;
}

function submitForm(formID) {
	cleanErrors();
	var $form = $("#" + formID);
	jQuery.ajax({
		url : $form.attr("action"),
		context : document.body,
		type : 'post',
		data : $form.serialize()
	}).done(
			function(res) {
				alert(res);
				if (res === "ERROR") {
					var errorMsg = "Some Error Occured";
					var err = "<div class=\"formFieldError\" id=\"" + i
							+ "errorSpan\">" + errorMsg + "</div>";
					$form.before(err);
				} else if (res === "SUCCESS") {
					$.magnificPopup.instance.close();
				} else {
					var items = res.split(',');
					for ( var i = 0; i < items.length; i++) {
						var id;
						if (i == 0) {
							var err = "<div class=\"formFieldError\" id=\"" + i
									+ "errorSpan\">" + items[i] + "</div>";
							$form.before(err);
							id = i + "errorSpan";
						} else {
							var err = "<div class=\"formFieldError\" id=\"" + i
									+ "errorSpan\">" + items[i] + "</div>";
							jQuery("#" + id).after(err);
							id = i + "errorSpan";
						}
					}
				}
			}).fail(
			function(data) {
				var errorMsg = "Some Error Occured";
				var err = "<div class=\"formFieldError\" id=\"" + i
						+ "errorSpan\">" + errorMsg + "</div>";
				$form.before(err);
			});
	return false;
}