function cleanErrors(needToRemoveFieldValues) {
	// Remove Error <div>
	$(".formFieldError").each(function() {
		$(this).remove();
	});

	// Clear value from form controls
	if (needToRemoveFieldValues) {
		$(".dummy-form-control").each(function() {
			$(this).val('');
		});
		
		//Select the first child in case of select box
		$("select.dummy-form-control").prop("selectedIndex",0);
	}
	
	// Clear value from checkbox controls
	if (needToRemoveFieldValues) {
		$(".dummyChkBoxClass").each(function() {
			$(this).removeAttr('checked');
		});
	}
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
	cleanErrors(false);
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
						window.location.href="./home.htm";
					} else if(formID == "registerationForm") {
						$("#anchorSignIn").hide();
						$("#anchorSignUp").hide();
						$("#anchorWantDrop").show();
						$("#anchorPostDrop").show();
						$("#anchorSignOut").show();
						window.location.href="./home.htm";
					} else if(formID == "dealPostForm") {						
						window.location.href="./showMyDropPost.htm";
					} else if(formID == "dealWantedForm") {
						window.location.href="./showMyDropWanted.htm";
					} else if(formID == "addressForm") {
						window.location.href="./showAddressBook.htm";
					} else if(formID == "editAddressForm") {
						window.location.href="./showAddressBook.htm";
					} else if(formID == "editDealWantedForm") {
						window.location.href="./showMyDropWanted.htm";
					} else if(formID == "deleteDropWantedForm") {
						window.location.href="./showMyDropWanted.htm";
					} else if(formID == "deleteDropPostForm") {
						window.location.href="./showMyDropPost.htm";
					} else if(formID == "rejectMatchingDealForm") {
						window.location.href="./getMatchingDeals.htm?dropWantedId="+$("#txtDealWantedToMatchHidden").val();
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

