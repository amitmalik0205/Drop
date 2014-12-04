<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>

	<head>
	    <title>Drop - Address Book</title>
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
	</head>
	
<body>

    <div class="global-wrap">
        
        <jsp:include page="header.jsp"/>

		<!-- PAGE TITLE -->
		<!-- <div class="top-title-area">
			<div class="container">
				<h1 class="title-page"></h1>
			</div>
		</div> -->
		<!-- END PAGE TITLE -->

		<jsp:include page="search.jsp"/>

        <div class="gap"></div>

        <!-- //////////////////////////////////
	//////////////PAGE CONTENT///////////// 
	////////////////////////////////////-->

        <div class="container">
        
            <div class="row">
                
                	<jsp:include page="profileLeftNevigation.jsp"/>
                
                    <div id="add-address-dialog" class="mfp-with-anim mfp-hide mfp-dialog clearfix">
                    	<span class="formFieldError" id="errorSpan" style="display: none;"></span>
                        <form:form cssClass="dialog-form" method="POST" commandName="addressForm" action="addAddress.htm" id="addressForm">
                            <div class="form-group" >
                                <label>Address Line1</label>
                                <form:input path="addressLine1" cssClass="form-control" />
                            </div>
                            <div class="form-group">
                                <label>Address Line2</label>
                                <form:input path="addressLine2" cssClass="form-control" />
                            </div>
                            <div class="form-group">
                                <label>State</label>
                                <form:input path="state" cssClass="form-control" />
                            </div>
                            <div class="form-group">
                                <label>City</label>
                                <form:input path="city" cssClass="form-control" />
                            </div>
                            <div class="form-group">
                                <label>Zip/Postal</label>
                                <form:input path="zip" cssClass="form-control" />
                            </div>
                            <!-- <div class="checkbox">
                                <label>
                                    <input type="checkbox" checked class="i-check" />Set Primary</label>
                            </div> -->
                           <a class="btn btn-primary" onclick="submitForm('addressForm')" href="#">Add Address</a>
                        </form:form>
                    </div>
                    
                    <div class="row row-wrap">
	                    <c:forEach var="address" items="${requestScope.addressForm.addressList}">
		                    <div class="col-md-4">
	                            <div class="address-box">
	                                <a class="address-box-remove" href="#" data-toggle="tooltip" data-placement="right" title="Remove"></a>
	                                <a class="address-box-edit popup-text" href="#edit-address-dialog" data-effect="mfp-move-from-top" data-toggle="tooltip" data-placement="right" title="Edit"></a>
	                                <ul>
	                                	<li>Address: ${address.addressLine1}, ${address.addressLine2}</li>
	                                    <li>State: ${address.state}</li>	
	                                    <li>City: ${address.city}</li>	                                    
	                                    <li>Zip/Postal: ${address.zip}</li>
	                                </ul>
	                                <!-- <div class="radio">
	                                    <label>
	                                        <input type="radio" class="i-radio" name="primaryAddressOption" />Primary Address</label>
	                                </div> -->
	                            </div>
	                        </div>	                  
	                    </c:forEach>
	                    <div class="col-md-4">
							<a class="address-box address-box-new popup-text" href="#add-address-dialog" data-effect="mfp-move-from-top">
								<div class="vert-center">
									<i class="fa fa-plus address-box-new-icon"></i>
									<p>Add New Address</p>
								</div>
							</a>
					</div>
                    </div>					
					<div class="gap"></div>
                </div>
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
    </div>
</body>

</html>
