<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>

	<head>
	    <title>Drop - My Drop Wanted</title>
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
	        
	        
	        <jsp:include page="dealWantedDialog.jsp"/>
        
        
           <jsp:include page="dealPostDialog.jsp"/>
	        
	        
	        <div class="container">
	        
	            <div class="row">
	            
	               <jsp:include page="profileLeftNevigation.jsp"/>
	               
	                <div class="col-md-9">
	                    <div class="row">
	                        <div class="col-md-12">
	                            <table class="table table-order">
	                                <thead>
	                                    <tr>
	                                    	<th></th>
	                                    	<th>Title</th>
	                                        <th>Category</th>	                                        
	                                        <th>PostedOn</th>
	                                        <th></th>
	                                        <th></th>
	                                        <th></th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach var="dealWanted" items="${requestScope.dealWantedList}">
	                                		<tr>
		                                        <td class="table-order-img">
		                                            <a href="#">
		                                                <img src="img/70x70.png" alt="Image Alternative text" title="AMaze" />
		                                            </a>
		                                        </td>
		                                        <td>${dealWanted.title}</td>
		                                        <td>${dealWanted.dealCategory.name}</td>
		                                        <td>${dealWanted.date}</td>
		                                        <td><a class="btn btn-primary" href="#" onclick="editDropWanted(this);" id="${dealWanted.id}" data-effect="mfp-move-from-top" data-toggle="tooltip" data-placement="right">Edit</a></td>
		                                        <td><a class="btn btn-primary" href="#" onclick="deleteDropWanted(this);" id="${dealWanted.id}" data-effect="mfp-move-from-top" data-toggle="tooltip" data-placement="right">Delete</a></td>
		                                        <td><a class="btn btn-primary" href="#">Matching Deals</a></td>
	                                    	</tr>
	                                	</c:forEach>	                                    
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                    <div class="gap"></div>
	                </div>
	            </div>
        </div> <!-- End container -->
			
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
        	$("#mydropwantedli").attr("class","active");
        	$("#myaccountli").attr("class","active");
        	
        	$('.dateTimePicker').datetimepicker({
                pick12HourFormat: false,
            });
        	
        	
        	function editDropWanted($anchorEditDropWanted) {
        		
        		$.magnificPopup.open({
        			
        			items: {
            		      src: 'showEditDropWanted.htm?dropWantedId='+$anchorEditDropWanted.id,
            		      type: 'ajax'
            		  },
        			
            		  ajax: {
              			  
             			   // Ajax settings object that will extend default one - http://api.jquery.com/jQuery.ajax/#jQuery-ajax-settings
             			  // For example-- settings: {cache:false, async:false}
             			  settings: null, 
             			            			 
             			   // CSS class that will be added to body during the loading (adds "progress" cursor)
             			  cursor: 'mfp-ajax-cur',
             			  
             			  //  Error message, can contain %curr% and %total% tags if gallery is enabled
             			  tError: '<a href="%url%">The content</a> could not be loaded.....' 
             			},
             		  	
           			callbacks: {
           				beforeOpen: function () { }
             		},
           
           			closeBtnInside: true,
           			
           			closeOnContentClick : false
        			
        		});
        	}
        	
        	function deleteDropWanted($anchorDeleteDropWanted) {
        		
        		$.magnificPopup.open({
        			
        			items: {
            		      src: 'showReasonToDeleteDialog.htm?dealId='+$anchorDeleteDropWanted.id,
            		      type: 'ajax'
            		  },
        			
            		  ajax: {
              			  
             			   // Ajax settings object that will extend default one - http://api.jquery.com/jQuery.ajax/#jQuery-ajax-settings
             			  // For example-- settings: {cache:false, async:false}
             			  settings: null, 
             			            			 
             			   // CSS class that will be added to body during the loading (adds "progress" cursor)
             			  cursor: 'mfp-ajax-cur',
             			  
             			  //  Error message, can contain %curr% and %total% tags if gallery is enabled
             			  tError: '<a href="%url%">The content</a> could not be loaded.....' 
             			},
             		  	
           			callbacks: {
           				beforeOpen: function () { }
             		},
           
           			closeBtnInside: true,
           			
           			closeOnContentClick : false
        			
        		});
        	}
        	
        </script>
			
	</div> <!-- End Global Wrap -->
	
</body>
</html>