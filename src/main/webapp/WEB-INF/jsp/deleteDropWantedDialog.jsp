<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

			         <div id="reason-to-delete-dialog" class="mfp-with-anim mfp-dialog clearfix">
			         <h3>Do you really want to delete it?</h3>
			         <span class="formFieldError" id="errorSpan" style="display: none;"></span>
                        <form:form method="POST" commandName="reasonToDeleteForm" action="deleteDropWanted.htm" cssClass="dialog-form" id="deleteDropWantedForm">
                            <div class="form-group" >
                                <label>Reason to Delete</label>
                                <form:textarea path="reason" placeholder="Reason to delete" cssClass="form-control dummy-form-control" />
                            </div>
                            <form:hidden path="dealId"/>
                            <!-- <div class="checkbox">
                                <label>
                                    <input type="checkbox" checked class="i-check" />Set Primary</label>
                            </div> -->
                           <a class="btn btn-primary" onclick="submitForm('deleteDropWantedForm')" href="#">Delete</a>
                        </form:form>
                    </div>
                  