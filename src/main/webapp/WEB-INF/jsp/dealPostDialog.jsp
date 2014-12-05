<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

        <!--Deal Post Dialog -->
        <div id="post-drop-dialog" class="mfp-with-anim mfp-hide mfp-dialog clearfix broadPopup scrollPopup">
            <i class="fa fa-edit dialog-icon"></i>
            <h3>Post Drop</h3>
            <h5>Want to get best offers? Don't worry just post what you want to sell</h5>
            <span class="formFieldError" id="errorSpan" style="display: none;"></span>
            <form:form method="POST" commandName="dealPostForm" action="postdrop.htm" cssClass="dialog-form" id="dealPostForm">
            	<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Title</label>
							<form:input path="title" placeholder="Title" cssClass="form-control dummy-form-control" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label>Category</label>
							<form:select path="category" cssClass="form-control dummy-form-control">
								<form:option value="0" label="--Select Category--" />
								<form:options itemValue="id" itemLabel="name"
									items="${dealPostForm.dealCategories}" />
							</form:select>
						</div>
					</div>
				</div>
				<div class="row">
				<div class="col-md-6">
						<div class="form-group">
							<label>Description</label>
							<form:textarea path="description" placeholder="Describe what you want" cssClass="form-control dummy-form-control" />
						</div>
					</div>					
					<div class="col-md-6">
						<div class="form-group">
							<label>Special Instructions</label>
							<form:textarea path="specialInstructions" placeholder="Special Instructions" cssClass="form-control dummy-form-control" />
						</div>
					</div>
				</div>
				<div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                    		<label>Sale Price</label>
                    		<form:input path="salePrice" placeholder="Sale Price" cssClass="form-control dummy-form-control"/>
                		</div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                    		<label>Retail Price</label>
                    		<form:input path="retailPrice" placeholder="Retail Price" cssClass="form-control dummy-form-control"/>
                		</div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                    		<label>Discount %</label>
                    		<form:input path="discountPercent" placeholder="Discount %" cssClass="form-control dummy-form-control"/>
                		</div>
                    </div>
                </div>
                <div class="row">
					<div class="col-md-5">
						<div class="form-group">
							<label>Starts On</label>
							<div>
								<div class='input-group date dateTimePicker'
									onclick="dateTimePicker()">
									<form:input cssClass="form-control dummy-form-control" path="starts" />
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="control-label">Expires On</label>
							<div>
								<div class='input-group date dateTimePicker'
									onclick="dateTimePicker()">
									<form:input cssClass="form-control dummy-form-control" path="expires" />
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>						
				<div class="row">
                	<div class="col-md-6">
						<div class="checkbox">
							<form:checkbox path="couponsRequired" label="Coupons Required" cssClass="dummyChkBoxClass"></form:checkbox>
						</div>
					</div>
	                <div class="col-md-6">
		                <div class="checkbox">
		                    <form:checkbox path="membershipRequired" label="Membership Required" cssClass="dummyChkBoxClass"></form:checkbox>	
		                </div> 	
	                </div>                
                </div>
				<div class="row">
					<div class="col-md-6">						
						<div class="radio">
							<form:radiobutton path="dealType" value="localDeal" label="Local Deal" cssClass="dummyChkBoxClass" id="localDealRadio" onclick="showDealLocation()"></form:radiobutton>
						</div>
					</div>
					<div class="col-md-6">
		                <div class="radio">
		                	<form:radiobutton path="dealType"  value="onlineDeal" label="Online Deal" cssClass="dummyChkBoxClass" id="onlineDealRadio" onclick="showDealLocation()"></form:radiobutton>		                   
		                </div>   
	                </div>	                               
                </div> 
                <div class="row">
                	<div class="col-md-6">
                		<div class="form-group hiddenTag adderssDummyClass">
                    		<label>Address Line1</label>
                    		<form:input path="addressLine1" placeholder="Address Line1" cssClass="form-control dummy-form-control"/>
                		</div>
                	</div>
                	<div class="col-md-6">
                		<div class="form-group hiddenTag adderssDummyClass">
                    		<label>Address Line2</label>
                    		<form:input path="addressLine2" placeholder="Address Line2" cssClass="form-control dummy-form-control"/>
                		</div>
                	</div>
                </div>  
                <div class="row">
                	<div class="col-md-6">
                		<div class="form-group hiddenTag adderssDummyClass">
                    		<label>City</label>
                    		<form:input path="city" placeholder="City" cssClass="form-control dummy-form-control"/>
                		</div>
                	</div>
                	<div class="col-md-6 hiddenTag adderssDummyClass">
                		<div class="form-group">
                    		<label>State</label>
                    		<form:input path="state" placeholder="State" cssClass="form-control dummy-form-control"/>
                		</div>
                	</div>
                	<div class="col-md-4 hiddenTag adderssDummyClass">
                		<div class="form-group">
                    		<label>ZIP</label>
                    		<form:input path="zip" placeholder="ZIP" cssClass="form-control dummy-form-control"/>
                		</div>
                	</div>
                </div>
				<div class="form-group hiddenTag urlDummyClass">
					<label>Deal URL</label>
					<form:input path="url" placeholder="Deal URL" cssClass="form-control dummy-form-control" />
				</div>
				<a class="btn btn-primary" onclick="submitForm('dealPostForm')" href="#">Publish</a>
            </form:form>
        </div>