<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head>
    <title>Drop.com</title>
 
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
	<link rel="stylesheet" type="text/css" href="css/ie.css" /> <![endif]-->

    <!-- Your custom styles (blank file) -->
    <link rel="stylesheet" href="css/mystyles.css"/>
    
    <!-- Date time picker css -->
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css" type="text/css"/>
 </head>
 
   <body class="boxed" style="background-image: url(img/textures/wood-1.jpg)">


    <div class="global-wrap">
 
 		<jsp:include page="header.jsp"/>

		<!-- PAGE TITLE -->
		<!-- <div class="top-title-area">
			<div class="container">
				<h1 class="title-page"></h1>
			</div>
		</div> -->
		<!-- END PAGE TITLE -->

 		<jsp:include page="loginDialog.jsp"/>
 		
 		<jsp:include page="registrationDialog.jsp"/>
 		
 		<jsp:include page="forgotPasswordDialog.jsp"/>
 		
                    
        <!-- TOP AREA -->
        <div class="top-area">
            <div class="owl-carousel owl-slider" id="owl-carousel-slider" data-inner-pagination="true" data-white-pagination="true">
                <div>
                    <div class="bg-holder">
                        <img src="img/1200x480.png" alt="Image Alternative text" title="4 Strokes of Fun" />
                        <div class="bg-mask"></div>
                        <div class="bg-front vert-center text-white text-center">
                            <h2 class="text-hero">Adrenaline Madness</h2>
                            <div class="countdown countdown-big" data-countdown="Jul 18, 2014 5:30:00"></div><a class="btn btn-lg btn-ghost btn-white" href="#">Save 60% Now</a>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="bg-holder">
                        <img src="img/1200x480.png" alt="Image Alternative text" title="Bridge" />
                        <div class="bg-mask"></div>
                        <div class="bg-front vert-center text-white text-center">
                            <h2 class="text-hero">London Weekends</h2>
                            <div class="countdown countdown-big" data-countdown="Jul 30, 2014 5:30:00"></div><a class="btn btn-lg btn-ghost btn-white" href="#">Save 70% Now</a>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="bg-holder">
                        <img src="img/1200x480.png" alt="Image Alternative text" title="LHOTEL PORTO BAY SAO PAULO luxury suite" />
                        <div class="bg-mask"></div>
                        <div class="bg-front vert-center text-white text-center">
                            <h2 class="text-hero">Premium Apartments</h2>
                            <div class="countdown countdown-big" data-countdown="Jul 16, 2014 5:30:00"></div><a class="btn btn-lg btn-ghost btn-white" href="#">Save 70% Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END TOP AREA -->

 		<jsp:include page="search.jsp"/>

        <div class="gap"></div>

        <!-- //////////////////////////////////
	//////////////PAGE CONTENT///////////// 
	////////////////////////////////////-->


        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <div class="text-center">
                        <h1>Explore Best Deals in Your City</h1>
                        <p class="text-bigger">Iaculis hendrerit integer fringilla at placerat bibendum gravida malesuada porta luctus fusce ad suscipit porttitor molestie litora cubilia fringilla parturient dictum ad lobortis turpis accumsan ligula posuere ad facilisis ultricies</p>
                    </div>
                </div>
            </div>
            <div class="gap"></div>
        </div>
        <div class="bg-holder">
            <div class="bg-mask"></div>
            <div class="bg-blur" style="background-image:url(img/backgrounds/phone.jpg)"></div>
            <div class="container bg-holder-content">
                <div class="gap gap-big text-center">
                    <h1 class="mb30 text-white">Find These Deals For People, Get Paid</h1>
                    <div class="row row-wrap">
                    	<c:forEach items="${requestScope.featuredDealWantedList}" var="featuredDealWanted">
	                        <a class="col-md-3" href="#">
	                            <div class="product-thumb">
	                                 <header class="product-header">
	                                    <img src="img/categories/${featuredDealWanted.imageName}" alt="Image Alternative text" title="Ana 29" />
	                                </header>
	                                <div class="product-inner">
	                                    <h5 class="product-title">${featuredDealWanted.title}</h5>
	                                    <p class="product-desciption">${featuredDealWanted.description}</p>
	                                    <div class="product-meta">
	                                        <ul class="product-price-list">
	                                            <li>
	                                            	<span class="product-price">$${featuredDealWanted.maxPrice}</span>
	                                            </li>
	                                            <!-- <li><span class="product-old-price">$213</span>
	                                            </li>
	                                            <li><span class="product-save">Save 49%</span>
	                                            </li> -->
	                                        </ul>
	                                    </div>	                                   
	                                </div>
	                            </div>
	                        </a>                    	
                    	</c:forEach>
                    </div>	<a href="searchMore.htm?searchType=Drop Wanted" class="btn btn-white btn-lg btn-ghost">Find More</a>
                </div>
            </div>
        </div>
        <div class="gap"></div>
        <div class="container">
            <div class="text-center">
                <h2 class="mb30">Most Recent Found Drops</h2>
                <div class="row row-wrap" id="masonry">
	                <c:forEach items="${requestScope.featuredDealPostList}" var="featuredDealPost">
		                    <a class="col-md-2 col-masonry" href="viewHomePageDropDetails.htm?dealPostId=${featuredDealPost.id}">
		                        <div class="product-thumb">
		                            <header class="product-header">
		                                <img src="img/categories/${featuredDealPost.imageName}" alt="Image Alternative text" title="cascada" />
		                            </header>
		                            <div class="product-inner">
		                                <h5 class="product-title">${featuredDealPost.title}</h5>
		                                <div class="product-desciption">${featuredDealPost.description}</div>
		                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> ${featuredDealPost.timeToExpire}</span>
		                                    <ul class="product-price-list">
		                                        <li><span class="product-price">$${featuredDealPost.salePrice}</span>
		                                        </li>
		                                        <li><span class="product-old-price">$${featuredDealPost.retailPrice}</span>
		                                        </li>
		                                    </ul>
		                                </div>		                               
		                            </div>
		                        </div>
		                    </a>
	                   </c:forEach> 
                </div>	
                <a href="searchMore.htm?searchType=Drops" class="btn btn-primary btn-ghost">Explore All New Deals</a>
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
        	$("#homeli").attr("class","active");
        	
        	$('.dateTimePicker').datetimepicker({
                pick12HourFormat: false,
            });   
        </script>
    </div>
</body>

</html>
