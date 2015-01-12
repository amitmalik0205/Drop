<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

        <div id="password-recover-dialog" class="mfp-with-anim mfp-hide mfp-dialog clearfix">
            <i class="icon-retweet dialog-icon"></i>
            <h3>Password Recovery</h3>
            <h5>Fortgot your password? Don't worry we can deal with it</h5>
            <span class="formFieldError" id="errorSpan" style="display: none;"></span>
            <form:form cssClass="dialog-form" id="forgotPassForm" commandName="forgotPasswordForm" action="forgotPassword.htm">
                <label>E-mail</label>
                <form:input path="email" placeholder="email@domain.com" cssClass="span12 dummy-form-control"/>
                <a class="btn btn-primary" onclick="submitForm('forgotPassForm')" href="#">Request password</a>
                <!-- <input type="submit" value="Request new password" class="btn btn-primary"> -->
            </form:form>
            <ul class="dialog-alt-links">
                <li><a class="popup-text" href="#login-dialog" data-effect="mfp-zoom-out">Member Login</a>
                </li>
            </ul>
        </div>