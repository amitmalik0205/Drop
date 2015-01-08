<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

		<!-- User Registration Dialog -->
        <div id="register-dialog" class="mfp-with-anim mfp-hide mfp-dialog clearfix">
            <i class="fa fa-edit dialog-icon"></i>
            <h3>Member Register</h3>
            <h5>Ready to get best offers? Let's get started!</h5>
            <span class="formFieldError" id="errorSpan" style="display: none;"></span>
            <form:form method="POST" commandName="registerationForm" action="register.htm" cssClass="dialog-form" id="registerationForm">
            	<div class="form-group">
                    <label>First Name</label>
                    <form:input path="firstName" placeholder="First Name" cssClass="form-control dummy-form-control"/>
                </div>
                <div class="form-group">
                    <label>Last Name</label>
                    <form:input path="lastName" placeholder="Last Name" cssClass="form-control dummy-form-control"/>
                </div>
                <div class="form-group">
                    <label>E-mail</label>
                    <form:input path="email" placeholder="email@domain.com" cssClass="form-control dummy-form-control"/>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <form:password path="password" placeholder="Password" cssClass="form-control dummy-form-control"/>
                </div>
                <a class="btn btn-primary" onclick="submitForm('registerationForm')" href="#">Sign up</a>
				<ul class="dialog-alt-links">
					<li><a class="popup-text" href="#login-dialog"
						data-effect="mfp-zoom-out">Already member</a></li>
				</ul>
			</form:form>
        </div>