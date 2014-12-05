<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

	<div class="top-main-area text-center">
		<div class="container">
			<a href="index.html" class="logo mt5"> <img
				src="img/logo-small-dark.png" alt="Image Alternative text"
				title="Image Title" />
			</a>
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
                                <li id="myaccountli"><a href="#">My Account</a>
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
                                                	<a href="#">Manage Posts</a>
                                                </li>                                        
                                    </ul>
                                </li>
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
                            	<li><a class="popup-text" href="#want-drop-dialog" data-effect="mfp-move-from-top" id="anchorWantDrop" style="display: none"><i class="fa fa-sign-in"></i>Want Drop</a></li>
                           		<li><a class="popup-text" href="#post-drop-dialog" data-effect="mfp-move-from-top" id="anchorPostDrop" style="display: none"><i class="fa fa-sign-in"></i>Post Drop</a></li>
                            	<li><a href="signOut.htm"  id="anchorSignOut"  style="display: none"><i class="fa fa-sign-in"></i>Sign out</a></li>
                        	</c:if>    
                        	<c:if test="${sessionScope.user ne null}">
                        		<li><a class="popup-text" href="#login-dialog" data-effect="mfp-move-from-top" id="anchorSignIn" style="display: none"><i class="fa fa-sign-in"></i>Sign in</a></li>                          
                            	<li><a class="popup-text" href="#register-dialog" data-effect="mfp-move-from-top" id="anchorSignUp" style="display: none"><i class="fa fa-edit"></i>Sign up</a></li>
                            	<li><a class="popup-text" href="#want-drop-dialog" data-effect="mfp-move-from-top" id="anchorWantDrop" ><i class="fa fa-sign-in"></i>Want Drop</a></li>
                           		<li><a class="popup-text" href="#post-drop-dialog" data-effect="mfp-move-from-top" id="anchorPostDrop"><i class="fa fa-sign-in"></i>Post Drop</a></li>
                            	<li><a href="signOut.htm"  id="anchorSignOut"><i class="fa fa-sign-in"></i>Sign out</a></li>
                        	</c:if>                                                
                        </ul>
                    </div>
                </div>
            </div>
        </header>
