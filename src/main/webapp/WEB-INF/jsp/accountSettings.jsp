<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>

<head>
    <title>Drop - My Settings</title>
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
                
                <div class="col-md-9">
                    <div class="row">
                        <div class="col-md-6">
                            <form:form action="updateAccountSettings.htm" commandName="accountSettingsForm">
                                <div class="form-group">
                                    <label for="">First Name</label>
                                    <form:input path="firstName" cssClass="form-control"/>
                                    <form:errors path="firstName" cssClass="formFieldError"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="">Last Name</label>
                                    <form:input path="lastName" cssClass="form-control"/>
                                    <form:errors path="lastName" cssClass="formFieldError"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="">Phone Number</label>
                                   <form:input path="phone" cssClass="form-control"/>
                                   <form:errors path="phone" cssClass="formFieldError"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="">Skype Name</label>
                                   <form:input path="skypeName" cssClass="form-control"/>
                                   <form:errors path="skypeName" cssClass="formFieldError"></form:errors>
                                </div>                               
                                <input type="submit" value="Save Changes" class="btn btn-primary"/>
                            </form:form>
                        </div>
                    </div>
                    <div class="gap"></div>
                </div>
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
		<script src='js/moment.min.js'></script>
		<script src='js/bootstrap-datetimepicker.min.js'></script>
        
        <script type="text/javascript">
        	$("#myprofileli").attr("class","active");
        	$("#myaccountli").attr("class","active");
        	
        	$('.dateTimePicker').datetimepicker({
                pick12HourFormat: false,
            });
        </script>
    </div>
</body>

</html>
