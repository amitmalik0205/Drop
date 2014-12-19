<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

	<head>
	    <title>Drop - Matching Deals</title>
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

		<jsp:include page="dealWantedDialog.jsp"/>
        
        <jsp:include page="dealPostDialog.jsp"/>


        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <div class="row">
                        <!-- <div class="col-md-7">
                            <div class="fotorama" data-nav="thumbs" data-allowfullscreen="1" data-thumbheight="150" data-thumbwidth="150">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Gamer Chick" />
                                <img src="img/800x600.png" alt="Image Alternative text" title="AMaze" />
                                <img src="img/800x600.png" alt="Image Alternative text" title="Urbex Esch/Lux with Laney and Laaaaag" />
                                <img src="img/800x600.png" alt="Image Alternative text" title="Food is Pride" />
                            </div>
                        </div> -->
                        <div class="col-md-12">
                            <div class="product-info box">
                                <h3>${requestScope.dealWantedToMatch.title}</h3>
                                <p class="product-info-price">$${requestScope.dealWantedToMatch.maxPrice}</p>
                                <p class="text-smaller text-muted">${requestScope.dealWantedToMatch.description}</p>  
                                <div class="row">
                                	<div class="col-md-offset-9 col-md-3">
                                		<a href="showMyDropWanted.htm" class="btn btn-primary" style="float: right;">Back to My Drop Wanted</a> </div>
                            		</div>                                                             
                            	</div> 
                            	<input id="txtDealWantedToMatchHidden" type="hidden" value="${requestScope.dealWantedToMatch.id}"/>                           
                        </div>
                    </div>
                    <div class="gap"></div>
					
					<!-- <div calss="tabblale"></div> -->
					
                    <div class="gap"></div>
                    <h3>Matching Drops</h3>
                    <div class="gap gap-mini"></div>
                    
              <div class="row">
                
                <div class="col-md-12">
<!--                     <div class="row">
                        <div class="col-md-3">
                            <div class="product-sort">
                                <span class="product-sort-selected">sort by <b>Price</b></span>
                                <a href="#" class="product-sort-order fa fa-angle-down"></a>
                                <ul>
                                    <li><a href="#">sort by Name</a>
                                    </li>
                                    <li><a href="#">sort by Ended Soon</a>
                                    </li>
                                    <li><a href="#">sort by Popularity</a>
                                    </li>
                                    <li><a href="#">sort by Location</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-2 col-md-offset-7">
                            <div class="product-view pull-right">
                                <a class="fa fa-th-large" href="category-page-coupon.html"></a>
                                <a class="fa fa-list active" href="#"></a>
                            </div>
                        </div>
                    </div> -->
                    
                    <c:forEach var="dealPost" items="${requestScope.matchingDealPostList}" varStatus="counter">
                    	<div class="product-thumb product-thumb-horizontal">
	                        <header class="product-header">
	                            <img src="img/800x600.png" alt="Image Alternative text" title="The Violin" />
	                        </header>
	                        <div class="product-inner">
	                            <h5 class="product-title">${dealPost.title}</h5>
	                            <div class="product-desciption">${dealPost.description}</div>
	                            
	                            <div class="product-meta" style="width: 55%"><span class="product-time"><i class="fa fa-clock-o"></i>${dealPost.timeToExpire}</span>
	                                <ul class="product-price-list">
	                                    <li><span class="product-price">$${dealPost.salePrice}</span>
	                                    </li>
	                                    <li><span class="product-old-price">$${dealPost.retailPrice}</span>
	                                    </li>
	                                    <li><span class="product-save">${dealPost.discountPercent}%</span>
	                                    </li>
	                                </ul>	
	                                <ul class="product-actions-list">
	                                	<!-- Enable Accept button only for first deal -->
				                    	<c:if test="${counter.count eq 1}">
				                    	    <li>
				                    		  <a onclick="hideViewDetails(${dealPost.id})" id="btnAcceptDeal${dealPost.id}" class="btn btn-primary">Accept the Deal</a>
				                    		</li>
				                    	</c:if>
				                    	<c:if test="${counter.count ne 1}">
				                    		<li>
				                    			<a onclick="hideViewDetails(${dealPost.id})" id="btnAcceptDeal${dealPost.id}" class="btn btn-primary" style="display: none">Accept the Deal</a>
				                    		</li>
				                    	</c:if>
	                                	<li>
	                                		<a href="#"  id="btnViewDealDetails${dealPost.id}" class="btn btn-primary" style="display: none">View Details</a>
	                                	</li>
	                                	<li>
	                                		<a href="#"  id="btnGotIt${dealPost.id}" class="btn btn-primary" style="display: none">Got It</a>
	                                	</li>	                                	                           	 
                    					<li>
                    						<a onclick="rejectMatchingDeal(${dealPost.id})" style="display: none" id="btnRejectDeal${dealPost.id}" class="btn btn-primary" data-effect="mfp-move-from-top" data-toggle="tooltip" data-placement="right">Reject</a>
                    					</li>                    					   
	                                </ul>                              
	                            </div>		                                                      
	                        </div>
                    	</div>                     	                                        	        
                    </c:forEach>            
                    
                    			<c:if test="${fn:length(requestScope.matchingDealPostList) lt 1}">
	                        		No Matches found
	                        	</c:if>       

<!--                     <ul class="pagination">
                        <li class="prev disabled">
                            <a href="#"></a>
                        </li>
                        <li class="active"><a href="#">1</a>
                        </li>
                        <li><a href="#">2</a>
                        </li>
                        <li><a href="#">3</a>
                        </li>
                        <li><a href="#">4</a>
                        </li>
                        <li><a href="#">5</a>
                        </li>
                        <li class="next">
                            <a href="#"></a>
                        </li>
                    </ul> -->
                    <div class="gap"></div>
                </div>
            </div>
                    
                    
 
                    <div class="gap gap-small"></div>
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
        	
        </script>
        
    </div>

</body>
</html>