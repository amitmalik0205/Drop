<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

			         <div id="reason-to-delete-dialog" class="mfp-with-anim mfp-dialog clearfix">
			         <h3>Do you really want to reject the deal?</h3>
			         <span class="formFieldError" id="errorSpan" style="display: none;"></span>
                        <form:form method="POST" commandName="reasonToRejectDealForm" action="rejectDeal.htm" cssClass="dialog-form" id="rejectMatchingDealForm">
                            <div class="form-group" >
                                <label>Reason to Reject</label>
                                <form:textarea path="reasonToReject" placeholder="Reason to reject" cssClass="form-control dummy-form-control" />
                            </div>
                           
                           <form:hidden path="dealPostId"/>
                           
                           <form:hidden path="dealWantedId"/>
                           
                           <a class="btn btn-primary" onclick="submitForm('rejectMatchingDealForm')" href="#">Reject</a>
                        </form:form>
                    </div>