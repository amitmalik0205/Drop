<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

        <div id="post-drop-dialog" class="mfp-with-anim mfp-dialog clearfix broadPopup scrollPopup">
            <i class="fa fa-edit dialog-icon"></i>
            <h3>Edit Drop</h3>         
            <span class="formFieldError" id="errorSpan" style="display: none;"></span>
            <form:form method="POST" commandName="editDealPostForm" action="updatePostDrop.htm" cssClass="dialog-form" id="editDealPostForm">
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
									items="${editDealPostForm.dealCategories}" />
							</form:select>
						</div>
					</div>
				</div>
				<div class="row">
				<div class="col-md-6">
						<div class="form-group">
							<label>Description</label>
							<form:textarea path="description" placeholder="Describe what you found" cssClass="form-control dummy-form-control" />
						</div>
					</div>					
					<div class="col-md-6">
						<div class="form-group">
							<label>Special Instructions</label>
							<form:textarea path="specialInstructions" placeholder="Is there anything special you need to do to get discount" cssClass="form-control dummy-form-control" />
						</div>
					</div>
				</div>
				<div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                    		<label>Sale Price</label>
                    		<form:input path="salePrice" placeholder="Sale Price" cssClass="form-control dummy-form-control"/>
                		</div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                    		<label>Retail Price</label>
                    		<form:input path="retailPrice" placeholder="Retail Price" cssClass="form-control dummy-form-control"/>
                		</div>
                    </div>
<%--                     <div class="col-md-4">
                        <div class="form-group">
                    		<label>Discount %</label>
                    		<form:input path="discountPercent" placeholder="Discount %" cssClass="form-control dummy-form-control"/>
                		</div>
                    </div> --%>
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
                  
                 <c:if test="${requestScope.editDealPostForm.dealType eq 'localDeal'}">
	                 <div class="row">
	                	<div class="col-md-6">
	                		<div class="form-group adderssDummyClass">
	                    		<label>Address Line1</label>
	                    		<form:input path="addressLine1" placeholder="Address Line1" cssClass="form-control dummy-form-control"/>
	                		</div>
	                	</div>
	                	<div class="col-md-6">
	                		<div class="form-group adderssDummyClass">
	                    		<label>Address Line2</label>
	                    		<form:input path="addressLine2" placeholder="Address Line2" cssClass="form-control dummy-form-control"/>
	                		</div>
	                	</div>
	                </div>
	                <div class="row">
	                	<div class="col-md-6">
	                		<div class="form-group adderssDummyClass">
	                    		<label>City</label>
	                    		<form:input path="city" placeholder="City" cssClass="form-control dummy-form-control"/>
	                		</div>
	                	</div>
	                	<div class="col-md-6 adderssDummyClass">
	                		<div class="form-group">
	                    		<label>State</label>
	                    		<form:input path="state" placeholder="State" cssClass="form-control dummy-form-control"/>
	                		</div>
	                	</div>
	                	<div class="col-md-4 adderssDummyClass">
	                		<div class="form-group">
	                    		<label>ZIP</label>
	                    		<form:input path="zip" placeholder="ZIP" cssClass="form-control dummy-form-control"/>
	                		</div>
	                	</div>
	                </div>
	            </c:if>
	            
	            <c:if test="${requestScope.editDealPostForm.dealType ne 'localDeal'}">
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
	            </c:if>
                <c:if test="${requestScope.editDealPostForm.dealType eq 'onlineDeal'}">
					<div class="form-group urlDummyClass">
						<label>Deal URL</label>
						<form:input path="url" placeholder="Deal URL" cssClass="form-control dummy-form-control" />
					</div>
				</c:if>
				<c:if test="${requestScope.editDealPostForm.dealType ne 'onlineDeal'}">
					<div class="form-group hiddenTag urlDummyClass">
						<label>Deal URL</label>
						<form:input path="url" placeholder="Deal URL" cssClass="form-control dummy-form-control" />
					</div>
				</c:if>				
				
				<form:hidden path="dealPostId"/> 
				
				<a class="btn btn-primary" onclick="submitForm('editDealPostForm')" href="#">Publish</a>
            </form:form>
            
            <script src="js/custom.js"></script>
       		<script src="js/myscript.js"></script>
			<script src='js/moment.min.js'></script>
			<script src='js/bootstrap-datetimepicker.min.js'></script>
            
            <script type="text/javascript">	        	
	        	$('.dateTimePicker').datetimepicker({
	                pick12HourFormat: false,
	            });
            </script>
        </div>