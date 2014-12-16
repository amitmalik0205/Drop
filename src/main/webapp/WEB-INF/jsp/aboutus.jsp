<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head>
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
  
		
		<jsp:include page="search.jsp"/>
	
	     <div class="gap"></div>	    

        <div class="container">
            <h1>Hello, World!</h1>
            <p>Quisque varius consequat platea purus iaculis quam dis suspendisse augue consequat in porta quis nec purus habitasse orci velit dui justo leo nostra etiam vulputate imperdiet id tristique per dictumst non nam mollis arcu cubilia dui dignissim proin felis fringilla primis justo viverra tincidunt pulvinar sociis maecenas gravida felis dolor penatibus pharetra aptent elementum taciti habitant aptent dapibus urna fringilla tortor porttitor donec commodo platea arcu molestie non ad vestibulum erat fusce vitae odio egestas enim curabitur sagittis ridiculus senectus parturient orci purus accumsan at ullamcorper magnis facilisi maecenas senectus suscipit odio massa class dictum mattis duis ut facilisis tempus dui augue natoque duis nibh primis sociis magna vehicula nostra lacus dapibus himenaeos vel dolor duis libero ligula magnis consectetur facilisi habitasse curae semper maecenas habitasse metus scelerisque consectetur placerat leo aenean sagittis arcu eros vitae netus porta senectus non consectetur felis litora inceptos ligula cras aliquam fames dui tempus tempor sed luctus quis sociis tellus sociosqu magnis elit venenatis in faucibus venenatis ante proin ultrices feugiat non velit mollis dictumst nisi pellentesque torquent tellus magnis ultricies dis elementum est habitant diam nullam iaculis aliquam aliquet tempus scelerisque nascetur mattis gravida a amet sem suspendisse vehicula arcu parturient rhoncus consectetur</p>
            <p>Porttitor erat ultrices lobortis natoque gravida pellentesque lorem quisque cras senectus commodo suspendisse gravida quis senectus vestibulum dis fames nisl pharetra inceptos semper ullamcorper auctor dictum conubia praesent quis eros neque magnis senectus sollicitudin aliquam donec taciti pharetra semper fermentum vitae vehicula risus sem purus in erat cubilia et egestas condimentum etiam curabitur cursus pellentesque porttitor lorem lacus rhoncus himenaeos sociis pulvinar ridiculus quam fermentum felis metus senectus molestie tempor habitant ante nascetur nunc congue inceptos ac faucibus cras fringilla velit convallis etiam sociis ac sed tempus hendrerit enim mus sagittis torquent etiam commodo gravida tincidunt dignissim pellentesque id felis nec vel eros ornare mi dis sem suscipit orci congue imperdiet integer ad turpis sociis eros eleifend fermentum luctus tristique inceptos mus dictum euismod condimentum fringilla eu torquent laoreet tempor nulla phasellus volutpat tristique montes conubia taciti nunc torquent dignissim parturient risus ad habitant leo nascetur quis nascetur sociosqu posuere libero felis ultricies lacus vel nam facilisis lectus nisi ridiculus aptent vestibulum eleifend ultrices rutrum primis dolor lorem neque varius habitant inceptos aliquet feugiat ad dapibus erat class aptent purus senectus vitae scelerisque nisl sit diam class posuere tempor lacinia euismod mi ante varius duis aliquet vel laoreet eleifend porttitor per convallis dictum maecenas scelerisque purus inceptos suspendisse laoreet malesuada primis scelerisque diam consectetur mauris rhoncus condimentum scelerisque nostra primis senectus eu habitasse cursus justo rutrum neque aenean magna accumsan ornare scelerisque convallis dignissim imperdiet leo ut amet curabitur lacus vulputate rutrum risus primis tristique conubia nostra sed quis facilisis massa nullam cubilia ad sapien per cursus malesuada fermentum tortor interdum litora lacus risus vitae conubia vestibulum etiam leo dui etiam tempor urna ut dolor molestie ac ad blandit praesent rutrum pharetra ridiculus integer sodales nullam non ante eu class lacus odio porttitor sagittis netus nullam dictumst varius ultricies volutpat</p>
            <p>Fringilla erat rutrum dis eleifend vestibulum a dui magna rutrum ut massa dictumst ultricies justo ante ullamcorper congue scelerisque fames risus nam ultrices enim himenaeos volutpat conubia habitasse ridiculus nullam erat nec nibh dictum pellentesque imperdiet ultrices tellus dictum quisque gravida justo curae dapibus velit ante laoreet natoque hendrerit lobortis hendrerit lectus ornare tristique rutrum purus interdum auctor aliquam vel pulvinar dictumst venenatis aliquam eget vitae ullamcorper ligula vitae parturient primis dignissim natoque ut quam posuere congue amet sociis vestibulum dictumst porttitor fames leo tortor consequat ad congue sit adipiscing felis lacus augue nullam amet himenaeos elementum ornare mollis integer interdum fames parturient magnis diam feugiat pretium consequat odio ultrices nisi elementum imperdiet sem nulla feugiat mauris dui nunc habitasse nulla sollicitudin vitae risus sagittis ut malesuada neque nullam dignissim montes egestas duis sociosqu vel pharetra curae netus auctor interdum mollis per pulvinar imperdiet velit sapien faucibus bibendum mattis dictum</p>
            <p>Nulla lobortis rutrum morbi accumsan ac tempus consequat fringilla penatibus imperdiet nostra ultrices ullamcorper inceptos elit nullam primis mattis primis sapien natoque semper facilisi eros interdum turpis parturient iaculis praesent tempus adipiscing vivamus ligula feugiat conubia justo condimentum gravida dictumst molestie nullam metus turpis pharetra maecenas velit metus pharetra pellentesque elementum purus torquent non lobortis sapien curabitur fames eget imperdiet semper egestas nullam pulvinar praesent feugiat elit neque dolor bibendum dictumst consequat consectetur ligula aliquam bibendum scelerisque urna tincidunt phasellus condimentum litora gravida odio hac metus magnis cursus lacinia bibendum urna vulputate nunc nisl purus fringilla sociis at mollis turpis hendrerit urna inceptos urna auctor dapibus ultrices conubia fames enim dis posuere tristique cubilia senectus inceptos donec faucibus torquent ipsum at primis a luctus senectus taciti elementum scelerisque felis phasellus dolor congue tortor pellentesque viverra nulla integer quis dictum amet dictum leo tempus nostra augue platea phasellus aliquet lacus vitae per mollis blandit enim libero sociosqu imperdiet vulputate congue cubilia dictum diam elit dictumst sed erat vel dictum congue dui semper sodales laoreet mattis ut in nullam consequat turpis mus aenean mattis senectus mollis luctus ornare et at feugiat a habitasse hendrerit justo mollis penatibus cras blandit proin euismod nostra dignissim morbi sit mattis at ligula himenaeos ante morbi lacinia mattis varius vulputate ultricies habitant ipsum elit ultrices lorem diam orci tempus vitae ligula aenean rutrum ligula vestibulum quam bibendum a urna ornare quisque dui arcu at etiam diam neque sollicitudin nisl magna donec gravida dui enim cursus in at</p>
            <p>Amet curae fringilla orci vitae lacinia ad tortor nulla per rutrum faucibus class consectetur luctus sodales vitae luctus leo vitae hac viverra mi pellentesque sollicitudin habitant at iaculis mi rutrum turpis blandit vulputate ad inceptos et gravida est id tincidunt cursus elit dignissim interdum mus diam placerat sed congue risus dis mollis ultrices est nec netus nec hac cursus ipsum dictum eu sed volutpat felis quam sociosqu risus blandit viverra consectetur quis bibendum porta vivamus imperdiet in congue est odio nulla vehicula feugiat viverra sollicitudin justo a in phasellus enim odio feugiat dolor pellentesque auctor quisque ad hac parturient curae donec nec eget magnis himenaeos adipiscing cras eleifend non ultricies egestas habitant massa vulputate sit fames curae libero scelerisque auctor curae ad luctus consectetur nisl turpis potenti interdum blandit sodales sodales justo porttitor amet praesent congue aliquet eget per venenatis dis non mus ut rhoncus ullamcorper posuere malesuada sociosqu habitasse vitae nullam accumsan pellentesque porttitor risus id aliquam proin urna mollis dolor interdum morbi natoque imperdiet nec nisl bibendum fames nec nam curae dapibus erat in integer posuere velit nascetur tortor cras metus ante elit dapibus facilisis felis molestie aenean conubia feugiat hac laoreet habitant mus semper nisl semper vestibulum malesuada erat taciti aliquet malesuada non fusce primis ipsum id netus mattis fringilla sapien libero habitasse sollicitudin nam et primis arcu tristique tortor nisi mus montes per laoreet laoreet porta lacus dui euismod bibendum metus imperdiet dui ultrices sapien varius scelerisque ultricies netus fringilla cras fermentum quam molestie sit sit nulla ad venenatis parturient aliquet purus convallis mi hac dis lacus penatibus ipsum feugiat ligula rutrum mus nostra facilisis condimentum nullam adipiscing nam habitant vestibulum aenean fermentum primis lacinia netus dictum venenatis fusce dictum eleifend etiam nam rhoncus augue imperdiet lacinia sit scelerisque massa risus felis a hendrerit vestibulum potenti</p>
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
        	$("#aboutusli").attr("class","active");
        </script>	
        
    </div>
</body>

</html>
