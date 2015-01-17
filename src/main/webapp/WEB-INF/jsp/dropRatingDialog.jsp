<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

                   	<div id="review-dialog" class="mfp-with-anim mfp-dialog clearfix">
                    	<h3>Add a Review</h3>
                        <span class="formFieldError" id="errorSpan" style="display: none;"></span>
                        <form:form method="POST" commandName="dropRatingForm" action="saveDropRating.htm" cssClass="dialog-form" id="dropRatingForm">                          
                            <div class="form-group">
                                <label>Review</label>
                                <form:textarea path="description" placeholder="Add your review" cssClass="form-control dummy-form-control" />
                            </div>
                            <div class="form-group">
                                <label>Rating</label>
                                <ul class="icon-list icon-list-inline star-rating" id="star-rating">
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                </ul>
                            </div>
                            
                            <form:hidden path="rating" id="dropRating"/>
                            
                            <form:hidden path="dealPostId"/>
                            
                            <form:hidden path="dealWantedId"/>
                            
                            <input class="btn btn-primary" type="submit" value="Submit"/>
                        </form:form>
                        
                        <script src="js/custom.js"></script>
                        
                        <script type="text/javascript">                        	
	                        $("#dropRatingForm").submit(function(e){	
	                            var count = $('#star-rating li.selected').length;	                    		
	                    		$('#dropRating').val(count);
	                    		if(count == 0) {
	                    			$('#errorSpan').text("Please select rating");
	                    			$('#errorSpan').show();
	                    			e.preventDefault(e);
	                    		}	                            
	                        });
                        </script>                                                                        
                    </div>