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
                
                    <div id="review-dialog" class="mfp-with-anim mfp-hide mfp-dialog clearfix">
                        <h3>Add a Review</h3>
                        <form>
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" placeholder="e.g. John Doe" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>E-mail</label>
                                <input type="text" placeholder="e.g. jogndoe@gmail.com" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>Review</label>
                                <textarea class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <label>Rating</label>
                                <ul class="icon-list icon-list-inline star-rating" id="star-rating">
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                </ul>
                            </div>
                            <input type="submit" class="btn btn-primary" value="Submit" />
                        </form>
                    </div>
                                    
                    <div class="row">
                        <div class="col-md-7">
                            <div class="fotorama" data-nav="thumbs" data-allowfullscreen="1" data-thumbheight="150" data-thumbwidth="150">
                                <img src="img/800x600.png" alt="Image Alternative text" title="Gamer Chick" />                                
                            </div>
                        </div> 
                        <div class="col-md-5">
                            <div class="product-info box">
	                            <ul class="icon-group icon-list-rating text-color" title="4.5/5 rating">
	                                    <li><i class="fa fa-star"></i>
	                                    </li>
	                                    <li><i class="fa fa-star"></i>
	                                    </li>
	                                    <li><i class="fa fa-star"></i>
	                                    </li>
	                                    <li><i class="fa fa-star"></i>
	                                    </li>
	                                    <li><i class="fa fa-star-half-empty"></i>
	                                    </li>
	                             </ul>	
	                             
	                             <small><a href="#" class="text-muted">based on 8 reviews</a></small>
                            
                                <h3>${requestScope.dealPostDetail.title}</h3>
                                <p class="product-info-price">$${requestScope.dealPostDetail.salePrice}</p>
                                <div class="row">
                                	<div class="col-md-offset-9 col-md-3">
                                		<a href="getMatchingDeals.htm?dropWantedId=${requestScope.dealWantedToMatch.id}" class="btn btn-primary" style="float: right;">Back to Matches</a> 
                                	</div>
                            	</div>                                                             
                            </div> 
                            	<input id="txtDealWantedToMatchHidden" type="hidden" value="${requestScope.dealWantedToMatch.id}"/>                           
                        </div>
                    </div>
                    
                    <div class="gap"></div>
					
					<!-- Details of the item in tabs -->
					
                    <div class="tabbable">
                        <ul class="nav nav-tabs" id="myTab">
                            <li class="active"><a href="#tab-1" data-toggle="tab"><i class="fa fa-pencil"></i>Desciption</a>
                            </li>
                            <li><a href="#tab-2" data-toggle="tab"><i class="fa fa-info"></i>Additional Information</a>
                            </li>
                            <li><a href="#tab-3" data-toggle="tab"><i class="fa fa-truck"></i>Shipping & Payment</a>
                            </li>
                            <li><a href="#tab-4" data-toggle="tab"><i class="fa fa-comments"></i>Reviews</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="tab-1">
                            	<p>${requestScope.dealPostDetail.description}</p>
                            </div>
                            <div class="tab-pane fade" id="tab-2">
                                <table class="table table-striped mb0">
                                    <tbody>
                                        <tr>
                                            <td>Sale Price</td>
                                            <td>${requestScope.dealPostDetail.salePrice}</td>
                                        </tr>
                                        <tr>
                                            <td>Retail Price</td>
                                            <td>${requestScope.dealPostDetail.retailPrice}</td>
                                        </tr>
                                        <tr>
                                            <td>Discount</td>
                                            <td>${requestScope.dealPostDetail.discountPercent}%</td>
                                        </tr>
                                        <tr>
                                            <td>Time Remaining</td>
                                            <td>${requestScope.dealPostDetail.timeToExpire}</td>
                                        </tr>
                                        <tr>
                                            <td>Special Instructions</td>
                                            <td>${requestScope.dealPostDetail.specialInstructions}</td>
                                        </tr>                                                                               
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="tab-3">
                                <p>Cursus libero commodo ipsum conubia curabitur augue dui cras nisl netus mus morbi et proin penatibus dolor consequat condimentum enim laoreet nullam sapien montes condimentum quis in enim mollis vivamus</p>
                                <p>A nisi netus erat dolor fames nibh at condimentum netus interdum iaculis euismod cursus hendrerit parturient suscipit erat felis taciti inceptos nec nisi luctus ac habitasse aptent lobortis etiam facilisis</p>
                            </div>
                            <div class="tab-pane fade" id="tab-4">
                                <ul class="comments-list">
                                    <li>
                                        <!-- REVIEW -->
                                        <article class="comment">
                                            <div class="comment-author">
                                                <img src="img/50x50.png" alt="Image Alternative text" title="Gamer Chick" />
                                            </div>
                                            <div class="comment-inner">
                                                <ul class="icon-group icon-list-rating comment-review-rate" title="5/5 rating">
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                </ul><span class="comment-author-name">Richard Jones</span>
                                                <p class="comment-content">Dolor nibh aliquet imperdiet ante mollis duis ultrices magna volutpat molestie nam magna mollis potenti penatibus mauris etiam venenatis auctor montes sem ac consequat aliquet duis at penatibus vestibulum condimentum</p>
                                            </div>
                                        </article>
                                    </li>
                                    <li>
                                        <!-- REVIEW -->
                                        <article class="comment">
                                            <div class="comment-author">
                                                <img src="img/50x50.png" alt="Image Alternative text" title="Ana 29" />
                                            </div>
                                            <div class="comment-inner">
                                                <ul class="icon-group icon-list-rating comment-review-rate" title="4/5 rating">
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star-o"></i>
                                                    </li>
                                                </ul><span class="comment-author-name">Dylan Taylor</span>
                                                <p class="comment-content">Arcu ante litora eu tristique fusce metus sem feugiat etiam tortor aliquet diam sollicitudin dictumst placerat scelerisque massa luctus vitae aliquam egestas himenaeos nostra eleifend lobortis velit sociis eget</p>
                                            </div>
                                        </article>
                                    </li>
                                    <li>
                                        <!-- REVIEW -->
                                        <article class="comment">
                                            <div class="comment-author">
                                                <img src="img/50x50.png" alt="Image Alternative text" title="Afro" />
                                            </div>
                                            <div class="comment-inner">
                                                <ul class="icon-group icon-list-rating comment-review-rate" title="5/5 rating">
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                </ul><span class="comment-author-name">Carol Blevins</span>
                                                <p class="comment-content">Habitant tempus nec quisque suspendisse neque iaculis hendrerit integer fringilla at placerat bibendum gravida</p>
                                            </div>
                                        </article>
                                    </li>
                                    <li>
                                        <!-- REVIEW -->
                                        <article class="comment">
                                            <div class="comment-author">
                                                <img src="img/50x50.png" alt="Image Alternative text" title="Bubbles" />
                                            </div>
                                            <div class="comment-inner">
                                                <ul class="icon-group icon-list-rating comment-review-rate" title="4/5 rating">
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star-o"></i>
                                                    </li>
                                                </ul><span class="comment-author-name">Bernadette Cornish</span>
                                                <p class="comment-content">Imperdiet habitasse dignissim convallis felis libero dictumst taciti placerat adipiscing</p>
                                            </div>
                                        </article>
                                    </li>
                                    <li>
                                        <!-- REVIEW -->
                                        <article class="comment">
                                            <div class="comment-author">
                                                <img src="img/50x50.png" alt="Image Alternative text" title="Me with the Uke" />
                                            </div>
                                            <div class="comment-inner">
                                                <ul class="icon-group icon-list-rating comment-review-rate" title="4/5 rating">
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star-o"></i>
                                                    </li>
                                                </ul><span class="comment-author-name">Alison Mackenzie</span>
                                                <p class="comment-content">Natoque sit dis tristique nisi morbi varius himenaeos dui urna a molestie enim ut vivamus consequat arcu aliquam velit gravida maecenas dictumst mollis ultricies tellus tempor</p>
                                            </div>
                                        </article>
                                    </li>
                                    <li>
                                        <!-- REVIEW -->
                                        <article class="comment">
                                            <div class="comment-author">
                                                <img src="img/50x50.png" alt="Image Alternative text" title="AMaze" />
                                            </div>
                                            <div class="comment-inner">
                                                <ul class="icon-group icon-list-rating comment-review-rate" title="5/5 rating">
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                </ul><span class="comment-author-name">John Mathis</span>
                                                <p class="comment-content">Himenaeos eu taciti ad placerat praesent penatibus dui feugiat porttitor porttitor leo ad neque litora sem vestibulum dolor turpis tortor tortor tristique massa</p>
                                            </div>
                                        </article>
                                    </li>
                                    <li>
                                        <!-- REVIEW -->
                                        <article class="comment">
                                            <div class="comment-author">
                                                <img src="img/50x50.png" alt="Image Alternative text" title="Chiara" />
                                            </div>
                                            <div class="comment-inner">
                                                <ul class="icon-group icon-list-rating comment-review-rate" title="4/5 rating">
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star-o"></i>
                                                    </li>
                                                </ul><span class="comment-author-name">Richard Jones</span>
                                                <p class="comment-content">Faucibus arcu inceptos varius vitae semper congue ridiculus nibh elit platea quisque tempus platea nostra ac arcu accumsan sollicitudin condimentum sodales molestie taciti cubilia libero congue</p>
                                            </div>
                                        </article>
                                    </li>
                                    <li>
                                        <!-- REVIEW -->
                                        <article class="comment">
                                            <div class="comment-author">
                                                <img src="img/50x50.png" alt="Image Alternative text" title="Andrea" />
                                            </div>
                                            <div class="comment-inner">
                                                <ul class="icon-group icon-list-rating comment-review-rate" title="4/5 rating">
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star"></i>
                                                    </li>
                                                    <li><i class="fa fa-star-o"></i>
                                                    </li>
                                                </ul><span class="comment-author-name">Joseph Watson</span>
                                                <p class="comment-content">Posuere eleifend massa etiam per feugiat tempus tortor ultrices praesent integer</p>
                                            </div>
                                        </article>
                                    </li>
                                </ul><a class="popup-text btn btn-primary" href="#review-dialog" data-effect="mfp-zoom-out"><i class="fa fa-pencil"></i> Add a review</a>
                            </div>
                        </div>
                    </div>					
					
					
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