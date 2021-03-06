<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

	<div class="top-main-area text-center">
		<div class="container">
			<a href="home.htm" class="logo mt5"> <img
				src="img/drop_blue.png" alt="Image Alternative text"
				title="Image Title" />
			</a>
			
		    <c:if test="${sessionScope.user ne null}">
			    <div style=" clear: both; text-align: right;">
					<span style="color: #2A8FBD; font-weight: bold;">Welcome:</span> 
					<span style="font-weight: bold; color: black;">${sessionScope.user.firstName}  ${sessionScope.user.lastName}</span> 
				</div>	
		    </c:if>				
		</div>
	</div>

	<header class="main">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <!-- MAIN NAVIGATION -->
                        <div class="flexnav-menu-button" id="flexnav-menu-button">Menu</div>
                        <nav>
                            <ul class="nav nav-pills flexnav" id="flexnav" data-breakpoint="800">
                                <li id="homeli">
                                    <a href="home.htm">Home</a>
                        		</li> 
                        		<li id="aboutusli">
                                	<a href="aboutus.htm">About Us</a>
                                </li>
                        		<c:if test="${sessionScope.user ne null}">                               
	                                <li id="myaccountli"><a href="showAccountSettings.htm">My Account</a>
	                                    <ul>
	                                               <li>
	                                                	<a href="showAccountSettings.htm">My Profile</a>
	                                                </li>
	                                                <li>
	                                                	<a href="showAddressBook.htm">Address Book</a>
	                                                <li>
	                                                	<a href="myStatistics.htm">My Statistics</a>
	                                                </li>
	                                                <li>
	                                                	<a href="showMyDropWanted.htm">My Drop Wanted</a>
	                                                </li>                                                
	                                                <li>
	                                                	<a href="showMyDropPost.htm">My Drops</a>
	                                                </li>                                        
	                                    </ul>
	                                </li>
                                </c:if>                                
                            </ul>
                        </nav>
                        <!-- END MAIN NAVIGATION -->
                    </div>
                    <div class="col-md-6">
                        <!-- LOGIN REGISTER LINKS -->
                        <ul class="login-register">    
                        	<c:if test="${sessionScope.user eq null}">                         	
                        		<li><a class="popup-text" href="#login-dialog" data-effect="mfp-move-from-top" id="anchorSignIn"><i class="fa fa-sign-in"></i>Sign in</a></li>                          
                            	<li><a class="popup-text" href="#register-dialog" data-effect="mfp-move-from-top" id="anchorSignUp"><i class="fa fa-edit"></i>Sign up</a></li>
                            	<li><a href="showDealWantedPage.htm" id="anchorWantDrop" style="display: none"><i class="fa fa-sign-in"></i>Want Drop</a></li>
                           		<li><a href="showDealPostPage.htm" id="anchorPostDrop" style="display: none"><i class="fa fa-sign-in"></i>Drop</a></li>
                            	<li><a href="signOut.htm"  id="anchorSignOut"  style="display: none"><i class="fa fa-sign-in"></i>Sign out</a></li>
                        	</c:if>    
                        	<c:if test="${sessionScope.user ne null}">
                        		<li><a class="popup-text" href="#login-dialog" data-effect="mfp-move-from-top" id="anchorSignIn" style="display: none"><i class="fa fa-sign-in"></i>Sign in</a></li>                          
                            	<li><a class="popup-text" href="#register-dialog" data-effect="mfp-move-from-top" id="anchorSignUp" style="display: none"><i class="fa fa-edit"></i>Sign up</a></li>
                            	<li><a href="showDealWantedPage.htm" id="anchorWantDrop" ><i class="fa fa-sign-in"></i>Want Drop</a></li>
                           		<li><a href="showDealPostPage.htm" id="anchorPostDrop"><i class="fa fa-sign-in"></i>Drop</a></li>
                            	<li><a href="signOut.htm"  id="anchorSignOut"><i class="fa fa-sign-in"></i>Sign out</a></li>
                        	</c:if>                                                
                        </ul>
                    </div>
                </div>
            </div>
        </header>
