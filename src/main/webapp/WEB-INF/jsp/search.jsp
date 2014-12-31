<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form class="search-area form-group" method="POST" commandName="searchDealForm" action="search.htm">
	<div class="container">
		<div class="row">
			<div class="col-md-8 clearfix">
				<label><i class="fa fa-search"></i><span>Enter your search</span> </label>
				<div class="search-area-division search-area-division-input">
					<form:input class="form-control" type="text" id="searchString" path="searchString" placeholder="Your Search String" />
				</div>
			</div>
			<div class="col-md-3 clearfix">
				<div class="search-area-division search-area-division-location">
					<form:select class="form-control dummy-form-control" path="searchType" id="searchType">
					   <form:option value="Drops" label="Drops"/>	
					   <form:option value="Drop Wanted" label="Drop Wanted"/>					   				  		
					</form:select>				
				</div>
			</div>
			<div class="col-md-1">
				<button class="btn btn-block btn-white search-btn" type="submit">Search</button>
			</div>
		</div>
	</div>
</form:form>
