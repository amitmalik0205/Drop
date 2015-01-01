<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <title>Drop - Drop Post</title>
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
                <div class="col-md-3">
                    <aside class="sidebar-left">                    	 
                        <ul class="nav nav-tabs nav-stacked nav-coupon-category">
                        	<c:if test="${sessionScope.sessionSearchDealForm.selectedCategory eq 'All'}">
                        		<li class="active"><a href="searchByCategory.htm?categoryName=All"><i class="fa fa-ticket"></i>All</a></li>
                        	</c:if> 
                        	<c:if test="${sessionScope.sessionSearchDealForm.selectedCategory ne 'All'}">
                        		<li><a href="searchByCategory.htm?categoryName=All"><i class="fa fa-ticket"></i>All</a></li>
                        	</c:if>                       	
                        	<c:forEach var="category" items="${requestScope.dealCategories}" varStatus="counter">
                        		<c:if test="${sessionScope.sessionSearchDealForm.selectedCategory eq category.name}">
                        			<li class="active" id="${category.id}"><a href="searchByCategory.htm?categoryName=${category.name}"><i class="fa fa-ticket"></i>${category.name}</a></li>
                        		</c:if>  
                        		<c:if test="${sessionScope.sessionSearchDealForm.selectedCategory ne category.name}">
                        			<li id="${category.id}"><a href="searchByCategory.htm?categoryName=${category.name}"><i class="fa fa-ticket"></i>${category.name}</a></li>
                        		</c:if>                  	 		
                    	 	</c:forEach>                                                       
                        </ul>
                        <div class="sidebar-box">
                            <h5>Filter By Price</h5>
                            <input type="text" id="price-slider">
                        </div>
                    </aside>
                </div>
                <div class="col-md-9">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="product-sort">
                            	<c:choose>
                            		<c:when test="${sessionScope.sessionSearchDealForm.sortType == 'PRICE'}">
                            			<span class="product-sort-selected">sort by <b>Price</b></span>
                                		<a href="sortSearch.htm?sortBy=PRICE" class="product-sort-order fa fa-angle-down"></a>
                                		<ul>
		                                    <li><a href="sortSearch.htm?sortBy=TITLE">sort by Title</a>
		                                    </li>
		                                    <li><a href="sortSearch.htm?sortBy=DATE">sort by Date</a>
		                                    </li>
		                                </ul>
                            		</c:when>
                            		<c:when test="${sessionScope.sessionSearchDealForm.sortType == 'TITLE'}">
                            			<span class="product-sort-selected">sort by <b>Title</b></span>
                                		<a href="sortSearch.htm?sortBy=TITLE" class="product-sort-order fa fa-angle-down"></a>
                                		<ul>
		                                    <li><a href="sortSearch.htm?sortBy=PRICE">sort by Price</a>
		                                    </li>
		                                    <li><a href="sortSearch.htm?sortBy=DATE">sort by Date</a>
		                                    </li>
		                                </ul>
                            		</c:when> 
                            		<c:when test="${sessionScope.sessionSearchDealForm.sortType == 'DATE'}">
                            			<span class="product-sort-selected">sort by <b>Date</b></span>
                                		<a href="sortSearch.htm?sortBy=DATE" class="product-sort-order fa fa-angle-down"></a>
                                		<ul>
		                                    <li><a href="sortSearch.htm?sortBy=PRICE">sort by Price</a>
		                                    </li>
		                                    <li><a href="sortSearch.htm?sortBy=TITLE">sort by Title</a>
		                                    </li>
		                                </ul>
                            		</c:when>                            
                            	</c:choose>                                                       
                            </div>
                        </div>
                        <!-- <div class="col-md-2 col-md-offset-7">
                            <div class="product-view pull-right">
                                <a class="fa fa-th-large active" href="#"></a>
                                <a class="fa fa-list" href="category-page-thumbnails-shop-horizontal.html"></a>
                            </div>
                        </div> -->
                    </div>
                    
                     <!-- //////////////////////////////////
						//////////////ITEM LIST///////////// 
						////////////////////////////////////-->
						
                    <div class="row row-wrap">
                    	<c:if test="${fn:length(requestScope.dealWantedListForSearch) ne null}">                    	                    	
                    		<c:forEach var="dealWantedSearch" items="${requestScope.dealWantedListForSearch}" varStatus="counter">
			                     <div class="col-md-4">
		                            <div class="product-thumb">
		                                <header class="product-header">
		                                    <img src="img/800x600.png" alt="Image Alternative text" title="Hot mixer" />
		                                </header>
		                                <div class="product-inner">
		                                    <ul class="icon-group icon-list-rating" title="4/5 rating">
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
		                                    </ul>
		                                    <h5 class="product-title">${dealWantedSearch.title}</h5>
		                                    <p class="product-desciption">${dealWantedSearch.description}</p>
		                                    <div class="product-meta">
		                                        <ul class="product-price-list">
		                                            <li><span class="product-price">${dealWantedSearch.maxPrice}</span>
		                                            </li>
		                                        </ul>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>                    			
                    		</c:forEach>
	                    </c:if>
                        
                        <c:if test="${fn:length(requestScope.dealPostListForSearch) ne null}">                    	                    	
                    		<c:forEach var="dealPostSearch" items="${requestScope.dealPostListForSearch}" varStatus="counter">
					             <div class="col-md-4">
		                            <div class="product-thumb">
		                                <header class="product-header">
		                                    <img src="img/800x600.png" alt="Image Alternative text" title="the best mode of transport here in maldives" />
		                                </header>
		                                <div class="product-inner">
		                                    <ul class="icon-group icon-list-rating" title="3.1/5 rating">
		                                        <li><i class="fa fa-star"></i>
		                                        </li>
		                                        <li><i class="fa fa-star"></i>
		                                        </li>
		                                        <li><i class="fa fa-star"></i>
		                                        </li>
		                                        <li><i class="fa fa-star-half-empty"></i>
		                                        </li>
		                                        <li><i class="fa fa-star-o"></i>
		                                        </li>
		                                    </ul>
		                                    <h5 class="product-title">${dealPostSearch.title}</h5>
		                                    <p class="product-desciption">${dealPostSearch.description}</p>
		                                    <div class="product-meta">
		                                        <ul class="product-price-list">
		                                            <li><span class="product-price">${dealPostSearch.salePrice}</span>
		                                            </li>
		                                            <li><span class="product-old-price">${dealPostSearch.retailPrice}</span>
		                                            </li>
		                                            <li><span class="product-save">${dealPostSearch.discountPercent}%</span>
		                                            </li>
		                                        </ul>		                                        
		                                    </div>
		                                </div>
		                            </div>
		                        </div>                    			
                    		</c:forEach>
	                    </c:if>                       
                        
                    </div>
                    <ul class="pagination">
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
                    </ul>
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
        	
        </script>
    </div>

</body>
</html>