<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>

<head>
    <title>Drop - Drop Post</title>
    <!-- meta info -->
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <meta name="keywords" content="Koupon HTML5 Template" />
    <meta name="description" content="Koupon - Premiun HTML5 Template for Coupons Website"/>
    <meta name="author" content="Tsoy"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- Google fonts -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700,300' rel='stylesheet' type='text/css'/>
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,100,300' rel='stylesheet' type='text/css'/>
    <!-- Bootstrap styles -->
    <link rel="stylesheet" href="css/boostrap.css"/>
    <!-- Font Awesome styles (icons) -->
    <link rel="stylesheet" href="css/font_awesome.css"/>
    <!-- Main Template styles -->
    <link rel="stylesheet" href="css/styles.css"/>
    <!-- IE 8 Fallback -->
    <!--[if lt IE 9]>
	<link rel="stylesheet" type="text/css" href="css/ie.css"/>
    <![endif]-->

   	<!-- Custom css -->
    <link rel="stylesheet" href="css/mystyles.css"/>
    
    <!-- Date time picker css -->
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css" type="text/css"/>
</head>
<body>

    <div class="global-wrap">
 		
 		<jsp:include page="header.jsp"/>

		<jsp:include page="search.jsp"/>

        <div class="gap"></div>


        <!-- //////////////////////////////////
	//////////////PAGE CONTENT///////////// 
	////////////////////////////////////-->
             

        <div class="container">                    	
                 	
                 	<div class="row">
                    	<div class="col-md-6"></div>
                    	 <div class="col-md-6">                    	 		
            				<h3>Drop</h3>           					
                    	 </div>
                    </div>
                 	
                    <div class="row">
                    	<div class="col-md-4"></div>
                    	 <div class="col-md-8">                    	 		
            					<h5>Want to get best offers? Don't worry just post what you want to sell</h5>            					
                    	 </div>
                    </div>
                    
                    <div class="gap"></div>
                    
                    <div class="row">
                        <div class="col-md-2"></div>
                        
                        <div class="col-md-8">
                        
				           <form:form method="POST" commandName="dealPostForm" action="postdrop.htm" cssClass="dialog-form" id="dealPostForm">
				            	<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Title</label>
											<form:input path="title" placeholder="Title" cssClass="form-control dummy-form-control" />
											<form:errors path="title" cssClass="error"></form:errors>
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
											<form:errors path="category" cssClass="error"></form:errors>
										</div>
									</div>
								</div>
								<div class="row">
								<div class="col-md-6">
										<div class="form-group">
											<label>Description</label>
											<form:textarea path="description" placeholder="Describe what you found" cssClass="form-control dummy-form-control" />
											<form:errors path="description" cssClass="error"></form:errors>
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
				                    		<form:errors path="salePrice" cssClass="error"></form:errors>
				                		</div>
				                    </div>
				                    <div class="col-md-6">
				                        <div class="form-group">
				                    		<label>Retail Price</label>
				                    		<form:input path="retailPrice" placeholder="Retail Price" cssClass="form-control dummy-form-control"/>
				                    		<form:errors path="retailPrice" cssClass="error"></form:errors>
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
												<form:errors path="starts" cssClass="error"></form:errors>
												
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
												<form:errors path="expires" cssClass="error"></form:errors>
												
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
				                <form:errors path="dealType"  cssClass="error"></form:errors>
				                <div class="row">
				                	<div class="col-md-6">
				                		<div class="form-group hiddenTag adderssDummyClass">
				                    		<label>Address Line1</label>
				                    		<form:input path="addressLine1" placeholder="Address Line1" cssClass="form-control dummy-form-control"/>
				                    		<form:errors path="addressLine1" cssClass="error"></form:errors>
				                		</div>
				                	</div>
				                	<div class="col-md-6">
				                		<div class="form-group hiddenTag adderssDummyClass">
				                    		<label>Address Line2</label>
				                    		<form:input path="addressLine2" placeholder="Address Line2" cssClass="form-control dummy-form-control"/>
				                    		<form:errors path="addressLine2" cssClass="error"></form:errors>
				                		</div>
				                	</div>
				                </div>  
				                <div class="row">
				                	<div class="col-md-6">
				                		<div class="form-group hiddenTag adderssDummyClass">
				                    		<label>City</label>
				                    		<form:input path="city" placeholder="City" cssClass="form-control dummy-form-control"/>
				                    		<form:errors path="city" cssClass="error"></form:errors>
				                		</div>
				                	</div>
				                	<div class="col-md-6 hiddenTag adderssDummyClass">
				                		<div class="form-group">
				                    		<label>State</label>
				                    		<form:input path="state" placeholder="State" cssClass="form-control dummy-form-control"/>
				                    		<form:errors path="state" cssClass="error"></form:errors>
				                		</div>
				                	</div>
				                	<div class="col-md-4 hiddenTag adderssDummyClass">
				                		<div class="form-group">
				                    		<label>ZIP</label>
				                    		<form:input path="zip" placeholder="ZIP" cssClass="form-control dummy-form-control"/>		
				                    		<form:errors path="zip" cssClass="error"></form:errors>		                    		
				                		</div>
				                	</div>
				                </div>
								<div class="form-group hiddenTag urlDummyClass">
									<label>Deal URL</label>
									<form:input path="url" placeholder="Deal URL" cssClass="form-control dummy-form-control" />
									<form:errors path="url" cssClass="error"></form:errors>	
								</div>
								<button class="btn btn-primary" type="submit">Publish</button>
								<!-- <a class="btn btn-primary" onclick="submitForm('dealPostForm')" href="#">Publish</a> -->
				            </form:form>
				                                      
                        </div>
                    </div>
                    <div class="gap"></div>
      
        </div>


        <!-- //////////////////////////////////
	//////////////END PAGE CONTENT///////// 
	////////////////////////////////////-->



		<jsp:include page="mainFooter.jsp"/>


        <!-- Scripts queries -->
        <script src="js/jquery.js"></script>
        <script src="js/boostrap.min.js"></script>
        <script src="js/countdown.min.js"></script>
        <script src="js/flexnav.min.js"></script>
        <script src="js/magnific.js"></script>
        <script src="js/tweet.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
        <script src="js/fitvids.min.js"></script>
        <script src="js/mail.min.js"></script>
        <script src="js/ionrangeslider.js"></script>
        <script src="js/icheck.js"></script>
        <script src="js/fotorama.js"></script>
        <script src="js/card-payment.js"></script>
        <script src="js/owl-carousel.js"></script>
        <script src="js/masonry.js"></script>
        <script src="js/nicescroll.js"></script>

        <!-- Custom scripts -->
        <script src="js/custom.js"></script>
        <script src="js/myscript.js"></script>
		<script src='js/moment.min.js'></script>
		<script src='js/bootstrap-datetimepicker.min.js'></script>
        
        <script type="text/javascript"> 
            
	        var nowDate = new Date();
	        var today = new Date(nowDate.getFullYear(), nowDate.getMonth(), nowDate.getDate(), 0, 0, 0, 0);
        
        	$('.dateTimePicker').datetimepicker({
                pick12HourFormat: false,
                
                minDate:today
            });
        	
        	
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
        	
        </script>
    </div>

</body>
</html>