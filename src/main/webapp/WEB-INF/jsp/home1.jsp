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

		<!-- LOGIN REGISTER LINKS CONTENT -->
        <div id="login-dialog" class="mfp-with-anim mfp-hide mfp-dialog clearfix">
            <i class="fa fa-sign-in dialog-icon"></i>
            <h3>Member Login</h3>
            <h5>Welcome back, friend. Login to get started</h5>
            <span class="formFieldError" id="errorSpan" style="display: none;"></span>
            <form:form cssClass="dialog-form" method="POST" commandName="loginForm" action="login.htm" id="loginForm">
                <div class="form-group">
                    <label>E-mail</label>
                    <form:input path="email" placeholder="email@domain.com" cssClass="form-control dummy-form-control"/>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <form:password path="password" placeholder="My secret password" cssClass="form-control dummy-form-control"/>
                </div>
               <!--  <input type="submit" value="Sign in" class="btn btn-primary"/> -->
               <a class="btn btn-primary" onclick="submitForm('loginForm')" href="#">Sign in</a>
            </form:form>
            <ul class="dialog-alt-links">
                <li><a class="popup-text" href="#register-dialog" data-effect="mfp-zoom-out">Not member yet</a>
                </li>
                <li><a class="popup-text" href="#password-recover-dialog" data-effect="mfp-zoom-out">Forgot password</a>
                </li>
            </ul>
        </div>

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
        </div>
        
        <jsp:include page="dealWantedDialog.jsp"/>
        
        
        <jsp:include page="dealPostDialog.jsp"/>
        
        
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
                        <a class="col-md-3" href="#">
                            <div class="product-thumb">
                                <header class="product-header">
                                    <img src="img/800x600.png" alt="Image Alternative text" title="Ana 29" />
                                </header>
                                <div class="product-inner">
                                    <h5 class="product-title">Hot Summer Collection</h5>
                                    <p class="product-desciption">Elit gravida neque mollis purus vivamus habitasse iaculis</p>
                                    <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 6 days 10 h remaining</span>
                                        <ul class="product-price-list">
                                            <li><span class="product-price">$104</span>
                                            </li>
                                            <li><span class="product-old-price">$213</span>
                                            </li>
                                            <li><span class="product-save">Save 49%</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                                </div>
                            </div>
                        </a>
                        <a class="col-md-3" href="#">
                            <div class="product-thumb">
                                <header class="product-header">
                                    <img src="img/800x600.png" alt="Image Alternative text" title="Green Furniture" />
                                </header>
                                <div class="product-inner">
                                    <h5 class="product-title">Green Furniture Pack</h5>
                                    <p class="product-desciption">Elit gravida neque mollis purus vivamus habitasse iaculis</p>
                                    <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 1 day 10 h remaining</span>
                                        <ul class="product-price-list">
                                            <li><span class="product-price">$85</span>
                                            </li>
                                            <li><span class="product-old-price">$173</span>
                                            </li>
                                            <li><span class="product-save">Save 49%</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                                </div>
                            </div>
                        </a>
                        <a class="col-md-3" href="#">
                            <div class="product-thumb">
                                <header class="product-header">
                                    <img src="img/800x600.png" alt="Image Alternative text" title="Urbex Esch/Lux with Laney and Laaaaag" />
                                </header>
                                <div class="product-inner">
                                    <h5 class="product-title">Best Camera</h5>
                                    <p class="product-desciption">Elit gravida neque mollis purus vivamus habitasse iaculis</p>
                                    <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 5 days 32 h remaining</span>
                                        <ul class="product-price-list">
                                            <li><span class="product-price">$88</span>
                                            </li>
                                            <li><span class="product-old-price">$258</span>
                                            </li>
                                            <li><span class="product-save">Save 34%</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                                </div>
                            </div>
                        </a>
                        <a class="col-md-3" href="#">
                            <div class="product-thumb">
                                <header class="product-header">
                                    <img src="img/800x600.png" alt="Image Alternative text" title="The Hidden Power of the Heart" />
                                </header>
                                <div class="product-inner">
                                    <h5 class="product-title">Beach Holidays</h5>
                                    <p class="product-desciption">Elit gravida neque mollis purus vivamus habitasse iaculis</p>
                                    <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 2 days 21 h remaining</span>
                                        <ul class="product-price-list">
                                            <li><span class="product-price">$108</span>
                                            </li>
                                            <li><span class="product-old-price">$250</span>
                                            </li>
                                            <li><span class="product-save">Save 43%</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                                </div>
                            </div>
                        </a>
                    </div>	<a href="#" class="btn btn-white btn-lg btn-ghost">Find More</a>
                </div>
            </div>
        </div>
        <div class="gap"></div>
        <div class="container">
            <div class="text-center">
                <h2 class="mb30">Most Recent Found Drops</h2>
                <div class="row row-wrap" id="masonry">
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="cascada" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Adventure in Woods</h5>
                                <div class="product-desciption">Montes lacinia dui nisi aliquam</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 8 days 46 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$133</span>
                                        </li>
                                        <li><span class="product-old-price">$265</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="iPhone 5 iPad mini iPad 3" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Electronics Big Deal</h5>
                                <div class="product-desciption">Faucibus potenti egestas nisi aenean dictum ligula</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i>  14 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$117</span>
                                        </li>
                                        <li><span class="product-old-price">$217</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="the best mode of transport here in maldives" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Finshing in Maldives</h5>
                                <div class="product-desciption">Mi porttitor at sit habitasse phasellus</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 9 days 22 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$71</span>
                                        </li>
                                        <li><span class="product-old-price">$124</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="waipio valley" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Awesome Vacation</h5>
                                <div class="product-desciption">Pulvinar nostra himenaeos pulvinar facilisi dui</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 9 days 30 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$80</span>
                                        </li>
                                        <li><span class="product-old-price">$195</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Hot mixer" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Modern DJ Set</h5>
                                <div class="product-desciption">Condimentum porttitor eros metus quisque scelerisque</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 9 days 22 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$66</span>
                                        </li>
                                        <li><span class="product-old-price">$108</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Old No7" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Jack Daniels Huge Pack</h5>
                                <div class="product-desciption">Hendrerit pretium magna ultricies massa</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 2 days 33 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$136</span>
                                        </li>
                                        <li><span class="product-old-price">$242</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Food is Pride" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Best Pasta</h5>
                                <div class="product-desciption">Praesent et justo sed platea est</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 1 day 41 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$97</span>
                                        </li>
                                        <li><span class="product-old-price">$152</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Aspen Lounge Chair" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Aspen Lounge Chair</h5>
                                <div class="product-desciption">Fermentum neque morbi vel nec sed imperdiet</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i>  56 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$72</span>
                                        </li>
                                        <li><span class="product-old-price">$131</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Green Furniture" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Green Furniture Pack</h5>
                                <div class="product-desciption">Aliquet suspendisse placerat feugiat tellus platea fermentum</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 2 days 25 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$49</span>
                                        </li>
                                        <li><span class="product-old-price">$101</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="The Violin" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Violin Lessons</h5>
                                <div class="product-desciption">Commodo quisque porta dolor scelerisque</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 3 days 7 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$155</span>
                                        </li>
                                        <li><span class="product-old-price">$299</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Our Coffee miss u" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Coffe Shop Discount</h5>
                                <div class="product-desciption">In accumsan ullamcorper laoreet nam</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 5 days 50 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$44</span>
                                        </li>
                                        <li><span class="product-old-price">$110</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Urbex Esch/Lux with Laney and Laaaaag" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Best Camera</h5>
                                <div class="product-desciption">Diam sapien hac turpis euismod</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 7 days 27 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$100</span>
                                        </li>
                                        <li><span class="product-old-price">$256</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="The Hidden Power of the Heart" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Beach Holidays</h5>
                                <div class="product-desciption">Mauris elementum gravida tincidunt magna ac</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 3 days 28 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$54</span>
                                        </li>
                                        <li><span class="product-old-price">$170</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="My Ice Cream and Your Ice Cream Spoons" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Lovely Ice Cream Spoons</h5>
                                <div class="product-desciption">Dapibus ante sapien gravida integer</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 3 days 18 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$61</span>
                                        </li>
                                        <li><span class="product-old-price">$130</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Ana 29" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Hot Summer Collection</h5>
                                <div class="product-desciption">Eu molestie habitasse tincidunt luctus</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 4 days 5 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$106</span>
                                        </li>
                                        <li><span class="product-old-price">$190</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="a turn" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Diving with Sharks</h5>
                                <div class="product-desciption">Eget penatibus hac tortor imperdiet ante elementum</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 9 days 58 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$103</span>
                                        </li>
                                        <li><span class="product-old-price">$257</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Gamer Chick" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">Playstation Accessories</h5>
                                <div class="product-desciption">Habitant in laoreet aenean sapien</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 7 days 35 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$68</span>
                                        </li>
                                        <li><span class="product-old-price">$159</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                    <a class="col-md-2 col-masonry" href="#">
                        <div class="product-thumb">
                            <header class="product-header">
                                <img src="img/800x600.png" alt="Image Alternative text" title="AMaze" />
                            </header>
                            <div class="product-inner">
                                <h5 class="product-title">New Glass Collection</h5>
                                <div class="product-desciption">Dolor class in potenti natoque</div>
                                <div class="product-meta"><span class="product-time"><i class="fa fa-clock-o"></i> 5 days 26 h</span>
                                    <ul class="product-price-list">
                                        <li><span class="product-price">$57</span>
                                        </li>
                                        <li><span class="product-old-price">$158</span>
                                        </li>
                                    </ul>
                                </div>
                                <p class="product-location"><i class="fa fa-map-marker"></i> Boston</p>
                            </div>
                        </div>
                    </a>
                </div>	<a href="#" class="btn btn-primary btn-ghost">Explore All New Deals</a>
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
