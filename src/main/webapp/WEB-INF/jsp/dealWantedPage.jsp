<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>

<head>
    <title>Drop - Drop Want</title>
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
                    	<div class="col-md-5"></div>
                    	 <div class="col-md-7">                    	 		
            				<h3>Want Drop</h3>           					
                    	 </div>
                    </div>
                 	
                    <div class="row">
                    	<div class="col-md-4"></div>
                    	 <div class="col-md-8">                    	 		
            					<h5>Want to get best offers? Don't worry just post what you want</h5>            					
                    	 </div>
                    </div>
                    
                    <div class="gap"></div>
                    
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                        
							<form:form method="POST" commandName="dealWantedForm" action="wantdrop.htm" cssClass="dialog-form" id="dealWantedForm">
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
											<label>Description</label>
											<form:textarea path="description" placeholder="Describe what you want" cssClass="form-control dummy-form-control" />
											<form:errors path="description" cssClass="error"></form:errors>
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
													items="${dealWantedForm.dealCategories}" />
											</form:select>
											<form:errors path="category" cssClass="error"></form:errors>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Maximum Price</label>
											<form:input path="maxPrice" placeholder="Maximum Price" cssClass="form-control dummy-form-control" />
											<form:errors path="maxPrice" cssClass="error"></form:errors>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Tip Amount</label>
											<form:input path="tipAmount" placeholder="Tip Amount" cssClass="form-control dummy-form-control" />
											<form:errors path="tipAmount" cssClass="error"></form:errors>
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
				                <button class="btn btn-primary" type="submit">Publish</button>               
				                <!-- <a class="btn btn-primary" onclick="submitForm('dealWantedForm')" href="#">Publish</a>	 -->			             
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
        	$('.dateTimePicker').datetimepicker({
                pick12HourFormat: false,
            });
        </script>
    </div>

</body>
</html>