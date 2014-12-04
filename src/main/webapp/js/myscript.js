function cleanErrors(needToRemoveFieldValues) {
	// Remove Error <div>
	$(".formFieldError").each(function() {
		$(this).remove();
	});

	// Clear value from form controls
	if (needToRemoveFieldValues) {
		$(".form-control").each(function() {
			$(this).val('');
		});
	}
	
	// Clear value from checkbox controls
	if (needToRemoveFieldValues) {
		$(".dummyChkBoxClass").each(function() {
			$(this).removeAttr('checked');
		});
	}
}

function dateTimePicker() {
	$('.dateTimePicker').datetimepicker({
        pick12HourFormat: false,
    });
}

function hideDealLocationFields() {
	$(".adderssDummyClass").each(function(){
    	$(this).hide();
    });
	
	$(".urlDummyClass").hide();
}

function showDealLocation(chkbox) {
	if($('input:radio[name=dealType]:checked').val() == "localDeal") { 
		$(".urlDummyClass").hide();
        $(".adderssDummyClass").each(function(){
        	$(this).show();
        });
    } else if($('input:radio[name=dealType]:checked').val() == "onlineDeal") {
    	$(".adderssDummyClass").each(function(){
        	$(this).hide();
        });
    	$(".urlDummyClass").show();
    }
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
				if (res === "ERROR") {
					var errorMsg = "Some Error Occured";
					var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
					$form.before(err);
				} else if (res === "SUCCESS") {
					$.magnificPopup.instance.close();
					if(formID == "forgotPassForm") {
						alert("forgotPassForm");
					} else if(formID == "loginForm") {
						$("#anchorSignIn").hide();
						$("#anchorSignUp").hide();
						$("#anchorWantDrop").show();
						$("#anchorPostDrop").show();
						$("#anchorSignOut").show();
					} else if(formID == "registerationForm") {
						$("#anchorSignIn").hide();
						$("#anchorSignUp").hide();
						$("#anchorWantDrop").show();
						$("#anchorPostDrop").show();
						$("#anchorSignOut").show();
					} else if(formID == "dealPostForm") {
						
					} else if(formID == "dealWantedForm") {
						
					} else if(formID == "addressForm") {
						alert("success");
						//window.location.href="./showAddressBook.htm";
					}
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
				var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
				$form.before(err);
			});
	return false;
}


/*function forgotPassword() {
	cleanErrors(false);
	var $form = $("#forgotPassForm");
	jQuery.ajax({
		url : $form.attr("action"),
		context : document.body,
		type : 'post',
		data : $form.serialize()
	}).done(
			function(res) {
				if (res === "ERROR") {
					var errorMsg = "Some Error Occured";
					var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
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
				var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
				$form.before(err);
			});
	return false;
}

function login() {
	cleanErrors(false);
	var $form = $("#loginForm");
	jQuery.ajax({
		url : $form.attr("action"),
		context : document.body,
		type : 'post',
		data : $form.serialize()
	}).done(
			function(res) {
				if (res === "ERROR") {
					var errorMsg = "Some Error Occured";
					var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
					$form.before(err);
				} else if (res === "SUCCESS") {
					$.magnificPopup.instance.close();
					$("#anchorSignIn").hide();
					$("#anchorSignUp").hide();
					$("#anchorWantDrop").show();
					$("#anchorPostDrop").show();
					$("#anchorSignOut").show();
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
				var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
				$form.before(err);
			});
	return false;
}

function registerUser() {
	cleanErrors(false);
	var $form = $("#registerationForm");
	jQuery.ajax({
		url : $form.attr("action"),
		context : document.body,
		type : 'post',
		data : $form.serialize()
	}).done(
			function(res) {
				if (res === "ERROR") {
					var errorMsg = "Some Error Occured";
					var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
					$form.before(err);
				} else if (res === "SUCCESS") {
					$.magnificPopup.instance.close();
					$("#anchorSignIn").hide();
					$("#anchorSignUp").hide();
					$("#anchorWantDrop").show();
					$("#anchorPostDrop").show();
					$("#anchorSignOut").show();
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
				var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
				$form.before(err);
			});
	return false;
}

function postDealWanted() {
	cleanErrors(false);
	var $form = $("#dealWantedForm");
	alert("inside1");
	jQuery.ajax({
		url : $form.attr("action"),
		context : document.body,
		type : 'post',
		data : $form.serialize()
	}).done(
			function(res) {
				if (res === "ERROR") {
					var errorMsg = "Some Error Occured";
					var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
					$form.before(err);
				} else if (res === "SUCCESS") {
					$.magnificPopup.instance.close();
					$("#anchorSignIn").hide();
					$("#anchorSignUp").hide();
					$("#anchorWantDrop").show();
					$("#anchorPostDrop").show();
					$("#anchorSignOut").show();
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
				alert("inside2");
				var errorMsg = "Some Error Occured";
				var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
				$form.before(err);
			});
	return false;
}

function postDealPost() {
	cleanErrors(false);
	var $form = $("#dealPostForm");
	jQuery.ajax({
		url : $form.attr("action"),
		context : document.body,
		type : 'post',
		data : $form.serialize()
	}).done(
			function(res) {
				if (res === "ERROR") {
					var errorMsg = "Some Error Occured";
					var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
					$form.before(err);
				} else if (res === "SUCCESS") {
					$.magnificPopup.instance.close();
					$("#anchorSignIn").hide();
					$("#anchorSignUp").hide();
					$("#anchorWantDrop").show();
					$("#anchorPostDrop").show();
					$("#anchorSignOut").show();
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
				alert("inside2");
				var errorMsg = "Some Error Occured";
				var err = "<div class=\"formFieldError\" id=\"errorSpan\">" + errorMsg + "</div>";
				$form.before(err);
			});
	return false;
}*/