<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

	<head>
	    <title>Drop - Item Details</title>
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

        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">               
                                    
                    <div class="row">
                        <div class="col-md-7">
                            <div class="fotorama" data-nav="thumbs" data-allowfullscreen="1" data-thumbheight="150" data-thumbwidth="150">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Gamer Chick" />                                
                            </div>
                        </div> 
                        <div class="col-md-5">
                            <div class="product-info box">
                            	<c:if test="${requestScope.dealPostDetail.totalRatings gt 0}">
                            	                            		
                            		<c:set var="result"  scope="request"  value="${requestScope.dealPostDetail.averageRating * 2}"/>
                            		
									<c:choose>
										<c:when test='${(result)%2 eq 0}'>
											<c:set var="halfStarRequired" scope="request" value="false"/>
										</c:when>
										<c:otherwise>
											<c:set var="halfStarRequired" scope="request" value="true"/>
										</c:otherwise>
									</c:choose>

                            		<ul class="icon-group icon-list-rating text-color">
	                            		<c:forEach var="i" begin="1" end="${result/2}">
	                            			<li>
	                            				<i class="fa fa-star"></i>
	                                    	</li>
	                            		</c:forEach>
	                            		<c:if test="${halfStarRequired eq true}">
	                            			<li>
	                            				<i class="fa fa-star-half-empty"></i>
	                                   	 </li>
	                            		</c:if>
                            		</ul>
                            		<small><a href="#tab-3" class="text-muted">based on ${requestScope.dealPostDetail.totalRatings} reviews</a></small>                  		                            		
                            	</c:if>                            	                             	
                            	 	                            		                          
                                <h3>${requestScope.dealPostDetail.title}</h3>
                                
                                <p class="product-info-price">$${requestScope.dealPostDetail.salePrice}</p>
                                
                                <div class="row">
                                	<div class="col-md-offset-9 col-md-3">
                                		<a href="home.htm" class="btn btn-primary" style="float: right;">Back to Home</a> 
                                	</div>
                            	</div>                                                             
                            </div> 
                            	       
                        </div>
                    </div>
                    
                    <div class="gap"></div>
					
					<!-- Details of the item in tabs -->
					
                    <div class="tabbable">
                        <ul class="nav nav-tabs" id="myTab">
                            <li class="active"><a href="#tab-1" data-toggle="tab"><i class="fa fa-pencil"></i>Desciption</a>
                            </li>                                                  
                            <li><a href="#tab-2" data-toggle="tab"><i class="fa fa-comments"></i>Reviews</a>
                            </li>
                        </ul>
                        
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="tab-1">
                            	<p>${requestScope.dealPostDetail.description}</p>
                            </div>

                            <div class="tab-pane fade" id="tab-2">
                            	<c:forEach items="${requestScope.dealPostDetail.dropRatings}" var="dropRating">
	                            	<ul class="comments-list">
	                                    <li>
	                                        <!-- REVIEW -->
	                                        <article class="comment">
	                                            <div class="comment-author">
	                                                <img src="img/50x50.png" alt="Image Alternative text" title="Gamer Chick" />
	                                            </div>
	                                            <div class="comment-inner">
	                                            	<c:if test="${dropRating.rating gt 0}">
	                                            		<ul class="icon-group icon-list-rating comment-review-rate" title="5/5 rating">
		                                            		<c:forEach var="i" begin="1" end="${dropRating.rating}">
	   															<li>
	   																<i class="fa fa-star"></i>
	                                                    		</li>
															</c:forEach>
														</ul>	
	                                            	</c:if>	                                            	
	                                                <span class="comment-author-name">${dropRating.rater.firstName} ${dropRating.rater.lastName}</span>
	                                                <p class="comment-content">${dropRating.description}</p>
	                                            </div>
	                                        </article>
	                                    </li>
	                                </ul>                             	
                            	</c:forEach> 
                            	      
                            	<c:if test="${sessionScope.user ne null}">
                            		<a onclick="rateDrop(${requestScope.dealPostDetail.id})" class="btn btn-primary" data-effect="mfp-move-from-top" data-toggle="tooltip" data-placement="right">Add a review</a>
                            	</c:if>                                                                                       
                            </div>
                        </div>
                    </div>										
					
                    <div class="gap"></div>
                   
                    <div class="gap gap-mini"></div>
                                                                                             
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
        
	        $('.dateTimePicker').datetimepicker({
	            pick12HourFormat: false,
	        });  
        
        	function hideViewDetails(id) { 
        		var dealPostId = id;
				var dealWantedId = $("#txtDealWantedToMatchHidden").val();

        		var data = { dealPostId : dealPostId , dealWantedId : dealWantedId};
        		
        		$.ajax({
	         		   url : "acceptDeal.htm",
	         		   dataType: "text",
	         		   data : data,
	         		   type : "POST",	         		   
	         		   success : function(response) {
	         			  $("#btnViewDealDetails"+id).show();
	              		  $("#btnRejectDeal"+id).show();
	              		  $("#btnGotIt"+id).show();
	              		  $("#btnAcceptDeal"+id).hide();
	         		   },
	         		   error : function(jqXHR, textStatus, errorThrown) {
	         			 	alert("Some error");		         			
	         		   }
	         		  });
        	}	 
        	
        	function rejectMatchingDeal($dealPostId) {        		
        		
        		$.magnificPopup.open({
        			
        			items: {
            		      src: 'showReasonToRejectDialog.htm?dealPostId='+$dealPostId+"&dealWantedId="+$("#txtDealWantedToMatchHidden").val(),
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
        	
        	
        	function rateDrop($dealPostId) {        		
        		
        		$.magnificPopup.open({
        			
        			items: {
            		      src: 'showDropRatingDialog.htm?dealPostId='+$dealPostId+'&dealWantedId='+$dealWantedId,
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
        
    </div>

</body>
</html>