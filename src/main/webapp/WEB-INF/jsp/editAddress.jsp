<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

			         <div id="edit-address-dialog" class="mfp-with-anim mfp-dialog clearfix">
			            <i class="fa fa-edit dialog-icon"></i>
			         	<h3>Edit Address</h3> 
			         	<span class="formFieldError" id="errorSpan" style="display: none;"></span>
                        <form:form method="POST" commandName="editAddressForm" action="updateAddress.htm" cssClass="dialog-form" id="editAddressForm">
                            <div class="form-group" >
                                <label>Address Line1</label>
                                <form:input path="addressLine1" cssClass="form-control dummy-form-control" />
                            </div>
                            <div class="form-group">
                                <label>Address Line2</label>
                                <form:input path="addressLine2" cssClass="form-control dummy-form-control" />
                            </div>
                            <div class="form-group">
                                <label>State</label>
                                <form:input path="state" cssClass="form-control dummy-form-control" />
                            </div>
                            <div class="form-group">
                                <label>City</label>
                                <form:input path="city" cssClass="form-control dummy-form-control" />
                            </div>
                            <div class="form-group">
                                <label>Zip/Postal</label>
                                <form:input path="zip" cssClass="form-control dummy-form-control" />
                            </div>
                            
                            <form:hidden path="addressId"/>
                            
                           <a class="btn btn-primary" onclick="submitForm('editAddressForm')" href="#">Save Changes</a>
                        </form:form>
                    </div>
                  