<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

        <div id="want-drop-dialog" class="mfp-with-anim mfp-dialog clearfix broadPopup">
            <i class="fa fa-edit dialog-icon"></i>
            <h3>Edit Want Drop</h3>         
            <span class="formFieldError" id="errorSpan" style="display: none;"></span>
            <form:form method="POST" commandName="editDealWantedForm" action="updateWantdrop.htm" cssClass="dialog-form" id="editDealWantedForm">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Title</label>
							<form:input path="title" placeholder="Title" cssClass="form-control dummy-form-control" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label>Description</label>
							<form:textarea path="description" placeholder="Describe what you want" cssClass="form-control dummy-form-control" />
						</div>
					</div>
				</div>
 				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Category</label>
							<form:select path="category" cssClass="form-control dummy-form-control">
								<form:option value="0" label="--Select Category--" />
								<form:options itemValue="id" itemLabel="name"
									items="${editDealWantedForm.dealCategories}" />
							</form:select>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label>Maximum Price</label>
							<form:input path="maxPrice" placeholder="Maximum Price" cssClass="form-control dummy-form-control" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label>Tip Amount</label>
							<form:input path="tipAmount" placeholder="Tip Amount" cssClass="form-control dummy-form-control" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="checkbox">
							<form:checkbox path="acceptCoupons" label="Accept Coupons" cssClass="dummyChkBoxClass"></form:checkbox>
						</div>
					</div>
					<div class="col-md-4">
		                <div class="checkbox">
		                	<form:checkbox path="wouldBuyOnline" label="Buy Online" cssClass="dummyChkBoxClass"></form:checkbox>		                   
		                </div>   
	                </div>
	                <div class="col-md-4">
		                <div class="checkbox">
		                    <form:checkbox path="wouldBuyLocally" label="Buy Locally" cssClass="dummyChkBoxClass"></form:checkbox>	
		                </div> 	
	                </div>                
                </div>    
                 <div class="row">
                	<div class="col-md-4">
		                <div class="checkbox">
		                    <form:checkbox path="wantNew" label="Want New" cssClass="dummyChkBoxClass"></form:checkbox>
		                </div>   
	                </div>
	                <div class="col-md-4">
		                <div class="checkbox">
		                    <form:checkbox path="wantUsed" label="Want Old" cssClass="dummyChkBoxClass"></form:checkbox>
		                </div> 	
	                </div>  
	                <div class="col-md-4">
		                <div class="checkbox">
		                	<form:checkbox path="refurbishedOK" label="Want Refurbished" cssClass="dummyChkBoxClass"></form:checkbox>		                   
		                </div> 	
	                </div>               
                </div>    
                
                <form:hidden path="dealWantedId"/> 
                                 
                <a class="btn btn-primary" onclick="submitForm('editDealWantedForm')" href="#">Save Changes</a>
            </form:form>
        </div>